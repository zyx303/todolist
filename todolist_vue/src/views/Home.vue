<template>
    <div class="video-background">
        <video playsinline autoplay muted loop>
        <source src="../static/video/Lavenza animated wallpaper.mp4" type="video/mp4">
        </video>
        <el-container class="container">
            <el-header class="loginTitle">Todo List</el-header>
            <el-container style="margin-left: 10%;">
                <el-aside class="side">
                    <el-col :span="15" style="margin-left: 40%;" class="menu">
                        <el-menu v-model="filter" @select="handleSelect">
                            <el-menu-item index="2">待办事项</el-menu-item>
                            <el-menu-item index="1">已完成事项</el-menu-item>
                        </el-menu>
                    </el-col>
                </el-aside>
                <el-main class="main">
                    <el-input style="width: 450px" v-model="newTodo" placeholder="What needs to be done?" @keyup.enter="addTodo"></el-input>
                    <div v-if="todos.length === 0">Loading...</div>
                    <el-row v-for="(todo, index) in filteredTodos" :key="index">
                        <el-col :span="12">
                            <el-checkbox v-model="todo.selected">{{ todo.text }}</el-checkbox>
                        </el-col>   
                        <!-- <el-col :span="6">
                            <el-button class="button" @click="updateById(todo.id)">修改</el-button>
                        </el-col> -->
                        <el-col :span="6">
                            <div>{{ formatDate(todo.createTime) }}</div>
                        </el-col>
                        <el-col :span="6">
                            <el-button class="button" @click="removeTodo(todo.id)">删除</el-button>
                        </el-col>
                    </el-row>
                    <el-button v-if="filter==2" @click="toggleTodos">完成了</el-button>
                    <el-button v-if="filter==1" @click="toggleTodos">没做完</el-button>
                </el-main>
                <el-aside width="40%"></el-aside>
        </el-container>
        </el-container>
    </div>
    
  </template>
  
  <script setup>
    import { ref, reactive,onMounted,computed } from 'vue'
    import axios from 'axios'
    const newTodo = ref('')
    const todos = ref([])
    const filter = ref('2')
    const filteredTodos = computed(() => {
        if (filter.value === '1') {
            return todos.value.filter(todo => todo.completed === 1)
        } else if (filter.value === '2') {
            return todos.value.filter(todo => todo.completed === 0)
        }
    })
    const handleSelect = (index) => {
        filter.value = index
    }
//得到todos
    onMounted(async () => {
    try {
        const response = await axios.get('todos')
        todos.value = response.data.data.map(todo => ({ ...todo, selected: false }))
        console.log(response.data.data)
        console.log(todos.value)
        
    } catch (error) {
        console.error(error)
    }
    })
    //更新text by id
    // const updateById = async (id) => {
    //     try {
    //         const response = await axios.put(`todos/updateById/${id}`, {
    //         completed: 1
    //         })
    //         console.log(response.data)
    //         //刷新页面
    //         location.reload()
    //     } catch (error) {
    //         if (error.response) {
    //             // 服务器返回了非2xx的状态码
    //             console.log(error.response.data)
    //             console.log(error.response.status)
    //             console.log(error.response.headers)
    //         } else if (error.request) {
    //             // 请求已经发送，但没有收到响应
    //             console.log(error.request)
    //         } else {
    //             // 发送请求时发生了错误
    //             console.log('Error', error.message)
    //         }
    //         console.log(error.config)
    //     }
    // }
//加入待办事项
    const addTodo = async () => {
        try {
            const response = await axios.post('/todos/add', {
            text: newTodo.value,
            completed: 0
            })
            console.log(response.data)
            //刷新页面
            location.reload()
        } catch (error) {
            if (error.response) {
                // 服务器返回了非2xx的状态码
                console.log(error.response.data)
                console.log(error.response.status)
                console.log(error.response.headers)
            } else if (error.request) {
                // 请求已经发送，但没有收到响应
                console.log(error.request)
            } else {
                // 发送请求时发生了错误
                console.log('Error', error.message)
            }
            console.log(error.config)
        }
    }
//删除待办事项
    const removeTodo = async (index) => {
        try {
            const response = await axios.delete(`todos/delete/${index}`)
            console.log(response.data)
            //刷新页面
            location.reload()
        } catch (error) {
            if (error.response) {
                // 服务器返回了非2xx的状态码
                console.log(error.response.data)
                console.log(error.response.status)
                console.log(error.response.headers)
            } else if (error.request) {
                // 请求已经发送，但没有收到响应
                console.log(error.request)
            } else {
                // 发送请求时发生了错误
                console.log('Error', error.message)
            }
            console.log(error.config)
        }
    }
//更新
    const toggleTodos = async () => {
    todos.value = todos.value.map(todo => {
        if (todo.selected) {
        return { ...todo, completed: todo.completed === 0 ? 1 : 0, selected: false}
        } else {
        return todo
        }
    })
        try {
        await axios.put('todos/update', todos.value)
        } catch (error) {
        console.error('Error updating todos:', error)
        }
    }    
    //日期格式转换
    const formatDate = computed(() => (dateString) => {
      const date = new Date(dateString);
      return `${date.getMonth() + 1}-${date.getDate()}`.padStart(2, '0');
    });
  </script>
  <style>
    .button{
        float: right;
    }
    .loginTitle{
        text-align: center;
        font-size: 26px;
        padding-top:50px;
        /* 字体白色 */
        color: #fff;
        margin-bottom: 20px;
    }
    .main{
        background-color: #fff;
        opacity: 0.8;
        padding:40px;
        border-radius: 4px; /* 设置边框圆角 */
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 设置阴影 */
        height: 600px;
        width: 300px;
        margin-left: 0%;
    }
    .video-background {
    position: fixed;
    right: 0;
    bottom: 0;
    min-width: 100%;
    min-height: 100%;
    overflow: hidden;
    }

    .video-background video {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    min-width: 100%;
    min-height: 100%;
    width: auto;
    height: auto;
    z-index: -100;
    }
    .menu{
        opacity: 0.8;
        background-color: rgb(77, 77, 77);
        border-radius: 4px; /* 设置边框圆角 */
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1); /* 设置阴影 */
    }
    .row{
        /*文字居中 */
        text-align: center;
        font-size: 20px;
        padding-top: 20px;
        padding-bottom: 20px;
    }
    /* body {
        background-image: "../static/img/1.jpg";
    } */
  </style>