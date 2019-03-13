package com.wy.study.zuul.demo.domain.api;

import lombok.Data;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/8 下午6:28
 */
@Data
public class BasicResult {

    private String code;
    private String msg;
    private Object data;

    private static final String SUCCESS_CODE = "0";
    private static final String SUCCESS_MSG = "success";
    private static final String FAIL_CODE = "1";
    private static final String FAIL_MSG = "fail";

    public BasicResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static BasicResult success() {
        return result(SUCCESS_MSG, SUCCESS_CODE);
    }

    public static BasicResult result(String code, String msg) {
        return result(code, msg, null);
    }

    public static BasicResult success(Object data) {
        return result(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static BasicResult result(String code, String msg, Object data) {
        return new BasicResult(SUCCESS_CODE, SUCCESS_MSG, data);
    }
}
