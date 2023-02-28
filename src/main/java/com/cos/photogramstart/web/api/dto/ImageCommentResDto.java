package com.cos.photogramstart.web.api.dto;

import com.cos.photogramstart.domain.Comment.Comment;
import com.cos.photogramstart.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

// Notnull null값 체크
// Notempty // 빈값이거나 null값체크
// NotBlank 빈값이거나 null값 체크 그리고 빈공백 까지
@Data
public class ImageCommentResDto {

    private long id;
    @NotBlank
    private String content;
    private String username;
    private long userId;
    public ImageCommentResDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username=comment.getUser().getUsername();
        this.userId=comment.getUser().getId();
    }
}
