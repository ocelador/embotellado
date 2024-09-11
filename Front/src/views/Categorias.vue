<template>
  <div class="container">
    <div class="form-container">
      <h2>{{ isEditing ? 'Editar categoría' : 'Agregar categoría' }}</h2>
      <form @submit.prevent="isEditing ? updateCategoria() : createCategoria()">
        <div class="form-group">
          <input id="nombre" v-model="categoriaForm.nombre" placeholder="Nombre" required />
        </div>
        <div class="form-group">
          <textarea id="descripcion" v-model="categoriaForm.descripcion" placeholder="Descripción" rows="4" cols="50"></textarea>
        </div>
        <div class="form-group checkbox-group">
          <label>
            <input type="checkbox" v-model="categoriaForm.is0Azucar" />
            Sin_Azúcar
          </label>
        </div>
        <div class="form-group checkbox-group">
          <label>
            <input type="checkbox" v-model="categoriaForm.isNatural" />
            Natural
          </label>
        </div>
        <div class="form-group checkbox-group">
          <label>
            <input type="checkbox" v-model="categoriaForm.isAlcohólica" />
            Alcohólica
          </label>
        </div>
        <div class="form-group checkbox-group">
          <label>
            <input type="checkbox" v-model="categoriaForm.is00" />
            0.0%
          </label>
        </div>
        <div class="form-actions">
          <button type="submit" class="btn button-standard">Guardar</button>
          <button type="button" class="btn button-standard" @click="resetForm">Cancelar</button>
        </div>
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
            <td>{{ categoria.is0Azucar ? 'V' : 'X' }}</td>
            <td>{{ categoria.isNatural ? 'V' : 'X' }}</td>
            <td>{{ categoria.isAlcohólica ? 'V' : 'X' }}</td>
            <td>{{ categoria.is00 ? 'V' : 'X' }}</td>
            <td>
              <button class="btn button-standard" @click="editCategoria(categoria)">Editar</button>
              <button class="btn button-standard" @click="deleteCategoria(categoria.id)">Eliminar</button>
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
        this.categorias = response.data;
      } catch (error) {
        console.error('Error fetching categorias:', error);
      }
    },
    async createCategoria() {
      try {
        const response = await axios.post('/api/categorias', this.categoriaForm);
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
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap');

html, body, #app {
  height: 100%;
  margin: 0;
}

.container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  height: 100%;
  box-sizing: border-box;
}

.form-container {
  flex: 1;
  margin-right: 20px;
  min-width: 300px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  box-sizing: border-box;
  max-height: calc(80vh - 60px); /* Ajusta la altura máxima para que no se extienda más allá de la página */
  overflow-y: auto;
}

.table-container {
  flex: 2;
  min-height: 400px;
  min-width: 300px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  box-sizing: border-box;
  max-height: calc(80vh - 60px); /* Ajusta la altura máxima para que no se extienda más allá de la página */
  overflow-y: auto; /* Agrega scroll vertical */
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
  word-wrap: break-word;
  text-align: center; /* Centra horizontalmente */
  vertical-align: middle; /* Centra verticalmente */
}

th {
  background-color: #343a40;
  color: #ffffff;
  text-align: left;
}

td {
  max-width: 120px;
  white-space: normal;
}

button {
  background-color: #343a40;
  color: #ffffff;
  border: none;
  padding: 10px 20px;
  margin: 5px 0;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
}

button:hover {
  background-color: #ffc107;
}

.button-standard {
  width: 120px;
  height: 40px; 
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: 500;
}

.form-group input,
.form-group textarea,
.form-group select {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
}

.checkbox-group {
  display: flex;
  align-items: center;
}

.checkbox-group label {
  display: flex;
  align-items: center;
}

.checkbox-group input {
  margin-right: 10px;
}

.form-actions {
  display: flex;
  justify-content: space-between;
}

@media (max-width: 768px) {
  .container {
    flex-direction: column;
  }

  .form-container, .table-container {
    margin-right: 0;
    margin-bottom: 20px;
  }
}

@media (max-width: 480px) {
  th, td {
    padding: 4px;
  }

  td {
    max-width: 100px;
  }
}
</style>