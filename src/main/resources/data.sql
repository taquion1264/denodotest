-- Eliminar tabla existente si existe
DROP TABLE IF EXISTS precios;
DROP TABLE IF EXISTS compras;
DROP TABLE IF EXISTS usuarios;

-- Crear tabla de usuarios solo si no existe
CREATE TABLE IF NOT EXISTS usuarios (
                                        user_id BIGINT PRIMARY KEY,
                                        age INT
);

-- Insertar datos en la tabla de usuarios
INSERT INTO usuarios (user_id, age) VALUES
                                        (101, 18),
                                        (102, 32),
                                        (103, 44),
                                        (104, 38),
                                        (105, 42);

-- Crear tabla de compras solo si no existe
CREATE TABLE IF NOT EXISTS compras (
                                       compra_id BIGINT PRIMARY KEY,
                                       user_id BIGINT,
                                       fecha_compra DATE,
                                       hora TIME,
                                       importe_total DECIMAL,
                                       FOREIGN KEY (user_id) REFERENCES usuarios(user_id)
    );

-- Insertar datos en la tabla de compras
INSERT INTO compras (compra_id, user_id, fecha_compra, hora, importe_total) VALUES
                                                                                (201, 104, '2023-11-22', '10:36', 78),
                                                                                (202, 105, '2023-11-22', '12:56', 131),
                                                                                (203, 104, '2023-11-22', '16:41', 99),
                                                                                (204, 102, '2023-11-20', '18:03', 26),
                                                                                (205, 101, '2023-11-21', '19:53', 165),
                                                                                (206, 101, '2023-11-20', '20:44', 125),
                                                                                (207, 105, '2023-11-20', '22:06', 217),
                                                                                (208, 105, '2023-11-21', '22:28', 177),
                                                                                (209, 104, '2023-11-22', '23:11', 86),
                                                                                (210, 105, '2023-11-23', '23:20', 164);

-- Crear índice en la columna user_id de la tabla compras
CREATE INDEX IF NOT EXISTS idx_user_id ON compras (user_id);

-- Crear tabla de precios solo si no existe
CREATE TABLE IF NOT EXISTS precios (
                                       item_id BIGINT PRIMARY KEY,
                                       precio_item DECIMAL
);

-- Insertar datos en la tabla de precios
INSERT INTO precios (item_id, precio_item) VALUES
                                               (1, 52),
                                               (2, 26),
                                               (3, 99),
                                               (4, 86),
                                               (5, 79);

-- Crear índice en la columna item_id de la tabla precios
CREATE INDEX IF NOT EXISTS idx_item_id ON precios (item_id);
