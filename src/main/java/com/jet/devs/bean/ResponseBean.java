package com.jet.devs.bean;

/**
 * Author: ab
 * Date Time: 07/11/23
 */
public class ResponseBean {
    private String message;

    public ResponseBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
