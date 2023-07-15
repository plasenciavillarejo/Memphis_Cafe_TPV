-- Inicio Desarrollo: 17/02/2023 - 14:41

----------------------------
-- CREACION BASE DE DATOS -- 
----------------------------

CREATE DATABASE IF NOT EXISTS Memphis_Cafe;

use Memphis_Cafe;


/*

var valorObjeto = $(this).parent().text();
var nuevaCadena = valorObjeto.replace(/[\n\t]/g, '').replace('x','').replace('€', '').replace('-', '');




var parte1 = nuevaCadena.replace('-','').replace('€','');
var segundaParte = partes[1];
//var parte2 = segundaParte.split(' x ');
//var arrayFinal = parte2[1].replace(",", ".")
//.replace("€", "").split(' ')
/*
var producto = arrayFinal[1]
var precio = arrayFinal[0]
*/

----------------------------------------------------------------
--	  # LISTA BEBIDA ALMACENA DURANTE EL PROCESO DE PAGAR #.  --
----------------------------------------------------------------
DROP TABLE IF EXISTS `ListaBebidaAlmacenada`;
CREATE TABLE `ListaBebidaAlmacenada` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombreBebida` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  `nombreTabla` varchar(100) NOT NULL,
  `total` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



----------------------------------------------------------------
--	  # LISTA COMIDA ALMACENA DURANTE EL PROCESO DE PAGAR #.  --
----------------------------------------------------------------
DROP TABLE IF EXISTS `ListaComidaAlmacenada`;
CREATE TABLE `ListaComidaAlmacenada` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombreComida` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  `nombreTabla` varchar(100) NOT NULL,	
  `total` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




------------------------------
--	  # LOGOS_INICIALES #.  --
------------------------------
DROP TABLE IF EXISTS `LogosPrincipales`;
CREATE TABLE `LogosPrincipales` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `Memphis_Cafe`.`LogosPrincipales`(`nombre`) VALUES ('Bebidas');

-----------------------
--	  # DESAYUNOS #.  --
-----------------------
DROP TABLE IF EXISTS `Desayunos`;
CREATE TABLE `Desayunos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio_media` varchar(100) NOT NULL,
  `precio_entera` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `Memphis_Cafe`.`Desayunos`(`nombre`,`precio_media`,`precio_entera`) VALUES ('Tostada con aceite','1,10','2,20');
INSERT INTO `Memphis_Cafe`.`Desayunos`(`nombre`,`precio_media`,`precio_entera`) VALUES ('Tostada con aceite y tomate','1,30','2,60');
INSERT INTO `Memphis_Cafe`.`Desayunos`(`nombre`,`precio_media`,`precio_entera`) VALUES ('Tostada con jamón','1,90','3,80');
INSERT INTO `Memphis_Cafe`.`Desayunos`(`nombre`,`precio_media`,`precio_entera`) VALUES ('Tostada con jamón cocido','1,90','3,90');
INSERT INTO `Memphis_Cafe`.`Desayunos`(`nombre`,`precio_media`,`precio_entera`) VALUES ('Tostada con queso','1,90','3,90');
INSERT INTO `Memphis_Cafe`.`Desayunos`(`nombre`,`precio_media`,`precio_entera`) VALUES ('Tostada con atún','1,90','3,90');
INSERT INTO `Memphis_Cafe`.`Desayunos`(`nombre`,`precio_media`,`precio_entera`) VALUES ('Tostada con caballa','1,90','3,90');
INSERT INTO `Memphis_Cafe`.`Desayunos`(`nombre`,`precio_media`,`precio_entera`) VALUES ('Tostada con patés','1,90','3,90');
INSERT INTO `Memphis_Cafe`.`Desayunos`(`nombre`,`precio_media`,`precio_entera`) VALUES ('Gratinada','2,70','5,00');
INSERT INTO `Memphis_Cafe`.`Desayunos`(`nombre`,`precio_media`,`precio_entera`) VALUES ('Mantequilla y mermelada','1,90','3,90');
INSERT INTO `Memphis_Cafe`.`Desayunos`(`nombre`,`precio_media`,`precio_entera`) VALUES ('Pulga de jamón','1,40','2,80');
INSERT INTO `Memphis_Cafe`.`Desayunos`(`nombre`,`precio_media`,`precio_entera`) VALUES ('Pulga de tomate','1,00','2,00');




-------------------------------
--	  # CAFES/INFUSIONES #	 --
-------------------------------

DROP TABLE IF EXISTS Cafes_Carajillos_Infusiones;
CREATE TABLE `Cafes_Carajillos_Infusiones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Cafes_Carajillos_Infusiones`(`nombre`,`precio`) VALUES ('Café solo','1,30');
INSERT INTO `Memphis_Cafe`.`Cafes_Carajillos_Infusiones`(`nombre`,`precio`) VALUES ('Café cortado','1,30');
INSERT INTO `Memphis_Cafe`.`Cafes_Carajillos_Infusiones`(`nombre`,`precio`) VALUES ('Café con leche','1,40');
INSERT INTO `Memphis_Cafe`.`Cafes_Carajillos_Infusiones`(`nombre`,`precio`) VALUES ('Café bombón','1,40');
INSERT INTO `Memphis_Cafe`.`Cafes_Carajillos_Infusiones`(`nombre`,`precio`) VALUES ('Carajillo normal','1,80');
INSERT INTO `Memphis_Cafe`.`Cafes_Carajillos_Infusiones`(`nombre`,`precio`) VALUES ('Carajillo importación','1,80');
INSERT INTO `Memphis_Cafe`.`Cafes_Carajillos_Infusiones`(`nombre`,`precio`) VALUES ('Irlandes','4,00');
INSERT INTO `Memphis_Cafe`.`Cafes_Carajillos_Infusiones`(`nombre`,`precio`) VALUES ('Infusiones','1,40');
INSERT INTO `Memphis_Cafe`.`Cafes_Carajillos_Infusiones`(`nombre`,`precio`) VALUES ('Té','1,50');
INSERT INTO `Memphis_Cafe`.`Cafes_Carajillos_Infusiones`(`nombre`,`precio`) VALUES ('Capuchino','2,00');
INSERT INTO `Memphis_Cafe`.`Cafes_Carajillos_Infusiones`(`nombre`,`precio`) VALUES ('Chocolate','1,90');


-----------------------
--	  # BEBIDAS #	 --
-----------------------
DROP TABLE IF EXISTS Refrescos;
CREATE TABLE `Refrescos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Coca Cola','2,00');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Coca Cola Zero','2,00');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Coca Cola Zero Zero','2,00');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Coca Cola Ligth','2,00');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Fanta de Naranja','2,00');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Fanta de Limón','2,00');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Pepsi','2,00');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Pepsi Max','2,00');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Aquarius Naranja','2,30');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Aquarius Limón','2,30');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Nestee','2,30');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Sprite','2,00');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Schweppes Limón','2,00');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Strawberry','2,00');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Tinto de Verano','2,00');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Zumos','2,00');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Batidos','2,00');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Monster','2,50');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Burn','2,50');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Tónica Bliss','2,20');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Tónica Premium','2,50');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Agua 1,5L','1,50');
INSERT INTO `Memphis_Cafe`.`Refrescos`(`nombre`,`precio`) VALUES ('Agua 0,33L','1,00');



DROP TABLE IF EXISTS Cervezas;
CREATE TABLE `Cervezas`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Mahou','2,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Cruzcampo','2,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Victoria','2,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Mahou 5 Estrellas','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Agüila','2,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Agúila sin filtrar','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Heineken','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Heineken 0.0','2,20');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Tostada 0.0','2,20');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Alhambra Reserva 1925','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Voll-Damm','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Paulaner','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Judas','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Salitos','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Desperados','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Barrica','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Sol','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Birra Moretti','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Guinness','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Alcazar','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Carlsberg','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('San Miguel 1516','3,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Ladrón de Manzanas','2,00');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('Radler','2,50');
INSERT INTO `Memphis_Cafe`.`Cervezas`(`nombre`,`precio`) VALUES ('La Casera','2,00');



DROP TABLE IF EXISTS Cervezas_Barril;
CREATE TABLE `Cervezas_Barril`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Cervezas_Barril`(`nombre`,`precio`) VALUES ('Heineken Jarrita','1,90');
INSERT INTO `Memphis_Cafe`.`Cervezas_Barril`(`nombre`,`precio`) VALUES ('Heineken Pinta','2,20');
INSERT INTO `Memphis_Cafe`.`Cervezas_Barril`(`nombre`,`precio`) VALUES ('Heineken Jarra XXL','3,00');



DROP TABLE IF EXISTS Vinos;
CREATE TABLE `Vinos`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio_copa` varchar(100) NOT NULL,
  `precio_botella` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Vinos`(`nombre`,`precio_copa`,`precio_botella`) VALUES ('Rioja','2,40','13,00');
INSERT INTO `Memphis_Cafe`.`Vinos`(`nombre`,`precio_copa`,`precio_botella`) VALUES ('Ribera','2,40','13,00');
INSERT INTO `Memphis_Cafe`.`Vinos`(`nombre`,`precio_copa`,`precio_botella`) VALUES ('Verdejo','2,50','13,00');
INSERT INTO `Memphis_Cafe`.`Vinos`(`nombre`,`precio_copa`,`precio_botella`) VALUES ('Frizzante','2,50','12,00');
INSERT INTO `Memphis_Cafe`.`Vinos`(`nombre`,`precio_copa`,`precio_botella`) VALUES ('Manzanilla','2,00','12,00');


-----------------------
--	  # COMIDAS #	 --
-----------------------


DROP TABLE IF EXISTS Roscas;
CREATE TABLE `Roscas`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Roscas`(`nombre`,`precio`) VALUES ('Lomo','9,00');
INSERT INTO `Memphis_Cafe`.`Roscas`(`nombre`,`precio`) VALUES ('Jamón','9,00');
INSERT INTO `Memphis_Cafe`.`Roscas`(`nombre`,`precio`) VALUES ('Queso','9,00');
INSERT INTO `Memphis_Cafe`.`Roscas`(`nombre`,`precio`) VALUES ('Lomo,Bacon y Queso','10,00');
INSERT INTO `Memphis_Cafe`.`Roscas`(`nombre`,`precio`) VALUES ('Serranito','11,00');
INSERT INTO `Memphis_Cafe`.`Roscas`(`nombre`,`precio`) VALUES ('Rosca Personalizada (3 ingredientes)','11,00');



DROP TABLE IF EXISTS Ensaladas;
CREATE TABLE `Ensaladas`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Ensaladas`(`nombre`,`precio`) VALUES ('Lechuga, Tomate, Cebolla y Aceitunas','9,00');


DROP TABLE IF EXISTS Pizzas;
CREATE TABLE `Pizzas`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Pizzas`(`nombre`,`precio`) VALUES ('York (Pequeña)','5,50');
INSERT INTO `Memphis_Cafe`.`Pizzas`(`nombre`,`precio`) VALUES ('Cuatro Quesos (Pequeña)','5,50');
INSERT INTO `Memphis_Cafe`.`Pizzas`(`nombre`,`precio`) VALUES ('Peperonni (Pequeña)','5,50');
INSERT INTO `Memphis_Cafe`.`Pizzas`(`nombre`,`precio`) VALUES ('Carbonara (Mediana)','6,50');
INSERT INTO `Memphis_Cafe`.`Pizzas`(`nombre`,`precio`) VALUES ('Barbacoa (Mediana)','6,50');
INSERT INTO `Memphis_Cafe`.`Pizzas`(`nombre`,`precio`) VALUES ('Vegana (Mediana)','6,50');
INSERT INTO `Memphis_Cafe`.`Pizzas`(`nombre`,`precio`) VALUES ('Atún con Cebolla Roja (Mediana)','6,50');
INSERT INTO `Memphis_Cafe`.`Pizzas`(`nombre`,`precio`) VALUES ('Atún y Bacon (Mediana)','6,50');
INSERT INTO `Memphis_Cafe`.`Pizzas`(`nombre`,`precio`) VALUES ('Kebab Pollo (Mediana)','6,50');
INSERT INTO `Memphis_Cafe`.`Pizzas`(`nombre`,`precio`) VALUES ('Pollo a la Barbacoa (Mediana)','6,50');
INSERT INTO `Memphis_Cafe`.`Pizzas`(`nombre`,`precio`) VALUES ('5 Quesos (Mediana)','6,50');


DROP TABLE IF EXISTS Perritos;
CREATE TABLE `Perritos`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Perritos`(`nombre`,`precio`) VALUES ('Normal','2,80');
INSERT INTO `Memphis_Cafe`.`Perritos`(`nombre`,`precio`) VALUES ('Memphis','3,50');


DROP TABLE IF EXISTS Hamburguesas;
CREATE TABLE `Hamburguesas`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Hamburguesas`(`nombre`,`precio`) VALUES ('Normal','4,00');
INSERT INTO `Memphis_Cafe`.`Hamburguesas`(`nombre`,`precio`) VALUES ('Memphis','5,00');
INSERT INTO `Memphis_Cafe`.`Hamburguesas`(`nombre`,`precio`) VALUES ('Pemium','6,50');
INSERT INTO `Memphis_Cafe`.`Hamburguesas`(`nombre`,`precio`) VALUES ('Pemium XL','8,00');
INSERT INTO `Memphis_Cafe`.`Hamburguesas`(`nombre`,`precio`) VALUES ('Mega Cheese','7,50');
INSERT INTO `Memphis_Cafe`.`Hamburguesas`(`nombre`,`precio`) VALUES ('Mega Cheese XL','9,00');


DROP TABLE IF EXISTS Bocadillos;
CREATE TABLE `Bocadillos`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Bocadillos`(`nombre`,`precio`) VALUES ('Jamón','4,50');
INSERT INTO `Memphis_Cafe`.`Bocadillos`(`nombre`,`precio`) VALUES ('Queso','4,50');
INSERT INTO `Memphis_Cafe`.`Bocadillos`(`nombre`,`precio`) VALUES ('Caballa','4,50');
INSERT INTO `Memphis_Cafe`.`Bocadillos`(`nombre`,`precio`) VALUES ('Lomo','4,50');
INSERT INTO `Memphis_Cafe`.`Bocadillos`(`nombre`,`precio`) VALUES ('Lomo y Queso','5,00');
INSERT INTO `Memphis_Cafe`.`Bocadillos`(`nombre`,`precio`) VALUES ('Lomo y Bacon','5,00');
INSERT INTO `Memphis_Cafe`.`Bocadillos`(`nombre`,`precio`) VALUES ('Bacon','4,50');
INSERT INTO `Memphis_Cafe`.`Bocadillos`(`nombre`,`precio`) VALUES ('Tortilla Francesa','4,50');
INSERT INTO `Memphis_Cafe`.`Bocadillos`(`nombre`,`precio`) VALUES ('Serranito','5,00');


DROP TABLE IF EXISTS Alpargatas;
CREATE TABLE `Alpargatas`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Alpargatas`(`nombre`,`precio`) VALUES ('Lomo','4,00');
INSERT INTO `Memphis_Cafe`.`Alpargatas`(`nombre`,`precio`) VALUES ('Bacon','4,00');
INSERT INTO `Memphis_Cafe`.`Alpargatas`(`nombre`,`precio`) VALUES ('Jamón','4,00');
INSERT INTO `Memphis_Cafe`.`Alpargatas`(`nombre`,`precio`) VALUES ('Queso','4,00');
INSERT INTO `Memphis_Cafe`.`Alpargatas`(`nombre`,`precio`) VALUES ('Tortilla','4,00');
INSERT INTO `Memphis_Cafe`.`Alpargatas`(`nombre`,`precio`) VALUES ('Lomo y Queso','4,50');
INSERT INTO `Memphis_Cafe`.`Alpargatas`(`nombre`,`precio`) VALUES ('Jamón y Queso','4,50');
INSERT INTO `Memphis_Cafe`.`Alpargatas`(`nombre`,`precio`) VALUES ('Bacon y Queso','4,50');
INSERT INTO `Memphis_Cafe`.`Alpargatas`(`nombre`,`precio`) VALUES ('Salmón y Philadelphia','6,50');
INSERT INTO `Memphis_Cafe`.`Alpargatas`(`nombre`,`precio`) VALUES ('Anchoa y Philadelphia','5,50');
INSERT INTO `Memphis_Cafe`.`Alpargatas`(`nombre`,`precio`) VALUES ('Anchoa y Tomate','5,50');
INSERT INTO `Memphis_Cafe`.`Alpargatas`(`nombre`,`precio`) VALUES ('Caballa y Tomate','5,50');
INSERT INTO `Memphis_Cafe`.`Alpargatas`(`nombre`,`precio`) VALUES ('Serranito','5,50');


DROP TABLE IF EXISTS Sandwich;
CREATE TABLE `Sandwich`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Sandwich`(`nombre`,`precio`) VALUES ('Mixto','3,00');
INSERT INTO `Memphis_Cafe`.`Sandwich`(`nombre`,`precio`) VALUES ('Americano','4,80');
INSERT INTO `Memphis_Cafe`.`Sandwich`(`nombre`,`precio`) VALUES ('Vegetal','4,50');




DROP TABLE IF EXISTS Raciones;
CREATE TABLE `Raciones`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Raciones`(`nombre`,`precio`) VALUES ('Jamón','12,00');
INSERT INTO `Memphis_Cafe`.`Raciones`(`nombre`,`precio`) VALUES ('Queso','12,00');
INSERT INTO `Memphis_Cafe`.`Raciones`(`nombre`,`precio`) VALUES ('Paté','7,00');
INSERT INTO `Memphis_Cafe`.`Raciones`(`nombre`,`precio`) VALUES ('Croquetas Gourmet','15,00');
INSERT INTO `Memphis_Cafe`.`Raciones`(`nombre`,`precio`) VALUES ('Croquetas Caseras','13,00');
INSERT INTO `Memphis_Cafe`.`Raciones`(`nombre`,`precio`) VALUES ('Flamenquín','6,50');
INSERT INTO `Memphis_Cafe`.`Raciones`(`nombre`,`precio`) VALUES ('Calamares','13,00');
INSERT INTO `Memphis_Cafe`.`Raciones`(`nombre`,`precio`) VALUES ('Gambones Fritos','13,00');
INSERT INTO `Memphis_Cafe`.`Raciones`(`nombre`,`precio`) VALUES ('Gambones a la plancha','14,00');
INSERT INTO `Memphis_Cafe`.`Raciones`(`nombre`,`precio`) VALUES ('Patatas Bravas','6,50');
INSERT INTO `Memphis_Cafe`.`Raciones`(`nombre`,`precio`) VALUES ('Patatas Alioli','6,50');
INSERT INTO `Memphis_Cafe`.`Raciones`(`nombre`,`precio`) VALUES ('Patatas Fritas','4,00');




DROP TABLE IF EXISTS Carnes;
CREATE TABLE `Carnes`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Carnes`(`nombre`,`precio`) VALUES ('Solomillo al Pedro Ximénez','13,00');
INSERT INTO `Memphis_Cafe`.`Carnes`(`nombre`,`precio`) VALUES ('Solomillo al Cabrales','13,00');
INSERT INTO `Memphis_Cafe`.`Carnes`(`nombre`,`precio`) VALUES ('Lagarto Ibérico con Patatas panaderas','13,00');
INSERT INTO `Memphis_Cafe`.`Carnes`(`nombre`,`precio`) VALUES ('Secreto Ibérico','13,00');
INSERT INTO `Memphis_Cafe`.`Carnes`(`nombre`,`precio`) VALUES ('Chuletillas de cordero','14,00');



DROP TABLE IF EXISTS Pescado;
CREATE TABLE `Pescado`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Pescado`(`nombre`,`precio`) VALUES ('Calamares Fritos','13,00');
INSERT INTO `Memphis_Cafe`.`Pescado`(`nombre`,`precio`) VALUES ('Atún con costra de frutos secos','13,00');
INSERT INTO `Memphis_Cafe`.`Pescado`(`nombre`,`precio`) VALUES ('Sepía a la plancha','13,00');
INSERT INTO `Memphis_Cafe`.`Pescado`(`nombre`,`precio`) VALUES ('Almejas a la marinera','12,00');



---------------------------
--	  # COMBINADOS #	 --
---------------------------

DROP TABLE IF EXISTS Cocteles;
CREATE TABLE `Cocteles`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Nacional','4,00');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Importación','4,50');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Bombay','5,00');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Saphire','5,00');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Master','5,00');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Chivas','7,00');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('E. Negra','7,00');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Barcelo Imperial','7,00');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Cacique 500','6,00');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Ginebras Premium','7,50');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Jack Daniels','5,00');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Cremas Especiales','3,00');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Licores sin Alcohol','2,00');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Melody','2,00');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Copas Nacional','2,00');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Copas Importación','2,50');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Champagne Freixenet','13,00');
INSERT INTO `Memphis_Cafe`.`Cocteles`(`nombre`,`precio`) VALUES ('Chupitos','2,00');




DROP TABLE IF EXISTS Lotes;
CREATE TABLE `Lotes`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `refrescos` int NOT NULL,
  `precio_uno` varchar(100) NOT NULL,
  `precio_dos` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Lotes`(`nombre`,`refrescos`,`precio_uno`,`precio_dos`) VALUES ('Larios, Dyc o Negritra',12,'38,00','36,00');
INSERT INTO `Memphis_Cafe`.`Lotes`(`nombre`,`refrescos`,`precio_uno`,`precio_dos`) VALUES ('Barcelo, Beefeater o Ballantines',12,'40,00','38,00');
INSERT INTO `Memphis_Cafe`.`Lotes`(`nombre`,`refrescos`,`precio_uno`,`precio_dos`) VALUES ('Jagermeister',0,'18,00','null');
INSERT INTO `Memphis_Cafe`.`Lotes`(`nombre`,`refrescos`,`precio_uno`,`precio_dos`)VALUES ('Fireball',0,'18,00','null');
INSERT INTO `Memphis_Cafe`.`Lotes`(`nombre`,`refrescos`,`precio_uno`,`precio_dos`) VALUES ('Tequila Rosa',0,'18,00','null');




-----------------------
--	  # BOLLERÍA #	 --
-----------------------

DROP TABLE IF EXISTS Reposteria;
CREATE TABLE `Reposteria`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Reposteria`(`nombre`,`precio`) VALUES ('Bandejita de Dulces','14,00');
INSERT INTO `Memphis_Cafe`.`Reposteria`(`nombre`,`precio`) VALUES ('Bubble Waffle','6,00');
INSERT INTO `Memphis_Cafe`.`Reposteria`(`nombre`,`precio`) VALUES ('Monstruo Batido','6,00');
INSERT INTO `Memphis_Cafe`.`Reposteria`(`nombre`,`precio`) VALUES ('Gofre','1,30');
INSERT INTO `Memphis_Cafe`.`Reposteria`(`nombre`,`precio`) VALUES ('Donuts','1,00');
INSERT INTO `Memphis_Cafe`.`Reposteria`(`nombre`,`precio`) VALUES ('Donetes','1,50');




----------------------------
--	  # Tabla Histórico #	 --
----------------------------

DROP TABLE IF EXISTS Historico;
CREATE TABLE `Historico`(
  `idCuenta` bigint NOT NULL AUTO_INCREMENT,
  `Mesa` bigint NOT NULL,
  `Bebidas` varchar(500) NOT NULL,
  `Comidas` varchar(500) NOT NULL,
  `Dia` varchar(15) NOT NULL,
  `Hora` varchar(20) NOT NULL,
  `Mesero` varchar(100) NOT NULL,
  `Cuenta` varchar(30) NOT NULL,	
  PRIMARY KEY (`idCuenta `)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

----------------------------
--	  # Tabla Comidas #	 --
----------------------------


DROP TABLE IF EXISTS Comidas;
CREATE TABLE `Comidas`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Memphis_Cafe`.`Comidas`(`nombre`) VALUES ('Roscas');
INSERT INTO `Memphis_Cafe`.`Comidas`(`nombre`) VALUES ('Ensaladas');
INSERT INTO `Memphis_Cafe`.`Comidas`(`nombre`) VALUES ('Pizzas');
INSERT INTO `Memphis_Cafe`.`Comidas`(`nombre`) VALUES ('Perritos');
INSERT INTO `Memphis_Cafe`.`Comidas`(`nombre`) VALUES ('Hamburguesas');
INSERT INTO `Memphis_Cafe`.`Comidas`(`nombre`) VALUES ('Bocadillos');
INSERT INTO `Memphis_Cafe`.`Comidas`(`nombre`) VALUES ('Alpargatas');
INSERT INTO `Memphis_Cafe`.`Comidas`(`nombre`) VALUES ('Sandwich');


