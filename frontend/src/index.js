import Vue from 'vue';
import Vuelidate from 'vuelidate';

import { router } from './_helpers';
import App from './app/App';

Vue.use(Vuelidate);

new Vue({
    el: '#app',
    router,
    render: h => h(App)
});