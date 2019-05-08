package com.nano.minibank.helpers;

public class CommonResponse<T> {

    private String status;
    private String message;
    private T data;

    public CommonResponse() {
        this.status = "20";
        this.message= "Success";
    }

    public CommonResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

/*
 * HTTP Status List
 * -------------------
 * Informational
 * 100 Continue
 * 101 Switching Protocols
 * 102 Processing
 * 103 Early Hints
 *
 * Success
 * 200 OK
 * 201 Created
 * 202 Accepted
 * 203 Non-Authoritative Information
 * 204 No Content
 * 205 Reset Content
 * 206 Partial Content
 * 207 Multi-Status
 * 208 Already Reported
 * 226 IM Used
 *
 */

