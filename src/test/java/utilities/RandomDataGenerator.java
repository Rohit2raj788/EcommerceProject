package utilities;

import java.security.SecureRandom;
import java.util.Random;

public class RandomDataGenerator {
    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final String ALPHA_NUMERIC = ALPHA + NUMERIC;
    private static final Random RANDOM = new SecureRandom();

    public static String getRandomString(int length) {
        return generateRandom(ALPHA, length);
    }

    public static String getRandomNumeric(int length) {
        return generateRandom(NUMERIC, length);
    }

    public static String getRandomAlphaNumeric(int length) {
        return generateRandom(ALPHA_NUMERIC, length);
    }

    private static String generateRandom(String chars, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(RANDOM.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
