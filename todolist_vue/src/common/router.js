import { createRouter, createWebHistory } from 'vue-router';

let routes = [
    // Define your routes here
    // Example:
    // {
    //   path: '/',
    //   name: 'Home',
    //   component: Home
    // }
    {
        path: '/',
        name: 'Home',
        component: () => import('@/views/Home.vue'),
    },
    {
        path:'/test',
        name: 'Test',
        component: () => import('@/views/Test.vue'),
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export { router, routes };
