package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.Comment.Comment;
import com.cos.photogramstart.domain.Comment.CommentRepository;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomVaildationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    private final ImageRepository imageRepository;

    private final UserRepository userRepository;

    @Transactional
    public Comment applywrite(String content,long imageId,long userId){

        System.out.println(imageId+content+userId);
        //TIP (객체를 만들때 id 값만 담아서 insert 할 수 있다.)
        User user = userRepository.findById(userId).orElseThrow(()->{return new CustomApiException("찾을 수 없는 Id입니다");});
        Image image = imageRepository.findById(imageId).orElseThrow(()->{return new CustomApiException("찾을 수 없는 Id입니다");});


        Comment comment=new Comment(content,user,image);
        return commentRepository.save(comment);

    }

    @Transactional
    public Comment applyDelete(long id){
        try{
            commentRepository.deleteById(id);
        }catch(Exception e){
            throw new CustomApiException(e.getMessage());
        }

        return null;
    }
}
