package com.zyx.domin.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Todos)表实体类
 *
 * @author makejava
 * @since 2024-02-22 13:39:05
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("todos")
public class Todos  {
@TableId
    private Integer id;


    private String text;

    private Integer completed;



}

