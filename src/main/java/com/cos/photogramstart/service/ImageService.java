package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.handler.ex.CustomVaildationException;
import com.cos.photogramstart.web.api.dto.ImageResDto;
import com.cos.photogramstart.web.dto.Image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    private final EntityManager em;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional(readOnly = true)
    public List<ImageResDto> imageStory(long principalId, int offset,int limit){

//        Page<Image> images = imageRepository.mStory(principalId,pageable);
//
//        //images에 좋아요 상태 담기
//        images.forEach((image)-> {
//
//            image.setLikeCount(image.getLikes().size());
//
//            image.getLikes().forEach((like)->{
//                if(like.getUser().getId() == principalId) {
//                    image.setLikeState(true);
//                }
//            });
//        });
        List<Image> resultList = em.createQuery("select i from Image i" +
                " join fetch i.user u", Image.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

        resultList.forEach((image)-> {

            image.setLikeCount(image.getLikes().size());

            image.getLikes().forEach((like)->{
                if(like.getUser().getId() == principalId) {
                    image.setLikeState("true");
                }
            });
        });

        List<ImageResDto> collect = resultList.stream().map(i -> new ImageResDto(i)).collect(Collectors.toList());


        return collect;

    }

    @Transactional// 2가지의 연산중 한가지만 실패해도 rollback 시켜버림
    public void ImageUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){


        if(imageUploadDto.getFile().isEmpty()){
            throw new CustomVaildationException("이미지가 첨부되지않았습니다",null);

        }


        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();
        //System.out.println("파일명 : "+imageFileName);

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);
        //System.out.println("파일패스 : "+imageFilePath);
        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //image 테이블에 저장
        imageRepository.save(imageUploadDto.toEntity(imageFileName,principalDetails.getUser()));


    }

    @Transactional(readOnly = true)
    public List<Image> popularImage() {

        return imageRepository.mPopular();
    }
}
