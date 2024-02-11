package com.kart.order.web.controller;

import com.kart.order.dto.DiscountOrTaxDTO;
import com.kart.order.dto.ProductDTO;
import com.kart.order.persistence.entity.ProductEntity;
import com.kart.order.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ProductEntity addProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductEntity productEntity = productService.addProduct(productDTO);
        return productEntity;
    }

    @GetMapping(value = "/{productId}", produces = "application/json")
    public ProductEntity getProduct(@PathVariable Long productId) {
        ProductEntity productEntity = productService.getProduct(productId);
        return productEntity;
    }

    @PutMapping(consumes = "application/json")
    public String updateProduct(@Valid @RequestBody ProductEntity productEntity) {
        boolean isUpdated = productService.updateProduct(productEntity);
        return isUpdated ? "Updated successfully" : "Error occurred while updating";
    }

    @DeleteMapping(value = "/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        boolean isUpdated = productService.deleteProduct(productId);
        return isUpdated ? "Deleted successfully" : "Error occurred while deleting";
    }

    @PostMapping(consumes = "application/json", value = "/alter")
    public String implementDiscountOrTax(@Valid @RequestBody DiscountOrTaxDTO discountOrTaxDTO) {
        boolean isUpdated = productService.discountOrTax(discountOrTaxDTO);
        return isUpdated ? "Changes made successfully" : "Error occurred!";
    }
}
