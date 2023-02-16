package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable long pageUserId,Model model,@AuthenticationPrincipal PrincipalDetails principalDetails)
    {
        UserProfileDto userProfileDto = userService.userProfile(pageUserId, principalDetails.getUser().getId());
        model.addAttribute("dto",userProfileDto);
        return "user/profile";
    }
    @GetMapping("/user/{id}/update")
    public String update(@PathVariable long id, @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("세션정보"+principalDetails.getUser().getUsername());
        return "user/update";
    }


}
