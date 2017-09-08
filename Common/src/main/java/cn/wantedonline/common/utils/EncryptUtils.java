package cn.wantedonline.common.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by louiswang on 17/9/8.
 * 提供计算 MD5 加解密算法工具
 */
public class EncryptUtils {

    private static MessageDigest MD5_DIGEST;
    private static final BASE64Encoder base64_Encoder = new BASE64Encoder();

    static {
        try {
            MD5_DIGEST = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String encoderByMD5(String sourceStr) throws UnsupportedEncodingException {
        byte[] bytes = MD5_DIGEST.digest(sourceStr.getBytes("utf8"));
        return base64_Encoder.encode(bytes);
    }

    private EncryptUtils() {}
}
