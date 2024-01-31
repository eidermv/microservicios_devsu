--  Insert de Persona
INSERT INTO persona (identificacion,nombre,genero,edad,direccion,telefono)
VALUES (123456,'Jose Lema','Masculino',31,'Portal villa esperanza',3124567890),
       (123457,'Marinela Montalvo','Femenino',25,'Avenida rojas',3216549878),
       (321987,'Juan Osorio','Masculino',40,'Calle 13 # 15-21',3209874567);

--  Insert de Cliente
INSERT INTO cliente (identificacion,contrasena,estado)
VALUES (123456,'1234',1),(123457,'1245',1),(321987,'3214',1);

--  Insert de Cuenta
INSERT INTO cuenta (numero_cuenta,clienteid,tipo,saldo_inicial,estado)
VALUES (484878,1,'Ahorros',2000,1),
       (565698,2,'Corriente',100,1),
       (363534,3,'Ahorros',0,1),
       (152535,2,'Ahorros',540,1);

--  Insert de Movimiento
INSERT INTO movimiento (fecha,tipo,valor,saldo,numero_cuenta)
VALUES ('2022-10-02','Corriente',600,700,565698),
       ('2022-08-02','Ahorros',-540,0,152535),
       ('2023-10-05','Ahorros',-575,1425,484878),
       ('2025-01-06','Ahorros',150,150,363534);
