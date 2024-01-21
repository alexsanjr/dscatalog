package com.alexsanjr.dscatalog.repositories;

import com.alexsanjr.dscatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
