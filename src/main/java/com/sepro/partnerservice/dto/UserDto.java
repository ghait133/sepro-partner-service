package com.sepro.partnerservice.dto;


import com.sepro.partnerservice.validation.PasswordMatches;
import com.sepro.partnerservice.validation.ValidEmail;
import com.sepro.partnerservice.validation.ValidPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
public class UserDto {


    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;

    private String role;

    public UserDto(String password, @NotNull @Size(min = 1) String matchingPassword, @NotNull @Size(min = 1, message = "{Size.userDto.email}") String email, String role) {
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.email = email;
        this.role = role;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role;}

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(final String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }



    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [username=").append(email).append(", password=").append(password).append(", matchingPassword=").append(matchingPassword).append(", email=").append(email).append(", isUsing2FA=")
                .append(role).append("]");
        return builder.toString();
    }

}
