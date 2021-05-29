package com.example.designtopicselectionsystem.controller;

import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.IOException;

@ControllerAdvice // 全局异常捕捉
public class CustomExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResponseJson uploadException(MaxUploadSizeExceededException e) throws IOException {
        return ResponseJsonUtil.error(-1, "最大上传文件为10M，上传文件大小超出了限制！");
    }


}
