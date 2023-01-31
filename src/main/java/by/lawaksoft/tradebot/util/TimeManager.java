package by.lawaksoft.tradebot.util;

import java.time.Instant;

public class TimeManager {

    private TimeManager() {
        throw new IllegalStateException("Utility class");
    }

    public static String getTimestampForOkx() {
        return String.valueOf(Instant.now()).substring(0, 23) + "Z";
    }
}
