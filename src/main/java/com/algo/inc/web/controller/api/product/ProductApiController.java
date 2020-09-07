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

    // Create, 상품등록하기
    @PostMapping
    public Long registerProduct(@RequestBody ProductSaveDto productSaveDto)
    {
        return productService.registerProduct(productSaveDto);
    }

    // Read Selling Product, 판매가능한 상품 리스트, enabled 가 true인 상품들만
    @GetMapping("/getProductList")
    public List<ProductResponseDto> getProductList()
    {
        return productService.getProductList("SELL");
    }

    // Read All, db에 존재하는 모든 상품의 목록
    @GetMapping("/lists")
    public List<ProductResponseDto> getLists()
    {
        return productService.getProductList("ALL");
    }

    // Update, 상품 등록정보 수정하기
    @PutMapping("/{productId}")
    public Long updateProductInfo(@PathVariable Long productId, @RequestBody ProductSaveDto productSaveDto)
    {
        return productService.updateProductInfo(productId, productSaveDto);
    }

    // Delete, 상품 삭제하기
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId)
    {
        productService.deleteProduct(productId);
    }

}
