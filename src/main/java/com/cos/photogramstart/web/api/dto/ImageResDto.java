package com.cos.photogramstart.web.api.dto;



import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ImageResDto {

    private long id;
    private String postImageUrl;

    private User user;
    private long likeCount;

    private String likeState;
    private String caption;
    private List<ImageCommentResDto> comments;



    public ImageResDto(Image image) {
        this.id = image.getId();
        this.postImageUrl = image.getPostImageUrl();
        this.user=image.getUser();
        this.likeCount = image.getLikeCount();
        this.caption = image.getCaption();
        this.likeState=image.getLikeState();
        this.comments = image.getComments().stream()
                .map(c->new ImageCommentResDto(c))
                .collect(Collectors.toList());
    }
}
