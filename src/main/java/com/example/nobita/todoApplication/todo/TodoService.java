package com.example.nobita.todoApplication.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	private static int todoCount = 0;
	
	static {
		todos.add(new Todo(++todoCount, "Nobita", "Learn Spring", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todoCount, "Nobita", "Learn Full Stack", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todoCount, "Nobita", "Learn DSA", LocalDate.now().plusYears(3), false));
	}
	
	public List<Todo> findByUsername(String username){
		Predicate <? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String username, String description, LocalDate date, boolean done)
	{
		todos.add(new Todo(++todoCount, username, description, date, done));
	}
	
	public void deleteATodo(int id) {
		for (int i=0;i<todoCount; i++)
		{
			if (todos.get(i).getId() == id) {
				todos.remove(todos.get(i));
				break;
			}
		}	
	}

	public Todo findById(int id) {
		for (int i=0;i<todoCount; i++)
		{
			if (todos.get(i).getId() == id) {
				return todos.get(i);
			}
		}
		return null;
	}

	public void updateTodo(@Valid Todo todo) {
		deleteATodo(todo.getId());
		todos.add(todo);
	}
}
