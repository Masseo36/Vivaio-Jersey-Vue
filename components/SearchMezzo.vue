<template>
  <div class="searchform-mezzo">
    <div class="custom-dropdown">
      <select v-model="marca" v-on:change="filtraModelli">
        <option disabled value>Scegli una marca</option>
        <option v-for="(marca) in marcaNonRipetuta " :key="marca">
          <router-link
            :to="{
                            params: { marca: marca }
                        }"
          >{{marca}}</router-link>
        </option>
      </select>
    </div>
    <div class="custom-dropdown">
      <select v-model="modello" v-on:change="filtraCarburante">
        <option disabled value>Scegli un modello</option>
        <option v-for="(modello) in modelloNonRipetuto " :key="modello">
          <router-link
            :to="{
                            params: { modello: modello }
                        }"
          >{{modello}}</router-link>
        </option>
      </select>
    </div>
    <div class="custom-dropdown">
      <select select v-model="carburante">
        <option disabled value>Scegli alimentazione</option>
        <option v-for="(carburante) in carburanteNonRipetuto " :key="carburante">
          <router-link
            :to="{
                            params: { carburante: carburante }
                        }"
          >{{carburante}}</router-link>
        </option>
      </select>
    </div>

    <!--<div class="form-group">

      Quando inserisco il valore nella tendina marca chiamo un metodo che associa a quel valore i modelli
      e i tipi carburante esistenti

      <select v-model="marca" v-on:change="filtraModelli">
        <option disabled value>Scegli una marca</option>
        <option v-for="(marca) in marcaNonRipetuta " :key="marca">
          <router-link
            :to="{
                            params: { marca: marca }
                        }"
          >{{marca}}</router-link>
        </option>
      </select>
    </div>-->

    <!-- Quando inserisco il valore nella tendina modello chiamo un metodo che associa a quel valore
    i tipi carburante esistenti

    <div class="form-group">
      <select v-model="modello" v-on:change="filtraCarburante">
        <option disabled value>Scegli un modello</option>
        <option v-for="(modello) in modelloNonRipetuto " :key="modello">
          <router-link
            :to="{
                            params: { modello: modello }
                        }"
          >{{modello}}</router-link>
        </option>
      </select>
    </div>

    <div class="form-group">
      <select v-model="carburante">
        <option disabled value>Scegli alimentazione</option>
        <option v-for="(carburante) in carburanteNonRipetuto " :key="carburante">
          <router-link
            :to="{
                            params: { carburante: carburante }
                        }"
          >{{carburante}}</router-link>
        </option>
      </select>
    </div>-->

    <div class="btn-group" style="position:fixed; top: 350px; right: 750px;">
      <div v-if="marca.length > 0 || modello.length > 0 || carburante.length > 0">
        <button v-on:click="getForm" class="btn btn-success">Cerca</button>
      </div>
    </div>
    <ul class="search-result-mezzo">
      <li v-for="(mezzo, index) in mezziTrovati" :key="index">
        <router-link
          :to="{
                            name: 'mezzo-dettagli',
                            params: { mezzo: mezzo, idMezzo: mezzo.idMezzo }
                        }"
        ><h4>{{mezzo.targa}} {{mezzo.marca}} {{mezzo.modello}} {{mezzo.tipoAlimentazione.carburante}}</h4>
        </router-link>
      </li>
    </ul>
    <div class="cercaMezzo">
      <h2 style="color:orangered;">CERCA MEZZO</h2>
    </div>
  </div>
</template>
 
<script>
import http from "../http-common";

export default {
  name: "search-mezzo",
  data() {
    return {
      marca: "",
      modello: "",
      carburante: "",
      mezziTrovati: [],
      mezzi: [],
      tutteMarche: [],
      marcaNonRipetuta: [],
      tuttiModelli: [],
      modelloNonRipetuto: [],
      tuttiCarburanti: [],
      carburanteNonRipetuto: []
    };
  },
  methods: {
    /* eslint-disable no-console */

    //Ottengo tutti i mezzi dal database
    retrieveMezzi() {
      http.get("/ricercaMezzi").then(response => {
        this.mezzi = response.data; // JSON are parsed automatically.
        //console.log(response.data);
        this.filtraValoriDoppi();
      });
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
    //Vedo quale form ha riempito l'utente e assegno un numero("caso")
    getForm() {
      if (
        this.marca.length > 0 &&
        this.modello.length == 0 &&
        this.carburante.length == 0
      ) {
        this.caso = 0;
      }
      if (
        this.marca.length == 0 &&
        this.modello.length > 0 &&
        this.carburante.length == 0
      ) {
        this.caso = 1;
      }
      if (
        this.marca.length == 0 &&
        this.modello.length == 0 &&
        this.carburante.length > 0
      ) {
        this.caso = 2;
      }
      if (
        this.marca.length > 0 &&
        this.modello.length > 0 &&
        this.carburante.length == 0
      ) {
        this.caso = 3;
      }
      if (
        this.marca.length == 0 &&
        this.modello.length > 0 &&
        this.carburante.length > 0
      ) {
        this.caso = 4;
      }
      if (
        this.marca.length > 0 &&
        this.modello.length > 0 &&
        this.carburante.length > 0
      ) {
        this.caso = 5;
      }
      if (
        this.marca.length > 0 &&
        this.modello.length == 0 &&
        this.carburante.length > 0
      ) {
        this.caso = 6;
      }
      this.searchMezzi();
    },
    //In base al "caso" chiamo metodo corrispondente
    searchMezzi() {
      this.mezziTrovati = [];
      console.log(this.mezzi);
      switch (this.caso) {
        case 0:
          this.mezzi.forEach(mezzo => {
            if (mezzo.marca == this.marca) this.mezziTrovati.push(mezzo);
          });
          break;

        case 1:
          this.mezzi.forEach(mezzo => {
            if (mezzo.modello == this.modello) this.mezziTrovati.push(mezzo);
          });
          break;

        case 2:
          this.mezzi.forEach(mezzo => {
            if (mezzo.tipoAlimentazione.carburante == this.carburante)
              this.mezziTrovati.push(mezzo);
          });
          break;

        case 3:
          this.mezzi.forEach(mezzo => {
            if (mezzo.marca == this.marca && mezzo.modello == this.modello)
              this.mezziTrovati.push(mezzo);
          });
          break;
        case 4:
          this.mezzi.forEach(mezzo => {
            if (
              mezzo.modello == this.modello &&
              mezzo.tipoAlimentazione.carburante == this.carburante
            )
              this.mezziTrovati.push(mezzo);
          });
          break;

        case 5:
          this.mezzi.forEach(mezzo => {
            if (
              mezzo.marca == this.marca &&
              mezzo.modello == this.modello &&
              mezzo.tipoAlimentazione.carburante == this.carburante
            )
              this.mezziTrovati.push(mezzo);
          });
          break;
        case 6:
          this.mezzi.forEach(mezzo => {
            if (
              mezzo.marca == this.marca &&
              mezzo.tipoAlimentazione.carburante == this.carburante
            )
              this.mezziTrovati.push(mezzo);
          });
          break;
      }
      this.refresh();
    },
    filtraValoriDoppi() {
      //Elimino i doppi valori nell'array delle marche
      this.mezzi.forEach(element => {
        this.tutteMarche.push(element.marca);
      });
      this.marcaNonRipetuta = [...new Set(this.tutteMarche)];

      //Elimino i doppi valori nell'array dei modelli
      this.mezzi.forEach(element => {
        this.tuttiModelli.push(element.modello);
      });
      this.modelloNonRipetuto = [...new Set(this.tuttiModelli)];

      //Elimino i doppi valori nell'array dei tipi carburante
      this.mezzi.forEach(element => {
        this.tuttiCarburanti.push(element.tipoAlimentazione.carburante);
      });
      this.carburanteNonRipetuto = [...new Set(this.tuttiCarburanti)];
    },
    //Questo metodo è chiamato quando scelgo un valore nella tendina "marca" e mi dà
    //il nome dei modelli associati a quella marca

    //mi dà anche i tipi di carburante associati

    filtraModelli() {
      this.carburanteNonRipetuto = [];
      this.modelloNonRipetuto = [];
      this.mezzi.forEach(mezzo => {
        if (mezzo.marca == this.marca) {
          this.modelloNonRipetuto.push(mezzo.modello);
          //Con questa riga elimino i doppioni
          this.modelloNonRipetuto = [...new Set(this.modelloNonRipetuto)];
          this.carburanteNonRipetuto.push(mezzo.tipoAlimentazione.carburante);
          //Con questa riga elimino i doppioni
          this.carburanteNonRipetuto = [...new Set(this.carburanteNonRipetuto)];
        }
      });
    },
    //Questo metodo è chiamato quando scelgo un valore nella tendina "modello" e mi dà
    //il nome dei tipi carburante associati a quel modello
    filtraCarburante() {
      this.carburanteNonRipetuto = [];
      this.mezzi.forEach(mezzo => {
        if (mezzo.modello == this.modello)
          this.carburanteNonRipetuto.push(mezzo.tipoAlimentazione.carburante);
        //Con questa riga elimino i doppioni
        this.carburanteNonRipetuto = [...new Set(this.carburanteNonRipetuto)];
      });
    },
    refresh() {
      this.mezzi = [];
      this.marca = "";
      this.modello = "";
      this.carburante = "";
      if (this.mezziTrovati.length == 0) this.$alert("Nessun mezzo trovato!", "Attenzione", 'warning')
      this.retrieveMezzi();
    }
    /* eslint-enable no-console */
  },
  //Non usato, lo tengo come esempio di filtro
  filters: {
    uppercase: function(carburante) {
      console.log(carburante);
      //console.log(...[new Set(mezzi)])
      return carburante;
    }
  },
  mounted() {
    this.retrieveMezzi();
  }
}; //A seguire ci sono i metodi che avevo sviluppato chiamando MyResource + Service + DAO nel back end
/*searchMezzi() {
      http
        .post(
          "/ricercaMezzi/" +
            this.marca +
            "ricercaMezzi/" +
            this.modello +
            "ricercaMezzi/" +
            this.carburante
        )
        .then(response => {
          this.mezziTrovati = response.data; // JSON are parsed automatically.
          this.retrieveMezzi();
          this.refresh();
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    searchMezziByMarca() {
      console.log(1);
      http
        .post("/ricercaMezzi/" + this.marca)
        .then(response => {
          this.mezziTrovati = response.data; // JSON are parsed automatically.
          this.retrieveMezzi();
          this.refresh();
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    searchMezziByModello() {
      console.log(2);
      http
        .post("/ricercaMezzi/mezzi/" + this.modello)
        .then(response => {
          this.mezziTrovati = response.data; // JSON are parsed automatically.
          this.retrieveMezzi();
          this.refresh();
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    searchMezziByCarburante() {
      console.log(3);
      http
        .post("/ricercaMezzi/mezzi/alimentazione/" + this.carburante)
        .then(response => {
          this.mezziTrovati = response.data; // JSON are parsed automatically.
          this.retrieveMezzi();
          this.refresh();
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    searchMezziByModelloAndCarburante() {
      console.log(4);
      this.mezziTrovati = [];
      this.mezzi.forEach(mezzo => {
        if (
          mezzo.modello == this.modello &&
          mezzo.tipoAlimentazione.carburante == this.carburante
        )
          this.mezziTrovati.push(mezzo);
      });
      this.retrieveMezzi();
      this.refresh();
    },*/
</script>
 
 
<style scoped>

.searchform-mezzo {
  max-width: 100%;
  margin: auto;
  color: orangered;
}
.search-result-mezzo {
  margin-top: 10px;
  text-align: left;
  color: orangered;
}
.cercaMezzo {
  position: absolute;
  content: "";
  bottom: 80px;
  right: 70px;
  height: 370px;
  width: 400px;
  background: #9ed9eb;
  transform: rotate(-25deg);
}
.custom-dropdown {
  position: relative;
  display: inline-block;
  vertical-align: middle;
  margin: 10px; /* demo only */
  font-family: cursive;
}
.custom-dropdown select {
  background-color: #1abc9c;
  color: #fff;
  font-size: inherit;
  padding: 0.5em;
  padding-right: 2.5em;
  border: 0;
  margin: 0;
  border-radius: 3px;
  text-indent: 0.01px;
  text-overflow: "";
  /*Hiding the select arrow for firefox*/
  -moz-appearance: none;
  /*Hiding the select arrow for chrome*/
  -webkit-appearance: none;
  /*Hiding the select arrow default implementation*/
  appearance: none;
}
/*Hiding the select arrow for IE10*/
.custom-dropdown select::-ms-expand {
  display: none;
}

.custom-dropdown::before,
.custom-dropdown::after {
  content: "";
  position: absolute;
  pointer-events: none;
}

.custom-dropdown::after {
  /*  Custom dropdown arrow */
  content: "\25BC";
  height: 1em;
  font-size: 0.625em;
  line-height: 1;
  right: 1.2em;
  top: 50%;
  margin-top: -0.5em;
}

.custom-dropdown::before {
  /*  Custom dropdown arrow cover */
  width: 2em;
  right: 0;
  top: 0;
  bottom: 0;
  border-radius: 0 3px 3px 0;
  background-color: rgba(0, 0, 0, 0.2);
}

.custom-dropdown::after {
  color: rgba(0, 0, 0, 0.6);
}

.custom-dropdown select[disabled] {
  color: rgba(0, 0, 0, 0.25);
}
</style>