package com.algo.inc.web.view.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ViewController {

    @GetMapping("/")
    public String home()
    {
        return "index";
    }

    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }
}
