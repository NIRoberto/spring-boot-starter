package com.codewithmosh.store.controllers;


import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.ProductRepository;
import com.codewithmosh.store.repositories.UserRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    public ProductController(ProductRepository productRepository, ProductMapper productMapper, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<ProductDto> getAllProducts(
            @RequestParam(required = false  ,name="categoryId"
            ) Byte   categoryId
    ){
        Iterable<Product> products;

        if (categoryId == null) {
            // If categoryId is null, get all products
            products = productRepository.findAll();
        } else {
            // Otherwise, get products by categoryId
            products = productRepository.findAllByCategoryId(categoryId);
        }

        // Map the products to ProductDto
        return productMapper.toDtoList(products);
    }

    @GetMapping("{id}")
    public ProductDto getProductById(@PathVariable long id){

        return productMapper.toDto(productRepository.findById(id).get());

    }
}
