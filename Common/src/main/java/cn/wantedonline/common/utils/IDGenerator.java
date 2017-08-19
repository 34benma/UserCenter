package cn.wantedonline.common.utils;

import java.util.*;

/**
 * Created by wangcheng on 19/08/2017.
 * 各种ID生成解决方案
 */
public class IDGenerator {
    /**
     * 生成随机UUID 含有字母数字和 -
     * @return
     */
    public String nextUUID() {
        return UUID.randomUUID().toString();
    }

    private IDGenerator() {}

}
