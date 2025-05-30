package org.mrshoffen.tasktracker.task.sticker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mrshoffen.tasktracker.commons.web.dto.StickerResponseDto;
import org.mrshoffen.tasktracker.commons.web.permissions.Permission;
import org.mrshoffen.tasktracker.task.sticker.model.dto.StickerCreateDto;
import org.mrshoffen.tasktracker.task.sticker.service.PermissionsService;
import org.mrshoffen.tasktracker.task.sticker.service.StickerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.mrshoffen.tasktracker.commons.web.authentication.AuthenticationAttributes.AUTHORIZED_USER_HEADER_NAME;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workspaces/{workspaceId}/desks/{deskId}/tasks/{taskId}/stickers")
public class ExternalStickerController {

    private final PermissionsService permissionsService;

    private final StickerService stickerService;

    @PostMapping
    Mono<ResponseEntity<StickerResponseDto>> createSticker(@RequestHeader(AUTHORIZED_USER_HEADER_NAME) UUID userId,
                                                           @PathVariable("workspaceId") UUID workspaceId,
                                                           @PathVariable("deskId") UUID deskId,
                                                           @PathVariable("taskId") UUID taskId,
                                                           @Valid @RequestBody Mono<StickerCreateDto> createDto) {
        return permissionsService
                .verifyUserPermission(userId, workspaceId, Permission.CREATE_STICKERS)
                .then(createDto.flatMap(dto ->
                        stickerService.createSticker(
                                dto, userId, workspaceId, taskId
                        ))
                )
                .map(createdSticker ->
                        ResponseEntity.status(HttpStatus.CREATED).body(createdSticker));
    }

    @DeleteMapping("/{stickerId}")
    Mono<ResponseEntity<StickerResponseDto>> deleteSticker(@RequestHeader(AUTHORIZED_USER_HEADER_NAME) UUID userId,
                                                           @PathVariable("workspaceId") UUID workspaceId,
                                                           @PathVariable("deskId") UUID deskId,
                                                           @PathVariable("taskId") UUID taskId,
                                                           @PathVariable("stickerId") UUID stickerId) {
        return permissionsService
                .verifyUserPermission(userId, workspaceId, Permission.DELETE_STICKERS)
                .then(stickerService.deleteSticker(
                        workspaceId, stickerId)
                )
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
