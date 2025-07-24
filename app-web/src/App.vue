<template>
  <div class="container mt-4">
    <h2>Gestión de TODOs por Usuario</h2>

    <!-- Buscar TODOs por userId -->
    <div class="input-group mb-3">
      <input v-model="userIdBuscar" type="number" class="form-control" placeholder="Buscar TODOs por User ID" />
      <button class="btn btn-primary" @click="buscarPorUserId">Buscar</button>
    </div>

    <div v-if="resultado" class="mb-4">
      <div class="card card-body mb-3 bg-light">
        <h5>Usuario Encontrado</h5>
        <p><strong>ID:</strong> {{ resultado.user.id }}</p>
        <p><strong>Nombre:</strong> {{ resultado.user.name }}</p>
        <p><strong>Email:</strong> {{ resultado.user.email }}</p>
      </div>

      <div v-if="resultado.todos.length === 0">
        <p>No hay TODOs para este usuario.</p>
      </div>

      <div v-else>
        <h5>Lista de TODOs</h5>
        <div v-for="t in resultado.todos" :key="t.id" class="card card-body mb-2">
          <p><strong>ID:</strong> {{ t.id }}</p>
          <p><strong>Título:</strong> {{ t.title }}</p>
          <p><strong>Completado:</strong> {{ t.completed ? 'Sí' : 'No' }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const userIdBuscar = ref('');
const resultado = ref(null);

async function buscarPorUserId() {
  try {
    const res = await axios.get(`http://localhost:8080/app-todos/todos/usuario/${userIdBuscar.value}`);
    resultado.value = res.data;
  } catch (error) {
    alert('No se encontró el usuario o hubo un error en la solicitud.');
    resultado.value = null;
  }
}
</script>

<style scoped>
.container {
  max-width: 800px;
}
</style>
