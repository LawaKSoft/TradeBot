package by.lawaksoft.tradebot.util;

import java.time.Instant;

public class TimeManager {

    public static String getTimestampForOkx() {
        return String.valueOf(Instant.now()).substring(0, 23) + "Z";
    }
}
