describe('Categorias', () => {
  beforeEach(() => {
    cy.visit('/categorias');
  });

  it('should create, edit, and delete the last category', () => {
    // Crear categoría
    cy.get('input[placeholder="Nombre"]').scrollIntoView().should('be.visible').type('Nueva Categoria');
    cy.get('textarea[placeholder="Descripción"]').scrollIntoView().should('be.visible').type('Descripción de la nueva categoría');
    cy.get('button[type="submit"]').scrollIntoView().should('be.visible').click();
    cy.contains('Nueva Categoria').scrollIntoView().should('be.visible');

    // Editar la última categoría
    cy.get('tr').last().contains('Editar').scrollIntoView().should('be.visible').click();
    cy.get('input[placeholder="Nombre"]').scrollIntoView().should('be.visible').clear().type('Categoria Editada');
    cy.get('button[type="submit"]').scrollIntoView().should('be.visible').click();
    cy.contains('Categoria Editada').scrollIntoView().should('be.visible');

    // Eliminar la última categoría
    cy.get('tr').last().contains('Eliminar').scrollIntoView().should('be.visible').click();
    cy.get('.modal-container').should('be.visible');
    cy.get('.modal-footer .btn-primary').contains('Confirmar').click();
    cy.contains('Categoria Editada').should('not.exist');
  });
});