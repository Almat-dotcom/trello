package kz.bitlab.trello.mapper;

import kz.bitlab.trello.dto.UserRegisterRequestDTO;
import kz.bitlab.trello.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRegisterRequestDTO registerRequest);
}
