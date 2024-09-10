<template>
  <div class="home">
    <h1>{{ message }}</h1>
    <img src="@/assets/inicio.jpg" alt="Imagen de bienvenida" class="welcome-image" />
  </div>
</template>

<script setup>
import { ref } from 'vue';

const message = ref('Bienvenido a la factoria de embotellados');
</script>

<style scoped>
.home {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  text-align: center;
}

h1 {
  font-size: 2em;
  margin: 0;
}

.welcome-image {
  margin-top: 20px;
  max-width: 100%;
  height: auto;
}
</style>