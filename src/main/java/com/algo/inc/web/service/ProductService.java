package com.algo.inc.web.service;

import com.algo.inc.domain.product.Product;
import com.algo.inc.web.dto.product.ProductSaveDto;
import com.algo.inc.web.dto.product.ProductResponseDto;
import com.algo.inc.web.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    // 상품리스트
    public List<ProductResponseDto> getProductList(String command)
    {
        List<Product> productList = new ArrayList<>();
        if(command.equals("ALL"))       // enabled 관계없이
            productList = productRepository.findAll();
        else                            // enabled true인 상품만 즉, 판매중인 상품만 가져오기
            productList = productRepository.findAllByEnabledIsTrue();

        List<ProductResponseDto> list = new ArrayList<>();
        for(int i = 0; i < productList.size(); i++){
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setId(productList.get(i).getId());
            productResponseDto.setName(productList.get(i).getName());
            productResponseDto.setPrice(productList.get(i).getPrice());
            productResponseDto.setQuantity(productList.get(i).getQuantity());
            list.add(productResponseDto);
        }
        return list;
    }

    // 상품 상세 정보
    public Product getProductDetail(Long id)
    {
        return productRepository.findById(id).get();
    }

    // 상품 등록하기
    public Long registerProduct(ProductSaveDto productSaveDto) {
        Product product = new Product();
        product.setName(productSaveDto.getName());
        product.setPrice(productSaveDto.getPrice());
        product.setQuantity(productSaveDto.getQuantity());
        product.setRegDt(LocalDateTime.now());
        product.setEnabled(productSaveDto.isEnabled());
        return productRepository.save(product).getId();
    }

    // 상품정보 수정하기
    public Long updateProductInfo(Long productId, ProductSaveDto productSaveDto)
    {
        Product product = productRepository.findById(productId).get();
        product.setName(productSaveDto.getName());
        product.setPrice(productSaveDto.getPrice());
        product.setQuantity(productSaveDto.getQuantity());
        product.setEnabled(productSaveDto.isEnabled());
        return productRepository.save(product).getId();
    }

    // 상품 삭제하기
    public void deleteProduct(Long productId)
    {
        productRepository.deleteById(productId);
    }
}
