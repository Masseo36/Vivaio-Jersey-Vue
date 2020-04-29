<template>
  <v-container>
    <v-card
      v-model="employeeById"
      class="mx-auto mt-12"
      raised
      style="background: #F9FBE7 "
      width="750px"
    >
      <v-card-title>
        <h1 class="subheading grey--text">Modifica employee</h1>
      </v-card-title>
      <v-col>
        <v-form ref="form" class="md-5">
          <v-text-field
            style
            v-model="employee.name"
            label="Nome"
            required
            id="styled-input-update"
            class="styled-input-update"
          ></v-text-field>
          <v-text-field
            v-model="employee.surname"
            label="Cognome"
            required
            id="styled-input-update"
            class="styled-input-update"
          ></v-text-field>
          <v-text-field
            v-model="employee.age"
            label="Età"
            required
            id="styled-input-update"
            class="styled-input-update"
          ></v-text-field>
          <v-btn class="ma-2" color="success" dark @click="updateEmployee(employeesToUpdate)">
            Modifica
            <v-icon dark right>done</v-icon>
          </v-btn>
          <v-btn class="ma-2" color="error" dark @click="goBack">
            Indietro
            <v-icon dark right>undo</v-icon>
          </v-btn>
        </v-form>
      </v-col>
    </v-card>
  </v-container>
</template>

	<script>
//import http from "../http-common";
import { employeeService } from "@/services/employeeService";
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
  computed: {
    employeeById: {
      get: function() {
        var array = [];
        this.$store.state.moduleA.employees.forEach(employee => {
          if (employee.id == this.id) array.push(employee);
          this.employee = array[0];
        });
        return this.employee;
      },
      set: function() {
        var array = [];
        this.$store.state.moduleA.employees.forEach(employee => {
          if (employee.id == this.id) array.push(employee);
          this.employee = array[0];
        });
        return this.employee;
      }
    },
    employees() {
      return this.$store.state.moduleA.employees;
    },
    employeesToUpdate() {
      return this.$store.state.moduleA.employees;
    }
  },
  methods: {
    // Aggiorno l'oggetto
    updateEmployee(employeesToUpdate) {
      var data = {
        id: this.id,
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
        this.$confirm("Confermi la modifica?", "Modifica utente", "question")
          .then(() => {
            const response = employeeService.getUpdateEmployee(this.id, data);
            response.then(() => {
              const index = employeesToUpdate.indexOf(this.employeeById);
              employeesToUpdate.splice(index, 1, data);
              this.$store.dispatch("moduleA/popolaStore", employeesToUpdate);
              this.$router.push("/employeesListFromVuex");
            });
          })
          .catch(() => {});
      }
    },
    refresh() {
      this.$alert("Modifica utente", "Modifica avvenuta!", "success");
    },
    goBack() {
      this.$router.push({
        path: "/employees/dettagliEmployeeVuex/" + this.employee.id
      });
    }
  }
};
</script>

<style>
#styled-input-update {
  height: 40px;
  font-size: 20pt;
  color: orangered;
}
.styled-input-update label[for] {
  height: 30px;
  font-size: 15pt;
  color: blue;
}
</style>