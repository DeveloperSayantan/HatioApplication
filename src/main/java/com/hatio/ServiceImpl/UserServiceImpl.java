package com.hatio.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hatio.Entity.UserEntity;
import com.hatio.Repository.UserRepo;
import com.hatio.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserEntity addUser(UserEntity userEntity) {	//Add new user
		
		return userRepo.save(userEntity);
	}

	@Override
	public Optional<UserEntity> loginByEmailPass(UserEntity userMailPass) {	//User login
		
		return Optional.ofNullable(userRepo.findByEmailAndPassword(userMailPass.getEmail(), userMailPass.getPassword()));
	}
	
	@Override
	public boolean isEmailExists(String email) {
		
		return userRepo.existsByEmail(email);
	}

	@Override
	public List<UserEntity> fetchAllUser() {
		
		return userRepo.findAll();
	}

	@Override
	public UserEntity loginByEmail(String email) {
		
		return userRepo.findByEmail(email);
	}

}
