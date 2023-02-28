package com.cos.photogramstart.domain.image;

import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {

    @Query(value="SELECT * FROM image i join user u on i.userId=u.id WHERE userId IN (SELECT toUserid FROM subscribe WHERE fromuserId=:principalId)",nativeQuery = true)
    Page<Image> mStory(long principalId, Pageable pageable);

    @Query(value="SELECT i.* FROM image i INNER JOIN (SELECT imageId,COUNT(imageId) LIKECount FROM likes GROUP BY imageId) c ON i.id=c.imageid ORDER BY LIKEcount DESC",nativeQuery = true)
    List<Image> mPopular();





}
