package gaea.hubeimobile.statics.utils;

import java.util.Random;

public class StringUtils {

    public static String getCodeString() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands = new char[4];
        int length = 4;
        for (int i = 0; i < length; i++) {
            int rand = new Random().nextInt(36);
            rands[i] = chars.charAt(rand);
        }
        return String.valueOf(rands);
    }



}
