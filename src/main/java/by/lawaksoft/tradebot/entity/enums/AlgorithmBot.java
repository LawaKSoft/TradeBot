package by.lawaksoft.tradebot.entity.enums;

import lombok.AllArgsConstructor;

import java.util.Locale;

@AllArgsConstructor
public enum AlgorithmBot {

    GRID("Grid");

    private final String text;

    public String text() {
        return this.text;
    }


    public static AlgorithmBot byText(String text) {
        return AlgorithmBot.valueOf(text.toUpperCase(Locale.ROOT));
    }
}
