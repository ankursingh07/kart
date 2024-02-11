package com.kart.order.persistence.repository;

import com.kart.order.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
