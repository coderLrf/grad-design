package com.example.designtopicselectionsystem.response;

// 返回json数据工具类
public class ResponseJsonUtil {

    /**
     * 成功时的返回json，带数据
     * @param data
     * @return
     */
    public static ResponseJson successData(Object data) {
        return new ResponseJson(data);
    }

    /**
     * 成功时的返回json，带 data 数据，和 message 信息
     * @param data
     * @param message
     * @return
     */
    public static ResponseJson successData(Object data, String message) {
        return new ResponseJson(data, message);
    }

    /**
     * 成功时的返回，不带数据
     * @param message
     * @return
     */
    public static ResponseJson success(String message) {
        return new ResponseJson(message);
    }

    /**
     * 数据请求失败时的返回
     * @param state
     * @param message
     * @return
     */
    public static ResponseJson error(int state, String message) {
        return new ResponseJson(state, message);
    }

    public static ResponseJson error(Object data, String message) {
        return new ResponseJson(data, message);
    }
}
