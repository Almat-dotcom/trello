package kz.bitlab.trello.service;

import kz.bitlab.trello.entity.User;

public interface AuthenticationService {
    User currentUser();
}
