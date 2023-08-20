package com.stockhero.backend.service;

import com.stockhero.backend.entity.CategoryProduct;
import com.stockhero.backend.repository.CategoryProductRepository;
import com.stockhero.backend.utiliy.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class CategoryProductServiceImpl implements CategoryProductService {

    private final CategoryProductRepository categoryProductRepository;

    @Autowired
    public CategoryProductServiceImpl(CategoryProductRepository categoryProductRepository) {
        this.categoryProductRepository = categoryProductRepository;
    }

    @Override
    public CategoryProduct saveCategoryProduct(CategoryProduct categoryProduct) {
        try {
            return categoryProductRepository.save(categoryProduct);
        } catch (BackendException ex) {
            throw new BackendException("Unable to save category product" + ex);
        }
    }

    @Override
    public List<CategoryProduct> listCategoryProduct() {
        try {
            return (List<CategoryProduct>) categoryProductRepository.findAll();
        } catch (BackendException ex) {
            throw new BackendException("Unable to list categories" + ex);
        }
    }

    @Override
    public Optional<CategoryProduct> findById(Long categoryId) {
        try {
            return categoryProductRepository.findById(categoryId);
        } catch (Exception ex) {
            throw new BackendException("Unable to retrieve category with ID " + categoryId + ex);
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        try {
            Optional<CategoryProduct> categoryProductOptional = categoryProductRepository.findById(categoryId);
            if (categoryProductOptional.isPresent()) {
                categoryProductRepository.delete(categoryProductOptional.get());
            } else {
                throw new BackendException("Category not found with ID " + categoryId);
            }
        } catch (BackendException ex) {
            throw new BackendException("Unable to delete category");
        }
    }

    @Override
    public CategoryProduct updateCategory(Long categoryId, CategoryProduct updatedCategory) {
        try {
            Optional<CategoryProduct> categoryOptional = categoryProductRepository.findById(categoryId);
            if (categoryOptional.isPresent()) {
                CategoryProduct category = categoryOptional.get();
                category.setCategoryName(updatedCategory.getCategoryName());
                return categoryProductRepository.save(category);
            } else {
                throw new BackendException("Category not found with ID: " + categoryId);
            }
        } catch (Exception ex) {
            throw new BackendException("Unable to update category with ID: " + categoryId + ex);
        }
    }
}
