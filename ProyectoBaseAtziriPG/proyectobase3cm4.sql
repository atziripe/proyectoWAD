CREATE DATABASE ProyectoBase3CM4;
USE ProyectoBase3CM4;
CREATE TABLE 'Evento' (
  'idEvento' INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  'nombreEvento' VARCHAR(40) NOT NULL,
  'sede' VARCHAR(40) NOT NULL,
  'duracion' DOUBLE NOT NULL,
  'fechaInicio' DATE NOT NULL,
  'fechaTermino' DATE NOT NULL);
