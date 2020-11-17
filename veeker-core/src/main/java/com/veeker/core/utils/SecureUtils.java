package com.veeker.core.utils;

import cn.hutool.crypto.SecureUtil;

import java.util.Objects;
import java.util.Random;

/**
 * @author ：qiaoliang
 * @date ：2020-11-09
 */
public class SecureUtils {
    /**
     * 生成含有随机盐的token
     */
    public static String generate(String identification) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        identification = SecureUtil.md5(identification + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = identification.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = identification.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * 校验密码是否正确
     */
    public static boolean verify(String identification, String generate) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = generate.charAt(i);
            cs1[i / 3 * 2 + 1] = generate.charAt(i + 2);
            cs2[i / 3] = generate.charAt(i + 1);
        }
        String salt = new String(cs2);
        return Objects.equals(SecureUtil.md5(identification + salt), new String(cs1));
    }


}
