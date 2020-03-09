<template>
  <div id="app" class="container-fluid">
    <div class="site-info">
      <h1>Jersey & Vue example</h1>
    </div>
   
    <app-header></app-header>
   
    <br />
    <router-view />
  </div>
</template>
 
<script>
import Header from "./components/Header.vue";
export default {
  components: {
    //Metto il component header, quello comune a tutte le pagine
    "app-header": Header
  },
  name: "app"
};
</script>
 
<style>
/*::-webkit-input-placeholder {
  background-color: lightgreen;
  color: black;
}*/
.site-info {
  background-color: orangered;
  font-style: italic;
  color: rgb(5, 5, 17);
  margin-bottom: 20px;
}
.container-fluid {
  background-color: lightgreen;
  text-align: center;
  height: 100vh;
  width: 100%;
}
.btn-primary {
  margin-right: 5px;
}
</style>

