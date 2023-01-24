package com.app.todoapp.controller;

import com.app.todoapp.model.Todo;
import com.app.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todos")
    public class TodoController {

        @Autowired
        private TodoRepository todoRepository;

        @GetMapping("")
        public String home(Model model) {
            model.addAttribute("todos", todoRepository.findAll());
            return "home";
        }

        @GetMapping("/create")
        public String create() {
            return "create";
        }

        @PostMapping("/save")
        public String save(Todo todo) {
            todoRepository.save(todo);
            return "redirect:/todos";
        }

        @GetMapping("/edit/{id}")
        public String edit(@PathVariable Integer id, Model model) {
            Todo todo = todoRepository.getOne(id);
            model.addAttribute("todo", todo);
            return "edit";
        }

        @GetMapping("/delete/{id}")
        public String delete(@PathVariable Integer id) {
            Todo todo = todoRepository.getOne(id);
            todoRepository.delete(todo);
            return "redirect:/todos";
        }
    }
