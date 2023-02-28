package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomVaildationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDtoRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    @Value("${file.path}")
    private String uploadFolder;

    //optional 1. 무조건 찾았다 get() 2. 못찾았다. exception 발동 orElseThrow
    private final UserRepository userRepository;

    private final SubscribeRepository subscribeRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserProfileDto userProfile(long pageUserId,long principalId){

        UserProfileDto dto=new UserProfileDto();

        User userEntity= userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
        });

        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId==principalId);
        dto.setImageCount(userEntity.getImages().size());

        int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
        dto.setSubscribeState(subscribeState==1);
        dto.setSubscribeCount(subscribeCount);

        //좋아요 카운트 추가하기
        userEntity.getImages().forEach((image)->{
            image.setLikeCount(image.getLikes().size());
        });

        return dto;
    }

    @Transactional
    public UserUpdateDtoRes memberUpdate(long id, UserUpdateDto userUpdateDto,@AuthenticationPrincipal PrincipalDetails principalDetails){
        //1 영속화
        User userEntity=userRepository.findById(id).orElseThrow(()->{return new CustomVaildationApiException("찾을 수 없는 Id입니다");});
        //2.영속화된 오브젝트를 수정 - 더티체킹 (업데이트완료)
        userEntity.updateUser(
                bCryptPasswordEncoder.encode(userUpdateDto.getPassword()),
                userUpdateDto.getName(),
                userUpdateDto.getWebsite(),
                userUpdateDto.getBio(),
                userUpdateDto.getPhone(),
                userUpdateDto.getGender());
        //3 principal 세션변경
        principalDetails.setUser(userEntity);

        //4. dto에 Entity데이터 담아주기
        UserUpdateDtoRes userUpdateDtoRes=new UserUpdateDtoRes(userEntity);

        return userUpdateDtoRes;
    }

    @Transactional
    public User profileimageupdate(long principalId, MultipartFile profileImageFile,PrincipalDetails principalDetails) {

        UUID uuid = UUID.randomUUID();
        System.out.println("여기입니다"+profileImageFile);
        String imageFileName = uuid+"_"+profileImageFile.getOriginalFilename();
        //System.out.println("파일명 : "+imageFileName);

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);
        //System.out.println("파일패스 : "+imageFilePath);
        try {
            Files.write(imageFilePath, profileImageFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        User userEntity=userRepository.findById(principalId).orElseThrow(()->{
            throw new CustomApiException("유저를 찾을 수 없습니다");
        });
        userEntity.setProfileImageUrl(imageFileName);

        principalDetails.setUser(userEntity); //세션변경

        return userEntity;

    } //더티 체킹으로 업데이트함
}
