package com.codewithmosh.store.controllers;


import com.codewithmosh.store.dtos.CategoryDto;
import com.codewithmosh.store.mappers.CategoryMapper;
import com.codewithmosh.store.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public  Iterable<CategoryDto> findAllCategories() {

        return categoryRepository.findAll().stream().map
                (         categoryMapper::toDto).toList();

    }

    @GetMapping("/{id}")
    public CategoryDto findCategoryById(@PathVariable Byte id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElse(null));

    }
}
