package by.lawaksoft.tradebot.util;

import by.lawaksoft.tradebot.exception.entity.BusinessException;
import by.lawaksoft.tradebot.exception.entity.enums.ERROR_CODE;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encoder {

    private final static String ALGORITHM = "HmacSHA256";

    public static String generateBase64Hash(String message, String secret) {
        try {
            Mac sha256Hmac = Mac.getInstance(ALGORITHM);
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            sha256Hmac.init(secret_key);
            return Base64.encodeBase64String(sha256Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            if (e.getCause() instanceof NoSuchAlgorithmException) {
                throw new BusinessException(String.format("Bad algorithm %s", ALGORITHM), ERROR_CODE.BAD_ALGORITHM);
            }
            if (e.getCause() instanceof InvalidKeyException) {
                throw new BusinessException("Bad secret key");
            }
        }
        return null;
    }
}
