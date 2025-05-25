--liquibase formatted sql


--changeset mrshoffen:2
CREATE TABLE IF NOT EXISTS stickers
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name         VARCHAR(64) NOT NULL,
    color        VARCHAR(32),
    icon         VARCHAR(32),
    created_at   TIMESTAMP   NOT NULL,
    user_id      UUID        NOT NULL,
    workspace_id UUID        NOT NULL,
    desk_id      UUID        NOT NULL,
    task_id      UUID        NOT NULL
);