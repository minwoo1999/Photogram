package com.cos.photogramstart.handler.ex;


import java.util.Map;

public class CustomApiException extends RuntimeException {

    //객체를 구분할 떄!! serialvseionUID

    public CustomApiException(String message) {
        super(message);
    }
}
