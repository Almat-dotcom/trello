package kz.bitlab.trello.service.impl;

import kz.bitlab.trello.exception.RoleNotExistException;
import kz.bitlab.trello.exception.UserNotExistException;
import kz.bitlab.trello.repository.RoleRepository;
import kz.bitlab.trello.repository.UserRepository;
import kz.bitlab.trello.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public Void addRole(String username, String roleName) {
        validate(username, roleName);

        var user = userRepository.findByUsername(username).get();
        var role = roleRepository.findRoleByName(roleName).get();
        user.getRoles().add(role);
        userRepository.save(user);
        return null;
    }

    @Override
    public Void deleteRole(String username, String roleName) {
        validate(username, roleName);

        var user = userRepository.findByUsername(username).get();
        var role = roleRepository.findRoleByName(roleName).get();
        user.getRoles().remove(role);
        userRepository.save(user);
        return null;
    }

    private void validate(String username, String roleName) {
        if (userRepository.findByUsername(username).isEmpty()) {
            throw new UserNotExistException("User " + username + " does not exist");
        }

        if (roleRepository.findRoleByName(roleName).isEmpty()) {
            throw new RoleNotExistException("Role " + roleName + " does not exist");
        }
    }
}
