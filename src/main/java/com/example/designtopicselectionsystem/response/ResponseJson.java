package com.example.designtopicselectionsystem.response;


// 对返回数据进行疯转
public class ResponseJson {

    // 状态码
    private int state;

    // 返回的数据
    private Object data;

    // 返回的信息
    private String message;

    /**
     * 数据接口请求成功的构造，带数据
     * @param data
     */
    ResponseJson(Object data) {
        this.state = 1;
        this.data = data;
        this.message = "数据请求成功...";
    }

    ResponseJson(Object data, String message) {
        this.state = 1;
        this.data = data;
        this.message = message;
    }

    /**
     * 数据接口请求成功的构造，不带数据
     * @param message
     */
    ResponseJson(String message) {
        this.state = 1;
        this.data = null;
        this.message = message;
    }

    /**
     * 数据接口请求失败的返回，出现业务异常
     * @param state
     * @param message
     */
    ResponseJson(int state, String message) {
        this.state = state;
        this.data = null;
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "state=" + state +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
