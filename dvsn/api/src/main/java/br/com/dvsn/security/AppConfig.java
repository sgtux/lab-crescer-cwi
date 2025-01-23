package br.com.dvsn.security;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;

import br.com.dvsn.helpers.StringHelper;

public class AppConfig {

    private static String apiKey;

    private static final String apiKeyFileName = ".api_key";

    public static final String getCookieBase64ApiKey() {
        try {
            if (!StringHelper.isNullOrEmpty(apiKey))
                return apiKey;

            var path = Paths.get(apiKeyFileName);

            var file = new File(apiKeyFileName);
            if (file.exists()) {
                apiKey = Files.readString(path).trim();
                return apiKey;
            }

            apiKey = generateApiKey();
            Files.writeString(path, apiKey);
            return apiKey;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String generateApiKey() {
        String chars = "0123456789abcdef";
        int length = 5;
        SecureRandom RANDOM = new SecureRandom();
        StringBuilder apiKey = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(chars.length());
            apiKey.append(chars.charAt(index));
        }
        return apiKey.toString();
    }
}