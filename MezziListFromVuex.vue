<template>
  <v-container class="my-5">
    {{this.$store.state.moduleB.mezzi
    }}
    <v-card raised style="background: #F9FBE7" class="mt-8 mx-auto" width="1000">
      <v-card-title>
        <h1 class="subheading grey--text">Mezzi</h1>
        <v-spacer></v-spacer>
        <v-text-field
          v-model="modelloMezzo"
          append-icon="mdi-magnify"
          label="Cerca per modello"
          single-line
          hide-detail
        ></v-text-field>
      </v-card-title>
      <v-container>
        <v-row row wrap>
          <v-col md="2">
            <div class="caption blue--text">
              <h5>Targa</h5>
            </div>
          </v-col>
          <v-col md="2" class="ml-10">
            <div class="caption blue--text">
              <h5>Marca</h5>
            </div>
          </v-col>
          <v-col md="2" class="ml-10">
            <div class="caption blue--text">
              <h5>Modello</h5>
            </div>
          </v-col>
          <v-col md="2" class="ml-8">
            <div class="caption blue--text">
              <h5>Alimentazione</h5>
            </div>
          </v-col>
        </v-row>

        <v-col flat v-for="(mezzo, index) in mezzi" :key="index">
          <v-row
            row
            wrap
            class="mt-n6"
            v-if=" (mezzo.modello).toUpperCase() == modelloMezzo.toUpperCase() || modelloMezzo.length == 0"
          >
            <v-col class="ml-n3">
              <div class="caption orange--text">
                <h4>{{mezzo.targa}}</h4>
              </div>
            </v-col>
            <v-col class="ml-n3">
              <div class="caption orange--text">
                <h4>{{mezzo.marca}}</h4>
              </div>
            </v-col>
            <v-col class="ml-n3">
              <div class="caption orange--text">
                <h4>{{mezzo.modello}}</h4>
              </div>
            </v-col>
            <v-col class="ml-n3">
              <div class="caption orange--text">
                <h4>{{mezzo.tipoAlimentazione.carburante}}</h4>
              </div>
            </v-col>
            <v-flex xs4 md2>
              <router-link
                :to="{
                            name: 'mezzo-dettagli',
                            params: { mezzo: mezzo, idMezzo: mezzo.idMezzo }
                        }"
              >
                <v-btn class="ma-2" color="primary">
                  Dettagli
                  <v-icon dark right>description</v-icon>
                </v-btn>
              </router-link>
            </v-flex>
          </v-row>
        </v-col>
      </v-container>
    </v-card>
  </v-container>
</template>

<script>
//import http from "../http-common";
import { mezzoService } from "@/services/mezzoService";

export default {
  name: "mezzi-list",
  data() {
    return {
      mezzi: [],
      modelloMezzo: ""
    };
  },
  methods: {
    retrieveMezzi() {
      const response = mezzoService.getListMezzi();
      response
        .then(result => {
          this.mezzi = result.data;
          this.$store.dispatch("moduleB/popolaStore", this.mezzi);
        })
        .catch(() => {});
    }
  },
  refreshList() {
    this.retrieveMezzi();
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
</style>