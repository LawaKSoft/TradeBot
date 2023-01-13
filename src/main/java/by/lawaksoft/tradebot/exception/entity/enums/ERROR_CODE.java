package by.lawaksoft.tradebot.exception.entity.enums;

public enum ERROR_CODE {

    USER_NOT_FOUND("User not found"),
    ORDER_NOT_FOUND("Order not found");

    private String message;

    ERROR_CODE(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
