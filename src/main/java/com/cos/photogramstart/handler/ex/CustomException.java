package com.cos.photogramstart.handler.ex;


import java.util.Map;

public class CustomException extends RuntimeException {

    //객체를 구분할 떄!! serialvseionUID


    public CustomException(String message) {
        super(message);
    }

}
