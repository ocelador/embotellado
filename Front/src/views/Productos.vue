<template>
  <div class="container">
    <div class="form-container">
      <h2>{{ isEditing ? 'Editar' : 'Agregar' }}</h2>
      <form @submit.prevent="isEditing ? updateProducto() : createProducto()" class="form-content">
        <div class="form-group">
          <input type="text" id="nombre" v-model="productoForm.nombre" placeholder="Nombre" maxlength="35" required />
        </div>
        <div class="form-group">
          <input type="text" id="marca" v-model="productoForm.marca" placeholder="Marca" maxlength="35" required />
        </div>
        <div class="form-group">
          <input type="text" id="sabor" v-model="productoForm.sabor" placeholder="Sabor" maxlength="35" required />
        </div>
        <div class="form-group">
          <input type="text" id="envase" v-model="productoForm.envase" placeholder="Envase" maxlength="35" required />
        </div>
        <div class="form-group input-group small-input-group">
          <input type="number" id="capacidad" v-model="productoForm.capacidad" placeholder="Capacidad" min="1" required />
          <span class="input-group-text">ml</span>
        </div>
        <div class="form-group">
          <textarea id="descripcion" v-model="productoForm.descripcion" placeholder="Descripción" rows="4" cols="33" maxlength="50"></textarea>
        </div>
        <div class="form-group">
          <select id="categoria" v-model="productoForm.categoria">
            <option v-for="categoria in categorias" :key="categoria.id" :value="categoria">
              {{ categoria.nombre }}
            </option>
          </select>
        </div>
        <div class="form-actions">
          <button type="submit" class="button-standard">Guardar</button>
          <button type="button" class="button-standard" @click="resetForm">Cancelar</button>
        </div>
      </form>
    </div>

    <div class="table-container">
      <h2>Nuestros Productos</h2>
      <table>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Marca</th>
            <th>Sabor</th>
            <th>Envase</th>
            <th>Capacidad(ml)</th>
            <th>Descripción</th>
            <th>Categoría</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="producto in productos" :key="producto.id">
            <td>{{ producto.nombre }}</td>
            <td>{{ producto.marca }}</td>
            <td>{{ producto.sabor }}</td>
            <td>{{ producto.envase }}</td>
            <td>{{ producto.capacidad }} ml</td>
            <td>{{ producto.descripcion }}</td>
            <td>{{ producto.categoria.nombre }}</td>
            <td>
              <button class="button-standard" @click="editProducto(producto)">Editar</button>
              <button class="button-standard" @click="deleteProducto(producto.id)">Eliminar</button>
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
  name: "Productos",
  data() {
    return {
      productos: [],
      categorias: [],
      productoForm: {
        id: null,
        nombre: '',
        marca: '',
        sabor: '',
        envase: '',
        capacidad: 0,
        descripcion: '',
        categoria: null
      },
      isEditing: false
    };
  },
  created() {
    this.fetchCategorias();
    this.fetchProductos();
  },
  methods: {
    async fetchProductos() {
      try {
        const response = await axios.get('/api/productos');
        this.productos = response.data;
      } catch (error) {
        console.error('Error fetching productos:', error);
      }
    },
    async fetchCategorias() {
      try {
        const response = await axios.get('/api/categorias');
        this.categorias = response.data;
      } catch (error) {
        console.error('Error fetching categorias:', error);
      }
    },
    async createProducto() {
      try {
        const response = await axios.post('/api/productos', this.productoForm, {
          headers: {
            'Content-Type': 'application/json'
          }
        });
        this.productos.push(response.data);
        this.resetForm();
      } catch (error) {
        console.error('Error creating producto:', error.response ? error.response.data : error.message);
      }
    },
    async updateProducto() {
      try {
        const response = await axios.put(
          `/api/productos/${this.productoForm.id}`,
          this.productoForm,
          {
            headers: {
              'Content-Type': 'application/json'
            }
          }
        );
        const index = this.productos.findIndex(p => p.id === this.productoForm.id);
        if (index !== -1) {
          this.productos[index] = response.data;
        }
        this.resetForm();
      } catch (error) {
        console.error('Error updating producto:', error.response ? error.response.data : error.message);
      }
    },
    async deleteProducto(id) {
      try {
        await axios.delete(`/api/productos/${id}`);
        this.productos = this.productos.filter(p => p.id !== id);
      } catch (error) {
        console.error('Error deleting producto:', error.response ? error.response.data : error.message);
      }
    },
    editProducto(producto) {
      this.productoForm = { 
        ...producto,
        categoria: producto.categoria
      };
      this.isEditing = true;
    },
    resetForm() {
      this.productoForm = {
        id: null,
        nombre: '',
        marca: '',
        sabor: '',
        envase: '',
        capacidad: 0,
        descripcion: '',
        categoria: null
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
}

.form-container {
  flex: 1;
  margin-right: 20px;
  min-width: 300px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-height: calc(80vh - 60px); /* Ajusta la altura máxima para que no se extienda más allá de la página */
  overflow-y: auto; /* Agrega scroll vertical */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.form-content {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}

.table-container {
  flex: 3; 
  min-height: 400px;
  max-height: calc(80vh - 60px); /* Ajusta la altura máxima para que no se extienda más allá de la página */
  overflow-y: auto; /* Agrega scroll vertical */
  min-width: 300px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  box-sizing: border-box;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
  word-wrap: break-word;
  text-align: center;
  vertical-align: middle; 
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
  width: 100px; 
  height: 40px; 
}

.input-group {
  display: flex;
  justify-content: flex-end; 
  align-items: center;
}

.input-group input {
  flex: 1;
  text-align: right; 
  padding-right: 20px; 
  width: 80px; 
}

.input-group-text {
  margin-left: 8px;
  background-color: #ffffff;
  color: #000000; 
  padding: 8px;
  border: none; 
}

.form-actions {
  display: flex;
  justify-content: space-between;
  margin-top: auto;
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

.small-input-group {
  max-width: 150px; /* Ajusta el ancho máximo del contenedor */
}

.small-input-group input {
  width: 60px; /* Ajusta el ancho del input */
  text-align: right;
}

.small-input-group .input-group-text {
  padding: 8px;
  margin-left: 8px;
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