package org.mrshoffen.tasktracker.task.sticker.service;

import lombok.RequiredArgsConstructor;
import org.mrshoffen.tasktracker.commons.web.dto.StickerResponseDto;
import org.mrshoffen.tasktracker.commons.web.exception.EntityNotFoundException;
import org.mrshoffen.tasktracker.task.sticker.event.StickerEventPublisher;
import org.mrshoffen.tasktracker.task.sticker.mapper.StickerMapper;
import org.mrshoffen.tasktracker.task.sticker.model.dto.StickerCreateDto;
import org.mrshoffen.tasktracker.task.sticker.model.entity.Sticker;
import org.mrshoffen.tasktracker.task.sticker.repository.StickerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StickerService {

    private final StickerMapper stickerMapper;

    private final StickerRepository stickerRepository;

    private final StickerEventPublisher eventPublisher;

    public Mono<StickerResponseDto> createSticker(StickerCreateDto dto, UUID userId, UUID workspaceId, UUID taskId) {
        Sticker sticker = stickerMapper.toEntity(dto, userId, workspaceId, taskId);
        return stickerRepository
                .save(sticker)
                .map(stickerMapper::toDto)
                .doOnSuccess(eventPublisher::publishStickerCreatedEvent);
    }

    public Flux<StickerResponseDto> getAllStickersInWorkspace(UUID workspaceId) {
        return stickerRepository
                .findAllByWorkspaceId(workspaceId)
                .map(stickerMapper::toDto);
    }

    public Mono<Void> deleteSticker(UUID workspaceId,  UUID stickerId) {
        return stickerRepository
                .findByWorkspaceIdAndId(workspaceId, stickerId)
                .doOnSuccess(sticker -> {
                    if (sticker != null) {
                        eventPublisher.publishStickerDeletedEvent(sticker);
                    }
                })
                .switchIfEmpty(
                        Mono.error(new EntityNotFoundException(
                                "Стикер с id %s не найдена в данном пространстве"
                                        .formatted(stickerId.toString())
                        ))
                )
                .flatMap(stickerRepository::delete);
    }
}
