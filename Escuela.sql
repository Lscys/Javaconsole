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

CREATE TABLE Promedios (
        IDALUMNO varchar(25),
        IDPROFESOR varchar(25),
        IDCURSO varchar(25),
        PROMEDIO FLOAT,
        APROBADO VARCHAR(10)
);

DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE `calcular_promedio_alumno_curso_profesor`(IN `idAlumno` VARCHAR(10), IN `idCurso` VARCHAR(10), IN `idProfesor` VARCHAR(10))
BEGIN
    -- Inserta el registro de promedio y aprobado del curso seleccionado
    INSERT INTO Promedios (IDALUMNO, IDPROFESOR, IDCURSO, PROMEDIO, APROBADO)
    SELECT 
        C.idAlumno, C.idProfesor, C.idCurso, 
        (C.Nota1 + C.Nota2 + C.Nota3) / 3 AS PROMEDIO, 
        IF((C.Nota1 + C.Nota2 + C.Nota3) / 3 >= 13, 'APROBADO', 'REPROBADO') AS APROBADO
    FROM 
        Notas AS C
    WHERE 
        C.idAlumno = idAlumno
        AND C.idProfesor = idCurso
        AND C.idCurso = idProfesor;
END$$
DELIMITER ;

CALL calcular_promedio_alumno_curso_profesor('A0001', 'P0001', 'C0002');

drop procedure `calcular_promedio_alumno_curso_profesor`;
drop table Alumno;
drop table Cursos;
drop table Notas;
drop table Profesor;
drop table promedios;


select * from Alumno;
select * from cursos;
select * from Profesor;
select * from Notas;
select * from Promedios;


