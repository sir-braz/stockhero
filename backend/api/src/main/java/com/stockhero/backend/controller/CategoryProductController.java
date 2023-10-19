package com.stockhero.backend.controller;

import com.stockhero.backend.entity.CategoryProduct;
import com.stockhero.backend.service.CategoryProductService;
import com.stockhero.backend.utiliy.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Logger;

@Controller
@CrossOrigin(origins = "*") // Replace with your frontend origin
public class CategoryProductController {

    private static final Logger logger = Logger.getLogger(ProductController.class.getName());

    private CategoryProductService categoryProductService;

    @Autowired
    public void setCategoryProductService(CategoryProductService categoryProductService) {
        this.categoryProductService = categoryProductService;
    }

    @GetMapping("/list-category")
    public ResponseEntity<List<CategoryProduct>> listCategoryProduct() {
        try {
            List<CategoryProduct> categories = categoryProductService.listCategoryProduct();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (BackendException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getReason());
        }
    }

    @PostMapping("/save-category")
    public ResponseEntity<CategoryProduct> createCategoryProduct(@RequestBody CategoryProduct categoryProduct) {
        try {
            CategoryProduct newCategoryProduct = categoryProductService.saveCategoryProduct(categoryProduct);
            return new ResponseEntity<>(newCategoryProduct, HttpStatus.CREATED);
        } catch (BackendException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getReason());
        }
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        try {
            categoryProductService.deleteCategory(categoryId);
            logger.info("Method delete category with id " + categoryId);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (BackendException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting category");
        }
    }

    @PutMapping("/category/{categoryId}")
    public CategoryProduct updateCategory(@PathVariable Long categoryId, @RequestBody CategoryProduct updatedCategory) {
        return categoryProductService.updateCategory(categoryId, updatedCategory);
    }
}
