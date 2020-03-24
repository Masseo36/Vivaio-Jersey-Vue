<template>
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-heading">
        <h2 class="text-center">Recupero password</h2>
      </div>
      <hr />
      <div class="modal-body">
        <form action role="form">
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">
                <span class="glyphicon glyphicon-user"></span>
              </span>
              <input
                type="text"
                class="form-control"
                name="username"
                v-model="username"
                placeholder="Inserisci username"
              />
            </div>
          </div>
        </form>
      </div>

      <div class="form-group text-center">
        <button
          type="button"
          class="btn btn-success btn-lg"
          v-on:click="recuperaPassword()"
        >Recupera</button>
      </div>
    </div>
  </div>
</template>

<script>
import http from "../http-common";
export default {
  name: "RecuperoPassword",
  data() {
    return {
      username: ""
    };
  },
  methods: {
    retrieveEmployees() {
      http
        .get("/employees")
        .then(response => {
          this.employees = response.data; // JSON are parsed automatically.
        })
        .catch(e => {
          console.log(e);
        });
    },
    recuperaPassword() {
      var present;
      for (let index = 0; index < this.employees.length; index++) {
        const element = this.employees[index];
        if (element.username == this.username) {
          present = true;
          break;
        }
      }
      if (present == true) {
        http.post("/recuperaPassword/" + this.username);
        window.alert(
          "Abbiamo inviato la tua password alla tua casella di posta"
        );
        this.$router.push("/login");
      } else window.alert("Username non trovato!");
    }
  },
  mounted() {
    this.retrieveEmployees();
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