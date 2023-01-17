package app.domain.store;

import app.domain.model.Email;
import app.domain.model.Music;
import app.domain.model.Password;
import app.domain.model.User;
import app.domain.shared.Constants;

import java.io.*;
import java.util.*;

public class UserStore {

    private FileOutputStream outFile;
    private ObjectOutputStream output;
    private FileInputStream inFile;
    private ObjectInputStream input;

    private Set<User> store = new HashSet<>();

    public UserStore() {
    }

    public User create(String name, String email, String password) {
        return new User(new Email(email), new Password(password), name);
    }

    public boolean add(User user) {
        if(user != null && !this.exists(user.getId()) && this.store.add(user)) {
            saveList();
            return true;
        }else{
            return false;
        }
    }

    public boolean remove(User user) {
        return user != null && this.store.remove(user);
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
        Iterator<User> var2 = this.store.iterator();

        User user;
        do {
            if (!var2.hasNext()) {
                return Optional.empty();
            }

            user = var2.next();
        } while(!user.hasId(email));

        return Optional.of(user);
    }

    public User getUserByID(Email email) {
        Iterator<User> var2 = this.store.iterator();
        User user;
        do {
            user = var2.next();
            if(user.getId().getEmail().equals(email.getEmail())) {
                return user;
            }
        } while(var2.hasNext());
        return user;
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

    public void saveList() {
        try {
            this.outFile = new FileOutputStream(Constants.USER_FILE);
            this.output = new ObjectOutputStream(outFile);
            this.output.writeObject(this.store);
            this.output.close();
            this.outFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadToLocalList() {
        try {
            this.inFile = new FileInputStream(Constants.USER_FILE);
            this.input = new ObjectInputStream(inFile);
            this.store = (Set<User>) input.readObject();
            this.input.close();
            this.inFile.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
