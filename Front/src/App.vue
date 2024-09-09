<script setup>
import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <main>
    <!-- Barra de navegación -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-center">
      <div class="container-fluid d-flex justify-content-center">
        <RouterLink class="nav-link" active-class="active" to="/">Inicio </RouterLink>
        <RouterLink class="nav-link" active-class="active" to="/productos">Productos</RouterLink>
        <RouterLink class="nav-link" active-class="active" to="/categorias">Categorias</RouterLink>
      </div>
    </nav>
    
    <div class="container mt-4">
      <!-- Contenido dinámico -->
      <RouterView />
    </div>
  </main>
</template>

<style scoped>
html, body {
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
}

main {
  padding: 20px;
}

a {
  padding-right: 2vw;
}

.navbar {
  text-align: center;
}

.nav-link {
  color: #333;
  font-weight: 500;
  transition: color 0.3s ease-in-out;
}

.nav-link:hover {
  color: #0056b3;
}

.nav-link.active {
  color: #007bff;
  font-weight: bold;
}
</style>