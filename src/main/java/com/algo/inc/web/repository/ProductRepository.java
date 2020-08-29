package com.algo.inc.web.repository;

import com.algo.inc.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByEnabledIsTrue();
}
