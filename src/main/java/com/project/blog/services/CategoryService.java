/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.blog.services;

import com.project.blog.payloads.CategoryDto;
import java.util.List;

/**
 *
 * @author amritesh
 */
public interface CategoryService {
        //create
    public CategoryDto createCategory(CategoryDto categoryDto);
    
    //update
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    
    //delete
    void deleteCategory(Integer categoryId);
    
    //get
    public CategoryDto getCategory(Integer categoryId);
    
    //getAll
    List<CategoryDto> getCategories();
    
}
