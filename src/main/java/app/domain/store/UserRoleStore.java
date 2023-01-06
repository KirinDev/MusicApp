package app.domain.store;

import app.domain.model.UserRole;

import java.util.*;

public class UserRoleStore {

    private Set<UserRole> store = new HashSet();

    public UserRoleStore() {}

    public UserRole create(String id, String description) {
        return new UserRole(id, description);
    }

    public boolean add(UserRole role) {
        return role != null && !this.exists(role) ? this.store.add(role) : false;
    }

    public boolean remove(UserRole role) {
        return role != null ? this.store.remove(role) : false;
    }

    public Set<UserRole> getAll() {
        return Collections.unmodifiableSet(this.store);
    }

    public Optional<UserRole> getById(String id) {
        Iterator var2 = this.store.iterator();

        UserRole role;
        do {
            if (!var2.hasNext()) {
                return Optional.empty();
            }

            role = (UserRole)var2.next();
        } while(!role.hasId(id));

        return Optional.of(role);
    }

    public boolean exists(String id) {
        Optional<UserRole> result = this.getById(id);
        return result.isPresent();
    }

    public boolean exists(UserRole role) {
        return this.store.contains(role);
    }
}
