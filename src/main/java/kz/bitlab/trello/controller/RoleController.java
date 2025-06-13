package kz.bitlab.trello.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.bitlab.trello.exception.RoleNotExistException;
import kz.bitlab.trello.exception.UserNotExistException;
import kz.bitlab.trello.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/role")
@RequiredArgsConstructor
@Slf4j
public class RoleController {
    private final RoleService roleService;

    @Operation(summary = "Добавление роля", description = "Добавляет роль пользователю по имени пользователя и имени роли")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно добавлена роль"),
            @ApiResponse(responseCode = "400", description = "Ошибка пользователя"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка в системе")
    })
    @PostMapping("/assign-role")
    public ResponseEntity<Void> addRole(@RequestParam(name = "username") String username,
                                        @RequestParam(name = "roleName") String role) {
        return new ResponseEntity<>(roleService.addRole(username, role), HttpStatus.OK);
    }

    @Operation(summary = "Удаление роля", description = "Удаляет роль у пользователя по имени пользователя и имени роли")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно удалена роль"),
            @ApiResponse(responseCode = "400", description = "Ошибка пользователя"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка в системе")
    })
    @PostMapping("/remove-role")
    public ResponseEntity<Void> deleteRole(@RequestParam(name = "username") String username,
                                           @RequestParam(name = "roleName") String role) {
        try {
            return new ResponseEntity<>(roleService.deleteRole(username, role), HttpStatus.OK);
        } catch (UserNotExistException | RoleNotExistException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
