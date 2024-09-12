<template>
  <div class="container">
    <div v-if="showMessage" class="message">{{ messageText }}</div>
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
          <button type="submit" class="button-standard button-save">Guardar</button>
          <button type="button" class="button-standard button-cancel" @click="resetForm">Cancelar</button>
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
            <td class="capacidad-cell">{{ producto.capacidad }}</td>
            <td>{{ producto.descripcion }}</td>
            <td>{{ producto.categoria.nombre }}</td>
            <td>
              <button class="button-standard button-edit" @click="editProducto(producto)">Editar</button>
              <button class="button-standard button-delete" @click="showDeleteModal(producto.id)">Eliminar</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <Modal
      :visible="isModalVisible"
      title="Confirmar Eliminación"
      message="¿Estás seguro de que deseas eliminar este producto?"
      @cancel="isModalVisible = false"
      @confirm="confirmDeleteProducto"
    />
  </div>
</template>

<script>
import axios from 'axios';
import Modal from '@/components/Modal.vue';

export default {
  name: "Productos",
  components: {
    Modal
  },
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
      isEditing: false,
      isModalVisible: false,
      productoToDelete: null,
      showMessage: false,
      messageText: ''
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
        this.showMessage = true;
        this.messageText = 'Producto creado exitosamente';
        setTimeout(() => {
          this.showMessage = false;
        }, 5000);
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
        this.showMessage = true;
        this.messageText = 'Producto actualizado exitosamente';
        setTimeout(() => {
          this.showMessage = false;
        }, 5000);
      } catch (error) {
        console.error('Error updating producto:', error.response ? error.response.data : error.message);
      }
    },
    showDeleteModal(id) {
      this.productoToDelete = id;
      this.isModalVisible = true;
    },
    async confirmDeleteProducto() {
      try {
        await axios.delete(`/api/productos/${this.productoToDelete}`);
        this.productos = this.productos.filter(p => p.id !== this.productoToDelete);
        this.isModalVisible = false;
        this.productoToDelete = null;
        this.showMessage = true;
        this.messageText = 'Producto eliminado exitosamente';
        setTimeout(() => {
          this.showMessage = false;
        }, 5000);
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
  background-color: #e0e0e0;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(176, 176, 176, 0.5);
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
  box-shadow: 0 4px 8px rgba(176, 176, 176, 0.5);
  box-sizing: border-box;
  max-height: calc(80vh - 60px); 
  overflow-y: auto; 
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
  min-width: 300px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(176, 176, 176, 0.5);
  box-sizing: border-box;
  max-height: calc(80vh - 60px); 
  overflow-y: auto; 
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #b0b0b0;
  padding: 8px;
  word-wrap: break-word;
  text-align: center; 
  vertical-align: middle;
}

th {
  background-color: #34495e !important;
  color: #ffffff;
  text-align: left;
}

td {
  max-width: 120px;
  white-space: normal;
}

.capacidad-cell {
  text-align: left;
}

button {
  color: #ffffff;
  border: none;
  padding: 10px 20px;
  margin: 5px 0;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
}

.button-standard {
  width: 100px;
  height: 40px; 
}

.button-edit {
  background-color: #28a745; 
}

.button-edit:hover {
  background-color: #218838;
}

.button-edit:active {
  background-color: #1e7e34;
}

.button-delete {
  background-color: #dc3545;
}

.button-delete:hover {
  background-color: #c82333;
}

.button-delete:active {
  background-color: #bd2130;
}

.button-save {
  background-color: #007bff; 
}

.button-save:hover {
  background-color: #0056b3;
}

.button-save:active {
  background-color: #004085;
}

.button-cancel {
  background-color: #ffc107; 
}

.button-cancel:hover {
  background-color: #e0a800;
}

.button-cancel:active {
  background-color: #c69500; 
}

.symbol {
  font-size: 1.5em; 
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
  border: 1px solid #b0b0b0;
  border-radius: 4px;
  box-sizing: border-box;
}

.form-group select {
  background-color: #ffffff; 
}

.input-group {
  display: flex;
  align-items: center;
}

.input-group input {
  flex: 1;
}

.input-group-text {
  margin-left: 5px;
  font-size: 0.9em;
  border: none;
  background-color: transparent;
}

.small-input-group input {
  flex: 0.5;
}

.checkbox-group {
  display: flex;
  align-items: center;
}

.checkbox-group label {
  display: flex;
  align-items: center;
  white-space: nowrap;
}

.checkbox-group input {
  margin-right: 10px;
}

.form-actions {
  display: flex;
  justify-content: space-between;
  margin-top: auto;
}
.message {
  position: fixed;
  top: 20px;
  right: 20px;
  background-color: #4caf50;
  color: white;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  z-index: 1000;
}

@media (max-width: 768px) {
  .container {
    flex-direction: column;
  }

  .form-container, .table-container {
    margin-right: 0;
    margin-bottom: 20px;
  }

  .container {
    overflow-x: auto; 
  }

  .button-edit, .button-delete {
    width: 80px;
    height: 30px;
    font-size: 12px; 
  }
}

@media (max-width: 480px) {
  th, td {
    padding: 4px;
  }

  td {
    max-width: 100px;
  }

  .container {
    overflow-x: auto;
  }

  .button-edit, .button-delete {
    width: 60px; 
    height: 25px; 
    font-size: 10px; 
  }
}
</style>