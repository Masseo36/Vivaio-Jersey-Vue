<template>
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-heading">
        <h2 class="text-center">Sostituisci password iniziale</h2>
      </div>
      <hr />
     <div class="modal-body">
        <form action role="form">
         <!-- <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">
                <span class="glyphicon glyphicon-user"></span>
              </span>
              <input
                type="password"
                class="form-control"
                name="vecchiaPassword"
                v-model="vecchiaPassword"
                placeholder="Vecchia password"
              />
            </div>
          </div>-->
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">
                <span class="glyphicon glyphicon-lock"></span>
              </span>
              <input
                type="password"
                class="form-control"
                name="nuovaPassword"
                v-model="nuovaPassword"
                placeholder="Nuova password"
              />
            </div>
          </div>

          <div class="form-group text-center">
            <button
              type="button"
              class="btn btn-success btn-lg"
              v-on:click="changePassword()"
            >Sostituisci password</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "CambiaPasswordPrimoAccesso",
  data() {
    return {
      //Questa riga consente di portare in questa pagina l'id dell'oggetto
      //dalla pagina di provenienza
      id: this.$route.params.id,
      nuovaPassword: "",
    };
  },
  methods: {
    retrieveEmployeesById() {
      http
        .get("/employee/" + this.id)
        .then(response => {
          this.employees = response.data; // JSON are parsed automatically.
          this.employee = this.employees[0];
        })
        .catch(e => {
          console.log(e);
        });
    },
    changePassword() {
      http
        .put(
          "/CambiaPasswordIniziale/" +
            this.id +
            "/CambiaPasswordIniziale/" +
            this.nuovaPassword
        )
        .then(
          (this.active = true),
          this.$emit("authenticated", true),
          this.$router.push("/employees")
        );
    }
  },
  mounted() {
    this.retrieveEmployeesById();
  }
};
</script>

<style scoped>
.modal-content {
  top: 75px;
  background-color: rgba(9, 196, 196, 0.849);
}
.btn-link {
  color: white;
}
.modal-heading h2 {
  color: #ffffff;
}
</style>
