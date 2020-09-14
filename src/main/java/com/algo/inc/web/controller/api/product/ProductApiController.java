package com.algo.inc.web.controller.api.product;

import com.algo.inc.web.dto.product.ProductSaveDto;
import com.algo.inc.web.dto.product.ProductResponseDto;
import com.algo.inc.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductApiController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> registerProduct(@RequestBody ProductSaveDto productSaveDto)
    {
        productService.registerProduct(productSaveDto);
        return new ResponseEntity<>(HttpStatus.OK);
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

    @PutMapping("/{id}")
    public Long updateProductInfo(@PathVariable Long id, @RequestBody ProductSaveDto productSaveDto)
    {
        return productService.updateProductInfo(id, productSaveDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
    }

}
