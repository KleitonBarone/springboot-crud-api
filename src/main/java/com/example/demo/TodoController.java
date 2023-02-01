package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class TodoController {

	TodoRepository todoRepository;
	
	@GetMapping("/todo")
	public List<Todo> getAllTodos() {
		return todoRepository.findAll();
	}

	@GetMapping("/todo/{id}")
	public Optional<Todo> getTodoById(@PathVariable Long id) {
		return todoRepository.findById(id);
	}
	
	@PostMapping("/todo")
	public Todo createTodo(@RequestBody Todo todo) {
		return todoRepository.save(todo);
	}

	@PutMapping("/todo/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
		Optional<Todo> currentTodo = todoRepository.findById(id);
	
		if(currentTodo.isEmpty())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		todo.setId(id);
		return new ResponseEntity<>(todoRepository.save(todo), HttpStatus.OK);

	}

	@DeleteMapping("/todo/{id}")
	public void deleteTodo(@PathVariable Long id) {
		todoRepository.deleteById(id);
	}
}
