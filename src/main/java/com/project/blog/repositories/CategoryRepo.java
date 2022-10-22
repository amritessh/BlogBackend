/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.blog.repositories;

import com.project.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author amritesh
 */
public interface CategoryRepo extends JpaRepository<Category,Integer> {
    
    
    
}
