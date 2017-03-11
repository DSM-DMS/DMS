package org.boxfox.dms.util;

import org.boxfox.dms.algorithm.AES256;

/**
 * Created by boxfox on 2017-03-04.
 */
public class Guardian {
    public static final int AES = 1;
    public static final int SHA = 2;

    private static final AES256 aes;
    private static final String KEY = "asfpialshvlajvhavav";

    static {
        aes = new AES256(KEY);
    }

    public static String encrypt(int key, String value){
        switch (key){
            case AES:
                //value = aes.encrypt(value);
                break;
            case SHA:
                //value = SHA256.encrypt(value);
                break;
        }
        return value;
    }

    public static boolean checkParameters(Object... args) {
        for (Object obj : args) {
            if (obj == null) {
            	return false;
            }
        }
        return true;
    }

    public static boolean checkApplyAvailable(int type){
        switch(type){
            case 2:
                break;
        }
        return false;
    }

}
