package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String testController(){
        return "Test Controller";
    }
}
