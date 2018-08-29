package com.daibing.myblog.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Random;

/**
 * @program: myblog
 * @description: 工具类
 * @author: daibing
 * @create: 2018-08-12 16:41
 **/
public class Tools {

    private static final Random random = new Random();
    /**
     * 编码对象
     */
    final static Base64.Encoder encoder = Base64.getEncoder();

    /**
     * 解码对象
     */
    final static Base64.Decoder decoder = Base64.getDecoder();

    /**
     * 编码
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String enAes(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return encoder.encodeToString(encryptedBytes);
    }

    /**
     * 解码
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String deAes(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] cipherTextBytes = decoder.decode(data);
        byte[] decValue = cipher.doFinal(cipherTextBytes);
        return new String(decValue);
    }

    /**
     * 判断字符串是否为数字和有正确的值
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {

        if (null != str && 0 != str.trim().length() && str.matches("\\d*")) {
            return true;
        }

        return false;
    }

    /**
     * 根据条件获取随机数
     * @param min
     * @param max
     * @return
     */
    public static int rand(int min, int max) {
        return random.nextInt(max) % (max - min + 1) + min;
    }
}
