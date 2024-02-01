package com.alexsanjr.dscatalog.dto;

import com.alexsanjr.dscatalog.entities.Category;
import com.alexsanjr.dscatalog.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public record ProductDTO(Long id,
                         @Size(min = 5, max = 60, message = "the name must be between 5 and 60 characters") @NotBlank(message = "Required field") String name,
                         String description,
                         @Positive(message = "Price must be positive") Double price,
                         String imgUrl,
                         @PastOrPresent(message = "Date must not be in the future") Instant date, List<CategoryDTO> categories) {

    public ProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl(),
                product.getDate(), List.of());
    }

    public ProductDTO(Product product, Set<Category> categories) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl(),
                product.getDate(), categories.stream().map(CategoryDTO::new).toList());
    }
}
