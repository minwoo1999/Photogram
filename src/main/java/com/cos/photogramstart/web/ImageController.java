package com.cos.photogramstart.web;


import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.Image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ImageController {


    private final ImageService imageService;

    @GetMapping({"/","/image/story"})
    public String story(){

        return "image/story";
    }


    @GetMapping("/image/popular")
    public String pupular(Model model){

        List<Image> images=imageService.popularImage();
        model.addAttribute("images",images);
        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String upload(){

        return "image/upload";
    }
    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails){


        imageService.ImageUpload(imageUploadDto,principalDetails);

        return "redirect:/user/"+principalDetails.getUser().getId();
    }
}
