package br.com.cwi.shop.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Pattern;

public final class StringHelper {

    public static String toBase64(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public static String fromBase64(String data) {
        return new String(Base64.getDecoder().decode(data), StandardCharsets.UTF_8);
    }

    public static String toJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public static <T> T fromJson(String jsonData, Class<T> valueType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructType(valueType);
        return (T)mapper.readValue(jsonData, type);
    }

    public static String md5(String texto) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");;
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        BigInteger hash = new BigInteger(1, md.digest(texto.getBytes()));
        return hash.toString(16);
    }

    public static String pathJoin(String... directories) {
        String path = "";
        for(var dir : directories)
            path = new File(path, dir).toString();
        return path;
    }

    public static String createUploadFilePath(String filename) {
        return StringHelper.pathJoin(System.getProperty("user.dir"), "src", "main", "resources", "public", "upload", filename);
    }

    public static String createFilenameFromMultipartFile(MultipartFile file) {
        return String.format("%s.%s", UUID.randomUUID(), file.getOriginalFilename().split("\\.")[1]);
    }

    public static boolean isEmail(String email) {
        return Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches();
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
