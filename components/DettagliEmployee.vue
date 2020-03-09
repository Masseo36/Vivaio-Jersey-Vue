<template>
  <div v-if="this.employee">
    <table
      style="position: relative; top: 4em; "
      id="employee"
      width="50%"
      border="1"
      align="left"
      bgcolor="lightblue"
      bordercolor="blue"
      bordercolorlight="#00CCFF"
      bordercolordark="#000099"
    >
      <tr style=" bgcolor:blue">
        <td style="color:black" bgcolor="orangered">Name</td>
        <td style="color:black" bgcolor="orangered">Surname</td>
        <td style="color:black" bgcolor="orangered">Age</td>
      </tr>
      <tr>
        <td style="color:blue">{{this.employee.name}}</td>
        <td style="color:blue">{{this.employee.surname}}</td>
        <td style="color:blue">{{this.employee.age}}</td>
      </tr>
    </table>
    <button
      type="button"
      class="btn btn-danger"
      style="position:relative; right: 45em;top: 20em;"
      v-on:click="deleteEmployee()"
    >CANCELLA</button>
    <p></p>
    <button
      v-on:click="goToPage()"
      class="btn btn-success"
      style="position:relative; right: 45em;top: 12em;"
    >MODIFICA</button>
    <div class="employeeDetails">
      <h2 style="color:orangered;;">DETTAGLI EMPLOYEE</h2>
    </div>
  </div>
</template>
	<script>
import http from "../http-common";

export default {
  name: "DettagliEmployee",
  data() {
    return {
      //Questa riga consente di portare in questa pagina l'id dell'oggetto
      //dalla pagina di provenienza
      id: this.$route.params.id,
      employee: ""
    };
  },
  methods: {
    goToPage() {
      this.$router.push({ path: "/employees/update/" + this.employee.id });
    },
    // Prima di tutto ottengo l'oggetto tramite il suo id
    retrieveEmployeesById() {
      http
        .get("/employee/" + this.id)
        .then(response => {
          this.employees = response.data; // JSON are parsed automatically.
          this.employee = this.employees[0];
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    deleteEmployee() {
      if (window.confirm("Confermi l'eliminazione?")) {
        http
          .delete("/delete/" + this.employee.id)
          .then(response => {
            console.log(response.data);
            this.$emit("refreshData");
            this.$router.push("/");
          })
          .catch(e => {
            console.log(e);
          });
      }
    }
  },
  mounted() {
    this.retrieveEmployeesById();
  }
};
</script>

<style scoped>
.employeeDetails {
  position: absolute;
  content: "";
  bottom: 80px;
  right: 60px;
  height: 370px;
  width: 400px;
  background: #9ed9eb;
  transform: rotate(-25deg);
}
table {
  border-collapse: collapse;
  border-spacing: 50px;
  font-size: 25px;
}
td,
th {
  border: 1px solid #ddd;
  padding: 8px;
}
</style>
