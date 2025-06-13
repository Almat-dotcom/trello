package kz.bitlab.trello.mapper;

import kz.bitlab.trello.dto.TaskCreateRecord;
import kz.bitlab.trello.dto.TaskFullRecord;
import kz.bitlab.trello.dto.UserRegisterRequestDTO;
import kz.bitlab.trello.entity.Task;
import kz.bitlab.trello.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        uses = {MapperHelper.class})
public interface TaskMapper {
    @Mapping(target = "userId", source = "user.id")
    TaskFullRecord toFullRecord(Task task);

    @Mapping(target = "user", source = "userId", qualifiedByName = "mapToUser")
    @Mapping(target = "status", constant = "NEW")
    Task toEntity(TaskCreateRecord request);

    default Page<TaskFullRecord> map(Page<Task> data) {
        return data.map(this::toFullRecord);
    }

    void updateMap(TaskFullRecord taskFullRecord, @MappingTarget Task task);
}
