<template>
  <div id="vue-app">{{ text }}
  <div id="ciao">{{prova}}</div>
      </div>
</template>


<script>
export default {
  data() {
    return {
      text: "Hello World from Vue!",
      prova: "Hello!"
    };
  }
};
</script>

<style>
</style>