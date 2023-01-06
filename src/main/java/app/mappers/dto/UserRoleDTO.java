package app.mappers.dto;

public class UserRoleDTO {

    private String id;
    private String description;

    public UserRoleDTO(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }
}
