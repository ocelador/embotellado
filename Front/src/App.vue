<script setup>
import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <main>
    <!-- Barra de navegación -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
          <RouterLink class="nav-link" active-class="active" to="/">Inicio</RouterLink>
          <RouterLink class="nav-link" active-class="active" to="/productos">Productos</RouterLink>
          <RouterLink class="nav-link" active-class="active" to="/categorias">Categoría</RouterLink>
        </div>
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

@media (max-width: 992px) {
  .navbar-nav {
    text-align: center;
  }
  
  .nav-link {
    padding: 10px 0;
  }
}
</style>