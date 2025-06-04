package kz.bitlab.trello.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record TaskCreateRecord(
        @NotNull
        @Schema(description = "Имя задачи", example = "Сделать домашку", required = true)
        String title,

        @NotNull
        @Schema(description = "Описание задачи", example = "Сделать домашнее задание по Java", required = false)
        String description,

        @Schema(description = "Испольнитель задачи Id", example = "1", required = false)
        Long userId
) {
}
