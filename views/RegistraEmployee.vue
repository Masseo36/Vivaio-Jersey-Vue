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
                placeholder="Inserisci e-mail"
              />
            </div>
          </div>
          <!--<div class="form-group">
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
          </div>-->
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
        username: ""
        // password: ""
      },
      employees: [],
      isPresent: false
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
          //console.log(response.data);
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
        // password: this.employee.password,
        active: this.employee.active
      };
      //condizioni per inserire valori adatti
      if (
        this.employee.name.length == 0 ||
        this.employee.surname.length == 0 ||
        this.employee.age <= 0 ||
        this.employee.username.length ==
          0 /*||
        this.employee.password.length == 0*/
      ) {
       this.$alert("Riempire tutti i campi", "Errore", 'error')
      } else if (!this.employee.username.includes("@")) {
       this.$alert("Inserire un indirizzo e-mail corretto!", "Errore", 'error')
      } else {
        //controllo che non esista uno username uguale e setto la variabile isPresent
        this.employees.forEach(employee => {
          if (employee.username == data.username) this.isPresent = true;
        });
        if (this.isPresent == true) {
          this.$alert("Username già presente!", "Errore", 'error')
          //se isPresent è true do il messaggio di errore e risetto variabile a false
          this.isPresent = false;
        } else {
          //Invio mail al nuovo Employee registrato
          // this.sendEmail();
          http
            .post("/registraEmployee/", data)
            .then(response => {
              this.employee = response.data;
              //this.$emit("authenticated", true);
              this.$alert(
                "Abbiamo inviato una e-mail alla tua casella di posta",
                "Registrazione avvenuta!",
                "success"
              );
              this.$router.replace("/primoAccesso", () => {});
            })
            .catch(e => {
              console.log(e);
            });
        }
      }
      //this.submitted = true;
    }
    /* sendEmail() {
      http.post(
        "/registraEmployee/" +
          this.employee.username +
          "registraEmployee/" +
          this.employee.password
      );
    }*/
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