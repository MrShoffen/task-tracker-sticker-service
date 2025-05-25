package org.mrshoffen.tasktracker.task.sticker.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table("stickers")
public class Sticker {
    @Id
    @Column("id")
    private UUID id;

    @Column("name")
    private String name;

    @Column("created_at")
    private Instant createdAt = Instant.now();

    @Column("color")
    private String color;

    @Column("icon")
    private String icon;

    @Column("user_id")
    private UUID userId;

    @Column("workspace_id")
    private UUID workspaceId;

    @Column("desk_id")
    private UUID deskId;


    @Column("task_id")
    private UUID taskId;
}
