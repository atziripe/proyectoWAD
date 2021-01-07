CREATE TABLE Genero (
  idGenero SERIAL PRIMARY KEY,
  nombreGenero VARCHAR(20) NOT NULL,
  descripcionGenero VARCHAR(255)
);

INSERT INTO Genero(nombreGenero, descripcionGenero) VALUES('Misterio','El suspenso es un recurso literario y un amplio género,cuyo objetivo principal es mantener al lector a la expectativa, generalmente en un estado de tensión, de lo que pueda ocurrirles a los personajes.');
INSERT INTO Genero(nombreGenero, descripcionGenero) VALUES('Adolescentes','Genero destinado a adolescentes y adultos jóvenes, cuyo argumento se basa temas intereantes para jóvenes como:  el primer amor, la primera relación sexual, la popularidad, la rebeldía, entre muchos más.');
INSERT INTO Genero(nombreGenero, descripcionGenero) VALUES('Western','El wéstern o películas del Oeste​ es un género cinematográfico típico del cine estadounidense que se ambienta en el viejo Oeste estadounidense.');
INSERT INTO Genero(nombreGenero, descripcionGenero) VALUES('Comedia','La comedia es una obra que presenta una mayoría de escenas y situaciones humorísticas o festivas. Las comedias buscan entretener al público y generar risas, con finales que suelen ser felices.');
INSERT INTO Genero(nombreGenero, descripcionGenero) VALUES('Documental','El documental es la expresión de un aspecto de la realidad, mostrada en forma audiovisual. La organización y estructura de imágenes y sonidos (textos y entrevistas), según el punto de vista del autor, determina el tipo de documental.');


CREATE TABLE Pelicula (
  idPelicula SERIAL PRIMARY KEY,
  idGenero INT NOT NULL,
  nombrePelicula VARCHAR(50) NOT NULL,
  sinopsis VARCHAR(255) NOT NULL,
  clasificacion VARCHAR(3) NOT NULL,
  anio INT NOT NULL,
  duracion FLOAT NOT NULL,
  director VARCHAR(50) NOT NULL,
  votosPositivos INT,
  votosNegativos INT,
  link VARCHAR(255),
  FOREIGN KEY (idGenero) REFERENCES Genero ON DELETE CASCADE ON UPDATE CASCADE
);

insert into pelicula values(1,3,'Por un puñado de dolares','Cazarecompensas','B', 1964, 99,'Sergio Leone', 0, 0);
insert into pelicula values(2,2,'Diario de un rebelde','Las drogas destruyen la carrera de un prometedor jugador de basquetbol y lo llevan a un mundo de adicción y crimen.','B', 1995, 104,'Scott Kalvert', 0, 0);
insert into pelicula values(3,1,'El precio de la verdad: Dark Waters','Un abogado descubre que una serie de muertes están vinculadas a una de las compañías más grandes y poderosas del mundo. ','B', 2019, 126,'Todd Haynes', 0, 0);
insert into pelicula values(4,4,'Luces de la ciudad','Un vagabundo puede resolverle los problemas económicos a su amada, una ciega, gracias a su amistad con un magnate.','B', 1931, 87,'Charles Chaplin', 0, 0);
insert into pelicula values(5,5,'Dame el poder','Historia de la banda Molotov y su significado más amplio para la política mexicana explicando cómo la música rock mexicana siempre ha tenido una relación bastante ambigua con el gobierno mexicano y la sociedad desde finales de la década de 1950.','B', 2012, 101,'	Olallo Rubio', 0, 0);

  
CREATE TABLE Usuario (
  idUsuario serial primary key,
  nombre varchar(50) NOT NULL,
  paterno varchar(50) NOT NULL,
  materno varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  nombreUsuario varchar(20) NOT NULL,
  claveUsuario varchar(20) NOT NULL,
  tipoUsuario varchar(1)
);

insert into Usuario values(1, 'Fernando', 'Hernández', 'Escobedo', 'weas.wad.2020@gmail.com', 'SrR', '12345>', 'A');
insert into Usuario values(2, 'Atziri', 'Pérez', 'Garcia', 'weas.wad.2020@gmail.com', 'AtPG', '54321>', 'A');
insert into Usuario values(3, 'Roberto', 'Sánchez', 'Veloz', 'weas.wad.2020@gmail.com', 'RSV', '54321>', 'A');


SELECT idusuario, nombre, paterno, materno, email, nombreusuario, claveusuario, tipousuario
FROM public.usuario;

SELECT idpelicula, idgenero, nombrepelicula, sinopsis, clasificacion, anio, duracion, director, votospositivos, votosnegativos, link
FROM public.pelicula;

SELECT idgenero, nombregenero, descripciongenero
FROM public.genero;