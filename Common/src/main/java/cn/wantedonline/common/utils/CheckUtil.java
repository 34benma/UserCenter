package cn.wantedonline.common.utils;


/**
 * Created by wangcheng on 20/08/2017.
 */
public class CheckUtil {

    public static boolean isEmptyStr(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmptyStr(String str) {
        return !isEmptyStr(str);
    }

    public static boolean shortValueIn(short value, short... in) {
        short[] inArray = in;
        for (short s : in) {
            if (value == s) {
                return true;
            }
        }
        return false;
    }

    public static boolean shortValueNotIn(short value, short... in) {
        return !shortValueIn(value, in);
    }

    public static void main(String[] args) {
        boolean value = shortValueIn((short)3, (short)4,(short)5,(short)6);
        System.out.println(value);
        boolean value2 = shortValueIn((short)5, (short)4,(short)5,(short)6);
        System.out.println(value2);
    }

    private CheckUtil() {}

    public static boolean longEqual(long value, long equalValue) {
        return value == equalValue;
    }

    public static boolean longNotEqual(long value, long equalValue) {
        return value != equalValue;
    }
}
