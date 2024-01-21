package com.alexsanjr.dscatalog.dto;

import com.alexsanjr.dscatalog.entities.Product;

import java.time.Instant;
import java.util.List;

public record ProductDTO(Long id, String name, String description, Double price,
                         String imgUrl, Instant date, List<CategoryDTO> categories) {

    public ProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl(),
                product.getDate(), product.getCategories().stream().map(CategoryDTO::new).toList());
    }
}
