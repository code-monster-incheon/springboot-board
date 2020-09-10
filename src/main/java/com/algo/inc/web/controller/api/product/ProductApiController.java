package com.algo.inc.web.controller.api.product;

import com.algo.inc.web.dto.product.ProductSaveDto;
import com.algo.inc.web.dto.product.ProductResponseDto;
import com.algo.inc.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductApiController {
    private final ProductService productService;

    @PostMapping
    public Long registerProduct(@RequestBody ProductSaveDto productSaveDto)
    {
        return productService.registerProduct(productSaveDto);
    }

    @GetMapping("/getProductList")
    public List<ProductResponseDto> getProductList()
    {
        return productService.getProductList("SELL");
    }

    @GetMapping("/lists")
    public List<ProductResponseDto> getLists()
    {
        return productService.getProductList("ALL");
    }

    @PutMapping("/{productId}")
    public Long updateProductInfo(@PathVariable Long productId, @RequestBody ProductSaveDto productSaveDto)
    {
        return productService.updateProductInfo(productId, productSaveDto);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId)
    {
        productService.deleteProduct(productId);
    }

}
