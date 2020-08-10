package com.sunday;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class TodoRest {

	private final TodoRepository repository;

	@DeleteMapping("delete/{task}")
	public void delete(@PathVariable("task") String task) {
		repository.delete(repository.findByTask(task));
	}

	@GetMapping("get")
	public List<String> get() {
		Iterable<Todo> todos = repository.findAll();
		List<Todo> todosList = new ArrayList<Todo>();
		todos.forEach(todosList::add);
		return todosList.stream().map(Todo::getTask).collect(Collectors.toList());
	}

	@PostMapping("post")
	public void insert(@RequestBody Todo todo) {
		repository.save(todo);
	}

	@GetMapping("own")
	public String owner() {
		return "Syed Mohiuddin";
	}
}
