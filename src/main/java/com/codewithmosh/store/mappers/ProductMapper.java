package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

 @Mapping(target = "categoryId", source = "category.id")  // Map category.id to categoryId in DTO
 ProductDto toDto(Product product);
 // Map list of Products to list of ProductDto
 List<ProductDto> toDtoList(Iterable<Product> products);
}
