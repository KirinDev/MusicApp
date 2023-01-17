package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

public class Email implements Serializable {

    private String email;

    public Email(String email) {
        if (!this.validate(email)) {
            throw new IllegalArgumentException("Invalid Email Address.");
        } else {
            this.email = email;
        }
    }

    private boolean validate(String email) {
        return !StringUtils.isBlank(email) && this.checkFormat(email);
    }

    private boolean checkFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    public String getEmail() {
        return this.email;
    }

    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.email.hashCode();
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
            Email obj = (Email)o;
            return Objects.equals(this.email, obj.email);
        }
    }

    public String toString() {
        return String.format("%s", this.email);
    }
}
