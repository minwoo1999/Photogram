package com.cos.photogramstart.config.oauth;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class Oauth2DetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User= super.loadUser(userRequest); // 응답한 userRequest정보를 파싱해서줌


        Map<String,Object> userInfo=oAuth2User.getAttributes();

        String name= (String) userInfo.get("name");
        String email= (String) userInfo.get("email");
        String username="facebook_"+(String) userInfo.get("id");
        String password=new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());

        User userEntity = userRepository.findByUsername(username);
        System.out.println(oAuth2User.getAttributes());
        if(userEntity==null) {//페이스북 최초 로그인
            User user=User.builder()
                    .username(username)
                    .password(password)
                    .name(name)
                    .email(email)
                    .role("ROLE_USER")
                    .build();

            return new PrincipalDetails(userRepository.save(user),oAuth2User.getAttributes());
        }else{ //회원가입이 되어 있다면
            return new PrincipalDetails(userEntity,oAuth2User.getAttributes()); //페이스북 로그인인지 일반로그인
        }



    }
}
