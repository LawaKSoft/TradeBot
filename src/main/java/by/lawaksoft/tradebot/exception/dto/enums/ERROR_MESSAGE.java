package by.lawaksoft.tradebot.exception.dto.enums;

public enum ERROR_MESSAGE {

    USER_NOT_FOUND("User not found"),
    ORDER_NOT_FOUND("Order not found"),
    BAD_ALGORITHM("Bad algorithm"),
    CANT_MAP_OBJECT_TO_JSON("Cant map object to json"),
    BAD_REQUEST("Bed request");

    private final String message;

    ERROR_MESSAGE(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}