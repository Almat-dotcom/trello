package kz.bitlab.trello.dto;

import jakarta.validation.constraints.NotNull;

public record TaskAssignRecord(
        @NotNull Long taskId,
        @NotNull Long userId
) {
}
