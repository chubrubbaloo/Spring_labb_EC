package com.example.spring_uppgifter.controller;

import com.example.spring_uppgifter.dto.ToDoConverterDTO;
import com.example.spring_uppgifter.dto.ToDoRequestDTO;
import com.example.spring_uppgifter.dto.ToDoResponseDTO;
import com.example.spring_uppgifter.entities.ToDo;
import com.example.spring_uppgifter.repositories.ToDoRepository;
import com.example.spring_uppgifter.service.ToDoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class ToDoController {

    ToDoService toDoService;
    ToDoConverterDTO toDoConverterDTO;
    public ToDoController(ToDoService toDoService, ToDoConverterDTO toDoConverterDTO) {
        this.toDoService = toDoService;
        this.toDoConverterDTO = toDoConverterDTO;
    }

    // Hämtar alla todos.
    @GetMapping
    public String returnTodos(Model model) {
        List <ToDo> todoList = toDoService.findAll();
        model.addAttribute("todoList",todoList);
        return "todo";
    }



    @GetMapping("/{id}")
    public ToDoResponseDTO returnToDoById(@PathVariable("id") int id) {
        ToDo toDo = toDoService.findToDoById(id);
        return toDoConverterDTO.entityToDoResponseDTO(toDo);
    }

    @DeleteMapping("/{id}")
    public void deleteToDoById(@PathVariable("id") int id) {
        toDoService.removeById(id);
    }


    @PostMapping
    public ToDoResponseDTO postTodo(@RequestBody ToDoRequestDTO toDoRequestDTO) {
       ToDo todo = toDoConverterDTO.todoRequestDTOToEntity(toDoRequestDTO);
        todo = toDoService.save(todo);
        return toDoConverterDTO.entityToDoResponseDTO(todo);
    }




    @PutMapping("/{id}")
    public ToDoResponseDTO updateTodoById(@PathVariable("id") int id, @RequestBody ToDoRequestDTO changedToDoDTO) {
        ToDo changedToDo = toDoConverterDTO.todoRequestDTOToEntity(changedToDoDTO);

        ToDo toDo = toDoService.updateById(id,changedToDo);
        return toDoConverterDTO.entityToDoResponseDTO(toDo);
    }
}
