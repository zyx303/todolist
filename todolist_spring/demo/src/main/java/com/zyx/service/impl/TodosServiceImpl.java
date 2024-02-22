package com.zyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyx.domin.ResponseResult;
import com.zyx.domin.entity.Todos;
import com.zyx.mapper.TodosMapper;
import com.zyx.service.TodosService;
import kotlin.jvm.internal.Lambda;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Todos)表服务实现类
 *
 * @author makejava
 * @since 2024-02-22 13:39:04
 */
@Service("todosService")
public class TodosServiceImpl extends ServiceImpl<TodosMapper, Todos> implements TodosService {


    @Override
    public ResponseResult addTodo(Todos todos) {
        //用mybatisplus储存todos
        save(todos);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateTodos(List<Todos> todosList) {
        for(Todos todos:todosList){
            updateById(todos);
        }
        return ResponseResult.okResult();
    }
}

