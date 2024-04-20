package com.hatio.Service;

import java.util.List;

import com.hatio.Entity.TodoEntity;

public interface TodoService {

	TodoEntity addTodo(TodoEntity todoEntity);
	
	List<TodoEntity> fetchAllTodo();
	
	TodoEntity getTodoById(int id);
	
	TodoEntity updateStatus(int id, TodoEntity todoEntity);
	
	String deleteTodo(int id);
}
