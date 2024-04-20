package com.hatio.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hatio.Entity.TodoEntity;
import com.hatio.Repository.TodoRepo;
import com.hatio.Service.TodoService;

@Service
public class TodoServiceImpl implements TodoService{
	
	@Autowired
	TodoRepo todoRepo;

	@Override
	public TodoEntity addTodo(TodoEntity todoEntity) {
		
		return todoRepo.save(todoEntity);
	}

	@Override
	public List<TodoEntity> fetchAllTodo() {
		
		return todoRepo.findAll();
	}

	@Override
	public TodoEntity getTodoById(int id) {

		return todoRepo.findById(id).get();
	}

	@Override
	public TodoEntity updateStatus(int id, TodoEntity todoEntity) {
		
		Optional<TodoEntity> existTodo = todoRepo.findById(id);
		
		if(existTodo.isPresent()) {
			
			TodoEntity currentTodoEntity = existTodo.get();
			
			currentTodoEntity.setStatus(todoEntity.getStatus());
			currentTodoEntity.setUpdateDate(todoEntity.getUpdateDate());
			
			return todoRepo.save(currentTodoEntity);
		}
		return null;
	}

	@Override
	public String deleteTodo(int id) {
		
		if(todoRepo.findById(id).isPresent()) {
			
			todoRepo.deleteById(id);
			
			return "Successfully deleted";
		}
		return "No such record found";
	}

}
