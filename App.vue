<template>
  <div id="app" class="container-fluid">
    <div class="site-info">
      <h1>Jersey & Vue example</h1>
    </div>
    <div v-if="this.authenticated">
      <app-header></app-header>
    </div>
    <div id="nav">
      <router-link v-if="authenticated" to="/login" v-on:click.native="logout()" replace>Logout</router-link>
    </div>
    <router-view @authenticated="setAuthenticated" />
  </div>
</template>
 
<script>
import Header from "./components/Header.vue";
export default {
  components: {
    //Metto il component header, quello comune a tutte le pagine
    "app-header": Header
  },
  name: "app",
  data() {
    return {
      authenticated: false,
      mockAccount: {
        username: "nraboy",
        password: "password"
      }
    };
  },
  mounted() {
    if (!this.authenticated) {
      this.$router.replace("/login", () => {});
    }
  },
  methods: {
    setAuthenticated(status) {
      this.authenticated = status;
    },
    logout() {
      this.authenticated = false;
    }
  }
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