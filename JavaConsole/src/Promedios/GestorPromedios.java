/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Promedios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Jeferson
 */

interface GestorTablaPromedios{
    abstract void calcularPromedioAlumnoCursoProfesor();
    abstract void mostrarTablaNotas();
    //abstract void verPDFNotas();
    abstract void menuGestion();
}

public abstract class GestorPromedios implements GestorTablaPromedios {
    
    /*------Variables globales---------*/
    
    //Inicializacion de la conexion bd mysql
    private Connection conn = null; 
    private PreparedStatement stmt = null;
    private CallableStatement cstmt = null;
    private Statement stmta = null;
    private ResultSet rs = null;
    private Scanner sc = new Scanner(System.in);
    
    private String tipo;
    private String idAlumno;
    private String idCurso;
    private String idProfesor;

    public GestorPromedios() {
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/escuela?" + "user=root&password=admin");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLStatus: " + ex.getSQLState());
            System.out.println("EventoError: " + ex.getErrorCode());
        }
    }
    
    @Override
    public void calcularPromedioAlumnoCursoProfesor() {
        System.out.println();
        try {
            cstmt = conn.prepareCall("{call calcular_promedio_alumno_curso_profesor(?,?,?)}");
            cstmt.setString(1, idAlumno);
            cstmt.setString(2, idCurso);
            cstmt.setString(3, idProfesor);
            cstmt.execute();
            System.out.println("Procedimiento almacenado ejecutado exitosamente");
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar el procedimiento almacenado: " + ex.getMessage());
        }
        System.out.println();
    }
    
    @Override
    public void mostrarTablaNotas() {
        System.out.println();
        try {
            stmt = conn.prepareStatement("SELECT * FROM Promedios");
            rs = stmt.executeQuery();

            System.out.println("IDALUMNO\tIDPROFESOR\tIDCURSO\tPROMEDIO\tAPROBADO");
            while (rs.next()) {
                idAlumno = rs.getString("IDALUMNO");
                idProfesor = rs.getString("IDPROFESOR");
                idCurso = rs.getString("IDCURSO");
                float promedio = rs.getFloat("PROMEDIO");
                String aprobado = rs.getString("APROBADO");

                System.out.println(idAlumno + "\t\t" + idProfesor + "\t\t" + idCurso + "\t" + promedio + "\t\t" + aprobado);
            }
        } catch (SQLException ex) {
            System.out.println();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("EventoError: " + ex.getErrorCode());
            
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    System.out.println();
                    System.out.println(sqlEx.getMessage());
                }
                stmt = null;
            }
        }
        System.out.println();
    }
    
    @Override
    public void menuGestion(){
        System.out.println("HA SELECCIONADO LA OPCION PROMEDIOS");
        System.out.println("================================");
        System.out.println("P.   PROMEDIAR");
        System.out.println("VT.  Ver Tabla");
        System.out.println("PDF. Alumno");
        System.out.println("O.   VOLVER");
        System.out.print("SELECIONE UNA OPCION: ");
        tipo = sc.next();
        
        if (tipo.equals("I")) {
            System.out.println();
            System.out.print("Ingrese id del Alumno: ");
            idAlumno = sc.next();
            System.out.print("Ingrese id del Curso:  ");
            idCurso = sc.next();
            System.out.print("Ingrese id del Profesor: ");
            idProfesor = sc.next();
            calcularPromedioAlumnoCursoProfesor();
        }else if (tipo.equals("VT")) {
            mostrarTablaNotas();
        }else if (tipo.equals("PDF")) {
            /*verPDFNotas();*/
        }else if (tipo.equals("O")) {
            System.out.println();
            return;
        }else{
            System.out.println();
            System.out.println("ESCOGA UNA OPCION VALIDA");
        }
    }
}
