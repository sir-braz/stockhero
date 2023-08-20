package com.stockhero.backend.service;

import com.stockhero.backend.entity.CategoryProduct;

import java.util.List;
import java.util.Optional;

public interface CategoryProductService {
    CategoryProduct saveCategoryProduct(CategoryProduct categoryProduct);

    List<CategoryProduct> listCategoryProduct();

    Optional<CategoryProduct> findById(Long categoryId);

    void deleteCategory(Long categoryId);

    CategoryProduct updateCategory(Long categoryId, CategoryProduct updatedCategory);
}
