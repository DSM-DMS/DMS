package com.dms.utilities.support;

import java.io.*;
import java.util.HashMap;

/**
 * Created by boxfox on 2017-03-21.
 */
public class SecureConfig {
    private static class FileAccess {
        private static HashMap<String, String> keyMap;

        static {
            File targetFile = new File("config/keys.cnf");
            keyMap = new HashMap<String, String>();
            try {
                FileInputStream fin = new FileInputStream(targetFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("=") && line.split("=").length > 1) {
                        String key = line.split("=")[0];
                        String value = line.split("=")[1].replaceAll(" ", "").replaceAll("\r", "").replaceAll("\n", "");
                        keyMap.put(key, value);
                    }
                }
            } catch (IOException e) {
                if (!targetFile.exists()) {
                    targetFile.getParentFile().mkdirs();
                    try {
                        targetFile.createNewFile();
                    } catch (IOException e1) {
                    }
                }
            }
        }
    }

    public static String get(String key) {
        return FileAccess.keyMap.get(key);
    }

}
