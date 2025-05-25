package org.mrshoffen.tasktracker.task.sticker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mrshoffen.tasktracker.commons.web.dto.StickerResponseDto;
import org.mrshoffen.tasktracker.task.sticker.model.dto.StickerCreateDto;
import org.mrshoffen.tasktracker.task.sticker.model.entity.Sticker;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StickerMapper {

    Sticker toEntity(StickerCreateDto stickerCreateDto, UUID userId, UUID workspaceId, UUID deskId, UUID taskId);

    StickerResponseDto toDto(Sticker sticker);
}
