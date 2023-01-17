package app.auth;

import app.domain.model.User;
import app.domain.model.UserRole;
import app.domain.store.UserRoleStore;
import app.domain.store.UserStore;
import app.mappers.UserMapper;
import app.mappers.UserRoleMapper;
import app.mappers.dto.UserDTO;
import app.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class AuthFacade {

    private UserSession userSession = new UserSession();
    private UserRoleStore roles = new UserRoleStore();
    private UserStore users;

    public AuthFacade() {
        this.users = new UserStore();
        this.users.loadToLocalList();
    }

    public boolean addUserRole(String id, String description) {
        UserRole role = this.roles.create(id, description);
        return this.roles.add(role);
    }

    public boolean existsRole(String id) {
        return this.roles.exists(id);
    }

    public boolean removeRole(String id) {
        Optional<UserRole> found = this.roles.getById(id);
        return found.isPresent() ? this.roles.remove((UserRole)found.get()) : false;
    }

    public List<UserRoleDTO> getUserRoles() {
        UserRoleMapper mapper = new UserRoleMapper();
        return mapper.toDTO(this.roles.getAll());
    }

    public UserStore getUserStore() {
        return this.users;
    }

    public Optional<UserRoleDTO> getRole(String id) {
        Optional<UserRole> found = this.roles.getById(id);
        if (found.isPresent()) {
            UserRoleMapper mapper = new UserRoleMapper();
            return Optional.of(mapper.toDTO((UserRole)found.get()));
        } else {
            return Optional.empty();
        }
    }

    public boolean changeUserRoleDescription(String id, String description) {
        Optional<UserRole> found = this.roles.getById(id);
        if (found.isPresent()) {
            UserRole role = (UserRole)found.get();
            return role.changeDescription(description);
        } else {
            return false;
        }
    }

    public boolean addUser(String name, String email, String pwd) {
        User user = this.users.create(name, email, pwd);
        return this.users.add(user);
    }

    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        Optional<UserRole> roleResult = this.roles.getById(roleId);
        if (!roleResult.isPresent()) {
            return false;
        } else {
            User user = this.users.create(name, email, pwd);
            user.addRole((UserRole)roleResult.get());
            return this.users.add(user);
        }
    }

    public boolean existsUser(String email) {
        return this.users.exists(email);
    }

    public boolean removeUser(String email) {
        Optional<User> found = this.users.getById(email);
        return found.isPresent() ? this.users.remove((User)found.get()) : false;
    }

    public List<UserDTO> getUsers() {
        UserMapper mapper = new UserMapper();
        return mapper.toDTO(this.users.getAll());
    }

    public List<UserDTO> getUsersWithRole(String roleId) {
        UserMapper mapper = new UserMapper();
        return mapper.toDTO(this.users.getAllWithRole(roleId));
    }

    public Optional<UserDTO> getUser(String email) {
        Optional<User> found = this.users.getById(email);
        if (found.isPresent()) {
            UserMapper mapper = new UserMapper();
            return Optional.of(mapper.toDTO((User)found.get()));
        } else {
            return Optional.empty();
        }
    }

    public boolean updateUser(UserDTO dto) {
        Optional<User> found = this.users.getById(dto.getId());
        if (found.isPresent()) {
            User user = (User)found.get();
            String oldName = user.getName();
            if (user.changeName(dto.getName())) {
                List<UserRole> roles = new ArrayList();
                Iterator var6 = dto.getRoles().iterator();

                while(var6.hasNext()) {
                    UserRoleDTO roleDto = (UserRoleDTO)var6.next();
                    Optional<UserRole> roleResult = this.roles.getById(roleDto.getId());
                    if (roleResult.isPresent()) {
                        roles.add((UserRole)roleResult.get());
                    }
                }

                if (user.changeRoles(roles)) {
                    return true;
                }

                user.changeName(oldName);
            }
        }

        return false;
    }

    public UserSession doLogin(String email, String password) {
        Optional<User> result = this.users.getById(email);
        if (result.isPresent()) {
            User user = (User)result.get();
            if (user.hasPassword(password)) {
                this.userSession = new UserSession(user);
            }
        }

        return this.userSession;
    }

    public void doLogout() {
        this.userSession.doLogout();
    }

    public UserSession getCurrentUserSession() {
        return this.userSession;
    }
}
