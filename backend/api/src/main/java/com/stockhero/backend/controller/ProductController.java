package com.stockhero.backend.controller;

import com.stockhero.backend.entity.CategoryProduct;
import com.stockhero.backend.entity.Product;
import com.stockhero.backend.service.CategoryProductService;
import com.stockhero.backend.service.ProductService;
import com.stockhero.backend.utiliy.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Service
public class ProductController {

    private static final Logger logger = Logger.getLogger(ProductController.class.getName());

    private ProductService productService;
    private CategoryProductService categoryProductService;

    @Autowired
    public void setCategoryProductService(CategoryProductService categoryProductService) {
        this.categoryProductService = categoryProductService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String testEndpoint() {
        logger.info("Test endpoint was called");
        return "App is working";
    }

    @PostMapping(value = "/save-product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Long categoryId = product.getCategoryProduct().getId();
        Optional<CategoryProduct> categoryOptional = categoryProductService.findById(categoryId);

        if (categoryOptional.isPresent()) {
            CategoryProduct categoryProduct = categoryOptional.get();
            product.setCategoryProduct(categoryProduct);

            Product newProduct = productService.createProduct(product);

            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/list-products")
    public ResponseEntity<List<Product>> listProducts() {
        logger.info("Method listProducts");
        return new ResponseEntity<>(productService.listProducts(), HttpStatus.OK);
    }

    @PostMapping("/{productId}/associate-category/{categoryId}")
    public ResponseEntity<String> associateCategoryToProduct(
            @PathVariable Long productId, @PathVariable Long categoryId) {
        try {
            productService.associateCategoryToProduct(productId, categoryId);

            Product associatedProduct = productService.findProductById(productId);

            logger.info("Method associateCategoryToProduct called with product: " + associatedProduct);
            return ResponseEntity.ok("Product associated with category successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error associating category with product");
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        try {
            Product product = productService.findProductById(productId);
            logger.info("Method getProduct with id " + product);
            if (product == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(product);
        } catch (BackendException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProductById(productId);
            logger.info("Method deleteProduct with id " + productId);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (BackendException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting product");
        }
    }

}
