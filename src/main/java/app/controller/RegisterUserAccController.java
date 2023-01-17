package app.controller;

import app.auth.AuthFacade;
import app.domain.model.User;
import app.domain.store.UserStore;

public class RegisterUserAccController {

    private App app;

    public RegisterUserAccController() {
        this.app = App.getInstance();
    }

    public boolean registerUser(String name, String email, String pwd, String roleId) {
        AuthFacade auth = this.app.getKirinDev().getAuthFacade();
        return auth.addUserWithRole(name, email, pwd, roleId);
    }
}
