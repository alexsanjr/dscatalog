package com.alexsanjr.dscatalog.dto;

import com.alexsanjr.dscatalog.entities.Category;
import com.alexsanjr.dscatalog.entities.Product;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public record ProductDTO(Long id, String name, String description, Double price,
                         String imgUrl, Instant date, List<CategoryDTO> categories) {

    public ProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl(),
                product.getDate(), List.of());
    }

    public ProductDTO(Product product, Set<Category> categories) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl(),
                product.getDate(), categories.stream().map(CategoryDTO::new).toList());
    }
}
