package br.com.cwi.shop.auth;

import org.springframework.util.PathMatcher;

import java.util.Comparator;
import java.util.Map;

public class AuthPathMatcher implements PathMatcher {
    @Override
    public boolean isPattern(String path) {
        return false;
    }

    @Override
    public boolean match(String pattern, String path) {
        return false;
    }

    @Override
    public boolean matchStart(String pattern, String path) {
        return false;
    }

    @Override
    public String extractPathWithinPattern(String pattern, String path) {
        return null;
    }

    @Override
    public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
        return null;
    }

    @Override
    public Comparator<String> getPatternComparator(String path) {
        return null;
    }

    @Override
    public String combine(String pattern1, String pattern2) {
        return null;
    }
}
