package kz.bitlab.trello.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDTO {
    @NotNull
    @Schema(name = "username", example = "simple", required = true)
    private String username;

    @NotNull
    @Schema(name = "fullName", example = "Александр Костылев", required = true)
    private String fullName;

    @Schema(name = "age", example = "25", required = false)
    private Integer age;

    @NotNull
    @Schema(name = "password", example = "qwerty", required = true)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
            message = "password must be min 4 and max 12 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private String password;

    @NotNull
    @Schema(name = "rePassword", example = "qwerty", required = true)
    private String rePassword;
}
