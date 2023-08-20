package com.stockhero.backend.service;

import com.stockhero.backend.entity.Product;

import java.util.List;

public interface ProductService {
    void associateCategoryToProduct(Long productId, Long categoryId);

    List<Product> listProducts();

    Product findProductById(Long productId);

    void deleteProductById(Long productId);

    Product createProduct(Product product);

    Product generateBarcode(Product product);
}
