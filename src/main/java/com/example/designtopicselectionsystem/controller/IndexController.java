package com.example.designtopicselectionsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String toIndex() {
        return "redirect:admin/index";
    }

}
