package com.nano.minibank.helpers;

public class UserException extends Exception {

    private String code;
    private String description;

    public UserException(String code, String description) {
        super();
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
