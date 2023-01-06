package app.auth;

import app.domain.model.Email;
import app.domain.model.User;
import app.mappers.UserRoleMapper;
import app.mappers.dto.UserRoleDTO;

import java.util.Collections;
import java.util.List;

public class UserSession {

    private User user = null;

    public UserSession() {
        this.user = null;
    }

    public UserSession(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        } else {
            this.user = user;
        }
    }

    public void doLogout() {
        this.user = null;
    }

    public boolean isLoggedIn() {
        return this.user != null;
    }

    public boolean isLoggedInWithRole(String roleId) {
        return this.isLoggedIn() ? this.user.hasRole(roleId) : false;
    }

    public String getUserName() {
        if (this.isLoggedIn()) {
            this.user.getName();
        }

        return null;
    }

    public Email getUserId() {
        return this.isLoggedIn() ? this.user.getId() : null;
    }

    public List<UserRoleDTO> getUserRoles() {
        if (this.isLoggedIn()) {
            UserRoleMapper mapper = new UserRoleMapper();
            return mapper.toDTO(this.user.getRoles());
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}
