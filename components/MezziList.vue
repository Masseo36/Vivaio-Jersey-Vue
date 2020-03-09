<template>
  <div class="container">
    <div class="contact-us"></div>
    <h4 style="position: relative; top: 0em; right: 20em; color: orangered">Lista mezzi</h4>
    <div class="mezzi-ricerca">
      <!-- <h4 style="color: orangered">Mezzi</h4>-->
      <div
        v-for="(mezzo, index) in mezzi"
        :key="index"
        style="position: relative; top: 4em; right: 30em;"
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
      <div class="fotoMezzo" style="position:absolute; top: 18em; right: 8em">
        <p>
          <img
            src="https://cdn.pixabay.com/photo/2017/07/29/22/18/drawing-2552889_1280.png"
            width="700"
            height="400"
          />
        </p>
      </div>
    </div>
  </div>
</template>
 
<script>
import http from "../http-common";

export default {
  name: "mezzi-list",
  data() {
    return {
      mezzi: []
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
  mounted() {
    this.retrieveMezzi();
  }
};
</script>
 
<style scoped>
.contact-us {
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