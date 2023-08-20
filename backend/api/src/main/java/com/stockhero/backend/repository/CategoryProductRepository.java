package com.stockhero.backend.repository;

import com.stockhero.backend.entity.CategoryProduct;
import org.springframework.data.repository.CrudRepository;

public interface CategoryProductRepository extends CrudRepository<CategoryProduct, Long> {
}
