package kz.bitlab.trello.service.impl;

import kz.bitlab.trello.dto.UserRegisterRequestDTO;
import kz.bitlab.trello.exception.PasswordNotMatchException;
import kz.bitlab.trello.exception.UsernameAlreadyExistException;
import kz.bitlab.trello.mapper.UserMapper;
import kz.bitlab.trello.repository.RoleRepository;
import kz.bitlab.trello.repository.UserRepository;
import kz.bitlab.trello.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public Void registerUser(UserRegisterRequestDTO request) {
        validateUser(request);

        var user=userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var userRole=roleRepository.findRoleUser();
        user.setRoles(Collections.singletonList(userRole));

        userRepository.save(user);
        return null;
    }

    private void validateUser(UserRegisterRequestDTO request) {
        userRepository.findByUsername(request.getUsername()).ifPresent(user -> {
            throw new UsernameAlreadyExistException("Username already exsits: " + request.getUsername());
        });

        if(!request.getPassword().equals(request.getRePassword())){
            throw new PasswordNotMatchException("Passwords do not match");
        }
    }
}
