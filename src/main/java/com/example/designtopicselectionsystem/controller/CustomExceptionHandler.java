package com.example.designtopicselectionsystem.controller;

import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

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
    public String handleError() {
        return "error/error_404";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
