package com.stockhero.backend;

import com.stockhero.backend.controller.CategoryProductController;
import com.stockhero.backend.entity.CategoryProduct;
import com.stockhero.backend.service.CategoryProductService;
import com.stockhero.backend.utiliy.BackendException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryProductController.class)

public class CategoryProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryProductService categoryProductService;

    @Autowired
    private CategoryProductController categoryProductController;

    @Test
    public void testListCategoryProduct() throws Exception {
        // Set up the expected behavior for the mock service
        when(categoryProductService.listCategoryProduct()).thenReturn(Collections.emptyList());

        // Execute the GET request and verify the expected status
        mockMvc.perform(get("/list-category"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListCategoryException() throws Exception {
        when(categoryProductService.listCategoryProduct()).thenThrow(new BackendException("Internal error"));

        mockMvc.perform(get("/list-category"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testCreateCategoryProduct() {
        CategoryProduct category = new CategoryProduct();
        category.setId(1L);

        when(categoryProductService.saveCategoryProduct(any(CategoryProduct.class))).thenReturn(category);

        ResponseEntity<CategoryProduct> response = categoryProductController.createCategoryProduct(category);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    public void testCreateCategoryProductException() {
        CategoryProduct categoryInput = new CategoryProduct(/* category values */);

        CategoryProductService categoryProductService = mock(CategoryProductService.class);
        when(categoryProductService.saveCategoryProduct(categoryInput)).thenThrow(new BackendException("Internal error"));

        CategoryProductController controller = new CategoryProductController();
        controller.setCategoryProductService(categoryProductService);

        assertThrows(ResponseStatusException.class, () -> {
            controller.createCategoryProduct(categoryInput);
        });
    }
}
