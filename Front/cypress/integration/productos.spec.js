describe('Productos', () => {
  beforeEach(() => {
    cy.visit('/productos');
  });

  it('should display the form and table', () => {
    cy.get('.form-container').should('be.visible');
    cy.get('.table-container').should('be.visible');
  });

  it('should create a new product', () => {
    cy.get('input#nombre').should('be.visible').type('Producto de Prueba');
    cy.get('input#marca').should('be.visible').type('Marca de Prueba');
    cy.get('input#sabor').should('be.visible').type('Sabor de Prueba');
    cy.get('input#envase').should('be.visible').type('Envase de Prueba');
    cy.get('input#capacidad').should('be.visible').type('500');
    cy.get('textarea#descripcion').should('be.visible').type('Descripción del producto de prueba');
    cy.get('select#categoria').should('be.visible').select('Refresco'); // Ajusta según el nombre de la categoría
    cy.get('button[type="submit"]').contains('Agregar Bebida').scrollIntoView().should('be.visible').click();

    cy.contains('Producto de Prueba').scrollIntoView().should('be.visible');
  });

  it('should edit the product', () => {
    cy.contains('Producto de Prueba').scrollIntoView().should('be.visible').parent().contains('Editar').scrollIntoView().should('be.visible').click();
    cy.get('input#nombre').should('be.visible').clear().type('Producto de Prueba Editado');
    cy.get('button[type="submit"]').contains('Actualizar Bebida').scrollIntoView().should('be.visible').click();

    cy.contains('Producto de Prueba Editado').scrollIntoView().should('be.visible');
  });

  it('should delete the product', () => {
    cy.contains('Producto de Prueba Editado').scrollIntoView().should('be.visible').parent().contains('Eliminar').scrollIntoView().should('be.visible').click();
    cy.contains('Producto de Prueba Editado').should('not.exist');
  });
});