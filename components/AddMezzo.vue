<template>
  <div class="submitform">
    <div class="form-group">
      <label for="targa">
        <h4>Targa</h4>
      </label>
      <input
        placeholder="Inserire targa (7 caratteri)"
        type="text"
        class="form-control"
        id="targa"
        required
        v-model="mezzo.targa"
        name="targa"
      />
    </div>

    <div class="form-group">
      <label for="marca">
        <h4>Marca</h4>
      </label>
      <input
        placeholder="Inserire marca"
        type="text"
        class="form-control"
        id="marca"
        required
        v-model="mezzo.marca"
        name="marca"
      />
    </div>

    <div class="form-group">
      <label for="modello">
        <h4>Modello</h4>
      </label>
      <input
        placeholder="Inserire modello"
        type="text"
        class="form-control"
        id="modello"
        required
        v-model="mezzo.modello"
        name="modello"
      />
    </div>
    <div class="dropdown">
      <label for="carburante">
        <h4>Alimentazione:</h4>
      </label>
      <p>
        <select v-model="mezzo.tipoAlimentazione" style="background-color: lightcyan">
          <option disabled value>Scegli alimentazione</option>
          <option>Benzina</option>
          <option>Gasolio</option>
          <option>Bi-fuel GPL + Benzina</option>
          <option>Ibrida</option>
        </select>
      </p>
    </div>

    <div
      v-if="mezzo.targa.length > 0 && mezzo.marca.length >0 && mezzo.modello.length > 0 && mezzo.tipoAlimentazione.length > 0"
    >
      <button v-on:click="saveMezzo" class="btn btn-success">Submit</button>
    </div>
    <div class="aggiungiMezzo">
      <h2 style="color:orangered;;">AGGIUNGI MEZZO</h2>
    </div>
  </div>
</template>
 
<script>
import http from "../http-common";

export default {
  name: "add-mezzo",
  data() {
    return {
      mezzo: {
        targa: "",
        marca: "",
        modello: "",
        tipoAlimentazione: ""
      }
    };
  },
  methods: {
    /* eslint-disable no-console */
    saveMezzo() {
      var data = {
        idMezzo: this.idMezzo,
        targa: this.mezzo.targa,
        marca: this.mezzo.marca,
        modello: this.mezzo.modello,
        tipoAlimentazione: this.mezzo.tipoAlimentazione
      };
      if (this.mezzo.targa.length != 7) {
        this.$alert("La targa deve contenere 7 caratteri!", "Errore", "error");
        console.log(this.mezzo.targa);
      } else {
        http
          .post("/createMezzo", data)
          .then(response => {
            this.mezzo.idMezzo = response.data.idMezzo;
            this.refresh();
            this.$router.push("/mezzi");
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });

        this.submitted = true;
      }
    },
    newMezzo() {
      this.submitted = false;
      this.mezzo = {};
    },
    refresh() {
       this.$alert("Aggiungi mezzo", "Mezzo aggiunto con successo!", "success");
      this.mezzo.targa = "";
      this.mezzo.marca = "";
      this.mezzo.modello = "";
      this.mezzo.tipoAlimentazione = "";
    }
    /* eslint-enable no-console */
  }  
};
</script>
 
<style scoped>
::-webkit-input-placeholder {
  background-color: lightcyan;
  color: black;
  font-size: 18px;
  font-family: cursive;
}
.dropdown {
  position: relative;
  display: inline-block;
   font-family: cursive;
}
.submitform {
  max-width: 300px;
  margin: auto;
  color: orangered;
}
.aggiungiMezzo {
  position: absolute;
  content: "";
  bottom: 100px;
  right: 60px;
  height: 370px;
  width: 400px;
  background: #9ed9eb;
  transform: rotate(-25deg);
}
</style>