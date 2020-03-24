<template>
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-heading">
        <h2 class="text-center">Login</h2>
      </div>
      <hr />
      <div class="modal-body">
        <form action role="form">
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">
                <span class="glyphicon glyphicon-user"></span>
              </span>
              <input
                type="text"
                class="form-control"
                name="username"
                v-model="username"
                placeholder="Username"
              />
            </div>
          </div>
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">
                <span class="glyphicon glyphicon-lock"></span>
              </span>
              <input
                type="password"
                class="form-control"
                name="password"
                v-model="password"
                placeholder="Password"
              />
            </div>
          </div>

          <div class="form-group text-center">
            <button type="button" class="btn btn-success btn-lg" v-on:click="login()">Login</button>
            <router-link to="recuperoPassword" class="btn btn-link">recupera Password</router-link>
            <router-link to="registraEmployee" class="btn btn-link">Non hai un account? Registrati!</router-link>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
<script>
import http from "../http-common";
export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: ""
    };
  },
  methods: {
    login() {
      if (this.username != "" && this.password != "") {
        http
          .post("/login/" + this.username + "login/" + this.password)
          .then(response => {
            this.employees = response.data; // JSON are parsed automatically.
            this.$session.start()
            console.log(this.$session.id())
            if (this.employees.length > 0 && this.employees[0].active == 1) {
              this.$emit("authenticated", true);
              this.$router.replace("/employees", () => {});
            } else if (
              this.employees.length > 0 &&
              this.employees[0].active == 0
            ) {
              this.$router.replace("/primoAccesso", () => {});
            } else if (this.employees.length == 0)
              window.alert("Username e/o password errati!");
          });
      } else window.alert("Riempire tutti i campi!");
    }
  }
};
</script>

<style scoped>
.modal-content {
  top: 75px;
  background-color: rgba(9, 196, 196, 0.849);
}
.btn-link {
  color: white;
}
.modal-heading h2 {
  color: #ffffff;
}
</style>