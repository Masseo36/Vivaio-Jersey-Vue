<template>
  <v-container>
    <v-card class="mx-auto mt-12" raised style="background: #F9FBE7 " width="750px">
      <v-card-title>
        <h1 class="subheading grey--text">Modifica mezzo</h1>
      </v-card-title>
      <v-col>
        <v-form ref="form" class="md-5">
          <v-text-field
            style
            v-model="mezzo.targa"
            label="Targa"
            required
            id="styled-input-mezzi"
            class="styled-input-mezzi"
          ></v-text-field>
          <v-text-field
            v-model="mezzo.marca"
            label="Marca"
            required
            id="styled-input-mezzi"
            class="styled-input-mezzi"
          ></v-text-field>
          <v-text-field
            v-model="mezzo.modello"
            label="Modello"
            required
            id="styled-input-mezzi"
            class="styled-input-mezzi"
          ></v-text-field>
          <v-select
            v-model="mezzo.tipoAlimentazione.carburante"
            label="Alimentazione"
            required
            :items="FuelOptions"
            id="styled-input-mezzi"
            class="styled-input-mezzi"
          >
            <template slot="selection" slot-scope="{ item }">
              <span style="font-size:20pt;  color:orangered;">{{item.text }}</span>
            </template>
          </v-select>
          <v-btn class="ma-2" color="success" dark @click="updateMezzo">
            Modifica
            <v-icon dark right>done</v-icon>
          </v-btn>
          <v-btn class="ma-2" color="error" dark @click="goBack">
            Indietro
            <v-icon dark right>undo</v-icon>
          </v-btn>
        </v-form>
      </v-col>
    </v-card>
  </v-container>
</template>

	<script>
import http from "../http-common";

export default {
  name: "update-mezzo",
  data() {
    return {
      idMezzo: this.$route.params.idMezzo,
      mezzo: "",
      FuelOptions: [
        { text: "Benzina" },
        { text: "Gasolio" },
        { text: "Bi-fuel gpl" },
        { text: "Ibrida" }
      ]
    };
  },
  methods: {
    retrieveMezziByIdMezzo() {
      http
        .get("/mezzi/update/" + this.idMezzo)
        .then(response => {
          this.mezzi = response.data; // JSON are parsed automatically.
          this.mezzo = this.mezzi[0];
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    updateMezzo() {
      var data = {
        targa: this.mezzo.targa,
        marca: this.mezzo.marca,
        modello: this.mezzo.modello,
        tipoAlimentazione: this.mezzo.tipoAlimentazione.carburante
      };
      if (this.mezzo.targa.length != 7) {
        this.$alert("La targa deve contenere 7 caratteri!", "Errore", "error");
      } else if (
        this.mezzo.marca.length == 0 ||
        this.mezzo.modello.length == 0
      ) {
        this.$alert("Riempire tutti i campi!", "Errore", "error");
      } else {
        this.$confirm(
          "Confermi la modifica?",
          "Modifica mezzo",
          "question"
        ).then(() => {
          http
            .put("/mezzi/update/" + this.mezzo.idMezzo, data)
            .then(response => {
              console.log(response.data);
              this.refresh();
              //Terminato il metodo, andrò alla seguente pagina
              this.$router.push("/mezzi");
            })
            .catch(e => {
              console.log(e);
            });
        });
      }
    },
    refresh() {
      this.$alert("Modifica mezzo", "Modifica avvenuta!", "success");
    },
    goBack() {
      this.$router.push({ path: "/mezzi/dettagli/" + this.mezzo.idMezzo });
    }
  },
  mounted() {
    this.retrieveMezziByIdMezzo();
  }
};
</script>

<style>
#styled-input-mezzi {
  height: 40px;
  font-size: 20pt;
  color: orangered;
}
.styled-input-mezzi label[for] {
  height: 30px;
  font-size: 15pt;
  color: blue;
}
</style>



