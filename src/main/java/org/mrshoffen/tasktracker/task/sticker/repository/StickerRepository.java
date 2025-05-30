package org.mrshoffen.tasktracker.task.sticker.repository;

import org.mrshoffen.tasktracker.task.sticker.model.entity.Sticker;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface StickerRepository extends ReactiveCrudRepository<Sticker, UUID> {

    Flux<Sticker> findAllByWorkspaceId(UUID workspaceId);

    Mono<Sticker> findByWorkspaceIdAndId(UUID workspaceId, UUID id);
}
