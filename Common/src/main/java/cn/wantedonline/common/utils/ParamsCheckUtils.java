package cn.wantedonline.common.utils;


import java.util.Arrays;

/**
 * Created by wangcheng on 20/08/2017.
 */
public class ParamsCheckUtils {

    /**
     * 校验 是否 含有 这个 整数
     * @param message
     * @param value
     * @param in
     */
    public static void intValueIn(String message, int value, int... in) {
        boolean is_not_contains = Arrays.stream(in).noneMatch(i -> i == value);
        if (is_not_contains) {
            throw new IllegalArgumentException(message);
        }
    }

    private ParamsCheckUtils() {}
}