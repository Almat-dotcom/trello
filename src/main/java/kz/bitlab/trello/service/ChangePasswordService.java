package kz.bitlab.trello.service;

import kz.bitlab.trello.dto.ForgetPasswordRecord;

public interface ChangePasswordService {
    Void changePassword(ForgetPasswordRecord dto);
}
