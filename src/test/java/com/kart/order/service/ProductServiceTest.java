package com.kart.order.service;

import com.kart.order.helper.ProductHelper;
import com.kart.order.persistence.entity.ProductEntity;
import com.kart.order.persistence.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.kart.order.helper.ProductHelperTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;
    @Mock
    ProductRepository productRepository;
    @Mock
    ProductHelper productHelper;

    @Test
    void addProductSuccess() {
        when(productRepository.save(any())).thenReturn(getProductEntity());
        when(productHelper.finalPrice(any(),any(),any())).thenReturn(7.77);
        ProductEntity productEntity = productService.addProduct(getProductDTO());
        assertEquals("test-name", productEntity.getName());
        assertEquals(7.77, productEntity.getPrice());
    }

    @Test
    void getProductSuccess() {
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(getProductEntity()));
        ProductEntity productEntity = productService.getProduct(1L);
        assertEquals("test-name", productEntity.getName());
        assertEquals(7.77, productEntity.getPrice());
    }

    @Test
    void updateProductSuccess() {
        when(productRepository.existsById(any())).thenReturn(true);
        when(productRepository.save(any())).thenReturn(getProductEntity());
        boolean updateProduct = productService.updateProduct(getProductEntity());
        assertEquals(true, updateProduct);
    }

    @Test
    void deleteProductSuccess() {
        when(productRepository.existsById(any())).thenReturn(true);
        boolean deleteProduct = productService.deleteProduct(1L);
        assertEquals(true, deleteProduct);
    }

    @Test
    void discountOrTaxSuccess() {
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(getProductEntity()));
        when(productHelper.finalPrice(any(),any(),any())).thenReturn(7.77);
        when(productRepository.save(any())).thenReturn(getProductEntity());
        boolean updated = productService.discountOrTax(getDiscountOrTaxDTO());
        assertEquals(true, updated);
    }
}
