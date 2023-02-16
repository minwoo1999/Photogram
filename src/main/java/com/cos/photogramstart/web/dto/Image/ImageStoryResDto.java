package com.cos.photogramstart.web.dto.Image;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageStoryResDto {

    private long id;
    private String username;
    private String profileImageUrl;

    List<ImageStoryImageDto> images;


    public ImageStoryResDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.profileImageUrl = user.getProfileImageUrl();
        this.images=user.getImages().stream().
                map(i -> new ImageStoryImageDto(i))
                .collect(Collectors.toList());
    }
}
