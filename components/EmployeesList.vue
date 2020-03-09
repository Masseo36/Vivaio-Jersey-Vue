<template>
  <div class="employees-list">
    <div class="cuboDecorativo"></div>
    <h4 style="position: relative; top: 4em; right: 25em; color: orangered">Lista employees</h4>
    <div
      v-for="(employee, index) in employees"
      :key="index"
      style="position: relative; top: 8em; right: 38em;"
    >
      <router-link
        :to="{
                            name: 'employee-dettagli',
                            params: { employee: employee, id: employee.id }
                        }"
      >
        <h4>{{employee.name}} {{employee.surname}}</h4>
      </router-link>
    </div>
    <div class="col-md-6">
      <router-view @refreshData="refreshList"></router-view>
    </div>
    <p></p>
    <div class="ricercaCognome">
      <h4
        style="position:absolute; top: 11em; right: 25em; color: orangered"
      >Employees: ricerca puntuale per cognome</h4>
      <input
        type="text"
        v-model="surnameEmployee"
        placeholder="Inserire cognome"
        style="position:absolute; top: 20em; right: 54em;"
      />
      <div
        v-for="(employee, index) in employees"
        :key="index"
        style="position:relative; top: 6em; right: 12em"
      >
        <router-link
          :to="{
                            name: 'employee-dettagli',
                            params: { employee: employee, id: employee.id }
                        }"
        >
          <div v-if=" (employee.surname).toUpperCase() == surnameEmployee.toUpperCase()">
            <h4>{{employee.name}} {{employee.surname}}</h4>
          </div>
        </router-link>
      </div>
    </div>
    <div class="fotoEmployee" style="position:absolute; top: 18em; right: 8em">
      <p>
        <img
          src="https://cdn.pixabay.com/photo/2016/03/19/16/50/checklist-1266989_1280.png "
          width="400"
          height="400"
        />
      </p>
    </div>
  </div>
</template>
 
<script>
import http from "../http-common";

export default {
  name: "employees-list",
  data() {
    return {
      employees: [],
      surnameEmployee: "",
      mezzi: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    retrieveEmployees() {
      http
        .get("/employees")
        .then(response => {
          this.employees = response.data; // JSON are parsed automatically.
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.retrieveEmployees();
    }
    /* eslint-enable no-console */
  },
  filters: {
    uppercase: function(v) {
      return v.toUpperCase();
    }
  },
  mounted() {
    this.retrieveEmployees();
  }
};
</script>
 
<style scoped>
.cuboDecorativo {
  position: absolute;
  content: "";
  bottom: -50px;
  left: -100px;
  height: 250px;
  width: 400px;
  background: #f8b7d8;
  transform: rotate(25deg);

  position: absolute;
  content: "";
  bottom: 200px;
  right: -100px;
  height: 270px;
  width: 400px;
  background: #9ed9eb;
  transform: rotate(-25deg);
}

::-webkit-input-placeholder {
  background-color: lightcyan;
  color: black;
  font-size: 18px;
  font-family: cursive;
}
.employee-list {
  text-align: left;
  max-width: 450px;
  margin: auto;
}
</style>