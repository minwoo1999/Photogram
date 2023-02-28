package com.cos.photogramstart.web.api.dto;

import com.cos.photogramstart.domain.Comment.Comment;
import com.cos.photogramstart.domain.user.User;
import lombok.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CommentResDto {

    private long id;
    private String username;
    private String content;

    public CommentResDto(Comment comment) {
        this.id=comment.getId();
        this.username=comment.getUser().getUsername();
        this.content=comment.getContent();
    }
}
