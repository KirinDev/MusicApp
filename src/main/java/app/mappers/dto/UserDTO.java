package app.mappers.dto;

import java.util.List;

public class UserDTO {

    private String id;
    private String name;
    private List<UserRoleDTO> roles;

    public UserDTO(String id, String name, List<UserRoleDTO> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<UserRoleDTO> getRoles() {
        return this.roles;
    }
}
