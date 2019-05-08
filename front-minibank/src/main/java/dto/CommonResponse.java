package dto;


public class CommonResponse<T> {

    private String status;
    private String message;
    private T data;

    public CommonResponse() {
        this.status = "20";
        this.message= "Success";
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
