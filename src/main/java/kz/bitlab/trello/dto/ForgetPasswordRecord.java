package kz.bitlab.trello.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record ForgetPasswordRecord(
        @NotNull
        @Schema(name = "username", example = "simple", required = true)
        String username,

        @NotNull
        @Schema(name = "newPassword", example = "Qwerty1!", required = true)
        String newPassword,

        @NotNull
        @Schema(name = "reNewPassword", example = "Qwerty1!", required = true)
        String reNewPassword
) {
}
