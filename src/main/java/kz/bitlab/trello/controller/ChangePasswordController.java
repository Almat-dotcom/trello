package kz.bitlab.trello.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.bitlab.trello.dto.ForgetPasswordRecord;
import kz.bitlab.trello.exception.PasswordNotMatchException;
import kz.bitlab.trello.exception.UserNotExistException;
import kz.bitlab.trello.service.ChangePasswordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/change-password")
@RequiredArgsConstructor
@Slf4j
public class ChangePasswordController {
    private final ChangePasswordService changePasswordService;

    @Operation(summary = "Смена пароля пользователя", description = "Смена пароля для существующего пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Успешно обновлен"),
            @ApiResponse(responseCode = "400", description = "Ошибка пользователя"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка в системе")
    })
    @PostMapping
    public ResponseEntity<Void> changePassword(@RequestBody @Valid ForgetPasswordRecord record) {
        try {
            return new ResponseEntity<>(changePasswordService.changePassword(record), HttpStatus.ACCEPTED);
        }catch (UserNotExistException | PasswordNotMatchException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
