package kz.bitlab.trello.mapper;

import kz.bitlab.trello.entity.User;
import kz.bitlab.trello.exception.UserNotExistException;
import kz.bitlab.trello.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperHelper {
    private final UserRepository userRepository;

    @Named("mapToUser")
    public User findByIdUser(Long id) {
        if( id == null) {
            return null;
        }
        return userRepository.findById(id).orElse(null);
    }
}
