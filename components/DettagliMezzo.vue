<template>
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
    >CANCELLA</button>
    <p></p>
    <button
      v-on:click="goToPage()"
      class="btn btn-success"
      style="position:relative; right: 45em;top: 12em;"
    >MODIFICA</button>
    <div class="mezzoDetails">
      <h2 style="color:orangered;;">DETTAGLI MEZZO</h2>
    </div>
  </div>
</template>
	<script>
import http from "../http-common";

export default {
  name: "DettagliMezzo",
  data() {
    return {
      idMezzo: this.$route.params.idMezzo,
      mezzo: ""
    };
  },
  methods: {
    goToPage() {
      this.$router.push({ path: "/mezzi/update/" + this.mezzo.idMezzo });
    },
    retrieveMezziByIdMezzo() {
      http
        .get("/mezzo/" + this.idMezzo)
        .then(response => {
          this.mezzi = response.data; // JSON are parsed automatically.
          this.mezzo = this.mezzi[0];
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    deleteMezzo() {
      if (window.confirm("Confermi l'eliminazione?")) {
        http
          .delete("/delete/" + this.mezzo.idMezzo)
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
    refresh() {
      window.alert("Modificato con successo!");
    }
  },
  mounted() {
    this.retrieveMezziByIdMezzo();
  }
};
</script>

<style scoped>
.mezzoDetails {
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