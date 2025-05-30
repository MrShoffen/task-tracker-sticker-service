package org.mrshoffen.tasktracker.task.sticker.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.commons.kafka.event.sticker.StickerCreatedEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.sticker.StickerDeletedEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.task.TaskCreatedEvent;
import org.mrshoffen.tasktracker.commons.web.dto.StickerResponseDto;
import org.mrshoffen.tasktracker.task.sticker.model.entity.Sticker;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class StickerEventPublisher {

    private final KafkaTemplate<UUID, Object> kafkaTemplate;

    public void publishStickerCreatedEvent(StickerResponseDto sticker) {
        StickerCreatedEvent event = new StickerCreatedEvent(sticker);
        log.info("Event published to kafka topic '{}' - {}", StickerCreatedEvent.TOPIC, event);
        kafkaTemplate.send(StickerCreatedEvent.TOPIC, sticker.getId(), event);
    }

    public void publishStickerDeletedEvent(Sticker sticker) {
        StickerDeletedEvent event = StickerDeletedEvent.builder()
                .workspaceId(sticker.getWorkspaceId())
                .taskId(sticker.getTaskId())
                .stickerId(sticker.getId())
                .deletedAt(Instant.now())
                .userId(sticker.getUserId())
                .build();
        log.info("Event published to kafka topic '{}' - {}", StickerDeletedEvent.TOPIC, event);
        kafkaTemplate.send(StickerDeletedEvent.TOPIC, sticker.getId(), event);
    }
}
