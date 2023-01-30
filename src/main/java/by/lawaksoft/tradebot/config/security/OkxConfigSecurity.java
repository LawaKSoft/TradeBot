package by.lawaksoft.tradebot.config.security;

import by.lawaksoft.tradebot.dto.config.APIConfiguration;
import by.lawaksoft.tradebot.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OkxConfigSecurity {

    private final APIConfiguration apiConfiguration;

    private final static String KEY = "OK-ACCESS-KEY";
    private final static String TIMESTAMP = "OK-ACCESS-TIMESTAMP";
    private final static String SIGN = "OK-ACCESS-SIGN";
    private final static String PASSPHRASES = "OK-ACCESS-PASSPHRASE";
    private final static String CONTENT_TYPE = "Content-Type";
    private final static String JSON = "application/json";

    @Autowired
    public OkxConfigSecurity(APIConfiguration apiConfiguration) {
        this.apiConfiguration = apiConfiguration;
    }

    public Map<String, String> getHeader(String message, String timestamp) {
        Map<String, String> map = new HashMap<>();
        map.put(KEY, apiConfiguration.getApiKey());
        map.put(TIMESTAMP, timestamp);
        map.put(SIGN, Encoder.generateBase64Hash(message, apiConfiguration.getSecretKey()));
        map.put(PASSPHRASES, apiConfiguration.getPassphrase());
        map.put(CONTENT_TYPE, JSON);
        return map;
    }
}
