package com.toba.pool.core.utils;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.regex.Pattern;



public class UUIDUtil {
    public static final String UUID_V4_STRING =
            "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$";
    public Pattern UUID_REGEX_PATTERN;

    public UUIDUtil() {
        UUID_REGEX_PATTERN = Pattern.compile(UUID_V4_STRING);
    }

    public boolean isValidUUID(String str) {
        if (str == null) {
            return false;
        }
        return UUID_REGEX_PATTERN.matcher(str).matches();
    }
}