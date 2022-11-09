/*sql para llenado de tabla enemigo*/
insert into yellow.enemigo (idEnemigo,nombre,idClase,ataque,experiencia,idGenero) 
values (1,"DevilMan",3,300,700,"M"),
(2,"HardRock",6,500,1000,"M"),
(3,"BombGirl",5,250,500,"F"),
(4,"DragonChaos",1,400,900,"M"),
(5,"MeanBird",7,150,350,"-");

/*sql para llenado de tabla genero*/
insert into yellow.genero (idGenero,nombre) 
values ("M","masculino"),("F","femenino"),
("O","otro"),("-","");

/*sql para llenado de tabla elemento*/
insert into yellow.elemento (idElemento,nombre) 
values (1,"Fuego"),(2,"Tierra"),(3,"Agua"),
(4,"Viento"),(5,"Void");

/*sql para llenado de tabla clase*/
insert into yellow.clase (idClase,nombre) 
values (1,"Dragon"),(2,"Fantasma"),(3,"Demonio"),(4,"Pez"),
(5,"Humano"),(6,"Bestia"),(7,"Ave"),(8,"Otros");

/*sql para llenado de tabla danioporelemento*/
insert into yellow.danioporelemento (idDanioPorElemento,idClase,idElemento,danioRecibido) values
(1,1,1,0.30),(2,1,2,0.50),(3,1,3,0.20),(4,1,4,0.10),(5,1,5,0.15),
(6,2,1,1.20),(7,2,2,0.05),(8,2,3,2.50),(9,2,4,1.50),(10,2,5,0.20),
(11,3,1,0.10),(12,3,2,0.60),(13,3,3,3.00),(14,3,4,0.30),(15,3,5,0.30),
(16,4,1,2.80),(17,4,2,1.50),(18,4,3,0.05),(19,4,4,0.80),(20,4,5,0.40),
(21,5,1,1.80),(22,5,2,0.80),(23,5,3,0.50),(24,5,4,0.30),(25,5,5,0.10),
(26,6,1,0.70),(27,6,2,0.20),(28,6,3,0.40),(29,6,4,0.10),(30,6,5,0.30),
(31,7,1,1.70),(32,7,2,0.80),(33,7,3,0.60),(34,7,4,0.05),(35,7,5,0.20),
(36,8,1,1.30),(37,8,2,0.70),(38,8,3,0.50),(39,8,4,0.40),(40,8,5,0.30);

/*sql para llenado de heroe*/
insert into yellow.heroe (idHeroe, nombre, edad, idGenero, clase, nivel, ataque, experiencia, idPareja)
values (1, "Urhan", 30, "M", "Caballero", 50, 40, 142500, null),
(2, "Ataz", 350, "F", "Curandero", 12, 20, 979.2, null), 
(3, "Ophior", 120, "F", "Bestia", 20, 230, 5440, null), 
(4, "Atosh", 560, "M", "Guardabosque", 70, 100, 459620, null), 
(5, "Ilrolius",  90, "O", "Mago", 5, 560, 65, null);

update heroe set idPareja = 4 where idHeroe = 1;
update heroe set idPareja = 1 where idHeroe = 4;
update heroe set idPareja = 2 where idHeroe = 3;
update heroe set idPareja = 3 where idHeroe = 2;

/*sql para llenado de catalogo de objetos*/
insert into yellow.catalogodeobjetos (nombre, efecto_uso, peso)
values ("Arma Artema", "Espada que inflinge un 50% menos de daño a enemigos cercanos, mientras que a los lejanos aumenta en 30%", 5.20),
("Aguja Sombría", "El personaje recibe 5% de experiencia adicional por cada muerte dentro de un intervalo de 1 minuto", 15.50),
("Apoyo Espectral", "Pergamino que invoca una version espectral de tu pareja", 7.80),
("Espada dorada", "Espada pemanentemente en llamas que aumenta el ataque del personaje en 10%", 7.50),
("Sable largo", "Roba un objeto de curación de tu oponente y atúrdelo durante 10 segunos", 30.60); 

/*sql para llenado de inventario*/
insert into yellow.inventario (idHeroe, idObjeto, cantidad)
values (1, 2, 3), (1, 4, 1), (1, 5, 2),
(2, 2, 1), (2, 5, 2), (2, 4, 3), 
(3, 1, 4), (3, 2, 1), (3, 3, 1), 
(4, 5, 1), (4, 2, 5), (4, 1, 4),
(5, 2, 3), (5, 4, 2), (5, 1, 4);

/*sql para llenado de dropeo*/
insert into yellow.dropeo (idObjeto,idEnemigo,probabilidad) values
(2,1,0.60),(1,2,0.40),(4,3,0.30),(3,4,0.70),(5,5,0.40);

/*sql para llenado de hechizos*/