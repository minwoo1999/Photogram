package com.cos.photogramstart.web.api;


import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.web.dto.CMResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;


    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@PathVariable long toUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        subscribeService.subscribe(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMResDto<>(1, "구독하기 성공!", null), HttpStatus.OK);
    }


    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> unsubscribe(@PathVariable long toUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        subscribeService.unsubscribe(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMResDto<>(1, "구독취소하기 성공!", null), HttpStatus.OK);
    }
}
