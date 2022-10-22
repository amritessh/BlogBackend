package com.project.blog.services.impl;

import com.project.blog.config.AppConstants;
import com.project.blog.entities.Role;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.blog.entities.User;
import com.project.blog.payloads.UserDto;
import com.project.blog.repositories.UserRepo;
import com.project.blog.services.UserService;
import com.project.blog.exceptions.*;
import com.project.blog.repositories.RoleRepo;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//Do not add @Service , was throwing errors
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
        
        @Autowired
        private ModelMapper modelMapper;
    
        @Autowired
        private PasswordEncoder passwordEncoder;
        
        @Autowired
        private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
                User user = this.dtoToUser(userDto);
                User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
                User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		user.setName(userDto.getName());
                user.setEmail(userDto.getEmail());
                user.setPassword(userDto.getPassword());
                user.setAbout(userDto.getAbout());
                
                User updatedUser = this.userRepo.save(user);
                UserDto userDto1 = this.userToDto(updatedUser);
                return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
                User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
                List<User> users = this.userRepo.findAll();
                List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
               User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
               this.userRepo.delete(user);

	}
	
	public User dtoToUser(UserDto userDto) {
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
                User user = this.modelMapper.map(userDto,User.class);
		return user;
		
	}
	
	public UserDto userToDto(User user) {
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
                UserDto userDto = this.modelMapper.map(user,UserDto.class);
                return userDto; 
        }

    @Override
    public UserDto registerNewUser(UserDto userDto) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        User user  = this.modelMapper.map(userDto,User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        User newUser = this.userRepo.save(user);
        return this.modelMapper.map(newUser, UserDto.class);
        
    }
}