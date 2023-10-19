package com.stockhero.backend;

import com.stockhero.backend.controller.ProductController;
import com.stockhero.backend.entity.CategoryProduct;
import com.stockhero.backend.entity.Product;
import com.stockhero.backend.service.CategoryProductService;
import com.stockhero.backend.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductService productService;

	@Mock
	private CategoryProductService categoryProductService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateProduct() {
		CategoryProduct categoryProduct = new CategoryProduct();
		categoryProduct.setId(1L);

		Product product = new Product();
		product.setCategoryProduct(categoryProduct);

		when(categoryProductService.findById(1L)).thenReturn(Optional.of(categoryProduct));
		when(productService.createProduct(any(Product.class))).thenReturn(product);

		ResponseEntity<Product> response = productController.createProduct(product);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(product, response.getBody());
	}

	@Test
	public void testListProducts() {
		List<Product> products = new ArrayList<>();
		products.add(new Product());
		products.add(new Product());

		when(productService.listProducts()).thenReturn(products);

		ResponseEntity<List<Product>> response = productController.listProducts();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(products, response.getBody());
	}

	@Test
	public void testGetProductSuccess() {
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);

		when(productService.findProductById(productId)).thenReturn(product);

		ResponseEntity<Product> response = productController.getProduct(productId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(product, response.getBody());
	}

	@Test
	public void testGetProductNotFound() {
		Long productId = 1L;

		when(productService.findProductById(productId)).thenReturn(null);

		ResponseEntity<Product> response = productController.getProduct(productId);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(null, response.getBody());
	}
}
