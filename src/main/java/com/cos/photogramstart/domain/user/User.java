package com.cos.photogramstart.domain.user;


import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// JPA -java persistance api (자바로 데이터를 영구적으로 저장할수있는 api제공)

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website; //웹사이트
    private String bio; //자기소개
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; //사진
    private String role; //권한

    //User를 Select 할때 해당 UserId로 등록된 image들을 다 가져와
    @JsonIgnore
    @OneToMany(mappedBy = "user") //나는 연관관계의 주인이 아니다. 그러므로 테이블에 컬럼을 만들지마
    private List<Image> images=new ArrayList<>();

    private LocalDateTime createDate;

    @PrePersist //디비에 insert 되기직전에 실행
    public void createDate(){
        this.createDate= LocalDateTime.now();
    }


    public void updateUser(String password, String name, String website, String bio, String phone, String gender) {
        this.password = password;
        this.name = name;
        this.website = website;
        this.bio = bio;
        this.phone = phone;
        this.gender = gender;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
