package kz.bitlab.trello.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.bitlab.trello.dto.UserRegisterRequestDTO;
import kz.bitlab.trello.exception.PasswordNotMatchException;
import kz.bitlab.trello.exception.UsernameAlreadyExistException;
import kz.bitlab.trello.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/register")
@RequiredArgsConstructor
@Slf4j
public class RegisterController {
    private final RegisterService registerService;

    @Operation(summary = "Регистрация пользователя", description = "Регистрация нового пользователя в системе с ролью User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Успешно создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка пользователя"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка в системе")
    })
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid UserRegisterRequestDTO request) {
        try {
            return new ResponseEntity<>(registerService.registerUser(request), HttpStatus.CREATED);
        } catch (UsernameAlreadyExistException | PasswordNotMatchException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
