package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe,Long> {

    //native 쿼리를 사용할때는 결과반환값이 모델값과 일치해야함
    @Modifying //INSERT,DELETE,UPDATE 를 네이티브 쿼리로 작성하려면 해당 어노테이션이 필요함
    @Query(value="INSERT INTO subscribe(fromUserId,toUserId,createDate) VALUES(:fromUserId,:toUserId,now())",nativeQuery = true)
    void mSubscribe(long fromUserId, long toUserId);

    @Modifying
    @Query(value="DELETE FROM subscribe where fromUserId=:fromUserId AND toUserId=:toUserId",nativeQuery = true)
    void mUnsubscribe(long fromUserId, long toUserId);


    @Query(value="SELECT COUNT(*) FROM subscribe WHERE FROMUserId= :principalId and TOUserId= :pageUserId",nativeQuery = true)
    int mSubscribeState(long principalId,long pageUserId);
   //페이지 주인이 구독한 수
    @Query(value="SELECT COUNT(*) FROM subscribe WHERE fromUserId= :pageUserId",nativeQuery = true)
    int mSubscribeCount(long pageUserId);


}
