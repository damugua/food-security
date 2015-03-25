package com.zsgj.mobileinspect.bean;

public class BusinessException {
    private int ExceptionType;
    private String Message;
    private String StackTrace;

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

    public String getStackTrace() {
        return StackTrace;
    }

    public void setStackTrace(String stackTrace) {
        StackTrace = stackTrace;
    }
}
