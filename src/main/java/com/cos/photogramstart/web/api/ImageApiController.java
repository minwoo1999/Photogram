package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.service.LikesService;
import com.cos.photogramstart.web.api.dto.ImageResDto;
import com.cos.photogramstart.web.dto.CMResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ImageApiController {


    private final ImageService imageService;

    private final LikesService likesServcie;

    @GetMapping("/api/image")
    public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                        @RequestParam(value="offset",defaultValue = "0") int offset,
                                        @RequestParam(value="limit",defaultValue = "100") int limit,

                                        @PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC)Pageable pageable){

        List<Image> images =  imageService.imageStory(principalDetails.getUser().getId(),offset,limit);
        List<ImageResDto> collect = images.stream().map(i -> new ImageResDto(i)).collect(Collectors.toList());

        return new ResponseEntity<>(new CMResDto<>(1,"성공",collect),HttpStatus.OK);
    }

    @PostMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> likes(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                   @PathVariable long imageId){
        likesServcie.likes(imageId,principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMResDto<>(1,"좋아요성공",null), HttpStatus.CREATED);
    }
    @DeleteMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> unlikes(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                   @PathVariable long imageId){
        likesServcie.cancellikes(imageId,principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMResDto<>(1,"좋아요취소성공",null), HttpStatus.OK);
    }

}
