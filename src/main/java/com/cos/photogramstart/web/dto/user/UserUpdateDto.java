package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    @NotBlank
    private String name; //필수
    @NotBlank
    private String password; //필수
    private String website;
    private String bio;
    private String phone;
    private String gender;


    public User toEntity(){
        return User.builder()
                .name(name) // 이름도 validation checking
                .password(password) //패스워드를 기재하지않았다면? 문제!! Validation checking
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
