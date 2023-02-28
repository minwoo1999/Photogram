package com.cos.photogramstart.domain.Comment;


import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100,nullable = false)
    private String content;



    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    private Image image;

    private LocalDateTime createDate;




    @PrePersist //디비에 insert 되기직전에 실행
    public void createDate(){
        this.createDate= LocalDateTime.now();
    }


    public Comment(String content, User user, Image image) {
        this.content = content;
        this.user = user;
        this.image = image;
    }

}
