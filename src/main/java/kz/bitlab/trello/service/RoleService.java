package kz.bitlab.trello.service;

public interface RoleService {
    Void addRole(String username, String roleName);

    Void deleteRole(String username, String roleName);
}
