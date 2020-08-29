package com.algo.inc.web.controller.api.product;

import com.algo.inc.web.dto.product.ProductResponseDto;
import com.algo.inc.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductApiController {
    private final ProductService productService;

    // 판매가능한 상품 리스트, enabled 가 true인 상품들만
    @GetMapping("/getProductList")
    public List<ProductResponseDto> getProductList(){
        return productService.getProductList("SELL");
    }

    // db에 존재하는 모든 상품의 목록
    @GetMapping("/lists")
    public List<ProductResponseDto> getLists(){
        return productService.getProductList("ALL");
    }

    // 김종범 TODO : 상품 상세받아오는 api, 상품 삭제를 enabled false로 변경
    // 김종범 TODO : 댓글 수정, 삭제 javascript

}
