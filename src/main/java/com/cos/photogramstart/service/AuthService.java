package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    @Transactional
    public User join(SignupDto signupDto){
        String encPassword = bCryptPasswordEncoder.encode(signupDto.getPassword());
        signupDto.setPassword(encPassword);
        signupDto.setRole("ROLE_USER");
        User userEntity = userRepository.save(signupDto.toEntity());
        return userEntity;
    }

}
