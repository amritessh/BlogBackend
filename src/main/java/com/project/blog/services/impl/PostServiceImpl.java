/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.blog.services.impl;

import com.project.blog.entities.Category;
import com.project.blog.entities.Post;
import com.project.blog.entities.User;
import com.project.blog.exceptions.ResourceNotFoundException;
import com.project.blog.payloads.PostDto;
import com.project.blog.payloads.PostResponse;
import com.project.blog.repositories.CategoryRepo;
import com.project.blog.repositories.PostRepo;
import com.project.blog.repositories.UserRepo;
import com.project.blog.services.PostService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author amritesh
 */
@Service
public class PostServiceImpl implements PostService {
    
    @Autowired
    private PostRepo postRepo;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private CategoryRepo categoryRepo;
    
    @Override
    public PostDto createPost(PostDto postDto, Integer userId , Integer categoryId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
        

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.jpg");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        
        Post newPost = this.postRepo.save(post);
        
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
//      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
        
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
//      post.setAddedDate(postDto.getAddedDate());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
        
    }

    @Override
    public void deletePost(Integer postId) {
//     throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
       this.postRepo.delete(post);
    }


    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    	Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
                PostResponse postResponse = new PostResponse();

		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());

		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
    
    }    
//    public List<PostDto> getAllPost(Integer pageNumber , Integer pageSize,String sortBy,String sortDir) {
//       throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    @Override
//    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir){
//       Pageable p = PageRequest.of(pageNumber,pageSize);
//       Page<Post> pagePost = this.postRepo.findAll(p);
//       List<Post> allPosts = pagePost.getContent();
//       
////      List<Post> allPosts = this.postRepo.findAll();
//        List<PostDto> allPostDto = allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
////        return allPostDto;
//    }
//	@Override
//	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
//
//		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
//		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
//
//		Page<Post> pagePost = this.postRepo.findAll(p);
//
//		List<Post> allPosts = pagePost.getContent();
//
//		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
//				.collect(Collectors.toList());
//
//		PostResponse postResponse = new PostResponse();
//
//		postResponse.setContent(postDtos);
//		postResponse.setPageNumber(pagePost.getNumber());
//		postResponse.setPageSize(pagePost.getSize());
//		postResponse.setTotalElements(pagePost.getTotalElements());
//
//		postResponse.setTotalPages(pagePost.getTotalPages());
//		postResponse.setLastPage(pagePost.isLast());
//
//		return postResponse;
//        }

    @Override
    public PostDto getPostById(Integer postId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Posts","post Id",postId));
       PostDto postDto = this.modelMapper.map(post, PostDto.class);
       return postDto;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);
        List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
//      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    		List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
    }


    
}
