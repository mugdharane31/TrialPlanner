package com.reservemyvax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Functions {
    @RequestMapping("/")
    public String getHomepage() {
        return "forward:/index.html";
    }
}
