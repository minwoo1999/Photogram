package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
//어노테이션이 없어도 ioc에 자동으로 등록이된다. jparespotiory를 등록하면
public interface UserRepository extends JpaRepository <User,Long> {

    User findByUsername(String username);
}
