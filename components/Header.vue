<template>
  <div class="header">
    <nav>
      <div class="custom-dropdown" style="position:relative; top: 10px; right: 300px;">
        <select v-model="menùEmployee" v-on:change="changeRout">
          <option disabled>{{menùEmployee}}</option>
          <option>Lista employees</option>
          <option>Aggiungi employee</option>
          <option>Cerca employee</option>
        </select>
      </div>
      <div class="custom-dropdown" style="position:relative; top: 10px; left: 325px;">
        <select v-model="menùMezzo" v-on:change="changeRout">
          <option disabled>{{menùMezzo}}</option>
          <option>Lista mezzi</option>
          <option>Aggiungi mezzo</option>
          <option>Cerca mezzo</option>
        </select>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menùEmployee: "Menù employee",
      menùMezzo: "Menù mezzo"
    };
  },
  methods: {
    changeRout() {
      if (event.target.value == "Lista employees") {
        this.$router.push("/", () => {});
      }
      if (event.target.value == "Aggiungi employee") {
        this.$router.push("/create", () => {});
      }
      if (event.target.value == "Cerca employee") {
        this.$router.push("/ricercaEmployee", () => {});
      }
      if (event.target.value == "Lista mezzi") {
        this.$router.push("/mezzi", () => {});
      }
      if (event.target.value == "Aggiungi mezzo") {
        this.$router.push("/addMezzo", () => {});
      }
      if (event.target.value == "Cerca mezzo") {
        this.$router.push("/ricercaMezzi", () => {});
      }
      this.menùEmployee = "Menù employee";
      this.menùMezzo = "Menù mezzo";
    }
  }
};
</script>

<style scoped>
nav {
  background: rgb(130, 229, 229);
  padding: 14px 0;
  margin-bottom: 40px;
}
.header {
  max-width: 100%;
  margin: auto;
  color: rgb(130, 229, 229);
}
.custom-dropdown {
  position: relative;
  display: inline-block;
  vertical-align: middle;
  margin: 10px; /* demo only */
  font-family: cursive;
  font-size: 20px;
}
.custom-dropdown select {
  background-color: rgb(130, 229, 229);
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

/*.custom-dropdown::before {
  
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
}*/
</style>