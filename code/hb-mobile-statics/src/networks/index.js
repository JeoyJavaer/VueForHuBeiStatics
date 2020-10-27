"use strict";


import Vue from "vue";
import axios from "axios";

let config = {
  baseURL: 'http://localhost:8090/hbydGame',
  proxy: {
    '/api': {
      target: "http://192.168.1.150:90/api",
      changeOrigin: true,
      pathRewrite: {
        '^/api': ''
      }
    }
  }
};
const _axios = axios.create(config);


/*跨域问题*/
axios.defaults.baseURL = '/api'
axios.defaults.headers.post['Content-Type'] = 'application/json';


_axios.interceptors.request.use(function (config) {
  return config
}, function (error) {
  console.log(error);
  return Promise.reject(error);
});

_axios.interceptors.response.use(function (response) {

  return response.data;
}, function (error) {
  console.log(error);
  return Promise.reject(error);
});


Plugin.install = function (Vue,) {
  Vue.axios = _axios;
  window.axios = _axios;
  Object.defineProperties(Vue.prototype, {
    axios: {
      get() {
        return _axios
      },
    },

    $axios: {
      get() {
        return _axios
      }
    }
  })

};

Vue.use(Plugin);

export default Plugin;

/*http://localhost:8090/hbydGame/role/getRolePermissionMenu 获取菜单栏数据
*
*
*
*
* */