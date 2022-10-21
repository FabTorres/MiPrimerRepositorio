CREATE SCHEMA if NOT EXISTS ejercicio1_practica;
use ejercicio1_practica;

create table Almacen(nro int primary key,
nombre varchar(40) not null,
responsable varchar(40) not null);

create table Articulo(cod_art int primary key,
descripcion varchar(40) not null,
precio decimal(7,2)); -- hacerlo not null

create table Material(cod_mat int primary key,
descripcion varchar(40) not null);

create table ciudad(cod_ciu int primary key,
nombre varchar(40) not null);

create table proveedor(cod_prov int primary key,
nombre varchar(40) not null,
domicilio varchar(40) not null,
cod_ciu int references ciudad(cod_ciu),
f_alta date not null);

CREATE TABLE contiene (
    nro INT references Almacen(nro),
    cod_art INT references articulo(cod_art),
    PRIMARY KEY (nro , cod_art));
    
    create table Compuesto_por(cod_art int references Articulo(cod_art),
    cod_mat int references Material(cod_mat),
    constraint pk_compuesto_por primary key(cod_art,cod_mat));
    
    create table Provisto_por(cod_mat int references Material(cod_mat),
    cod_prov int references proveedor (cod_prov),
    constraint pk_Porvisto_por primary key(cod_prov,cod_mat));
    
    SELECT 
    *
FROM
    information_schema.table_constraints
WHERE
    constraint_schema = 'ejercicio1_practica';   
    
    insert into Almacen (nro,nombre,responsable) values (1,'el chino','esteban');
    insert into Almacen (nro,nombre,responsable) values (2,'VEA','PATRICIA');
    insert into Almacen (nro,nombre,responsable) values (3,'CARREFOUR','esteban');
    insert into Almacen (nro,nombre,responsable) values (4,'JUMBO','esteban');
    
    INSERT INTO Articulo (cod_art,descripcion,precio) values (1,'A1',20);
    INSERT INTO Articulo (cod_art,descripcion,precio) values (2,'A2',30);
    INSERT INTO Articulo (cod_art,descripcion,precio) values (3,'A3',40);
    
    -- actualizar datos de una tabla
    update Articulo set precio = 100 where cod_art = 1 or cod_art = 3;
	update Articulo set precio = 100 where cod_art in (1,3);
    
    
    insert into Material (cod_mat,descripcion) values (1,'M1');
    insert into Material (cod_mat,descripcion) values (2,'M2');
    insert into Material (cod_mat,descripcion) values (3,'M3');
    
    -- otra forma de insert
     insert into Material (cod_mat,descripcion) 
     values (1,'M1') ,
			(2,'M2'),
			(3,'M3');    
    
    insert into Ciudad (cod_ciu, nombre) values (1,'CABA');
    insert into Ciudad (cod_ciu, nombre) values (2,'CABA');
    insert into Ciudad (cod_ciu, nombre) values (3,'MORON');
    insert into Ciudad (cod_ciu, nombre) values (4,'MERLO');
    insert into Ciudad (cod_ciu, nombre) values (5,'La Plata');
    insert into Ciudad (cod_ciu, nombre) values (6,'ROSARIO');

    
    insert into proveedor (cod_prov, nombre, domicilio, cod_ciu, f_alta)
    values (1,'Alimentos','Av. Rivadavia 1456',1,'2022-10-04');
     insert into proveedor (cod_prov, nombre, domicilio, cod_ciu, f_alta)
    values (2,'Bebidas','Suipacha 2022',3,'2001-10-04');
      insert into proveedor (cod_prov, nombre, domicilio, cod_ciu, f_alta)
    values (3,'Bebidas','Suipacha 25',3,'2008-10-04');
    insert into proveedor (cod_prov, nombre, domicilio, cod_ciu, f_alta)
    values (4,'Limpieza','Suipacha 25',5,'2008-10-04');
    insert into proveedor (cod_prov, nombre, domicilio, cod_ciu, f_alta)
    values (5,'Comida Perro','Av. Rivadavia 1456',6,'2022-10-04');
    insert into proveedor (cod_prov, nombre, domicilio, cod_ciu, f_alta)
    values (6,'Comida Perro','Av. Rivadavia 1456',6,'2022-10-04');
    
    
    insert into provisto_por (cod_mat, cod_prov) values (1,6),(2,5),(3,6);
    
	INSERT INTO contiene (nro, cod_art) values(1, 1);
	INSERT INTO contiene (nro, cod_art) values(2, 2);
	INSERT INTO compuesto_por (cod_art, cod_mat) values(1, 1);
    
/* consulta 1: Listar los números de artículos cuyo precio se encuentre entre $100 y $1000 y su
descripción comience con la letra A.*/ 
select cod_art, precio, descripcion
from Articulo
where precio between 100 and 1000 and descripcion like 'A%';

/* consulta 2: Listar todos los datos de todos los proveedores.*/
select *
from proveedor;

/*consulta 3: Listar la descripción de los materiales de código 1, 3, 6, 9 y 18.*/
select descripcion
from Material
where cod_mat in (1,3,6,9,18);

/*consulta 4: Listar código y nombre de proveedores de la calle Suipacha, que hayan sido dados
de alta en el año 2001.*/
select cod_prov, nombre
from proveedor
where domicilio like '%Suipacha%' and year (f_alta) = 2001;/*f_alta between '2001-01-01' and '2001-12-31'*/

/*consulta 5: listar nombre de todos lo sproveedores y su ciudad*/
SELECT prov.nombre, ciu.nombre
FROM proveedor prov 
LEFT JOIN ciudad ciu ON prov.cod_ciu = ciu.cod_ciu;

/*consulta 6: Listar los nombres de los proveedores de la ciudad de La Plata*/
SELECT prov.nombre
FROM proveedor prov
JOIN ciudad ciu ON prov.cod_ciu = ciu.cod_ciu
WHERE ciu.nombre like '%La Plata';

/*consulta 7: Listar los números de almacenes que almacenan el artículo de descripción A*/
SELECT DISTINCT A.nro
FROM almacen A
JOIN contiene C ON a.nro = C.nro
JOIN articulo art ON art.cod_art = C.cod_art
WHERE art.descripcion like '%A%';

/*consulta 8: Listar los materiales (código y descripción) provistos por proveedores de la ciudad
de Rosario.*/
SELECT DISTINCT M.cod_mat, M.descripcion
FROM material M
JOIN provisto_por PP ON M.cod_mat = PP.cod_mat
JOIN proveedor P ON PP.cod_prov = P.cod_prov
JOIN ciudad C ON P.cod_ciu = C.cod_ciu
WHERE C.nombre like '%ROSARIO';

/*consulta 9: Listar los nombres de los proveedores que proveen materiales para artículos
ubicados en almacenes que Martín Gómez tiene a su cargo.*/
SELECT DISTINCT P.nombre
FROM proveedor P
JOIN provisto_por PP ON P.cod_prov = PP.cod_prov
/*JOIN material M ON M.cod_mat = PP.cod_mat*/
JOIN compuesto_por CP ON CP.cod_mat = PP.cod_mat
JOIN contiene CONT ON CP.cod_art = CONT.cod_art
JOIN almacen A ON CONT.nro = A.nro
WHERE A.responsable like 'Martin%Gomez' or A.responsable like 'Gomez%Martin';

/*consulta 10:*/
SELECT * 
FROM contiene;
INSERT INTO contiene VALUES (10,9), (10,6), (10,4), (10,3);
UPDATE contiene set nro = 20
WHERE nro = 10;

INSERT INTO proveedor VALUES (8, 'ABCDARIO', 'Magnasco 1826', 3, 20000908);
INSERT INTO proveedor VALUES (10, 'ABCDERLIN', 'JORGELITO 1234', 2, 20101201);

/*consulta 11:*/
SELECT * 
FROM proveedor;
DELETE FROM proveedor
WHERE proveedor.nombre like '%ABC%';

/*consulta 12: indicar la cantidad de proveedores que comienzan con la letra F*/
INSERT INTO proveedor VALUES (11, 'FANTA SA', 'COLOMBIA 2000', 3, 20060423);
INSERT INTO proveedor VALUES (12, 'FANACOA', 'JORGELITO 1234', 1, 20040205);
SELECT COUNT(*) as 'cantidad de proveedores'
FROM proveedor
WHERE proveedor.nombre like 'F&' or proveedor.nombre LIKE 'f%';

/*consulta 13: listar el promedio de precios de los articulos por cada almacen (nombre)*/
INSERT contiene values
(1,2), (2,1);
SELECT A.nombre as 'almacen',
AVG(art.precio) as 'precio promedio'
FROM almacen A 
JOIN contiene C ON A.nro = C.nro
JOIN articulo art ON C.cod_art = art.cod_art
GROUP BY A.nro, A.nombre;

/*consulta 14: listar la descripcion de articulos compuestos por al menos dos materiales*/
SELECT A.descripcion
FROM articulo A 
JOIN compuesto_por C ON A.cod_art = C.cod_art
GROUP BY A.cod_art, A.descripcion
HAVING COUNT(*) >=2;

/*consulta 15: listar cantidad de materiales que provee cada proveedor (codigo, nombre y domicilio)*/
SELECT P.cod_prov, P.nombre, P.domicilio,
COUNT(PP.cod_mat) as 'cantidad de materiales'
FROM proveedor P JOIN provisto_por PP ON P.cod_prov = PP.cod_prov
GROUP BY P.cod_prov, P.nombre, P.domicilio

/*consulta 16: cual es el precio maximo de los articulos que proveen los proveedores de la ciudad de Zarate*/
INSERT INTO ciudad VALUES (8, 'Zárate');
INSERT INTO proveedor VALUES (13, 'CocaCola', 'Estanislao del Campo 1925', 8, 20121203);
INSERT INTO provisto_por VALUES (3, 13);
INSERT INTO contiene VALUES (1,3);
INSERT INTO compuesto_por VALUES (2,3);
SELECT max(A.precio)
FROM proveedor P 
JOIN provisto_por PP ON P.cod_prov= PP.cod_prov
JOIN compuesto_por CP ON PP.cod_mat = CP.cod_mat
JOIN articulo A ON CP.cod_art = A.cod_art
JOIN ciudad C ON C.cod_ciu = P.cod_ciu
WHERE C.nombre LIKE 'Zárate%'; 