package com.kart.order.service;

import com.kart.order.dto.DiscountOrTaxDTO;
import com.kart.order.dto.ProductDTO;
import com.kart.order.exception.ProductException;
import com.kart.order.helper.ProductHelper;
import com.kart.order.persistence.entity.ProductEntity;
import com.kart.order.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kart.order.exception.ErrorCode.PRODUCT_ID_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductHelper productHelper;

    public ProductEntity addProduct(ProductDTO productDTO) {
        log.info("Adding a Product with name={}...", productDTO.name());
        Double finalPrice = productDTO.price();
        if (productDTO.hasOptional()) {
            finalPrice = productHelper.finalPrice(
                    productDTO.price(),
                    productDTO.optional().get().discountOrTax(),
                    productDTO.optional().get().applicableValue()
            );
        }
        ProductEntity productEntity = ProductEntity.builder()
                .name(productDTO.name())
                .description(productDTO.description())
                .price(finalPrice)
                .quantity(productDTO.quantity())
                .build();
        productEntity = productRepository.save(productEntity);
        log.info("Added the following product={}!", productDTO.name());
        return productEntity;
    }

    public ProductEntity getProduct(Long id) {
        log.info("Searching for a Product with id={}...", id);
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isEmpty()) {
            log.error("Product with id={} does not exist.", id);
            throw new ProductException(PRODUCT_ID_NOT_FOUND.name(), PRODUCT_ID_NOT_FOUND.getErrorMessage());
        }
        log.info("Fetched a Product with id={}!", id);
        return productEntity.get();
    }

    public boolean updateProduct(ProductEntity productEntity) {
        log.info("Updating a Product with id={}...", productEntity.getId());
        boolean isExists = productRepository.existsById(productEntity.getId());
        if (!isExists) {
            log.error("Product with id={} does not exist.", productEntity.getId());
            throw new ProductException(PRODUCT_ID_NOT_FOUND.name(), PRODUCT_ID_NOT_FOUND.getErrorMessage());
        }
        productEntity = productRepository.save(productEntity);
        log.info("updated the following product with id={}!", productEntity.getId());
        return true;
    }

    public boolean deleteProduct(Long id) {
        log.info("Deleting a Product with id={}...", id);
        boolean isExists = productRepository.existsById(id);
        if (!isExists) {
            log.error("Product with id={} does not exist.", id);
            throw new ProductException(PRODUCT_ID_NOT_FOUND.name(), PRODUCT_ID_NOT_FOUND.getErrorMessage());
        }
        productRepository.deleteById(id);
        log.info("Deleted the following product with id={}!", id);
        return true;
    }

    public ProductEntity discountOrTax(DiscountOrTaxDTO discountOrTaxDTO) {
        log.info("Searching for a Product with id={}...", discountOrTaxDTO.id());
        ProductEntity productEntity = productRepository.findById(discountOrTaxDTO.id()).get();
        if (productEntity == null) {
            log.error("Product with id={} does not exist.", discountOrTaxDTO.id());
            throw new ProductException(PRODUCT_ID_NOT_FOUND.name(), PRODUCT_ID_NOT_FOUND.getErrorMessage());
        }
        Double finalPrice = productHelper.finalPrice(
                productEntity.getPrice(),
                discountOrTaxDTO.discountOrTax(),
                discountOrTaxDTO.applicableValue()
        );
        productEntity.setPrice(finalPrice);
        productEntity = productRepository.save(productEntity);
        log.info("updated the product price with id={}!", productEntity.getId());
        return productEntity;
    }
}
