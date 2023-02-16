package com.cos.photogramstart.web.dto.Image;

import com.cos.photogramstart.domain.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageStoryImageDto {

    private long id;
    private String caption;
    private String postImageUrl;

    public ImageStoryImageDto(Image image) {
        this.id = image.getId();
        this.caption = image.getCaption();
        this.postImageUrl = image.getPostImageUrl();
    }
}
