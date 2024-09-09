import { createRouter, createWebHistory } from "vue-router";
import Inicio from "../views/Inicio.vue";
import Productos from "../views/Productos.vue";
import Categorias from "../views/Categorias.vue";

const routes = [
  {
    path: "/",
    name: "Inicio",
    component: Inicio,
  },
  {
    path: "/productos",
    name: "Productos",
    component: Productos,
  },
  {
    path: "/categorias",
    name: "Categorias",
    component: Categorias,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;