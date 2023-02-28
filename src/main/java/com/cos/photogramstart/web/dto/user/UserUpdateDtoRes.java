package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class UserUpdateDtoRes {

    private String name;
    private String username;
    private String website;
    private String bio;
    private String email;
    private String phone;
    private String gender;

    public UserUpdateDtoRes(User user) {
        this.name = user.getName();
        this.username = user.getUsername();
        this.website = user.getWebsite();
        this.bio = user.getBio();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.gender = user.getGender();
    }
}
