<template>
  <div class="container">
    <div class="form-container">
      <h2>{{ isEditing ? 'Editar Bebida' : 'Agregar Bebida' }}</h2>
      <form @submit.prevent="isEditing ? updateProducto() : createProducto()">
        <div>
          <label for="nombre">Nombre:</label>
          <input type="text" v-model="productoForm.nombre" required />
        </div>
        <div>
          <label for="marca">Marca:</label>
          <input type="text" v-model="productoForm.marca" required />
        </div>
        <div>
          <label for="sabor">Sabor:</label>
          <input type="text" v-model="productoForm.sabor" required />
        </div>
        <div>
          <label for="envase">Envase:</label>
          <input type="text" v-model="productoForm.envase" required />
        </div>
        <div>
          <label for="capacidad">Capacidad (ml):</label>
          <input type="number" v-model="productoForm.capacidad" min="0" required />
        </div>
        <div>
          <label for="descripcion">Descripción:</label><br>
          <textarea v-model="productoForm.descripcion" rows="4" cols="50"></textarea>
        </div>
        <div>
          <label for="categoria">Categoría:</label>
          <select v-model="productoForm.categoria">
            <option v-for="categoria in categorias" :key="categoria.id" :value="categoria">
              {{ categoria.nombre }}
            </option>
          </select>
        </div>
        <button type="submit">{{ isEditing ? 'Actualizar' : 'Agregar' }} Bebida</button>
        <button type="button" @click="resetForm">Cancelar</button>
      </form>
    </div>

    <div class="table-container">
      <h2>Nuestras bebidas</h2>
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
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="producto in productos" :key="producto.id">
            <td>{{ producto.nombre }}</td>
            <td>{{ producto.marca }}</td>
            <td>{{ producto.sabor }}</td>
            <td>{{ producto.envase }}</td>
            <td>{{ producto.capacidad }}</td>
            <td>{{ producto.descripcion }}</td>
            <td>{{ producto.categoria.nombre}}</td>
            <td>
              <button @click="editProducto(producto)">Editar</button>
              <button @click="deleteProducto(producto.id)">Eliminar</button>
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
  padding: 4px; 
  word-wrap: break-word; 
}

th {
  background-color: #f2f2f2;
  text-align: left;
}

td {
  max-width: 120px; 
  white-space: normal; 
}
</style>