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
  padding: 20px; 
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