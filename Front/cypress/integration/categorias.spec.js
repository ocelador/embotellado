describe('Categorias', () => {
  beforeEach(() => {
    cy.visit('/categorias');
  });

  it('should display the form and table', () => {
    cy.get('.form-container').should('be.visible');
    cy.get('.table-container').should('be.visible');
  });

  it('should create a new category', () => {
    cy.get('input[placeholder="Nombre"]').type('Nueva Categoria');
    cy.get('textarea[placeholder="Descripción"]').type('Descripción de la nueva categoría');
    cy.get('button[type="submit"]').click();

    cy.contains('Nueva Categoria').scrollIntoView().should('be.visible');
  });

  it('should edit an existing category', () => {
    cy.contains('Editar').scrollIntoView().click();
    cy.get('input[placeholder="Nombre"]').clear().type('Categoria Editada');
    cy.get('button[type="submit"]').click();

    cy.contains('Categoria Editada').scrollIntoView().should('be.visible');
  });

  it('should delete a category', () => {
    cy.contains('Eliminar').scrollIntoView().click();
    cy.contains('Categoria Editada').should('not.exist');
  });
});