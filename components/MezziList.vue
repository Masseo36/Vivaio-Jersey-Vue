<template>
  <div class="mezzi-list">
    <div class="cuboDecorativo"></div>
    <h4 style="position: relative; top: 4em; right: 25em; color: orangered">Lista mezzi</h4>
    <!-- <h4 style="color: orangered">Mezzi</h4>-->
    <div
      v-for="(mezzo, index) in mezzi"
      :key="index"
      style="position: relative; top: 8em; right: 30em;"
    >
      <router-link
        :to="{
                            name: 'mezzo-dettagli',
                            params: { mezzo: mezzo, idMezzo: mezzo.idMezzo }
                        }"
      >
        <h4>
          {{mezzo.targa}}
          {{mezzo.marca}}
          {{mezzo.modello}}
          {{mezzo.tipoAlimentazione.carburante}}
        </h4>
      </router-link>
    </div>
    <div class="col-md-6">
      <router-view @refreshData="refreshList"></router-view>
    </div>
    <p></p>
    <div class="ricercaModello">
      <h4
        style="position:absolute; top: 11em; right: 25em; color: orangered"
      >Mezzo: ricerca puntuale per modello</h4>
      <input
        type="text"
        v-model="modelloMezzo"
        placeholder="Inserire modello"
        style="position:absolute; top: 20em; right: 54em;"
      />
      <div
        v-for="(mezzo, index) in mezzi"
        :key="index"
        style="position:relative; top: 6em; right: 8em"
      >
        <router-link
          :to="{
                            name: 'mezzi-dettagli',
                            params: { mezzo: mezzo, idMezzo: mezzo.idMezzo }
                        }"
        >
          <div v-if=" (mezzo.modello).toUpperCase() == modelloMezzo.toUpperCase()">
            <h4>{{mezzo.targa}} {{mezzo.marca}}{{mezzo.modello}}{{mezzo.tipoAlimentazione.carburante}}</h4>
          </div>
        </router-link>
      </div>
    </div>
    <div class="fotoMezzo" style="position:absolute; top: 18em; right: 0em">
      <p>
        <img
          src="https://cdn.pixabay.com/photo/2017/07/29/22/18/drawing-2552889_1280.png"
          width="700"
          height="400"
        />
      </p>
    </div>
  </div>
</template>
 
<script>
import http from "../http-common";

export default {
  name: "mezzi-list",
  data() {
    return {
      mezzi: [],
      modelloMezzo: "",
    };
  },
  methods: {
    /* eslint-disable no-console */
    retrieveMezzi() {
      http
        .get("/mezzi")
        .then(response => {
          this.mezzi = response.data; // JSON are parsed automatically.
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.retrieveMezzi();
    }
    /* eslint-enable no-console */
  },
  filters: {
    uppercase: function(v) {
      return v.toUpperCase();
    }
  },
  mounted() {
    this.retrieveMezzi();
  }
};
</script>
 
<style scoped>
.cuboDecorativo {
  /* position: relative;
  width: 250px;
  background: #c3ebec;
  overflow: hidden;*/

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
.list {
  text-align: left;
  max-width: 450px;
  margin: auto;
}
</style>