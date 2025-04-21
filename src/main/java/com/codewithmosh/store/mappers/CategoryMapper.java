package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.CategoryDto;
import com.codewithmosh.store.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // Explicit field mappings (optional if names match)
    CategoryDto toDto(Category category);
}
