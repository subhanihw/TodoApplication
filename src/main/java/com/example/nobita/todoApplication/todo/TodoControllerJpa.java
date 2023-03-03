package com.example.nobita.todoApplication.todo;

import java.time.LocalDate;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	private TodoRepository todoRepository;
	

	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}
	
	@RequestMapping("todo-service")
	public String getAllTodos(ModelMap model) {
		model.put("name", getLoggedUserName());
		model.put("todos", todoRepository.findByUsername((String)model.get("name")));
		return "listTodos";
	}
	
	@RequestMapping(value = "add-todo", method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		model.put("todo", new Todo(0,(String)model.get("name"),"",LocalDate.now(),false));
		return "todo";
	}
	
	@RequestMapping(value = "add-todo", method=RequestMethod.POST)
	public String addTodoSuccessPage(ModelMap model,@Valid Todo todo, BindingResult result) {
		if(result.hasErrors())
			return "todo";
		String username = (String) model.get("name");
		todo.setUsername(username);
		todoRepository.save(todo);

		return "redirect:todo-service";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:todo-service";
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.GET)
	public String updateTodo(@RequestParam int id, ModelMap model) {
		Todo todo = todoRepository.findById(id).get();
		model.put("todo", todo);
		return "todo";
	}
	
	
	@RequestMapping(value = "update-todo", method=RequestMethod.POST)
	public String updateTodoSuccessPage(ModelMap model,@Valid Todo todo, BindingResult result) {
		if(result.hasErrors())	
			return "todo";
		String username = (String) model.get("name");
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:todo-service";
	}
	

	private String getLoggedUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();	
	}
}
