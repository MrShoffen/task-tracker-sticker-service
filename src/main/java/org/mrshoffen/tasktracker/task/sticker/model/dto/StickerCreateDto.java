package org.mrshoffen.tasktracker.task.sticker.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StickerCreateDto(
        @Size(max = 256, min = 1, message = "Имя стикера должно быть между 1 и 64 символами")
        @NotBlank(message = "Имя стикера не может быть пустым")
        String name,
        String color,
        String icon
) {
}
