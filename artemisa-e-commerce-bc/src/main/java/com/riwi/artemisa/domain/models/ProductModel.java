package com.riwi.artemisa.domain.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    private Long id;
    private String name;
    private String description;
    private ProductInventoryModel productInventoryModel;
    private CategoryModel category;
    private List<MediaModel> media;

}
