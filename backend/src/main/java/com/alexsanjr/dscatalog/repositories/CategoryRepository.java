package com.alexsanjr.dscatalog.repositories;

import com.alexsanjr.dscatalog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
