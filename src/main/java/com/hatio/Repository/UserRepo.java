package com.hatio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hatio.Entity.UserEntity;


public interface UserRepo extends JpaRepository<UserEntity, Integer>{
	
	public UserEntity findByEmailAndPassword(String email, String password);
	
	boolean existsByEmail(String email);
	
	public UserEntity findByEmail(String email);

}
