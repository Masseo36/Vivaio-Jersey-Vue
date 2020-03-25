<template>
  <div class="primoAccesso">
    <h1>Benvenuto!</h1>
    <h2>Controlla la tua casella di posta ed inserisci i dati nel form</h2>
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-heading">
          <h2 class="text-center">Primo Accesso</h2>
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
              <button
                type="button"
                class="btn btn-success btn-lg"
                v-on:click="primoAccesso()"
              >Conferma</button>
            </div>
          </form>
        </div>
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
    primoAccesso() {
      if (this.username != "" && this.password != "") {
        console.log(this.username);
        http
          .post("/login/" + this.username + "login/" + this.password)
          .then(response => {
            this.employees = response.data; // JSON are parsed automatically.
            if (this.employees.length > 0) {
              this.$router.push({
                path: "/CambiaPasswordIniziale/" + this.employees[0].id
              });
            } else {
              this.$alert(
                "Username e/o password errati, controlla i dati che ti abbiamo inviato",
                "Errore",
                "error"
              );
            }
          });
      } else {
        this.$alert("Riempire tutti i campi", "Errore", "error");
      }
    }
  }
};
</script>

<style scoped>
.primoAccesso {
  color: orangered;
}
.modal-content {
  top: 10px;
  background-color: rgba(9, 196, 196, 0.849);
}
.btn-link {
  color: white;
}
.modal-heading h2 {
  color: #ffffff;
}
</style>
