package kz.bitlab.trello.service;

import kz.bitlab.trello.dto.TaskFullRecord;
import kz.bitlab.trello.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserTaskService {
    Page<TaskFullRecord> getTasks(Pageable pageable);
}
