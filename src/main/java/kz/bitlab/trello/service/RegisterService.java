package kz.bitlab.trello.service;

import kz.bitlab.trello.dto.UserRegisterRequestDTO;

public interface RegisterService {
    Void registerUser(UserRegisterRequestDTO request);
}
