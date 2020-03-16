<template>
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-heading">
        <h2 class="text-center">Registrati</h2>
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
                name="name"
                v-model="employee.name"
                placeholder="Inserisci nome"
              />
            </div>
          </div>
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">
                <span class="glyphicon glyphicon-user"></span>
              </span>
              <input
                type="text"
                class="form-control"
                name="surname"
                v-model="employee.surname"
                placeholder="Inserisci cognome"
              />
            </div>
          </div>
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">
                <span class="glyphicon glyphicon-user"></span>
              </span>
              <input
                type="number"
                class="form-control"
                name="age"
                v-model="employee.age"
                placeholder="Inserisci età"
              />
            </div>
          </div>
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">
                <span class="glyphicon glyphicon-user"></span>
              </span>
              <input
                type="text"
                class="form-control"
                name="username"
                v-model="employee.username"
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
                v-model="employee.password"
                placeholder="Password"
              />
            </div>
          </div>
          <div class="form-group text-center">
            <button
              type="button"
              class="btn btn-success btn-lg"
              v-on:click="registraEmployee()"
            >Registrati</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "registra-employee",
  data() {
    return {
      employee: {
        name: "",
        surname: "",
        age: "",
        username: "",
        password: ""
      },
      employees: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    // Ottengo tutti gli employee per controllare che non inserisca doppioni
    retrieveEmployees() {
      http
        .get("/employees")
        .then(response => {
          this.employees = response.data; // JSON are parsed automatically.
        })
        .catch(e => {
          console.log(e);
        });
    },
    registraEmployee() {
      var data = {
        name: this.employee.name,
        surname: this.employee.surname,
        age: this.employee.age,
        username: this.employee.username,
        password: this.employee.password
      };
      if (
        this.employee.name.length == 0 ||
        this.employee.surname.length == 0 ||
        this.employee.age <= 0 ||
        this.employee.username.length == 0 ||
        this.employee.password.length == 0
      ) {
        window.alert("Riempire tutti i campi!");
      } else {
        //controllo che non esista uno username uguale
        this.employees.forEach(employee => {
          if (employee.username == data.username) {
            window.alert("Username già presente!");
          } else {
            http
              .post("/registraEmployee", data)
              .then(response => {
                this.employee = response.data;
                this.$emit("authenticated", true);
                this.$router.replace("/employees", () => {});
              })
              .catch(e => {
                console.log(e);
              });
          }
        });
      }
      this.submitted = true;
    }
  },
  mounted() {
    this.retrieveEmployees();
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