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
    >Delete</button>
    <p></p>
    <button
      v-on:click="goToPage()"
      class="btn btn-success"
      style="position:relative; right: 45em;top: 12em;"
    >MODIFICA</button>
    <div class="employeeDetails">
      <h2 style="color:orangered;;">EMPLOYEE DETAILS</h2>
    </div>
  </div>
</template>
 
<script>
import http from "../http-common";

export default {
  name: "employee",
  props: ["employee"],
  methods: {
    /* eslint-disable no-console */
    goToPage() {
      this.$router.push({ path: "/employees/update/" + this.employee.id });
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
    /* eslint-enable no-console */
  }
};
</script>

<style scoped>
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