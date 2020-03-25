import Vue from "vue";
import App from "./App.vue";
import router from './router';
import VueSession from 'vue-session'
import VueSimpleAlert from "vue-simple-alert";
import 'bootstrap/dist/css/bootstrap.min.css';


Vue.config.productionTip = false;
Vue.use(VueSession);
Vue.use(VueSimpleAlert);

new Vue({
  router, // inject the router to make whole app router-aware
  render: h => h(App)
}).$mount("#app");