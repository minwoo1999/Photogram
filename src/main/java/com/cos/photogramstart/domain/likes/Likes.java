package com.cos.photogramstart.domain.likes;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Builder
//2개를 복합적으로 유니크하게 걸기위해
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="likes_uk",
                        columnNames={"ImageId","userId"}
                )
        }
)
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="imageId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;

    private LocalDateTime createDate;

    @PrePersist //디비에 insert 되기직전에 실행
    public void createDate(){
        this.createDate= LocalDateTime.now();
    }


}
