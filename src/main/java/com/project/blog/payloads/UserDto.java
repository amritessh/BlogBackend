package com.project.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter

public class UserDto {
    
        
	private int id;
        
        @NotEmpty
        @Size(min = 4,message="Username must be of min 4 characters")
	private String name;
        
        @Email(message="email addresss not valid")
	private String email;
	
        @NotEmpty
        @Size(min=3,max=10,message="Password min for 3 and max for 10")
        private String password;
        
        @NotEmpty
	private String about;
	

}
