package kz.bitlab.trello.service.impl;

import kz.bitlab.trello.dto.TaskFullRecord;
import kz.bitlab.trello.entity.Task;
import kz.bitlab.trello.entity.User;
import kz.bitlab.trello.mapper.TaskMapper;
import kz.bitlab.trello.repository.TaskRepository;
import kz.bitlab.trello.service.AuthenticationService;
import kz.bitlab.trello.service.UserTaskService;
import kz.bitlab.trello.utility.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTaskServiceImpl implements UserTaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final AuthenticationService authenticationService;

    @Override
    public Page<TaskFullRecord> getTasks(Pageable pageable) {
        User user = authenticationService.currentUser();
        Page<Task> tasks = taskRepository.findAll(TaskSpecification.getTaskSpecification(null, null, user.getId()), pageable);
        return taskMapper.map(tasks);
    }
}
