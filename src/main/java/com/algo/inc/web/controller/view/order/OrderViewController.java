package com.algo.inc.web.controller.view.order;

import com.algo.inc.domain.product.Product;
import com.algo.inc.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/view/order")
public class OrderViewController {

    private final ProductService productService;

    @GetMapping("/speedorder")
    public String getOrderView(@RequestParam Long id, Model model)
    {
        Product product = productService.getProductDetail(id);
        model.addAttribute("vo", product);
        return "/order/speedorder";
    }

    @GetMapping("/ordercomplete")
    public String getComplete(@RequestParam("id") Long id, @RequestParam("cnt") int cnt, Model model) {
        Product product = productService.getProductDetail(id);
        model.addAttribute("vo", product);
        model.addAttribute("cnt", cnt);
        return "/order/ordercomplete";
    }
}
