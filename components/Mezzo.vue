<template>
  <!--<div>
    <div v-if="this.mezzo">
      <h3>Dettaglio mezzo</h3>
      <div class="targa">
        <h4>{{"Targa:"}}</h4>
        <h4>{{this.mezzo.targa}}</h4>
      </div>
      <div class="marca">
        <h4>{{"Marca:"}}</h4>
        <h4>{{this.mezzo.marca}}</h4>
      </div>
      <div>
        <h4>{{"Modello:"}}</h4>
        <h4>{{this.mezzo.modello}}</h4>
      </div>
      <div>
        <h4>{{"Carburante:"}}</h4>
        <h4>{{this.mezzo.tipoAlimentazione.carburante}}</h4>
      </div>
      <button v-on:click="deleteMezzo()" class="btn btn-danger">CANCELLA</button>
      <p></p>
      <button v-on:click="goToPage()" class="btn btn-success">MODIFICA</button>
    </div>
  </div>-->
  <div v-if="this.mezzo">
    <table
      style="position: relative; top: 4em; "
      id="mezzo"
      width="50%"
      border="1"
      align="left"
      bgcolor="lightblue"
      bordercolor="blue"
      bordercolorlight="#00CCFF"
      bordercolordark="#000099"
    >
      <tr style=" bgcolor:blue">
        <td style="color:black" bgcolor="orangered">Targa</td>
        <td style="color:black" bgcolor="orangered">Marca</td>
        <td style="color:black" bgcolor="orangered">Modello</td>
        <td style="color:black" bgcolor="orangered">Carburante</td>
      </tr>
      <tr>
        <td style="color:blue">{{this.mezzo.targa}}</td>
        <td style="color:blue">{{this.mezzo.marca}}</td>
        <td style="color:blue">{{this.mezzo.modello}}</td>
        <td style="color:blue">{{this.mezzo.tipoAlimentazione.carburante}}</td>
      </tr>
    </table>
    <button
      type="button"
      class="btn btn-danger"
      style="position:relative; right: 45em;top: 20em;"
      v-on:click="deleteMezzo()"
    >Delete</button>
    <p></p>
    <button
      v-on:click="goToPage()"
      class="btn btn-success"
      style="position:relative; right: 45em;top: 12em;"
    >MODIFICA</button>
    <div class="employeeDetails">
      <h2 style="color:orangered;;">MEZZO DETAILS</h2>
    </div>
  </div>
</template>
 
<script>
import http from "../http-common";

export default {
  name: "mezzo",
  props: ["mezzo"],
  methods: {
    /* eslint-disable no-console */
    deleteMezzo() {
      if (window.confirm("Confermi l'eliminazione?")) {
        http
          .delete("/mezzi/delete/" + this.mezzo.idMezzo)
          .then(response => {
            console.log(response.data);
            this.$emit("refreshData");
            this.$router.push("/mezzi");
          })
          .catch(e => {
            console.log(e);
          });
      }
    },
    goToPage() {
      this.$router.push({ path: "/mezzi/update/" + this.mezzo.idMezzo });
    }
    /* eslint-enable no-console */
  }
};
</script>





