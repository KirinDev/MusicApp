package app.domain.model;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

public class Password implements Serializable {

    private String password;

    public Password(String password) {
        if (!this.validate(password)) {
            throw new IllegalArgumentException("Invalid Email Address.");
        } else {
            this.password = this.createHash(password);
        }
    }

    private boolean validate(String password) {
        return !StringUtils.isBlank(password);
    }

    private String createHash(String password) {
        return BCrypt.withDefaults().hashToString(4, password.toCharArray());
    }

    public boolean checkPassword(String pwd) {
        if (StringUtils.isBlank(pwd)) {
            return false;
        } else {
            BCrypt.Result result = BCrypt.verifyer().verify(pwd.toCharArray(), this.password.toCharArray());
            return result.verified;
        }
    }

    public int hashCode() {
        int hash = 7;
        hash = 7 * hash + this.password.hashCode();
        return hash;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (this.getClass() != o.getClass()) {
            return false;
        } else {
            Password obj = (Password)o;
            return Objects.equals(this.password, obj.password);
        }
    }
}
