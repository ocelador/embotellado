<template>
  <div>
    <h1>Productos</h1>
    <p>Bienvenido a la página de Productos.</p>

    <h2>{{ isEditing ? 'Editar Producto' : 'Añadir Producto' }}</h2>
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
        <label for="capacidad">Capacidad:</label>
        <input type="number" v-model="productoForm.capacidad" required />
      </div>
      <div>
        <label for="descripcion">Descripción:</label>
        <input type="text" v-model="productoForm.descripcion" required />
      </div>
      <div>
        <label for="categoria">Categoría:</label>
        <select v-model="productoForm.categoria" required>
          <option v-for="categoria in categorias" :key="categoria.id" :value="categoria">
            {{ categoria.nombre }}
          </option>
        </select>
      </div>
      <button type="submit">{{ isEditing ? 'Actualizar' : 'Añadir' }} Producto</button>
      <button type="button" @click="resetForm">Cancelar</button>
    </form>

    <br><h2>Lista de Productos</h2>
    <ul>
      <li v-for="producto in productos" :key="producto.id">
        <strong>Nombre:</strong> {{ producto.nombre }}<br>
        <strong>Marca:</strong> {{ producto.marca }}<br>
        <strong>Sabor:</strong> {{ producto.sabor }}<br>
        <strong>Envase:</strong> {{ producto.envase }}<br>
        <strong>Capacidad:</strong> {{ producto.capacidad }}<br>
        <strong>Descripción:</strong> {{ producto.descripcion }}<br>
        <strong>Categoría:</strong> {{ getCategoriaNombre(producto.categoria) }}<br>
        <button @click="editProducto(producto)">Editar</button>
        <button @click="deleteProducto(producto.id)">Eliminar</button>
      </li>
    </ul>
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
        categoria: null // Cambiado a objeto completo
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
        const response = await axios.post('/api/productos', this.productoForm);
        this.productos.push(response.data);
        this.resetForm();
      } catch (error) {
        console.error('Error creating producto:', error.response ? error.response.data : error.message);
      }
    },
    async updateProducto() {
      try {
        const response = await axios.put(`/api/productos/${this.productoForm.id}`, this.productoForm);
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
        categoria: producto.categoria // Asegúrate de que el objeto producto tenga el campo categoria
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
        categoria: null // Cambiado a objeto completo
      };
      this.isEditing = false;
    },
    getCategoriaNombre(categoria) {
      return categoria ? categoria.nombre : 'Sin categoría';
    }
  }
};
</script>

<style scoped>
form {
  margin-bottom: 20px;
}

form div {
  margin-bottom: 10px;
}

button {
  margin-right: 10px;
}
</style>