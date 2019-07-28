package com.sepro.partnerservice.dto;

import com.sepro.partnerservice.entity.Adresse;
import com.sepro.partnerservice.validation.ValidEmail;
import com.sepro.partnerservice.validation.ValidPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PartnerDto {
    @ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;

    private String telNumbre;


    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String name;

    @ValidPassword
    private String Password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNumbre() {
        return telNumbre;
    }

    public void setTelNumbre(String telNumbre) {
        this.telNumbre = telNumbre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
