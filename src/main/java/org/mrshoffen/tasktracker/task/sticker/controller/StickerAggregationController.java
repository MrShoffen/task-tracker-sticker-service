package org.mrshoffen.tasktracker.task.sticker.controller;


import lombok.RequiredArgsConstructor;
import org.mrshoffen.tasktracker.commons.web.dto.StickerResponseDto;
import org.mrshoffen.tasktracker.task.sticker.service.StickerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * Эндпоинты для агрегирующих сервисов.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/aggregate-api/workspaces")
public class StickerAggregationController {

    private final StickerService stickerService;

    @GetMapping("/{workspaceId}/stickers")
    Flux<StickerResponseDto> getAllStickersInWs(@PathVariable("workspaceId") UUID workspaceId) {
        return stickerService
                .getAllStickersInWorkspace(workspaceId);
    }

}
