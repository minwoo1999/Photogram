package com.cos.photogramstart.web.dto.Image;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {


    private MultipartFile file;
    private String caption;

    private long likeCount;

    private String likeState;


    public Image toEntity(String postImageUrl, User user){
        return Image.builder()
                .caption(caption)
                .likeState("0")
                .likeCount(0)
                .postImageUrl(postImageUrl)
                .user(user)
                .build();
    }
}
