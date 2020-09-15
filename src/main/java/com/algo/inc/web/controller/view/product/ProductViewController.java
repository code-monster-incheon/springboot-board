package com.algo.inc.web.controller.view.product;

import com.algo.inc.domain.product.Product;
import com.algo.inc.web.dto.product.ProductResponseDto;
import com.algo.inc.web.repository.ProductRepository;
import com.algo.inc.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/view/product")
public class ProductViewController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping("/list")
    public String getProductList(Model model, Product product)
    {
        List<ProductResponseDto> productList = productService.getProductList("SELL");

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

    @GetMapping("/manage/list")
    public String getProductListAll(Model model, Product product)
    {
        List<ProductResponseDto> productList = productService.getProductList("ALL");
        model.addAttribute("productList", productList);
        return "manage/list";
    }

    @GetMapping("/manage/detail")
    public String getProductInfo(Long id, Model model)
    {
        Product product = productService.getProductDetail(id);
        model.addAttribute("vo", product);
        return "manage/detail";
    }

    @GetMapping("/manage/register")
    public String registerProduct(@ModelAttribute("product") Product product)
    {
        return "manage/register";
    }

    @GetMapping("/manage/modify")
    public String modifyProductInfo(@RequestParam("id") Long id, Model model)
    {
        productRepository.findById(id).ifPresent(product->model.addAttribute("product", product));
        return "manage/modify";
    }

    @PostMapping("/manage/modify")
    public String deleteProduct(){

        return "";
    }
}
