package org.teamlaika.laikaspetpark.models;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String name;

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @Email
    private String email;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(String name,String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.email = email;
    }


    public String getUsername() {return username;}

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}
