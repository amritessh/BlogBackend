/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.blog.services.impl;

import com.project.blog.entities.Comment;
import com.project.blog.entities.Post;
import com.project.blog.exceptions.ResourceNotFoundException;
import com.project.blog.payloads.CommentDto;
import com.project.blog.repositories.CommentRepo;
import com.project.blog.repositories.PostRepo;
import com.project.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author amritesh
 */
@Service
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private PostRepo postRepo;
    
    @Autowired
    private CommentRepo commentRepo;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post Id",postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","comment Id",commentId));
        this.commentRepo.deleteById(commentId);
        
    }
    
}
