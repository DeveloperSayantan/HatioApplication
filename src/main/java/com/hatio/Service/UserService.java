package com.hatio.Service;

import java.util.List;
import java.util.Optional;

import com.hatio.Entity.UserEntity;

public interface UserService {

	UserEntity addUser(UserEntity userEntity);
	
	List<UserEntity> fetchAllUser();
	
	Optional<UserEntity> loginByEmailPass(UserEntity userMailPass);

	boolean isEmailExists(String email);
	
	UserEntity loginByEmail(String email);
}
