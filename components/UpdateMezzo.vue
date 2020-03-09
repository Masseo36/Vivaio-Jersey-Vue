<template>
<div class="updateMezzo">
  <div v-if="this.mezzo">
    <h3>Modifica mezzo</h3>
      <div class="form-group">
        <label for="targa">
          <h5>Targa</h5>
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
          <h5>Marca</h5>
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
          <h5>Modello</h5>
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
      <div class="form-group">
        <label for="tipoAlimentazione">
          <h5>Marca</h5>
        </label>
        <p>
          <select v-model="mezzo.tipoAlimentazione.carburante">
            <option>Benzina</option>
            <option>Gasolio</option>
            <option>Bi-fuel GPL + Benzina</option>
            <option>Ibrida</option>
          </select>
        </p>
      </div>
    <router-link
      :to="{
                            name: 'mezzo-details',
                            params: { mezzo: mezzo, idMezzo: mezzo.idMezzo }
                        }"
    ></router-link>
    <button v-on:click="updateMezzo()" class="btn btn-success">CONFERMA</button>
  </div>
  <div class="cuboDecorativo">
      <h2 style="color:orangered;">MODIFICA EMPLOYEE</h2></div>
</div>
</template>
	<script>
import http from "../http-common";

export default {
  name: "update-mezzo",
  data() {
    return {
      idMezzo: this.$route.params.idMezzo,
      mezzo: ""
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
      if (window.confirm("Confermi la modifica?")) {
        var data = {
          targa: this.mezzo.targa,
          marca: this.mezzo.marca,
          modello: this.mezzo.modello,
          tipoAlimentazione: this.mezzo.tipoAlimentazione.carburante
        };
        if (this.mezzo.targa.length != 7) {
          window.alert("La targa deve contenere 7 caratteri!");
        } else if (
          this.mezzo.marca.length == 0 ||
          this.mezzo.modello.length == 0
        ) {
          window.alert("Riempire tutti i campi!");
        } else {
          http
            .put("/mezzi/update/" + this.mezzo.idMezzo, data)
            .then(response => {
              console.log(response.data);
              this.refresh();
              this.$router.push("/mezzi");
            })
            .catch(e => {
              console.log(e);
            });
        }
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
::-webkit-input-placeholder {
  background-color: lightcyan;
  color: black;
  font-size: 18px;
  font-family: cursive;
}
.updateMezzo {
  max-width: 300px;
  margin: auto;
  color: orangered;
}
.cuboDecorativo {
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