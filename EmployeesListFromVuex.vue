<template>
  <v-container class="my-5">
    {{getEvents}}
    <p>{{eventsWithStars}}</p>
     <p>{{this.$store.state.moduleA.employees}}</p>
    <v-row wrap class="mb-n6 ml-6">
      <v-btn small color="primary" @click="increase()">
        <v-icon medium left>event</v-icon>
        <span class="caption text-lowercase">Increase</span>
      </v-btn>
      <v-col md="2" offset-md="1">
        <v-btn small color="primary" @click="sortBy('surname')">
          <v-icon medium left>sort_by_alpha</v-icon>
          <span class="caption text-lowercase">Ordina per cognome</span>
        </v-btn>
      </v-col>
      <v-col md="2">
        <v-btn small color="primary" @click="sortBy('age')">
          <v-icon medium left>event</v-icon>
          <span class="caption text-lowercase">Ordina per età</span>
        </v-btn>
      </v-col>
    </v-row>
    <v-card raised style="background: #F9FBE7" class="mt-8 mx-auto" width="850">
      <v-card-title>
        <h1 class="subheading grey--text">Employees</h1>
        <v-spacer></v-spacer>
        <v-text-field
          v-model.trim="search"
          append-icon="mdi-magnify"
          label="Ricerca puntuale"
          single-line
          hide-detail
        ></v-text-field>
      </v-card-title>
      <v-container>
        <v-row row wrap>
          <v-col md="2">
            <div class="headline font-weight-medium blue--text">
              <h5>Nome</h5>
            </div>
          </v-col>
          <v-col md="2">
            <div class="headline font-weight-medium blue--text">
              <h5>Cognome</h5>
            </div>
          </v-col>
          <v-col md="2">
            <div class="headline font-weight-medium blue--text">Età</div>
          </v-col>
        </v-row>
        <v-col>
          <v-row row wrap class="mt-n6" v-for="employee in employeesList" :key="employee.id">
            <v-col md="2" class="ml-n4">
              <div class="headline font-weight-medium orange--text">{{employee.name}}</div>
            </v-col>
            <v-col md="2" class="ml-2">
              <div class="headline font-weight-medium orange--text">{{employee.surname}}</div>
            </v-col>
            <v-col md="2" class="ml-2">
              <div class="headline font-weight-medium orange--text">{{employee.age}}</div>
            </v-col>
            <v-col md="3">
              <router-link
                :to="{
                            name: 'employee-dettagliVuex',
                            params: { employee: employee, id: employee.id }
                        }"
              >
                <v-btn color="primary" class="mb-5">
                  Dettagli
                  <v-icon dark right>description</v-icon>
                </v-btn>
              </router-link>
            </v-col>
            <v-col md="2" class="ml-2">
              <div v-if="employee.active" class="headline font-weight-medium orange--text">
                <v-chip class="green white--text">Attivo</v-chip>
              </div>
              <div v-else class="headline font-weight-medium orange--text">
                <v-chip class="red white--text">Inattivo</v-chip>
              </div>
            </v-col>
          </v-row>
        </v-col>
      </v-container>
    </v-card>
  </v-container>
</template>

<script>
import { employeeService } from "@/services/employeeService";
//import { mapState } from 'vuex'

export default {
  name: "employees-listFromVuex",
  data() {
    return {
      search: "",
      employees: [],
      loaded: false
    };
  },
  computed:
    /*mapState({
    emp: state => state.moduleA.employees
  }),*/
    {
      employeesList() {
        return this.$store.getters["moduleA/employeesList"];
      },
      getEvents() {
        return this.$store.getters["moduleA/getEvents"];
      },
      eventsWithStars() {
        return this.$store.getters["moduleA/eventsWithStars"];
      },
      filterData() {
        var reg = new RegExp(this.search, "i");
        var data = [];

        for (var i in this.employees) {
          if (
            this.employees[i].name.match(reg) ||
            this.employees[i].surname.match(reg)
          ) {
            data.push(this.employees[i]);
          }
        }
        return data;
      }
    },
  methods: {
    loadPage() {
      window.location.reload();
    },
    increase() {
      this.$store.dispatch("moduleA/increase");
    },
    sortBy(prop) {
      this.employees.sort((a, b) => (a[prop] < b[prop] ? -1 : 1));
    },
    getListEmployees() {
      const response = employeeService.getListEmployees(); //this.$store.state.employees
      response
        .then(result => {
          this.employees = result.data;
          this.$store.dispatch("moduleA/popolaStore", this.employees);
        })
        .catch(() => {});
    }
    //Metodo link visto con Fabio
    /* goToDetails(id) {
      console.log(id);
      var obj = { idEmployee :id };
      this.$router.push({ name: "employee-dettagliVuex", params: { obj } });
    }*/
  },
  mounted() {
    this.getListEmployees();
    /* if (localStorage.getItem('reloaded')) {
        // The page was just reloaded. Clear the value from local storage
        // so that it will reload the next time this page is visited.
        localStorage.removeItem('reloaded');
    } else {
        // Set a flag so that we know not to reload the page twice.
        localStorage.setItem('reloaded', '1');
        location.reload();
    }*/
  }
};
</script>

