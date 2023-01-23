package by.lawaksoft.tradebot.exception.entity.enums;

public enum ERROR_CODE {

    USER_NOT_FOUND("User not found"),
    ORDER_NOT_FOUND("Order not found"),
    BAD_ALGORITHM("Bad algorithm"),
    CANT_MAP_OBJECT_TO_JSON("Cant map object to json"),
    BAD_REQUEST("Bed request");

    private String message;

    ERROR_CODE(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
