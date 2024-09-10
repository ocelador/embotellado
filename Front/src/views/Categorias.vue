<template>
  <div class="container">
    <div class="form-container">
      <h2>{{ isEditing ? 'Editar categoría' : 'Crear categoría' }}</h2>
      <form @submit.prevent="isEditing ? updateCategoria() : createCategoria()">
        <div>
          <input v-model="categoriaForm.nombre" placeholder="Nombre" required /><br>
        </div>
        <div>
          <textarea v-model="categoriaForm.descripcion" placeholder="Descripción" rows="4" cols="50"></textarea><br>
        </div>
        <div>
          <label>
            <input type="checkbox" v-model="categoriaForm.is0Azucar" />
            Sin Azúcar
          </label><br>
        </div>
        <div>
          <label>
            <input type="checkbox" v-model="categoriaForm.isNatural" />
            Natural
          </label><br>
        </div>
        <div>
          <label>
            <input type="checkbox" v-model="categoriaForm.isAlcohólica" />
            Alcohólica
          </label><br>
        </div>
        <div>
          <label>
            <input type="checkbox" v-model="categoriaForm.is00" />
            0.0%
          </label><br>
        </div>
        <button type="submit">{{ isEditing ? 'Actualizar' : 'Crear' }}</button>
        <button type="button" @click="resetForm">Cancelar</button>
      </form>
    </div>

    <div class="table-container">
      <h2>Tipos de bebidas</h2>
      <table>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Sin Azúcar</th>
            <th>Natural</th>
            <th>Alcohólica</th>
            <th>0.0%</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="categoria in categorias" :key="categoria.id">
            <td>{{ categoria.nombre }}</td>
            <td>{{ categoria.descripcion }}</td>
            <td>{{ categoria.is0Azucar ? 'Sí' : 'No' }}</td>
            <td>{{ categoria.isNatural ? 'Sí' : 'No' }}</td>
            <td>{{ categoria.isAlcohólica ? 'Sí' : 'No' }}</td>
            <td>{{ categoria.is00 ? 'Sí' : 'No' }}</td>
            <td>
              <button @click="editCategoria(categoria)">Editar</button>
              <button @click="deleteCategoria(categoria.id)">Eliminar</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
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

<style scoped>
.container {
  display: flex;
  flex-direction: row;
}

.form-container {
  flex: 1;
  margin-right: 20px;
}

.table-container {
  flex: 2;
  max-height: 400px;
  overflow-y: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
}

th {
  background-color: #f2f2f2;
  text-align: left;
}
</style>