package com.cos.photogramstart.web.api;


import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.Comment.Comment;
import com.cos.photogramstart.handler.ex.CustomVaildationApiException;
import com.cos.photogramstart.handler.ex.CustomVaildationException;
import com.cos.photogramstart.service.CommentService;
import com.cos.photogramstart.web.api.dto.CommentResDto;
import com.cos.photogramstart.web.dto.CMResDto;
import com.cos.photogramstart.web.dto.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<?> commentSave(@Valid @RequestBody CommentDto commentDto,
                                         BindingResult bindingResult,
                                         @AuthenticationPrincipal PrincipalDetails principalDetails){

        CommentResDto commentResDto = commentService.applywrite(commentDto.getContent(), commentDto.getImageId(), principalDetails.getUser().getId());//content,imageId,userId
        return new ResponseEntity<>(new CMResDto<>(1,"댓글쓰기성공",commentResDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<?> commentDelete(@PathVariable long id){

        commentService.applyDelete(id);

        return new ResponseEntity<>(new CMResDto<>(1,"삭제성공",null),HttpStatus.OK);
    }
}
