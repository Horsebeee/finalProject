package com.study.app.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {
    @GetMapping("/")
    public String root() {
//        return "redirect:/articles";
        return "forward:/articles";

    }


}
