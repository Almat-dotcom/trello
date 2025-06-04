package kz.bitlab.trello.service;

import kz.bitlab.trello.dto.TaskAssignRecord;
import kz.bitlab.trello.dto.TaskCreateRecord;
import kz.bitlab.trello.dto.TaskFullRecord;

public interface TaskService {
    TaskFullRecord getTask(Long id);

    TaskFullRecord createTask(TaskCreateRecord request);

    TaskFullRecord updateTask(TaskFullRecord request);

    Void removeTask(Long id);

    Void assignTaskToUser(TaskAssignRecord request);
}
