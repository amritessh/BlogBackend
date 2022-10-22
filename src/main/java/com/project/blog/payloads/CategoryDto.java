/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author amritesh
 */
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;
    
    @NotBlank
    @Size(min=4, message="Min size of category title is 4")
    private String categoryTitle;
    
    @NotBlank
    @Size(min=10 , message = "min size of category description is 10")
    private String categoryDescription;
    
}
