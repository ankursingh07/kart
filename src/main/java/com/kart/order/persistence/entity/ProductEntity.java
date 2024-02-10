package com.kart.order.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_entity")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @Column(name = "product_id")
    @SequenceGenerator(name = "SQ_GEN", sequenceName = "ORDER_ENTITY_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.UUID, generator = "SQ_GEN")
    Long id;

    @Column(name = "product_name", nullable = false)
    String name;

    @Column(name = "product_description")
    String description;

    @Column(name = "product_price", nullable = false)
    Double price;

    @Column(name = "product_quantity", nullable = false)
    Long quantity;
}
