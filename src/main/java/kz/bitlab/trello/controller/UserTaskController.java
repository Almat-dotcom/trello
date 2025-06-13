package kz.bitlab.trello.controller;

import kz.bitlab.trello.dto.TaskFullRecord;
import kz.bitlab.trello.service.UserTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user/task")
@RequiredArgsConstructor
@Slf4j
public class UserTaskController {
    private final UserTaskService userTaskService;

    @GetMapping
    public ResponseEntity<Page<TaskFullRecord>> getTasks(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return new ResponseEntity<>(userTaskService.getTasks(pageable), HttpStatus.OK);
    }
}
