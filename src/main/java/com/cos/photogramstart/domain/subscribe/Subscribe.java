package com.cos.photogramstart.domain.subscribe;

import com.cos.photogramstart.domain.user.User;
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
                        name="subscribe_uk",
                        columnNames={"fromUserId","toUserId"}
                )
        }
)
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name="fromUserId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User fromUser;

    @JoinColumn(name="toUserId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User toUser;

    private LocalDateTime createDate;

    @PrePersist //디비에 insert 되기직전에 실행
    public void createDate(){
        this.createDate= LocalDateTime.now();
    }




}
