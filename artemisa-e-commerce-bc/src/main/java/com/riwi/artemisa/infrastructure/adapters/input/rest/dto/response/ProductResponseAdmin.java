package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseAdmin {
    private Long id;
    private int stock;
    private float sellingPrice;
    private ProductResponse product;
    //Auditable
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Boolean deleted;
}
