package com.gersonandre.GersonAndre.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {
    @PostMapping(value = "demo")
    public String test(){
        return "test";
    }
}
