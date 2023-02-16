package com.cos.photogramstart.config;

import com.cos.photogramstart.config.oauth.Oauth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity // 해당파일로 시큐리티를 활성화
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Oauth2DetailsService oauth2DetailsService;
    @Bean
    public BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**","/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()//위에있는 url말고 다른곳에 들어갈 경우 loginPage로 redirect시킴
                .loginPage("/auth/signin")//get
                .loginProcessingUrl("/auth/signin")//post-> 스프링 시큐리티가 로그인 프로세스진행
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()// form 로그인을 포함하여 oauth2 로그인도 할거임
                .userInfoEndpoint()//oauth2 로그인을하게되면, 바로 회원정보를 돌려줘
                .userService(oauth2DetailsService);
    }
}
