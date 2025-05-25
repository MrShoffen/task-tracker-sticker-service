package org.mrshoffen.tasktracker.task.sticker.service;

import lombok.RequiredArgsConstructor;
import org.mrshoffen.tasktracker.commons.web.dto.StickerResponseDto;
import org.mrshoffen.tasktracker.commons.web.dto.TaskResponseDto;
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

    public Mono<StickerResponseDto> createSticker(StickerCreateDto dto, UUID userId, UUID workspaceId, UUID deskId, UUID taskId) {
        Sticker sticker = stickerMapper.toEntity(dto, userId, workspaceId, deskId, taskId);
        return stickerRepository
                .save(sticker)
                .map(stickerMapper::toDto);
    }

    public Flux<StickerResponseDto> getAllStickersInWorkspace(UUID workspaceId) {
        return stickerRepository
                .findAllByWorkspaceId(workspaceId)
                .map(stickerMapper::toDto);
    }
//
//    public Flux<TaskResponseDto> getAllTasksOnUsersDesk(UUID workspaceId, UUID deskId) {
//        return taskRepository
//                .findAllByWorkspaceIdAndDeskId(workspaceId, deskId)
//                .map(taskMapper::toTaskResponse);
//    }
//
//    public Mono<Void> deleteUserTaskById(UUID workspaceId, UUID taskId) {
//        return taskRepository
//                .findByWorkspaceIdAndId(workspaceId, taskId)
//                .doOnSuccess(task -> {
//                    if (task != null) {
//                        eventPublisher.publishTaskDeletedEvent(task);
//                    }
//                })
//                .switchIfEmpty(
//                        Mono.error(new EntityNotFoundException(
//                                "Задача с id %s не найдена в данном пространстве"
//                                        .formatted(taskId.toString())
//                        ))
//                )
//                .flatMap(taskRepository::delete);
//    }
//
//    public Mono<Void> deleteAllTasksInWorkspace(UUID workspaceId) {
//        return taskRepository
//                .deleteAllByWorkspaceId(workspaceId);
//    }
//
//    public Mono<Void> deleteAllTasksInDesk(UUID workspaceId, UUID deskId) {
//        return taskRepository
//                .deleteAllByWorkspaceIdAndDeskId(workspaceId, deskId);
//    }
//
//
//    public Flux<TaskResponseDto> getAllTasksInWorkspace(UUID workspaceId) {
//        return taskRepository
//                .findAllByWorkspaceId(workspaceId)
//                .map(taskMapper::toTaskResponse);
//    }
//
//    public Mono<TaskResponseDto> updateTaskOrder(UUID workspaceId,
//                                                 UUID taskId, OrderIndexUpdateDto updateDto) {
//        return findTaskOrThrow(workspaceId, taskId)
//                .flatMap(task -> {
//                    task.setOrderIndex(updateDto.updatedIndex());
//                    return taskRepository.save(task);
//                })
//                .map(taskMapper::toTaskResponse);
//    }
//
//    public Mono<TaskResponseDto> updateTaskCompletion(UUID workspaceId,
//                                                      UUID taskId, TaskCompletionDto updateDto) {
//        return findTaskOrThrow(workspaceId, taskId)
//                .flatMap(task -> {
//                    task.setCompleted(updateDto.completed());
//                    return taskRepository.save(task);
//                })
//                .map(taskMapper::toTaskResponse);
//    }
//
//    public Mono<TaskResponseDto> updateTaskName(UUID workspaceId, UUID taskId, TaskNameUpdateDto dto) {
//        return findTaskOrThrow(workspaceId, taskId)
//                .flatMap(task -> {
//                    task.setName(dto.newName());
//                    return taskRepository.save(task);
//                })
//                .onErrorMap(DuplicateKeyException.class, e ->
//                        new EntityAlreadyExistsException(
//                                "Задача с именем '%s' уже существует"
//                                        .formatted(dto.newName())
//                        )
//                )
//                .map(taskMapper::toTaskResponse);
//    }
//
//    public Mono<TaskResponseDto> updateTaskColor(UUID workspaceId, UUID taskId, TaskColorUpdateDto dto) {
//        return findTaskOrThrow(workspaceId, taskId)
//                .flatMap(task -> {
//                    task.setColor(dto.newColor());
//                    return taskRepository.save(task);
//                })
//                .map(taskMapper::toTaskResponse);
//    }
//
//    public Mono<TaskResponseDto> updateTaskCover(UUID workspaceId, UUID taskId, TaskCoverUpdateDto dto) {
//        return findTaskOrThrow(workspaceId, taskId)
//                .flatMap(task -> {
//                    task.setCoverUrl(dto.newCoverUrl());
//                    return taskRepository.save(task);
//                })
//                .map(taskMapper::toTaskResponse);
//    }
//
//
//    public Mono<TaskResponseDto> updateDesk(UUID workspaceId, UUID deskId, UUID taskId, DeskEditDto dto) {
//        return taskRepository
//                .findByWorkspaceIdAndDeskIdAndId(workspaceId, deskId, taskId)
//                .switchIfEmpty(
//                        Mono.error(new EntityNotFoundException(
//                                "Задача с id %s не найдена в данном пространстве и доске"
//                                        .formatted(taskId.toString())
//                        ))
//                )
//                .flatMap(task -> {
//                    task.setDeskId(dto.newDeskId());
//                    return taskRepository
//                            .findMaxOrderIndexInDesk(dto.newDeskId())
//                            .flatMap(newOrd -> {
//                                task.setOrderIndex(next(newOrd));
//                                return taskRepository.save(task);
//                            });
//                })
//                .onErrorMap(DuplicateKeyException.class, e ->
//                        new EntityAlreadyExistsException(
//                                "Задач уже существует в данной доске")
//                )
//                .map(taskMapper::toTaskResponse);
//    }
//
//    private Mono<Task> findTaskOrThrow(UUID workspaceId, UUID taskId) {
//        return taskRepository
//                .findByWorkspaceIdAndId(workspaceId, taskId)
//                .switchIfEmpty(
//                        Mono.error(new EntityNotFoundException(
//                                "Задача с id %s не найдена в данном пространстве"
//                                        .formatted(taskId.toString())
//                        ))
//                );
//    }
}
