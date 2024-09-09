INSERT INTO Categoria (nombre, descripcion, is_alcohólica, is00, is0Azucar, is_natural) 
VALUES ('Vino', 'Bebida alcohólica', TRUE, FALSE, FALSE, TRUE);
INSERT INTO Categoria (nombre, descripcion, is_alcohólica, is00, is0Azucar, is_natural) 
VALUES ('Cerveza', 'Bebida alcohólica', TRUE, FALSE, FALSE, TRUE);
INSERT INTO Categoria (nombre, descripcion, is_alcohólica, is00, is0Azucar, is_natural) 
VALUES ('Refresco', 'Bebida no alcohólica', FALSE, TRUE, TRUE, FALSE);
INSERT INTO Categoria (nombre, descripcion, is_alcohólica, is00, is0Azucar, is_natural) 
VALUES ('Zumo', 'Bebida natural', FALSE, FALSE, TRUE, TRUE);

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