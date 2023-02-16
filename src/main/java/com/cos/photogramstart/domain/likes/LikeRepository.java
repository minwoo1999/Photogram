package com.cos.photogramstart.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikeRepository extends JpaRepository<Likes,Long> {

    @Modifying
    @Query(value = "INSERT INTO likes(imageId, userId,createDate) VALUES(:imageId, :principalId,now())", nativeQuery = true)
    int mLike(long imageId, long principalId);

    @Modifying
    @Query(value = "DELETE FROM likes WHERE imageId = :imageId AND userId = :principalId", nativeQuery = true)
    int mUnLike(long imageId, long principalId);
}
