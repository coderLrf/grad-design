package com.example.designtopicselectionsystem.controller;

import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice // 全局异常捕捉
@Controller
public class CustomExceptionHandler implements ErrorController {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResponseJson uploadException(MaxUploadSizeExceededException e) throws IOException {
        return ResponseJsonUtil.error(-1, "最大上传文件为10M，上传文件大小超出了限制！");
    }

    @RequestMapping("/error")
    public String handleError(final HttpServletRequest request) {
        // 获取错误状态码
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        // 判断类型
        if(statusCode == 401) {
            return "error/error_401";
        } else if(statusCode == 404) {
            return "error/error_404";
        } else if(statusCode == 403) {
            return "error/error_403";
        } else if(statusCode == 400) {
            return "error/error_400";
        } else {
            return "error/error_500";
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
