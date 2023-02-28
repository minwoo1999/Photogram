package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.Comment.Comment;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caption; // 오늘 나 너무 피곤해 !! 내용
    private String postImageUrl; //사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장 -> db 에 그 저장된 경로를 insert



    @JoinColumn(name="userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;



    @OneToMany(mappedBy = "image")
    // 이미지 좋아요
    private List<Likes> likes;


    @OrderBy("id DESC")
    // 이미지 댓글
    @OneToMany(mappedBy = "image")
    private List<Comment> comments;

    //좋아요 헀는지

    private String likeState;


    private long likeCount;


    private LocalDateTime createDate;

    @PrePersist //디비에 insert 되기직전에 실행
    public void createDate(){
        this.createDate= LocalDateTime.now();
    }

    public void setLikeState(String likeState) {
        this.likeState = likeState;
    }

    public void setLikeCount(long likecount) {
        this.likeCount = likecount;
    }
}
