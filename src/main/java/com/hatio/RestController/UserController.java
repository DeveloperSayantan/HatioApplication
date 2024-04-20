package com.hatio.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hatio.Entity.UserEntity;
import com.hatio.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<Object> userLogin(@RequestBody UserEntity userEmailPass){
		
		Optional<UserEntity> user = userService.loginByEmailPass(userEmailPass);
		
		if(user.isPresent()) {
			
			return ResponseEntity.ok(user.get());
		}
		
		else {
			return ResponseEntity.badRequest().body("Invalid email Or password");
		}
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> addUser(@RequestBody UserEntity userEntity) {
		
		if (userService.isEmailExists(userEntity.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
            
        }
        
		userService.addUser(userEntity);
        return new ResponseEntity<>("Account Created!!", HttpStatus.OK);
		
	}
	
	@GetMapping("/users")
	public List<UserEntity> fetchAllUser(){
		
		return userService.fetchAllUser();
	}

}
