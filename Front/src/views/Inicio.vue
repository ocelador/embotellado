<template>
  <div class="home">
    <h1>{{ message }}</h1>
    <img src="@/assets/inicio.jpg" alt="Imagen de bienvenida" class="welcome-image" />
  </div>
</template>

<script setup>
import { ref } from 'vue';

const message = ref('Bienvenido a la factoría de embotellados');
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap');

h1 {
  font-size: 2em;
  color: #333333;
  text-align: center;
  margin-top: 20px;
}

.welcome-image {
  display: block;
  margin: 20px auto;
  max-width: 100%;
  height: auto;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(176, 176, 176, 0.5);
}

@media (max-width: 768px) {
  h1 {
    font-size: 1.5em;
  }

  .welcome-image {
    margin-top: 15px;
  }
}

@media (max-width: 480px) {
  h1 {
    font-size: 1.2em;
  }

  .welcome-image {
    margin-top: 10px;
  }
}
</style>