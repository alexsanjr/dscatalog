package com.alexsanjr.dscatalog.dto;

import com.alexsanjr.dscatalog.entities.Category;

public record CategoryDTO(Long id, String name) {

    public CategoryDTO(Category cat) {
        this(cat.getId(), cat.getName());
    }
}

