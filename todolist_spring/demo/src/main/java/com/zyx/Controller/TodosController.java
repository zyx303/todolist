package com.zyx.Controller;

import com.zyx.domin.ResponseResult;
import com.zyx.domin.entity.Todos;
import com.zyx.service.TodosService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodosController {
    @Autowired
    private TodosService todosService;
    @GetMapping
    public ResponseResult getTodos() {
        return ResponseResult.okResult(todosService.getBaseMapper().selectList(null));
    }
    @PostMapping("/add")
    public ResponseResult addTodo(@RequestBody Todos todos) {
        return todosService.addTodo(todos);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteTodo(@PathVariable Integer id) {
        return ResponseResult.okResult(todosService.removeById(id));
    }
    @PutMapping("/update")
    public ResponseResult updateTodos(@RequestBody List<Todos> todosList) {
        return todosService.updateTodos(todosList);
    }

}
