package com.algo.inc.web.controller.view.product;

import com.algo.inc.domain.product.Product;
import com.algo.inc.web.dto.product.ProductResponseDto;
import com.algo.inc.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/view/product")
public class ProductViewController {

    private final ProductService productService;

    @GetMapping("/list")
    public String getProductList(Model model, Product product)
    {
        List<ProductResponseDto> productList = productService.getProductList("ALL");

        model.addAttribute("productList", productList);
        return "product/list";
    }

    @GetMapping("/detail")
    public String getProductDetail(Long id, Model model)
    {
        Product product = productService.getProductDetail(id);
        model.addAttribute("vo", product);
        return "product/detail";
    }

    @GetMapping("/register")
    public String registerProduct()
    {
        return "product/register";
    }

    // TODO : 상품 등록하는 템플릿화면 구해서 적용하고 상품 등록/수정/삭제 개발하기
}
