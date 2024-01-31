CREATE TABLE persona (
                         identificacion INT NOT NULL,
                         nombre varchar(100) NOT NULL,
                         genero varchar(15) NULL,
                         edad INT NULL,
                         direccion varchar(100) NULL,
                         telefono BIGINT NULL,
                         CONSTRAINT Persona_pk PRIMARY KEY (identificacion)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb3
COLLATE=utf8mb3_general_ci;

CREATE TABLE cliente (
                         clienteid INT auto_increment NOT NULL,
                         identificacion INT NOT NULL,
                         contrasena varchar(100) NOT NULL,
                         estado BOOL NOT NULL,
                         CONSTRAINT cliente_pk PRIMARY KEY (clienteid),
                         CONSTRAINT cliente_persona_FK FOREIGN KEY (identificacion) REFERENCES persona(identificacion)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb3
COLLATE=utf8mb3_general_ci;

CREATE TABLE cuenta (
                        numero_cuenta INT NOT NULL,
                        clienteid INT NOT NULL,
                        tipo varchar(15) NOT NULL,
                        saldo_inicial INT NOT NULL,
                        estado BOOL NOT NULL,
                        CONSTRAINT cuenta_pk PRIMARY KEY (numero_cuenta),
                        CONSTRAINT cuenta_cliente_FK FOREIGN KEY (clienteid) REFERENCES cliente(clienteid)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb3
COLLATE=utf8mb3_general_ci;

CREATE TABLE movimiento (
                            movimientoid INT auto_increment NOT NULL,
                            fecha DATE NOT NULL,
                            tipo varchar(100) NOT NULL,
                            valor INT NOT NULL,
                            saldo INT NOT NULL,
                            numero_cuenta INT NOT NULL,
                            CONSTRAINT movimiento_pk PRIMARY KEY (movimientoid),
                            CONSTRAINT movimiento_cuenta_FK FOREIGN KEY (numero_cuenta) REFERENCES cuenta(numero_cuenta)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb3
COLLATE=utf8mb3_general_ci;
