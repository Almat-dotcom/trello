package kz.bitlab.trello.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.bitlab.trello.dto.TaskAssignRecord;
import kz.bitlab.trello.dto.TaskCreateRecord;
import kz.bitlab.trello.dto.TaskFullRecord;
import kz.bitlab.trello.exception.TaskNotExistException;
import kz.bitlab.trello.exception.UserNotExistException;
import kz.bitlab.trello.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/task")
@RequiredArgsConstructor
@Slf4j
public class TaskController {
    private final TaskService taskService;

    @Operation(summary = "Создание задачи", description = "Создает новую задачу с заданными параметрами")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно создана задача"),
            @ApiResponse(responseCode = "400", description = "Ошибка пользователя"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка в системе")
    })
    @PostMapping
    public ResponseEntity<TaskFullRecord> createTask(@RequestBody @Valid TaskCreateRecord request) {
        try {
            return new ResponseEntity<>(taskService.createTask(request), HttpStatus.OK);
        } catch (UserNotExistException | TaskNotExistException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Берете Task по Id", description = "Получает задачу по заданному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно задача получена"),
            @ApiResponse(responseCode = "400", description = "Ошибка пользователя"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка в системе")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskFullRecord> getTask(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(taskService.getTask(id), HttpStatus.OK);
        } catch (TaskNotExistException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Обновление задачи", description = "Обновляет существующую задачу с заданными параметрами")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно обновлена задача"),
            @ApiResponse(responseCode = "400", description = "Ошибка пользователя"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка в системе")
    })
    @PutMapping
    public ResponseEntity<TaskFullRecord> updateTask(@RequestBody @Valid TaskFullRecord request) {
        try {
            return new ResponseEntity<>(taskService.updateTask(request), HttpStatus.OK);
        } catch (UserNotExistException | TaskNotExistException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Удаление задачи", description = "Удаляет задачу по заданному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно удалена задача"),
            @ApiResponse(responseCode = "400", description = "Ошибка пользователя"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка в системе")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(taskService.removeTask(id), HttpStatus.OK);
        } catch (UserNotExistException | TaskNotExistException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Распределение задачи", description = "Назначает задачу пользователю по заданному идентификатору задачи и пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно распределена задача"),
            @ApiResponse(responseCode = "400", description = "Ошибка пользователя"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка в системе")
    })
    @PostMapping("/assign-user")
    public ResponseEntity<Void> assignTaskToUser(@RequestBody @Valid TaskAssignRecord request) {
        try {
            return new ResponseEntity<>(taskService.assignTaskToUser(request), HttpStatus.OK);
        } catch (UserNotExistException | TaskNotExistException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
