package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails, OAuth2User {



    private User user;
    private Map<String,Object> attributes;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    public PrincipalDetails(User user,Map<String,Object> attributes) {
        this.user = user;
        this.attributes=attributes;
    }



    //권한: 한개가 아닐수있음(3개일수도 ?)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection=new ArrayList<>();
        collection.add(()->{
                return user.getRole();
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // =================oauth2=====================


    @Override
    public Map<String, Object> getAttributes() {
        return attributes; // {id:123123123,name:"김민우", email:"ㅁㄴㅇㅁ@nvaer.com}
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }
}
