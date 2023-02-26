package br.com.cwi.shop.helpers;

public class Constantes {
    public static final String AUTH_COOKIE_NAME = "fakebook_cookie";

    private static final String PREFIX_UPLOAD_PATH = "http://localhost:8080/upload/";

    public static final String getPrefixUploadPath() {
        var path = System.getenv("PREFIX_UPLOAD_PATH");
        return path == null || path.isEmpty() ? PREFIX_UPLOAD_PATH : path;
    }
}
