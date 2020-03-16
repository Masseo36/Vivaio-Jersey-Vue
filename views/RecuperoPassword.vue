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
        <button type="button" class="btn btn-success btn-lg" v-on:click="login()">Login</button>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "RecuperoPassword",
  data() {
    return {
      username: ""
    };
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