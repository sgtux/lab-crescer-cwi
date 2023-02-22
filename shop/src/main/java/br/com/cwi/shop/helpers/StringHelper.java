package br.com.cwi.shop.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

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
}
