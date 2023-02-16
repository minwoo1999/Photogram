package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomVaildationApiException;
import com.cos.photogramstart.handler.ex.CustomVaildationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice // 모든 exception을 낚아챔
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomVaildationException.class) //RuntimeException 함수를 가로챔
    public String validationException(CustomVaildationException e){

        //CMResDto,Script비교
        // 클라이언트에게 응답할때는 script가 좋음
        // ajax통신- cmRespDto 가 좋음
        // ajax통신- cmRespDto 가 좋음
        if (e.getErrorMap()==null){
            return Script.back(e.getMessage());
        }else{
            return Script.back(e.getErrorMap().toString());
        }

    }

    @ExceptionHandler(CustomVaildationApiException.class) //RuntimeException 함수를 가로챔
    public ResponseEntity<?> validationApiException(CustomVaildationApiException e){

        //CMResDto,Script비교
        // 클라이언트에게 응답할때는 script가 좋음
        // ajax통신- cmRespDto 가 좋음
        // ajax통신- cmRespDto 가 좋음
        return new ResponseEntity<>(new CMResDto<>(-1,e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class) //RuntimeException 함수를 가로챔
    public ResponseEntity<?> ApiException(CustomApiException e){

        //CMResDto,Script비교
        // 클라이언트에게 응답할때는 script가 좋음
        // ajax통신- cmRespDto 가 좋음
        // ajax통신- cmRespDto 가 좋음
        return new ResponseEntity<>(new CMResDto<>(-1,e.getMessage(),null),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class) //RuntimeException 함수를 가로챔
    public String CustomException(CustomException e){

        return Script.back(e.getMessage());

    }

}
