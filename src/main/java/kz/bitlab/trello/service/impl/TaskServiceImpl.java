package kz.bitlab.trello.service.impl;

import kz.bitlab.trello.dto.TaskAssignRecord;
import kz.bitlab.trello.dto.TaskCreateRecord;
import kz.bitlab.trello.dto.TaskFullRecord;
import kz.bitlab.trello.entity.Task;
import kz.bitlab.trello.entity.User;
import kz.bitlab.trello.enums.TaskStatus;
import kz.bitlab.trello.exception.TaskNotExistException;
import kz.bitlab.trello.exception.UserNotExistException;
import kz.bitlab.trello.mapper.TaskMapper;
import kz.bitlab.trello.repository.TaskRepository;
import kz.bitlab.trello.repository.UserRepository;
import kz.bitlab.trello.service.TaskService;
import kz.bitlab.trello.utility.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public Page<TaskFullRecord> getTasks(String title, TaskStatus status, Pageable pageable) {
        Page<Task> tasks = taskRepository.findAll(TaskSpecification.getTaskSpecification(title, status, null), pageable);
        return taskMapper.map(tasks);
    }

    private final TaskMapper taskMapper;

    @Override
    public TaskFullRecord getTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotExistException(String.format("Task with id %s does not exist", id)));
        return taskMapper.toFullRecord(task);
    }

    @Override
    public TaskFullRecord createTask(TaskCreateRecord request) {
        Task task = taskMapper.toEntity(request);
        task = taskRepository.save(task);
        return taskMapper.toFullRecord(task);
    }

    @Override
    public TaskFullRecord updateTask(TaskFullRecord request) {
        Task task = taskRepository.findById(request.id()).orElseThrow(() -> new TaskNotExistException("Task with id " + request.id() + " does not exist"));
        taskMapper.updateMap(request, task);
        task = taskRepository.save(task);
        return taskMapper.toFullRecord(task);
    }

    @Override
    public Void removeTask(Long id) {
        taskRepository.deleteById(id);
        return null;
    }

    @Override
    public Void assignTaskToUser(TaskAssignRecord request) {
        Task task = taskRepository.findById(request.taskId()).orElseThrow(() -> new TaskNotExistException("Task with id " + request.taskId() + " does not exist"));
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new UserNotExistException("User with id " + request.userId() + " does not exist"));
        task.setUser(user);
        taskRepository.save(task);
        return null;
    }
}
