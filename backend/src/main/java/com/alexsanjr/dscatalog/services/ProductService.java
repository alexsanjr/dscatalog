package com.alexsanjr.dscatalog.services;

import com.alexsanjr.dscatalog.dto.CategoryDTO;
import com.alexsanjr.dscatalog.dto.ProductDTO;
import com.alexsanjr.dscatalog.entities.Category;
import com.alexsanjr.dscatalog.entities.Product;
import com.alexsanjr.dscatalog.repositories.CategoryRepository;
import com.alexsanjr.dscatalog.repositories.ProductRepository;
import com.alexsanjr.dscatalog.services.exceptions.DatabaseException;
import com.alexsanjr.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> list = repository.findAll(pageable);
        return list.map(ProductDTO::new);
    }
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found!"));
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product(dto);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);

            entity = repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found!");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setDate(dto.date());
        entity.setImgUrl(dto.imgUrl());

        entity.getCategories().clear();
        for (CategoryDTO categoryDTO : dto.categories()) {
            Category category = categoryRepository.getReferenceById(categoryDTO.id());
            entity.getCategories().add(category);
        }
    }

}
