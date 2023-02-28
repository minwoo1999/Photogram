package com.cos.photogramstart.handler.aop;

import com.cos.photogramstart.handler.ex.CustomVaildationApiException;
import com.cos.photogramstart.handler.ex.CustomVaildationException;
import io.sentry.Sentry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Component //RestController Service 든 Component 구현체들이다. Compoenet 를 상속해서 만들어져있음
@Aspect //aop 처리를 할수있는 핸들러
public class ValidationAdvice {

       private static final Logger log= LoggerFactory.getLogger(ValidationAdvice.class);

//
//    @Before("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
//    public void testCheck(){
//        // 어디서든지 요청을 받아올수있음
//        HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//
//
//    }




    // web 패키지에서 Controller로 끝나는 모든클래스에서 모든 메서드의 파라미터가 뭐든상관없는거를 호출
    @Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("web api 컨틀롤러==============");

        String type=proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String method=proceedingJoinPoint.getSignature().getName();

        Object [] args=proceedingJoinPoint.getArgs();
        for (Object arg:args){
            if (arg instanceof BindingResult){
                System.out.println("유효성 검사를 하는 함수입니다");
                BindingResult bindingResult=(BindingResult) arg;
                if(bindingResult.hasErrors()){
                    Map<String,String> errorMap=new HashMap<>();

                    for(FieldError error:bindingResult.getFieldErrors()){
                        errorMap.put(error.getField(), error.getDefaultMessage());

                        log.warn(type+"."+method+"() => 필드:"+error.getField()+"메세지"+error.getDefaultMessage());
                        Sentry.captureMessage(type+"."+method+"() => 필드:"+error.getField()+"메세지"+error.getDefaultMessage());
                    }
                    throw new CustomVaildationApiException("유효성검사 실패함",errorMap);
                }
            }
        }
        // ProceedingJoinPoint 함수의 모든곳에 접근할수있는 변수
        // 호출한 함수보다 먼저 실행이됨

        return proceedingJoinPoint.proceed(); //호출한 함수가 실행됨
    }
    @Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // ProceedingJoinPoint 는  컨트롤러 메소드안에 접근할수있는 권한을 만듬

        System.out.println("web 컨틀롤러==============");

        String type=proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String method=proceedingJoinPoint.getSignature().getName();

        Object [] args=proceedingJoinPoint.getArgs();
        for (Object arg:args){
            if (arg instanceof BindingResult){
                BindingResult bindingResult=(BindingResult) arg;
                if(bindingResult.hasErrors()){
                    Map<String,String> errorMap=new HashMap<>();
                    for(FieldError error:bindingResult.getFieldErrors()){
                        errorMap.put(error.getField(),error.getDefaultMessage());
                        log.warn(type+"."+method+"() => 필드:"+error.getField()+"메세지"+error.getDefaultMessage());
                        Sentry.captureMessage(type+"."+method+"() => 필드:"+error.getField()+"메세지"+error.getDefaultMessage());
                    }
                    throw new CustomVaildationException("유효성검사 실패함",errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }

}
