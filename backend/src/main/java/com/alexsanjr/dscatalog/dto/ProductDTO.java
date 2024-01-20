package com.alexsanjr.dscatalog.dto;

import com.alexsanjr.dscatalog.entities.Product;

import java.time.Instant;
import java.util.List;

public record ProductDTO(Long id, String name, String description, Double price,
                         String imgUrl, Instant date, List<CategoryDTO> categories) {

    public ProductDTO(Product prod) {
        this(prod.getId(), prod.getName(), prod.getDescription(), prod.getPrice(), prod.getImgUrl(),
                prod.getDate(), prod.getCategories().stream().map(CategoryDTO::new).toList());
    }
}
