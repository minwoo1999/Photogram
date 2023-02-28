package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomVaildationApiException;
import com.cos.photogramstart.handler.ex.CustomVaildationException;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMResDto;
import com.cos.photogramstart.web.dto.subscribe.subscribeResponseDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDtoRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    private final SubscribeService subscribeService;

    @PostMapping("/api/user/{principalId}/profileImageUrl")
    public ResponseEntity<?> profileImageUrlUpdate(@PathVariable long principalId,
                                                   MultipartFile profileImageFile,
                                                   @AuthenticationPrincipal PrincipalDetails principalDetails){

            userService.profileimageupdate(principalId,profileImageFile,principalDetails);

            return new ResponseEntity<>(new CMResDto<>(1,"프로필 사진변경 성공",null),HttpStatus.OK);
    }

    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity<?> subscribeList(@PathVariable long pageUserId,@AuthenticationPrincipal PrincipalDetails principalDetails){

        List<subscribeResponseDto> subscribeResponseDtos=subscribeService.subscribeList(principalDetails.getUser().getId(),pageUserId);

        return new ResponseEntity<>(new CMResDto<>(1,"구독자 정보 리스트 가져오기 성공",subscribeResponseDtos), HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public CMResDto<?> update(@PathVariable long id,
                              @Valid UserUpdateDto userUpdateDto,
                              BindingResult bindingResult,
                              @AuthenticationPrincipal PrincipalDetails principalDetails
                              ){


        UserUpdateDtoRes userUpdateDtoRes = userService.memberUpdate(id, userUpdateDto, principalDetails);

        return new CMResDto<>(1,"회원수정완료",userUpdateDtoRes);// 응답시에 유저 엔티티의 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답한다.


    }
}
