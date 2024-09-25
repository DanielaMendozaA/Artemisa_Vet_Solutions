package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventoryCreateRequest {

    private Long id;
    @NotNull(message = "The stock of the product is required")
    private int stock;
    @NotNull(message = "The update date of the product is required")
    private LocalDate updateDate;
    @NotBlank(message = "The supplier of the product is required")
    private String supplier;
    @NotNull(message = "The supplier price of the product is required")
    private float supplierPrice;
    @NotNull(message = "The selling price of the product is required")
    private float sellingPrice;
    @NotNull(message = "The dueDate of the product is required")
    private LocalDate dueDate;
    @NotNull(message = "The status of the product is required")
    private boolean stateProduct;
    @NotNull(message = "The product is required")
    @Valid
    private ProductCreateRequest product;

}
