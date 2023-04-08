use escuela;


CREATE TABLE Alumno(
idAlumno varchar(25) primary key,
Nombres varchar(45),
Apellido varchar(45),
Sexo varchar(20),
Edad int,
Estado varchar(30) 
);

CREATE TABLE Cursos(
idCurso varchar(25) primary key,
Descripcon varchar(45)
);

CREATE TABLE Profesor(
idProfesor varchar(25) primary key,
Nombres varchar(45),
Apellido varchar(45),
dni varchar(8),
Edad int,
Estado varchar(30)
);

CREATE TABLE Notas(
idAlumno  varchar(25),
idCurso varchar(25),
idProfesor varchar(25),
Nota1 int,
Nota2 int,
Nota3 int,
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
drop table Notas;
drop table Profesor;

select * from Alumno;
select * from cursos;
select * from Profesor;
select * from Notas;


