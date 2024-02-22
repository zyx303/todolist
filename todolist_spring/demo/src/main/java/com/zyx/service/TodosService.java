package com.zyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyx.domin.ResponseResult;
import com.zyx.domin.entity.Todos;

import java.util.List;


/**
 * (Todos)表服务接口
 *
 * @author makejava
 * @since 2024-02-22 13:39:05
 */
public interface TodosService extends IService<Todos> {


    ResponseResult addTodo(Todos todos);

    ResponseResult updateTodos(List<Todos> todosList);
}

