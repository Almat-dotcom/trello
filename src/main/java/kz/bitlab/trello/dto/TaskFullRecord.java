package kz.bitlab.trello.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record TaskFullRecord(
        @NotNull
        @Schema(description = "Идентификатор задачи", example = "1", required = true)
        Long id,

        @NotNull
        @Schema(description = "Имя задачи", example = "Сделать домашку", required = true)
        String title,

        @Schema(description = "Описание задачи", example = "Сделать домашнее задание по Java", required = false)
        String description,

        @NotNull
        @Schema(description = "Статус задачи", example = "IN_PROGRESS", required = true)
        String status,

        @NotNull
        @Schema(description = "Испольнитель задачи Id", example = "1", required = true)
        Long userId
) {
}
