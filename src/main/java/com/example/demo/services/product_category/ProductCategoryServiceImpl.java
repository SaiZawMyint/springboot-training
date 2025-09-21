package com.example.demo.services.product_category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.product_category.ProductCategoryDTO;
import com.example.demo.persistence.model.product_category.ProductCategory;
import com.example.demo.persistence.model.user.User;
import com.example.demo.persistence.repositories.product_category.ProductCategoryRepository;
import com.example.demo.services.auth.AuthenticationService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public ProductCategoryDTO saveProductCategory(ProductCategoryDTO dto) {
        ProductCategory pc = new ProductCategory();
        pc.setName(dto.getName());
        pc.setCode(dto.getCode());
        pc.setSequence(dto.getSequence());
        pc.setStatus(dto.getStatus());

        User currentUser = authenticationService.getCurrentUser();
        pc.setCreatedBy(currentUser);

        ProductCategory saved = this.repository.save(pc);
        return new ProductCategoryDTO(saved);
    }

    @Override
    public List<ProductCategoryDTO> getAllProductCategories() {
        return repository.findAll()
                .stream()
                .map(ProductCategoryDTO::new)
                .toList();
    }

    @Override
    public ProductCategoryDTO updateProductCategory(ProductCategoryDTO dto) {
        ProductCategory pc = repository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Product category not found with id: " + dto.getId()));

        pc.setName(dto.getName());
        pc.setCode(dto.getCode());
        pc.setSequence(dto.getSequence());
        pc.setStatus(dto.getStatus());

        User currentUser = authenticationService.getCurrentUser();
        pc.setUpdatedBy(currentUser);

        ProductCategory updated = repository.save(pc);
        return new ProductCategoryDTO(updated);
    }

    @Override
    public void deleteProductCategory(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Product category not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
