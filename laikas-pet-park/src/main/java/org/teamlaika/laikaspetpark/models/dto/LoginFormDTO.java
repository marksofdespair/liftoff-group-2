package org.teamlaika.laikaspetpark.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import javax.annotation.processing.SupportedOptions;

public class LoginFormDTO {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 25, message = "Invalid username. Must be between 3 and 20 characters.")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 50, message = "Invalid password. Must be between 5 and 30 characters.")
    private String password;


    @NotNull
    private String accountType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
