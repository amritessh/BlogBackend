/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.blog.repositories;

import com.project.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author amritesh
 */
public interface CommentRepo extends JpaRepository<Comment,Integer> {
    
}
