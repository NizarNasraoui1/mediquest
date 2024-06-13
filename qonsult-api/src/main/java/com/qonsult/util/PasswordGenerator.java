package com.qonsult.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

    public class PasswordGenerator {

        private static final String CHAR_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
        private static final String CHAR_UPPERCASE = CHAR_LOWERCASE.toUpperCase();
        private static final String DIGIT = "0123456789";
        private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";

        private static SecureRandom random = new SecureRandom();

        public static String generatePassword(int length) {
            List<String> charCategories = Arrays.asList(CHAR_LOWERCASE,CHAR_UPPERCASE,DIGIT,SPECIAL_CHARS);

            List<Character> charPool = charCategories.stream()
                    .flatMap(s -> s.chars().mapToObj(c -> (char) c))
                    .collect(Collectors.toList());

            Collections.shuffle(charPool, random);

            StringBuilder password = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                password.append(charPool.get(random.nextInt(charPool.size())));
            }

            return password.toString();
        }
}
