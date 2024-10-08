CREATE TABLE Categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(35) NOT NULL,
    descripcion VARCHAR(50) NOT NULL,
    is_alcohólica BOOLEAN NOT NULL,
    is00 BOOLEAN NOT NULL,
    is0Azucar BOOLEAN NOT NULL,
    is_natural BOOLEAN NOT NULL
);

CREATE TABLE Producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(35) NOT NULL,
    marca VARCHAR(35) NOT NULL,
    sabor VARCHAR(35) NOT NULL,
    envase VARCHAR(35) NOT NULL,
    capacidad BIGINT NOT NULL CHECK (capacidad > 0),
    descripcion VARCHAR(50) NOT NULL,
    categoria_id BIGINT NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES Categoria(id)
);