package com.appfilrouge.projetfilrouge.controllers;

import com.appfilrouge.projetfilrouge.dto.AdDTO;
import com.appfilrouge.projetfilrouge.dto.CategoryDTO;
import com.appfilrouge.projetfilrouge.entities.Ad;
import com.appfilrouge.projetfilrouge.entities.Category;
import com.appfilrouge.projetfilrouge.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/ads")
    public ResponseEntity<List<AdDTO>> getAdsByCategoryId(@PathVariable Long categoryId) {
        List<AdDTO> ads = categoryService.getAdsByCategoryId(categoryId);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO addedCategory = categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(addedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(categoryId, categoryDTO);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{categoryId}/ads/add")
    public ResponseEntity<AdDTO> addAdToCategory(@PathVariable Long categoryId, @RequestBody AdDTO adDTO) {
        AdDTO addedAd = categoryService.addAdToCategory(categoryId, adDTO);
        return new ResponseEntity<>(addedAd, HttpStatus.CREATED);
    }
}
