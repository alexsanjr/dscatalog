package com.alexsanjr.dscatalog.tests;

import com.alexsanjr.dscatalog.dto.ProductDTO;
import com.alexsanjr.dscatalog.entities.Category;
import com.alexsanjr.dscatalog.entities.Product;

import java.time.Instant;

public class Factory {

    public static Product createProduct() {
        Product product = new Product(1L, "Phone", "Good phone", 800.0, "https://img.com/img.png",
                Instant.parse("2020-10-20T03:00:00Z"));
        product.getCategories().add(createCategory());
        return product;
    }

    public static ProductDTO createProductDTO() {
        Product product = createProduct();
        return new ProductDTO(product);
    }

    public static Category createCategory() {
        return new Category(1L, "Electronics");
    }
}
