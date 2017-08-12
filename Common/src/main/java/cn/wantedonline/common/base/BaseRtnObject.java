package cn.wantedonline.common.base;

/**
 * Created by wangcheng on 05/08/2017.
 */
public class BaseRtnObject {
    private String message;
    private int code;
    private Object data;

    private BaseRtnObject(int code) {
        this.code = code;
    }

    private BaseRtnObject(String message, int code) {
        this.message = message;
        this.code = code;
    }

    private BaseRtnObject(String message, int code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public static BaseRtnObject createRtnObject(int code) {
        return new BaseRtnObject(code);
    }

    public static BaseRtnObject createRtnObject(int code, String message) {
        return new BaseRtnObject(message, code);
    }

    public static BaseRtnObject createRtnObject(int code, String message, Object data) {
        return new BaseRtnObject(message, code, data);
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}
