<template>
    <el-container class="container">
      <el-header class="loginTitle">Todo List</el-header>
      <el-main class="main">
        <el-input v-model="newTodo" placeholder="What needs to be done?" @keyup.enter="addTodo"></el-input>
        <el-row v-for="(todo, index) in todos" :key="index">
            <el-list-item>
                <el-checkbox v-model="todo.completed">{{ todo.text }}</el-checkbox>
                <el-button type="link" @click="removeTodo(index)">Remove</el-button>
            </el-list-item>
        </el-row>
      </el-main>
    </el-container>
  </template>
  
  <script setup>
    import { ref, reactive } from 'vue'
  
    const newTodo = ref('')
    const todos = reactive([])
//得到todos
    onMounted(async () => {
    try {
        const response = await axios.get('http://your-api-url')
        todos.value = response.data
    } catch (error) {
        console.error(error)
    }
    })
//加入待办事项
    const addTodo = async () => {
        try {
            const response = await axios.get('http://your-api-url', {
            params: {
                id: todos.length // 使用当前待办事项的数量作为id
            }
            })
            console.log(response.data)
            todos.push({ text: newTodo.value, completed: false })
            newTodo.value = ''
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
            const response = await axios.get('http://your-api-url', {
            params: {
                id: index
            }
            })
            console.log(response.data)
            todos.splice(index, 1)
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
  </script>
  <style scoped>
    .loginTitle{
        text-align: center;
        font-size: 26px;
        padding-top:50px;
        margin-bottom: 20px;
    }
    .main{
        background: #fff;
        padding:40px;
        border-radius: 4px; /* 设置边框圆角 */
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 设置阴影 */
        margin:0 auto;
        height: 600px;
        width: 400px;
    }
  </style>