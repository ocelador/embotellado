describe('Inicio', () => {
  beforeEach(() => {
    cy.visit('/');
  });

  it('should display the welcome message', () => {
    cy.contains('Bienvenido a la factoria de embotellados').should('be.visible');
  });

  it('should display the welcome image', () => {
    cy.get('img[alt="Imagen de bienvenida"]').should('be.visible');
  });
});