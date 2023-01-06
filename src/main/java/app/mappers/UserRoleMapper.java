package app.mappers;

import app.domain.model.UserRole;
import app.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class UserRoleMapper {

    public UserRoleMapper() {}

    public UserRoleDTO toDTO(UserRole role) {
        return new UserRoleDTO(role.getId(), role.getDescription());
    }

    public List<UserRoleDTO> toDTO(List<UserRole> roles) {
        List<UserRoleDTO> rolesDTO = new ArrayList();
        Iterator var3 = roles.iterator();

        while(var3.hasNext()) {
            UserRole role = (UserRole)var3.next();
            rolesDTO.add(this.toDTO(role));
        }

        return rolesDTO;
    }

    public List<UserRoleDTO> toDTO(Set<UserRole> roles) {
        List<UserRoleDTO> rolesDTO = new ArrayList();
        Iterator var3 = roles.iterator();

        while(var3.hasNext()) {
            UserRole role = (UserRole)var3.next();
            rolesDTO.add(this.toDTO(role));
        }

        return rolesDTO;
    }
}
