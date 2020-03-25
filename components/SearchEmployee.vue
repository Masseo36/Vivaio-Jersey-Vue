<template> 
  <div class="searchform-employee">
    <h4>Trova per nome</h4>
    <div class="form-group">
      <input
        type="text"
        class="form-control"
        id="name"
        required
        v-model="name"
        name="name"
        placeholder="Inserire nome"
      />
    </div>

    <h4>Trova per cognome</h4>
    <div class="form-group">
      <input
        type="text"
        class="form-control"
        id="surname"
        required
        v-model="surname"
        name="surname"
        placeholder="Inserire cognome"
      />
    </div>
    <h4>Trova per età</h4>
    <div class="form-group">
      <input
        type="number"
        class="form-control"
        id="age"
        required
        v-model="age"
        name="age"
        placeholder="Inserire età"
      />
    </div>
    <div class="btn-group">
      <!-- Ricerca per età -->
      <div v-if="age > 0 && name.length == 0 && surname.length == 0">
        <button v-on:click="searchEmployees" class="btn btn-success">Cerca</button>
      </div>
      <!-- Ricerca per nome -->
      <div v-else-if="age == 0 && name.length > 0 && surname == 0">
        <button v-on:click="searchEmployeesByName" class="btn btn-success">Cerca</button>
      </div>
      <!-- Ricerca per cognome -->
      <div v-else-if="age == 0 && name.length == 0 && surname.length > 0 ">
        <button v-on:click="searchEmployeesBySurname" class="btn btn-success">Cerca</button>
      </div>
      <!-- Ricerca per età e nome -->
      <div v-else-if="age > 0 && name.length > 0 && surname.length == 0">
        <button v-on:click="searchEmployeesByAgeAndName" class="btn btn-success">Cerca</button>
      </div>
      <!-- Ricerca per età e cognome-->
      <div v-else-if="age > 0 && name.length == 0 && surname.length > 0">
        <button v-on:click="searchEmployeesByAgeAndSurname" class="btn btn-success">Cerca</button>
      </div>
      <!-- Ricerca per nome e cognome-->
      <div v-else-if="age == 0 && name.length > 0 && surname.length > 0">
        <button v-on:click="searchEmployeesByNameAndSurname" class="btn btn-success">Cerca</button>
      </div>
      <!-- Ricerca completa-->
      <div v-else-if="age > 0 && name.length > 0 && surname.length > 0">
        <button v-on:click="searchEmployeesComplete" class="btn btn-success">Cerca</button>
      </div>
    </div>

    <ul class="search-result-employee">
      <li v-for="(employee, index) in foundedEmployees" :key="index">
        <router-link
          :to="{
                            name: 'employee-dettagli',
                            params: { employee: employee, id: employee.id }
                        }"
        ><h4>{{employee.name}} {{employee.surname}} {{(employee.age)}}</h4></router-link>
      </li>
    </ul>
    <div class="cuboCercaEmployee"><h2 style="color:orangered;">CERCA EMPLOYEE</h2></div>
  </div>
</template>
 
<script>
import http from "../http-common";

export default {
  name: "search-employee",
  data() {
    return {
      age: "",
      name: "",
      surname: "",
      foundedEmployees: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    searchEmployees() {
      http
        .post("/ricercaEmployee/" + this.age)
        .then(response => {
          this.foundedEmployees = response.data; // JSON are parsed automatically.
          this.refresh();
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    searchEmployeesByName() {
      http
        .post("/ricercaEmployee/name/" + this.name)
        .then(response => {
          this.foundedEmployees = response.data; // JSON are parsed automatically.
          this.refresh();
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    searchEmployeesBySurname() {
      http
        .post("/ricercaEmployee/surname/" + this.surname)
        .then(response => {
          this.foundedEmployees = response.data; // JSON are parsed automatically.
          this.refresh();
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    searchEmployeesByAgeAndName() {
      http
        .post("/ricercaEmployee/" + this.age + "ricercaEmployee/" + this.name)
        .then(response => {
          this.foundedEmployees = response.data; // JSON are parsed automatically.
          this.refresh();
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    searchEmployeesByAgeAndSurname() {
      http
        .post(
          "/ricercaEmployee/age+surname/" +
            this.age +
            "ricercaEmployee/age+surname/" +
            this.surname
        )
        .then(response => {
          this.foundedEmployees = response.data; // JSON are parsed automatically.
          this.refresh();
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    searchEmployeesByNameAndSurname() {
      http
        .post(
          "/ricercaEmployee/name+surname/" +
            this.name +
            "ricercaEmployee/name+surname/" +
            this.surname
        )
        .then(response => {
          this.foundedEmployees = response.data; // JSON are parsed automatically.
          this.refresh();
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    searchEmployeesComplete() {
      http
        .post(
          "/ricercaEmployee/ricercaCompleta/" +
            this.age +
            "ricercaEmployee/ricercaCompleta/" +
            this.name +
            "ricercaEmployee/ricercaCompleta/" +
            this.surname
        )
        .then(response => {
          this.foundedEmployees = response.data; // JSON are parsed automatically.
          this.refresh();
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refresh() {
      this.age = "";
      this.name = "";
      this.surname = "";
      if (this.foundedEmployees.length == 0)
       this.$alert("Nessun employee trovato!", "Attenzione", 'warning')
    }
    /* eslint-enable no-console */
  }
};
</script>
 
<style scoped>
::-webkit-input-placeholder {
  background-color: lightcyan;
  color: black;
  font-size: 18px;
  font-family: cursive;
}
.searchform-employee {
  max-width: 300px;
  margin: auto;
  color: orangered;
}
.search-result-employee {
  margin-top: 10px;
  text-align: left;
}
.cuboCercaEmployee {
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