package com.stockhero.backend.service;

import com.stockhero.backend.controller.ProductController;
import com.stockhero.backend.entity.Product;
import com.stockhero.backend.repository.ProductRepository;
import com.stockhero.backend.utiliy.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Component
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private static final Logger logger = Logger.getLogger(ProductController.class.getName());

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void associateCategoryToProduct(Long productId, Long categoryId) {
        try {
            Product product = repository.findById(productId).orElseThrow(() -> new BackendException("Product not found"));
        } catch (Exception ex) {
            throw new BackendException("Unable to associate product and category");
        }
    }

    @Override
    public List<Product> listProducts() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public Product findProductById(Long productId) {
        try {
            Optional<Product> productOptional = repository.findById(productId);
            return productOptional.orElse(null);
        } catch (Exception ex) {
            throw new BackendException("Unable to retrieve product" + ex);
        }
    }

    @Override
    public void deleteProductById(Long productId) {
        try {
            repository.deleteById(productId);
        } catch (Exception ex) {
            throw new BackendException("Unable to delete product by id" + ex);
        }
    }

    @Override
    public Product createProduct(Product product) {
        try {
            return repository.save(product);
        } catch (Exception ex) {
            throw new BackendException("Unable to create product" + ex);
        }
    }


}
