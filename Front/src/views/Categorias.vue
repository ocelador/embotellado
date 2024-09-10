<template>
  <div>
    <h1>Categorias</h1>
    <p>Bienvenido a la página de Categorias.</p>
    
    <h2>{{ isEditing ? 'Editar Categoria' : 'Crear Categoria' }}</h2>
    <form @submit.prevent="isEditing ? updateCategoria() : createCategoria()">
      <input v-model="categoriaForm.nombre" placeholder="Nombre" required />
      <input v-model="categoriaForm.descripcion" placeholder="Descripción" required />
      <label>
        <input type="checkbox" v-model="categoriaForm.is0Azucar" />
        Sin Azúcar
      </label>
      <label>
        <input type="checkbox" v-model="categoriaForm.isNatural" />
        Natural
      </label>
      <label>
        <input type="checkbox" v-model="categoriaForm.isAlcohólica" />
        Alcohólica
      </label>
      <label>
        <input type="checkbox" v-model="categoriaForm.is00" />
        0.0%
      </label>
      <button type="submit">{{ isEditing ? 'Actualizar' : 'Crear' }}</button>
      <button type="button" @click="resetForm">Cancelar</button>
    </form>
    
    <br><h2>Lista de Categorias</h2>
    <ul>
      <li v-for="categoria in categorias" :key="categoria.id">
        {{ categoria.nombre }} - {{ categoria.descripcion }} - {{ categoria.is0Azucar ? 'Sin Azúcar' : 'Con Azúcar' }} - {{ categoria.isNatural ? 'Natural' : 'Conservantes' }} - {{ categoria.isAlcohólica ? 'Alcohólica' : 'No Alcohólica' }} - {{ categoria.is00 ? '0.0%' : 'Normal' }}
        <button @click="editCategoria(categoria)">Editar</button>
        <button @click="deleteCategoria(categoria.id)">Eliminar</button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "Categorias",
  data() {
    return {
      categorias: [],
      categoriaForm: {
        id: null,
        nombre: '',
        descripcion: '',
        is0Azucar: false,
        isNatural: false,
        isAlcohólica: false,
        is00: false
      },
      isEditing: false
    };
  },
  created() {
    this.fetchCategorias();
  },
  methods: {
    async fetchCategorias() {
      try {
        const response = await axios.get('/api/categorias');
        console.log('Categorías recibidas:', response.data);
        this.categorias = response.data;
      } catch (error) {
        console.error('Error fetching categorias:', error);
      }
    },
    async createCategoria() {
      try {
        console.log('Datos enviados:', this.categoriaForm);
        const response = await axios.post('/api/categorias', this.categoriaForm);
        console.log('Categoria creada:', response.data);
        this.categorias.push(response.data);
        this.resetForm();
      } catch (error) {
        console.error('Error creating categoria:', error.response ? error.response.data : error.message);
      }
    },
    async updateCategoria() {
      try {
        const response = await axios.put(`/api/categorias/${this.categoriaForm.id}`, this.categoriaForm);
        const index = this.categorias.findIndex(c => c.id === this.categoriaForm.id);
        if (index !== -1) {
          this.categorias[index] = response.data;
        }
        this.resetForm();
      } catch (error) {
        console.error('Error updating categoria:', error.response ? error.response.data : error.message);
      }
    },
    async deleteCategoria(id) {
      try {
        await axios.delete(`/api/categorias/${id}`);
        this.categorias = this.categorias.filter(c => c.id !== id);
      } catch (error) {
        console.error('Error deleting categoria:', error.response ? error.response.data : error.message);
      }
    },
    editCategoria(categoria) {
      this.categoriaForm = { ...categoria };
      this.isEditing = true;
    },
    resetForm() {
      this.categoriaForm = {
        id: null,
        nombre: '',
        descripcion: '',
        is0Azucar: false,
        isNatural: false,
        isAlcohólica: false,
        is00: false
      };
      this.isEditing = false;
    }
  }
};
</script>