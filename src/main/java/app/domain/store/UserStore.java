package app.domain.store;

import app.domain.model.Email;
import app.domain.model.Password;
import app.domain.model.User;

import java.util.*;

public class UserStore {

    private Set<User> store = new HashSet();

    public UserStore() {
    }

    public User create(String name, String email, String password) {
        return new User(new Email(email), new Password(password), name);
    }

    public boolean add(User user) {
        return user != null && !this.exists(user) ? this.store.add(user) : false;
    }

    public boolean remove(User user) {
        return user != null ? this.store.remove(user) : false;
    }

    public Set<User> getAll() {
        return Collections.unmodifiableSet(this.store);
    }

    public Set<User> getAllWithRole(String roleId) {
        Set<User> filtered = new HashSet();
        Iterator var3 = this.store.iterator();

        while(var3.hasNext()) {
            User user = (User)var3.next();
            if (user.hasRole(roleId)) {
                filtered.add(user);
            }
        }

        return Collections.unmodifiableSet(filtered);
    }

    public Optional<User> getById(String email) {
        return this.getById(new Email(email));
    }

    public Optional<User> getById(Email email) {
        Iterator var2 = this.store.iterator();

        User user;
        do {
            if (!var2.hasNext()) {
                return Optional.empty();
            }

            user = (User)var2.next();
        } while(!user.hasId(email));

        return Optional.of(user);
    }

    public boolean exists(String email) {
        Optional<User> result = this.getById(email);
        return result.isPresent();
    }

    public boolean exists(Email email) {
        Optional<User> result = this.getById(email);
        return result.isPresent();
    }

    public boolean exists(User user) {
        return this.store.contains(user);
    }
}
