import Vue from 'vue'
import VueRouter from 'vue-router'

const Login = () => import("@/views/Login");
const Main = () => import("@/views/home/Main");


Vue.use(VueRouter);

const routes = [
  {
    path: '',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/main',
    component: Main
  },

]

const router = new VueRouter({
  mode: 'history',
  routes
});

export default router
