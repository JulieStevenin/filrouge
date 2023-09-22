package com.appfilrouge.projetfilrouge.services;


import com.appfilrouge.projetfilrouge.dto.AdDTO;
import com.appfilrouge.projetfilrouge.dto.CategoryDTO;
import com.appfilrouge.projetfilrouge.entities.Ad;
import com.appfilrouge.projetfilrouge.entities.Category;
import com.appfilrouge.projetfilrouge.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        // Convertir les entités en DTOs
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AdDTO> getAdsByCategoryId(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            // Convertir les annonces en DTOs
            return category.getAds().stream()
                    .map(this::convertAdToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException("Category not found with id: " + categoryId);
        }
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category updatedCategory = categoryOptional.get();
            updatedCategory.setAdType(categoryDTO.getAdType());
            updatedCategory.setAdStyle(categoryDTO.getAdStyle());
            Category savedCategory = categoryRepository.save(updatedCategory);
            return convertToDTO(savedCategory);
        } else {
            throw new EntityNotFoundException("Category not found with id: " + categoryId);
        }
    }

    public void deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            // Supprimer les annonces associées à la catégorie (si nécessaire)
            // category.getAds().clear();
            categoryRepository.delete(category);
        } else {
            throw new EntityNotFoundException("Category not found with id: " + categoryId);
        }
    }

    public AdDTO addAdToCategory(Long categoryId, AdDTO adDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            Ad ad = convertAdToEntity(adDTO);
            category.getAds().add(ad);
            ad.setCategory(category);
            Category savedCategory = categoryRepository.save(category);
            return convertAdToDTO(ad);
        } else {
            throw new EntityNotFoundException("Category not found with id: " + categoryId);
        }
    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setAdType(category.getAdType());
        categoryDTO.setAdStyle(category.getAdStyle());
        return categoryDTO;
    }

    private AdDTO convertAdToDTO(Ad ad) {
        AdDTO adDTO = new AdDTO();
        adDTO.setId(ad.getId());
        // todo Ajoutez d'autres champs de l'annonce ici
        return adDTO;
    }

    private Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setAdType(categoryDTO.getAdType());
        category.setAdStyle(categoryDTO.getAdStyle());
        return category;
    }

    private Ad convertAdToEntity(AdDTO adDTO) {
        Ad ad = new Ad();
        // todo Ajoutez la conversion d'autres champs de l'annonce ici
        return ad;
    }
}

