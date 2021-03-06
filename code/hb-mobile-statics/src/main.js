import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from "./store/store";
import './plugins/element.js'
import './networks/index'
import './icons'

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
