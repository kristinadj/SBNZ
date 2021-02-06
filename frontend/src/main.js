import Vue from 'vue'
import Vuelidate from 'vuelidate';

import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import { router } from './_helpers';
import App from './App.vue'

Vue.use(BootstrapVue)
Vue.use(Vuelidate);

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router
}).$mount('#app')
