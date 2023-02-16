package com.cos.photogramstart.web.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

// Notnull null값 체크
// Notempty // 빈값이거나 null값체크
// NotBlank 빈값이거나 null값 체크 그리고 빈공백 까지
@Data
public class CommentDto {

    @NotBlank
    private String content;
    @NotNull // 빈값이거나 널값 체크
    private long imageId;

}
