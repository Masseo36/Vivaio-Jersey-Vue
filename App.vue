<template>
  <v-app style="background: #CFD8DC">
    <div v-if="this.$session.exists()">
      <Header />
    </div>
    <v-content class="ml-4">
      <router-view></router-view>
    </v-content>
  </v-app>
</template>
 
<script>
import Header from "./components/Header.vue";

export default {
  components: {
    //Metto il component header, quello comune a tutte le pagine
    Header
  },
  name: "App",
  data() {
    return {};
  },
  mounted() {
    if (!this.$session.exists()) {
      this.$router.replace("/login", () => {});
    }
  }
};
</script>
 
<style>
</style>