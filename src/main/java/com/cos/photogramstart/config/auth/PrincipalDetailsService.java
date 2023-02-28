package com.cos.photogramstart.config.auth;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    //패스워드는 알아서 체킹하니까 신경쓸 필요없다
    //리턴이 잘되면 내부적으로 자동으로 세션을 만든다.
    //UserDetails 타입을 세션으로 만든다.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {



        User userEntity = userRepository.findByUsername(username);

        if(userEntity==null){
            throw new UsernameNotFoundException("유저가 존재하지않습니다");
        }else{
            return new PrincipalDetails(userEntity);
        }


    }
}
