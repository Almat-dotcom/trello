package kz.bitlab.trello.service.impl;

import kz.bitlab.trello.dto.ForgetPasswordRecord;
import kz.bitlab.trello.exception.PasswordNotMatchException;
import kz.bitlab.trello.exception.UserNotExistException;
import kz.bitlab.trello.repository.UserRepository;
import kz.bitlab.trello.service.ChangePasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordServiceImpl implements ChangePasswordService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Void changePassword(ForgetPasswordRecord record) {
        if (userRepository.findByUsername(record.username()).isEmpty()) {
            throw new UserNotExistException("User with username " + record.username() + " does not exist.");
        }

        if (!record.newPassword().equals(record.reNewPassword())) {
            throw new PasswordNotMatchException("Passwords do not match");
        }

        userRepository.findByUsername(record.username()).ifPresent(user -> {
            user.setPassword(passwordEncoder.encode(record.newPassword()));
            userRepository.save(user);
        });
        return null;
    }
}
