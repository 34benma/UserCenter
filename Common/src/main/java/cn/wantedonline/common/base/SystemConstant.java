package cn.wantedonline.common.base;

/**
 * Created by wangcheng on 05/08/2017.
 * 系统常亮 所有模块可以公用
 * *************定义规范****************
 *
 * HTTP 开头 表示用于Controller层面的返回码定义
 * 10xx 中间状态 接收到数据 还未处理
 * 20xx 正常状态 处理成功
 * 30xx 中间处理状态 接受到数据 正在处理
 * 40xx 处理失败 一般是输入数据有问题
 * 50xx 处理失败 服务端问题
 *
 * CONS 开头 系统常量 一般用于Dao层数据状态定义 从 -1 开始
 *
 */
public class SystemConstant {
    //******************HTTP AREA**********************************
    public static final int HTTP_OK = 2000;
    public static final int HTTP_INVALID_PARAMETER = 4000;
    public static final int HTTP_SERVER_EXCEPTION = 5000;
    //*************************************************************

    //*******************CONS**************************************
    public static final int CONS_STATUS_OK = 0; //状态OK
    public static final int CONS_STATUS_DELETED = 1; //逻辑删除

    //********************用户性别常量定义***************************
    public static final int CONS_GENDER_MALE = 1;
    public static final int CONS_GENDER_FEMAILE = 2;

    private SystemConstant() {}

}
