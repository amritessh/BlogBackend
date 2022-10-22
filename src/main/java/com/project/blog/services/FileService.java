/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.blog.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author amritesh
 */

public interface FileService {
    String uploadImage(String path,MultipartFile file) throws IOException;
    InputStream getResource(String path,String fileName) throws FileNotFoundException;
    
}
