import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import { router } from './common/router'
import { createPinia } from 'pinia'
import axios from 'axios'
//axios访问的后端的前缀路径
axios.defaults.baseURL = 'http://localhost:9696'
const app = createApp(App)
app.use(ElementPlus)
app.use(router)
app.use(createPinia())
app.mount('#app')