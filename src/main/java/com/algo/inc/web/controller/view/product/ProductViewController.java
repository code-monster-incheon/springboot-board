package com.algo.inc.web.controller.view.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/view/product")
public class ProductViewController {

        @GetMapping("/list")
        public String getProduct(){
            return "product/list";
        }
}
