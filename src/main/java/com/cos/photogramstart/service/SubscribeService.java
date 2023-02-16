package com.cos.photogramstart.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.subscribeResponseDto;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class SubscribeService {


    private final SubscribeRepository subscribeRepository;
    private final EntityManager em; // Repository는 EntityManager를 구현해섯 만들어져 있는 구현체

    @Transactional
    public void subscribe(long fromUserId,long toUserId){

        try{
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        }catch (Exception e){
            throw new CustomApiException("이미 구독 하였습니다.");
        }


    }

    @Transactional
    public void unsubscribe(long fromUserId,long toUserId){

        subscribeRepository.mUnsubscribe(fromUserId, toUserId);

    }


    @Transactional(readOnly = true)
    public List<subscribeResponseDto> subscribeList(long principalId, long pageUserId) {

        StringBuffer sb = new StringBuffer();
        sb.append("select u.id userId, u.username, u.profileImageUrl,  ");
        sb.append("if( (select true from subscribe where fromUserId = ? and toUserId = u.id), true, false) subscribeState, ");  // principalDetails.user.id
        sb.append("if(u.id = ?, true, false) equalState "); // principalDetails.user.id
        sb.append("from subscribe f inner join user u on u.id = f.toUserId ");
        sb.append("where f.fromUserId = ? "); // pageUserId

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        System.out.println("쿼리 : "+query.getResultList().get(0));

        JpaResultMapper result  = new JpaResultMapper();
        List<subscribeResponseDto> subscribeRespDtos = result.list(query, subscribeResponseDto.class);
        return subscribeRespDtos;


    }
}
