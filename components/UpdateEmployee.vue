<template>
  <div class="updateEmployee" id="updateEmployee">
    <div class="form-group">
      <label for="name">
        <h4>Name</h4>
      </label>
      <input
        placeholder="Inserire nome"
        type="text"
        class="form-control"
        id="name"
        required
        v-model="employee.name"
        name="name"
      />
    </div>
    <div class="form-group">
      <label for="surname">
        <h4>Surname</h4>
      </label>
      <input
        placeholder="Inserire cognome"
        type="text"
        class="form-control"
        id="surname"
        required
        v-model="employee.surname"
        name="surname"
      />
    </div>

    <div class="form-group">
      <label for="age">
        <h4>Age</h4>
      </label>
      <input
        type="number"
        class="form-control"
        id="age"
        required
        v-model="employee.age"
        name="age"
        placeholder="Inserire età"
      />
    </div>
    <button v-on:click="updateEmployee()" class="btn btn-success">CONFERMA</button>
    <div class="cuboDecorativo">
      <h2 style="color:orangered;">MODIFICA EMPLOYEE</h2>
    </div>
  </div>
</template>
	<script>
import http from "../http-common";

export default {
  name: "update-employee",
  data() {
    return {
      //Questa riga consente di portare in questa pagina l'id dell'oggetto
      //dalla pagina di provenienza
      id: this.$route.params.id,
      employee: ""
    };
  },
  methods: {
    // Prima di tutto ottengo l'oggetto tramite il suo id
    retrieveEmployeesById() {
      http
        .get("/employees/update/" + this.id)
        .then(response => {
          this.employees = response.data; // JSON are parsed automatically.
          this.employee = this.employees[0];
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // Aggiorno l'oggetto
    updateEmployee() {
      var data = {
        name: this.employee.name,
        surname: this.employee.surname,
        age: this.employee.age
      };
      if (
        this.employee.name.length <= 0 ||
        this.employee.surname.length <= 0 ||
        this.employee.age <= 0
      ) {
        this.$alert("Riempire tutti i campi", "Errore", "error");
      } else {
        this.$confirm(
          "Confermi la modifica?",
          "Modifica utente",
          "question"
        ).then(() => {
          http
            .put("/employees/update/" + this.employee.id, data)
            .then(response => {
              console.log(response.data);
              this.refresh();
              //Terminato il metodo, andrò alla seguente pagina
              this.$router.push("/employees");
            })
            .catch(e => {
              console.log(e);
            });
        });
      }
    },
    refresh() {
      this.$alert("Modifica utente", "Modifica avvenuta!", "success");
    }
  },
  mounted() {
    this.retrieveEmployeesById();
  }
};
</script>

<style>
::-webkit-input-placeholder {
  background-color: lightcyan;
  color: black;
  font-size: 18px;
  font-family: cursive;
}
.updateEmployee {
  max-width: 300px;
  margin: auto;
  color: orangered;
}
.cuboDecorativo {
  position: absolute;
  content: "";
  bottom: 100px;
  right: 60px;
  height: 370px;
  width: 400px;
  background: #9ed9eb;
  transform: rotate(-25deg);
}
</style>