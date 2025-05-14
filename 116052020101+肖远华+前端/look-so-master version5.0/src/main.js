// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/index'
import Fav from './views/favorite/fav'
import '@/style/font.less'
import '@/style/reset.less'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import 'echarts'
import ECharts from 'vue-echarts'

Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.use(ElementUI)
Vue.component('ECharts', ECharts)
/* eslint-disable no-new */
new Vue({
  el: '#fav',
  router,
  store,
  components: { Fav },
  template: '<Fav/>'
})
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
