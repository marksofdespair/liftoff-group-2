package org.teamlaika.laikaspetpark.models;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int Id;
    @OneToOne(cascade = CascadeType.ALL)
    private Owner owner;

    @OneToOne(cascade = CascadeType.ALL)
    private Provider provider;

    @NotNull
    private String name;

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @Email
    private String email;

    @NotNull
    private String accountType;


    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {
    }

    public User(String name, String username, String password, String email, String accountType) {
        this.name = name;
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.email = email;
        this.accountType = accountType;
    }


    public String getUsername() {
        return username;
    }

    public int getId() {
        return Id;
    }



    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    //@Override
    public String getName() {
        return name;
    }

    //@Override
    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
