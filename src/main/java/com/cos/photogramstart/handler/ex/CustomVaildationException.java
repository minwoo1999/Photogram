package com.cos.photogramstart.handler.ex;


import java.util.Map;

public class CustomVaildationException extends RuntimeException {

    //객체를 구분할 떄!! serialvseionUID
    private Map<String,String> errorMap;

    public CustomVaildationException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }
    public Map<String,String> getErrorMap(){
        return errorMap;
    }
}
