package com.zsgj.mobileinspect.bean;

public class BusinessWarning {
    private int ExceptionType;
    private String Message;

    public int getExceptionType() {
        return ExceptionType;
    }

    public void setExceptionType(int exceptionType) {
        ExceptionType = exceptionType;
    } 

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
