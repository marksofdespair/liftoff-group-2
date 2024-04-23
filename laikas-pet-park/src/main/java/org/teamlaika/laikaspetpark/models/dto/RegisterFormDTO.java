package org.teamlaika.laikaspetpark.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterFormDTO extends org.teamlaika.laikaspetpark.models.dto.LoginFormDTO {

    @NotNull
    @Size(min = 4,max = 50, message = "Please enter a name between 4-50 characters long")
    private String name;
    @NotNull
    @Email(message="Please enter a valid email")
    private String email;
    private String verifyPassword;
    private int zipCode;


    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getZipCode() {return zipCode;}

    public void setZipCode(int zipCode) {this.zipCode = zipCode;}
}
