package dto;

public class TransactionResponse {

    private String status;
    private String message;

    public TransactionResponse() {
        this.status = "20";
        this.message = "Succes";
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

}
