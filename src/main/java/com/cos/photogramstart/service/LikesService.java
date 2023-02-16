package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.likes.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikeRepository likeRepository;


    @Transactional
    public void likes(long imageId, Long id) {
        likeRepository.mLike(imageId,id);

    }

    @Transactional
    public void cancellikes(long imageId, Long id) {
        likeRepository.mUnLike(imageId,id);
    }
}
