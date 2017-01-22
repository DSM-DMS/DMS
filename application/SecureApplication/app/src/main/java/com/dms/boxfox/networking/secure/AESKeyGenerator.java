package com.dms.boxfox.networking.secure;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AESKeyGenerator {
    private static final int AES_KEY_LENGTH = 16;

    public static String getRandomKey() {
        String uuid = UUID.randomUUID().toString();
        return shuffle(uuid);
    }

    public static String merge(String keyPice) {
        return shuffle(getRandomKey() + keyPice).substring(0, AES_KEY_LENGTH);
    }

    public static String shuffle(String input) {
        List<Character> characters = new ArrayList<Character>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }
}
