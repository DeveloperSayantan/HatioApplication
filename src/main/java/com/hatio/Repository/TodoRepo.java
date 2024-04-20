package com.hatio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hatio.Entity.TodoEntity;

@Repository
public interface TodoRepo extends JpaRepository<TodoEntity, Integer>{

}
