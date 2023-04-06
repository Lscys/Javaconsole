use escuela;


CREATE TABLE Alumno(
idAlumno varchar(25) primary key not null,
Nombres varchar(45) not null,
Apellido varchar(45) not null,
Sexo varchar(20) not null,
Edad int not null,
Estado varchar(30) not null
);

CREATE TABLE Cursos(
idCurso varchar(25) primary key not null,
Descripcon varchar(45) not null
);

CREATE TABLE Profesor(
idProfesor varchar(25) primary key not null,
Nombres varchar(45) not null,
Apellido varchar(45) not null,
dni varchar(8) not null,
Edad int not null,
Estado varchar(30) not null
);

CREATE TABLE Notas(
idAlumno  varchar(25) not null,
idCurso varchar(25) not null,
idProfesor varchar(25) not null,
Nota1 int not null,
Nota2 int not null,
Nota3 int not null,
CONSTRAINT PK_ALUMNO_CURSO_PROFESOR PRIMARY KEY (idAlumno,idCurso,idProfesor),
CONSTRAINT CHK_NOTA1 CHECK (NOTA1>=0 AND NOTA1<=20),
CONSTRAINT CHK_NOTA2 CHECK (NOTA2>=0 AND NOTA2<=20),
CONSTRAINT CHK_NOTA3 CHECK (NOTA3>=0 AND NOTA3<=20),
FOREIGN KEY (idAlumno) REFERENCES Alumno(idAlumno),
FOREIGN KEY (idCurso) REFERENCES Cursos(idCurso),
FOREIGN KEY (idProfesor) REFERENCES Profesor(idProfesor)
);

drop table Alumno;
drop table Cursos;
drop table Profesor;

select * from Alumno;
select * from cursos;
select * from Profesor;
select * from Notas;


