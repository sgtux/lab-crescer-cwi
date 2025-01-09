package br.com.dvsn.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsrfTokenCache {

    private static List<CsrfToken> tokens = new ArrayList<>();

    public static void addToken(CsrfToken token) {
        tokens.add(token);
    }

    public static void removeToken(String token) {
        tokens.removeIf(p -> p.getToken().equals(token));
    }

    public static CsrfToken getToken(String token) {
        Optional<CsrfToken> tokenOptional = tokens.stream().filter(p -> p.getToken().equals(token)).findFirst();
        return tokenOptional.isPresent() ? tokenOptional.get() : null;
    }
}