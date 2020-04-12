<template>
  <v-container>
    <v-card class="mx-auto mt-12" raised style="background: #F9FBE7 " width="750px">
      <v-card-title>
        <h1 class="subheading grey--text">Cerca employee</h1>
      </v-card-title>
      <v-col>
        <v-form ref="form" class="md-5">
          <v-text-field
            style
            v-model="name"
            label="Nome"
            required
            id="styled-input"
            class="styled-input"
          ></v-text-field>
          <v-text-field
            v-model="surname"
            label="Cognome"
            required
            id="styled-input"
            class="styled-input"
          ></v-text-field>
          <v-text-field v-model="age" label="Età" required id="styled-input" class="styled-input"></v-text-field>
          <!-- Ricerca per età -->
          <v-btn
            v-if="age > 0 && name.length == 0 && surname.length == 0"
            class="ma-2"
            color="primary"
            dark
            @click="searchEmployees"
          >
            <v-icon small left>search</v-icon>Cerca
          </v-btn>
          <!-- Ricerca per nome -->
          <v-btn
            v-else-if="age == 0 && name.length > 0 && surname == 0"
            class="ma-2"
            color="primary"
            dark
            @click="searchEmployeesByName"
          >
            <v-icon small left>search</v-icon>Cerca
          </v-btn>
          <!-- Ricerca per cognome -->
          <v-btn
            v-else-if="age == 0 && name.length == 0 && surname.length > 0 "
            class="ma-2"
            color="primary"
            dark
            @click="searchEmployeesBySurname"
          >
            <v-icon small left>search</v-icon>Cerca
          </v-btn>
          <!-- Ricerca per età e nome -->
          <v-btn
            v-else-if="age > 0 && name.length > 0 && surname.length == 0"
            class="ma-2"
            color="primary"
            dark
            @click="searchEmployeesByAgeAndName"
          >
            <v-icon small left>search</v-icon>Cerca
          </v-btn>
          <!-- Ricerca per età e cognome -->
          <v-btn
            v-else-if="age > 0 && name.length == 0 && surname.length > 0"
            class="ma-2"
            color="primary"
            dark
            @click="searchEmployeesByAgeAndSurname"
          >
            <v-icon small left>search</v-icon>Cerca
          </v-btn>
          <!-- Ricerca per nome e cognome -->
          <v-btn
            v-else-if="age == 0 && name.length > 0 && surname.length > 0"
            class="ma-2"
            color="primary"
            dark
            @click="searchEmployeesByNameAndSurname"
          >
            <v-icon small left>search</v-icon>Cerca
          </v-btn>
          <!-- Ricerca completa -->
          <v-btn
            v-else-if="age > 0 && name.length > 0 && surname.length > 0"
            class="ma-2"
            color="primary"
            dark
            @click="searchEmployeesComplete"
          >
            <v-icon small left>search</v-icon>Cerca
          </v-btn>
        </v-form>
      </v-col>
    </v-card>
    <v-card class="mx-auto mt-5" width="750">
      <v-data-table
        :headers="headers"
        :items="foundedEmployees"
        raised
        style="background: #F9FBE7;"
      >
    <template v-slot:item.name="{ item }" >
    <div style="font-size:20pt;  color:orangered;"  @click="goToDetails(item.id)">{{item.name}} </div>
    </template>
     <template v-slot:item.surname="{ item }">
    <div style="font-size:20pt;  color:orangered;"  @click="goToDetails(item.id)">{{item.surname}}</div>
    </template>
      </v-data-table>
    </v-card>
  </v-container>
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
      foundedEmployees: [],
      headers: [
        { text: "Nome", value: "name", class: "indigo--text title pr-12"},
        { text: "Cognome", value: "surname", class: "indigo--text title"}
        
      ]
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
          console.log(this.foundedEmployees);
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
        this.$alert("Nessun employee trovato!", "Attenzione", "warning");
    },
    goToDetails(id) {
       this.$router.push({ path: "/employees/dettagli/" + id });
    }
    /* eslint-enable no-console */
  }
};
</script>

<style>
#styled-input {
  height: 40px;
  font-size: 20pt;
  color: orangered;
}
.styled-input label[for] {
  height: 30px;
  font-size: 15pt;
  color: blue;
}
</style>