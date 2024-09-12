describe('Productos', () => {
  before(() => {
    cy.visit('/productos');
  });

  it('should create, edit, and delete the last product', () => {
    // Crear producto
    cy.get('input#nombre').scrollIntoView().should('be.visible').type('Producto de Prueba');
    cy.get('input#marca').scrollIntoView().should('be.visible').type('Marca de Prueba');
    cy.get('input#sabor').scrollIntoView().should('be.visible').type('Sabor de Prueba');
    cy.get('input#envase').scrollIntoView().should('be.visible').type('Envase de Prueba');
    cy.get('input#capacidad').scrollIntoView().should('be.visible').type('500');
    cy.get('textarea#descripcion').scrollIntoView().should('be.visible').type('Descripción del producto de prueba');
    cy.get('select#categoria').scrollIntoView().should('be.visible').select('Refresco'); 
    cy.get('button[type="submit"]').contains('Guardar').scrollIntoView().should('be.visible').click();
    cy.contains('Producto de Prueba').scrollIntoView().should('be.visible');

    // Editar el último producto
    cy.get('tr').last().contains('Editar').scrollIntoView().should('be.visible').click();
    cy.get('input#nombre').scrollIntoView().should('be.visible').clear().type('Producto de Prueba Editado');
    cy.get('button[type="submit"]').contains('Guardar').scrollIntoView().should('be.visible').click();
    cy.contains('Producto de Prueba Editado').scrollIntoView().should('be.visible');

    // Eliminar el último producto
    cy.get('tr').last().contains('Eliminar').scrollIntoView().should('be.visible').click();
    cy.get('.modal-container').should('be.visible');
    cy.get('.modal-footer .btn-primary').contains('Confirmar').click();
    cy.contains('Producto de Prueba Editado').should('not.exist');
  });
});