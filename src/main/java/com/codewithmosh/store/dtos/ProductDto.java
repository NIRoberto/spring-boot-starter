package com.codewithmosh.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId;  // This represents the 'category_id' in the Product entity

    // Optional: Add a category field if you want to map the full category details to the DTO
    // private CategoryDto category;
}
