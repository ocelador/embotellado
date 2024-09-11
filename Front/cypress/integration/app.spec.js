describe('App Navigation', () => {
  beforeEach(() => {
    cy.visit('/');
  });

  it('should navigate to Inicio', () => {
    cy.contains('Inicio').click();
    cy.url().should('include', '/');
    cy.contains('Bienvenido a la factoría de embotellados').should('be.visible');
  });

  it('should navigate to Productos', () => {
    cy.contains('Productos').click();
    cy.url().should('include', '/productos');
    cy.get('.form-container').should('be.visible');
  });

  it('should navigate to Categorias', () => {
    cy.contains('Categoría').click();
    cy.url().should('include', '/categorias');
    cy.get('.form-container').should('be.visible');
  });
});