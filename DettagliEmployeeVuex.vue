<template>
  <v-container>
    <v-card
      raised
      style="background: lime lighten-5"
      class="mt-12 mx-auto"
      width="850"
      height="250"
      v-model="employeeById"
    >
      <v-card-title>
        <h1 class="subheading grey--text">Dettagli employee</h1>
      </v-card-title>
      <v-container>
        <v-row row wrap>
          <v-col md="2">
            <div class="headline blue--text">Nome</div>
          </v-col>
          <v-col md="2">
            <div class="headline blue--text">Cognome</div>
          </v-col>
          <v-col md="2">
            <div class="headline blue--text">Età</div>
          </v-col>
        </v-row>
        <v-row class="mt-n5">
          <v-col md="2">
            <div class="headline orange--text text--darken-1">{{employee.name}}</div>
          </v-col>
          <v-col md="2">
            <div class="headline orange--text text--darken-1">{{employee.surname}}</div>
          </v-col>
          <v-col md="2">
            <div class="headline orange--text text--darken-1">{{employee.age}}</div>
          </v-col>
          <v-col md="2">
            <v-btn color="primary" dark @click="goToPage">
              Modifica
              <v-icon dark right>mdi-pen</v-icon>
            </v-btn>
          </v-col>
          <v-col>
            <v-btn color="red" dark @click="deleteEmployee(employeesForDelete)">
              Cancella
              <v-icon dark right>delete</v-icon>
            </v-btn>
          </v-col>
          <v-col md="2" class="ml-n2 mt-n5">
            <v-btn small class="ma-2" color="blue" dark @click="goBack">
              Indietro
              <v-icon dark right>undo</v-icon>
            </v-btn>
          </v-col>
        </v-row>
      </v-container>
    </v-card>

    <v-row row wrap class="ml-3">
      <v-col md1 offset-md="1">
        <v-card
          text
          class="text-xs-center ma-3 lime lighten-5"
          elevation="24"
          width="300"
          height="300"
        >
          <v-responsive class="pt-4">
            <v-avatar size="100">
              <img src="https://avatars0.githubusercontent.com/u/9064066?v=4&s=460" />
            </v-avatar>
          </v-responsive>
          <v-card-text>
            <div
              class="headline orange--text text--darken-1"
            >{{ employee.name}} {{ employee.surname }}</div>
            <v-col md="6" offset-md="8">
              <div v-if="employee.active" class="headline font-weight-medium orange--text">
                <v-chip class="green white--text">Attivo</v-chip>
              </div>
              <div v-else class="headline font-weight-medium orange--text">
                <v-chip class="red white--text">Inattivo</v-chip>
              </div>
            </v-col>
            <div class="mt-n11 grey--text text--darken-2 title font-italic">{{ employee.role }}</div>
          </v-card-text>

          <v-card-actions>
            <v-btn text color="grey" @click="curriculumActive">
              <v-icon small left>message</v-icon>
              <span class>Curriculum vitae</span>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
      <v-col md="6">
        <v-card
          v-if="curriculum == true"
          text
          class="lime lighten-5 mt-3 ml-n4"
          elevation="24"
          width="600"
          height="300"
        >
          <v-row>
            <v-col offset-md="9" class="mt-n2">
              <v-btn text color="error" @click="curriculumDeactive">
                <v-icon small left>highlight_off</v-icon>
                <span class>Chiudi</span>
              </v-btn>
            </v-col>
            <span md="5" class="mt-n8">
              <v-card-text>
                CV di Massimo Vitale
                Bla bla bla
                Bla bla bla
                Bla bla bla
                Bla bla bla
                Bla bla bla
                Bla bla bla
                Bla bla bla
                Bla bla bla
              </v-card-text>
            </span>
          </v-row>
        </v-card>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
  </v-container>
</template>

	<script>
//import http from "../http-common";
import { employeeService } from "@/services/employeeService";
export default {
  name: "DettagliEmployee",
  data() {
    return {
      //Questa riga consente di portare in questa pagina l'id dell'oggetto
      //dalla pagina di provenienza
      //id: this.$route.params.obj.idEmployee,
      id: this.$route.params.id,
      employee: "",
      curriculum: false
    };
  },
  computed: {
    employeeById() {
      var array = [];
      this.$store.state.moduleA.employees.forEach(employee => {
        if (employee.id == this.id) array.push(employee);
        this.employee = array[0];
      });
      return this.employee;
    },
    employeesForDelete() {
      return this.$store.state.moduleA.employees;
    }
  },
  methods: {
    deleteEmployee(employeesForDelete) {
      this.$confirm(
        "Confermi la cancellazione?",
        "Cancellazione utente",
        "question"
      )
        .then(() => {
          //Creando questo payload posso mandare dei dati sotto forma di oggetto allo store
          var payload = {
            arrayDaRidurre: employeesForDelete,
            oggettoAttuale: this.employeeById
          };
          const response = employeeService.getDeleteEmployee(this.id);
          response.then(() => {
            this.$store.dispatch("moduleA/deleteEmployee", payload);
            this.$router.push("/employeesListFromVuex");
          });
          //Soluzione con logica nel component anzichè nello store
          /* const response = employeeService.getDeleteEmployee(this.id);
          response.then(() => {
            const index = employeesForDelete.indexOf(this.employee);
            employeesForDelete.splice(index, 1);
            this.$store.dispatch("loadEmployees", employeesForDelete);
            this.$router.push("/employeesListFromVuex");
          });*/
        })
        .catch(() => {});
    },
    goToPage() {
      this.$router.push({
        path: "/employees/updateEmployeeVuex/" + this.employee.id
      });
    },
    goBack() {
      this.$router.push({ path: "/employeesListFromVuex" });
    },
    curriculumActive() {
      this.curriculum = true;
    },
    curriculumDeactive() {
      this.curriculum = false;
    }
  }
};
</script>

<style scoped>
</style>
