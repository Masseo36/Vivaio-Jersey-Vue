<template>
  <v-card v-if="this.mezzo" raised style="background: #F9FBE7" class="mt-12 mx-auto" width="1000" height="250">
    <v-card-title>
      <h1 class="subheading grey--text">Dettagli mezzo</h1>
    </v-card-title>
    <v-container>
      <v-row row wrap >
        <v-col md="2">
          <div class="caption blue--text">
            <h5>Targa</h5>
          </div>
        </v-col>
         <v-col md="2">
          <div class="caption blue--text">
            <h5>Marca</h5>
          </div>
        </v-col>
        <v-col md="2">
          <div class="caption blue--text">
            <h5>Modello</h5>
          </div>
        </v-col>
        <v-col md="2">
          <div class="caption blue--text">
            <h5>Alimentazione</h5>
          </div>
        </v-col>
      </v-row>
      <v-row class="mt-n5">
       <v-col md="2">
          <div class="caption orange--text">
            <h4>{{mezzo.targa}}</h4>
          </div>
        </v-col>
        <v-col md="2">
          <div class="caption orange--text">
            <h4>{{mezzo.marca}}</h4>
          </div>
        </v-col>
       <v-col md="2">
          <div class="caption orange--text">
            <h4>{{mezzo.modello}}</h4>
          </div>
        </v-col>
        <v-col md="2">
          <div class="caption orange--text">
            <h4>{{mezzo.tipoAlimentazione.carburante}}</h4>
          </div>
        </v-col>
        <v-col md="2">
          <v-btn color="primary" dark @click="goToPage">
            Modifica
            <v-icon dark right>mdi-pen</v-icon>
          </v-btn>
        </v-col>
        <v-col>
          <v-btn color="red" dark @click="deleteMezzo">
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
      this.$alert("Modifica mezzo", "Modifica avvenuta!", "success");
    },
    goBack() {
      this.$router.push({ path: "/mezzi" });
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