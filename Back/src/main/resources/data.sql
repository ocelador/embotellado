INSERT INTO Categoria (nombre, descripcion, is_alcohólica, is00, is0Azucar, is_natural) 
VALUES ('Vino', 'Bebida alcohólica', TRUE, FALSE, FALSE, TRUE);
INSERT INTO Categoria (nombre, descripcion, is_alcohólica, is00, is0Azucar, is_natural) 
VALUES ('Cerveza', 'Bebida alcohólica', TRUE, FALSE, FALSE, TRUE);
INSERT INTO Categoria (nombre, descripcion, is_alcohólica, is00, is0Azucar, is_natural) 
VALUES ('Refresco', 'Bebida no alcohólica', FALSE, TRUE, TRUE, FALSE);
INSERT INTO Categoria (nombre, descripcion, is_alcohólica, is00, is0Azucar, is_natural) 
VALUES ('Zumo', 'Bebida natural', FALSE, FALSE, TRUE, TRUE);
INSERT INTO Categoria (nombre, descripcion, is_alcohólica, is00, is0Azucar, is_natural) 
VALUES ('Agua', 'Bebida natural', FALSE, TRUE, TRUE, TRUE);
INSERT INTO Categoria (nombre, descripcion, is_alcohólica, is00, is0Azucar, is_natural) 
VALUES ('Energética', 'Bebida energética', FALSE, FALSE, TRUE, FALSE);
INSERT INTO Categoria (nombre, descripcion, is_alcohólica, is00, is0Azucar, is_natural) 
VALUES ('Té', 'Bebida natural', FALSE, TRUE, TRUE, TRUE);
INSERT INTO Categoria (nombre, descripcion, is_alcohólica, is00, is0Azucar, is_natural) 
VALUES ('Café', 'Bebida estimulante', FALSE, TRUE, TRUE, TRUE);

INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Coca Cola', 'Coca Cola Company', 'Cola', 'Lata', 500, 'Refresco de cola', 3);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Pepsi', 'PepsiCo', 'Cola', 'Lata', 500, 'Refresco de cola', 3);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Corona', 'Grupo Modelo', 'Lager', 'Botella', 355, 'Cerveza clara', 2);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Fanta Naranja', 'Coca Cola Company', 'Naranja', 'Lata', 500, 'Refresco de naranja', 3);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Minute Maid', 'Coca Cola Company', 'Naranja', 'Botella', 500, 'Zumo de naranja', 4);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Tropicana', 'PepsiCo', 'Manzana', 'Botella', 500, 'Zumo de manzana', 4);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Sprite', 'Coca Cola Company', 'Limón', 'Lata', 500, 'Refresco de limón', 3);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Red Bull', 'Red Bull GmbH', 'Original', 'Lata', 250, 'Bebida energética', 6);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Lipton Ice Tea', 'Lipton', 'Limón', 'Botella', 500, 'Té helado de limón', 7);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Nescafé', 'Nestlé', 'Original', 'Botella', 250, 'Café listo para beber', 8);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Heineken', 'Heineken N.V.', 'Lager', 'Botella', 330, 'Cerveza clara', 2);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Aquafina', 'PepsiCo', 'Sin sabor', 'Botella', 500, 'Agua embotellada', 5);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Powerade', 'Coca Cola Company', 'Mountain Blast', 'Botella', 500, 'Bebida deportiva', 6);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Nestea', 'Nestlé', 'Durazno', 'Botella', 500, 'Té helado de durazno', 7);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Monster Energy', 'Monster Beverage', 'Original', 'Lata', 500, 'Bebida energética', 6);
INSERT INTO Producto (nombre, marca, sabor, envase, capacidad, descripcion, categoria_id) 
VALUES ('Gatorade', 'PepsiCo', 'Naranja', 'Botella', 500, 'Bebida deportiva', 6);