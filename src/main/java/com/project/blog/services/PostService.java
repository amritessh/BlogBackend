/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.blog.services;

import com.project.blog.entities.Post;
import com.project.blog.payloads.PostDto;
import com.project.blog.payloads.PostResponse;
import java.util.List;

/**
 *
 * @author amritesh
 */
public interface PostService {
    //create
    PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
    
    //update
    PostDto updatePost(PostDto postDto, Integer postId);
    
    //delete
    void deletePost(Integer postId);
    
    //getAll posts
    
//   List<PostDto> getAllPost();
   PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
   
   //get single post
   
   PostDto getPostById(Integer postId);
   
   //get all post by category
   List<PostDto> getPostByCategory(Integer categoryId);
   
   //get all posts by user
   
   List<PostDto> getPostsByUser(Integer userId);
   
   //search
   List<PostDto> searchPosts(String keyword);
    
}
