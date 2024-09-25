package com.riwi.artemisa.domain.models;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventoryModel {

    private Long id;
    private int stock;
    private String supplier;
    private float supplierPrice;
    private float sellingPrice;
    private LocalDate dueDate;
    @Builder.Default
    private boolean stateProduct = true;
    private ProductModel product;

    //Auditable
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @Builder.Default
    private Boolean deleted = false;


}
