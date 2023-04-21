/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Notas;

import java.util.Scanner;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperExportManager;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jeferson
 */


interface GestorTablaNotas{
    abstract void insertarNotas();
    abstract void actualizarNotas();
    abstract void mostrarTablaNotas();
    abstract void eliminarNotas();
    abstract void verPDFNotas();
    abstract void menuGestion();
}

public abstract class GestorNotas implements GestorTablaNotas{
    /*------Variables globales---------*/
    
    //Inicializacion de la conexion bd mysql
    private Connection conn = null; 
    private PreparedStatement stmt = null;
    private Statement stmta = null;
    private ResultSet rs = null;
    private Scanner sc = new Scanner(System.in);
    
    
    //Variables de la tabla Alumno
    private String idAlumno;
    private String idCurso;
    private String idProfesor;
    private String Nota1;
    private String Nota2;
    private String Nota3;
    private String tipo;
    
    /*------------------------------------*/
    
    //Constructor
    public GestorNotas() {
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/escuela?" + "user=root&password=admin");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLStatus: " + ex.getSQLState());
            System.out.println("EventoError: " + ex.getErrorCode());
        }
        
        
    }
    
    
    @Override
    public void insertarNotas() {    
        try {
            stmt = conn.prepareStatement("INSERT INTO Notas (idAlumno, idCurso, idProfesor, Nota1, Nota2, Nota3) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, idAlumno);
            stmt.setString(2, idCurso);
            stmt.setString(3, idProfesor);
            stmt.setString(4, Nota1);
            stmt.setString(5, Nota2);
            stmt.setString(6, Nota3);
            stmt.executeUpdate();
            System.out.println("Las Notas del Alumno " + idAlumno + " ha sido agregado exitosamente. \n");
            System.out.println(" \n");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLStatus: " + ex.getSQLState());
            System.out.println("EventoError: " + ex.getErrorCode());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    System.out.println(sqlEx.getMessage());
                }
                stmt = null;
            }
        }
    }
    
    
    @Override
    public void actualizarNotas() {  
        System.out.println();
        try {
            stmt = conn.prepareStatement("UPDATE Notas SET Nota1 = ?, Nota2 = ?, Nota3 = ? WHERE idAlumno = ? AND idCurso = ? AND idProfesor = ?");
            stmt.setString(1, Nota1);
            stmt.setString(2, Nota2);
            stmt.setString(3, Nota3);
            stmt.setString(4, idAlumno);
            stmt.setString(5, idCurso);
            stmt.setString(6, idProfesor);
            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Las notas del Alumno " + idAlumno + " han sido actualizadas exitosamente.");
            } else {
                System.out.println("No se encontró ningún registro que coincida con los criterios de actualización.");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLStatus: " + ex.getSQLState());
            System.out.println("EventoError: " + ex.getErrorCode());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    System.out.println(sqlEx.getMessage());
                }
                stmt = null;
            }
        }
    }
    
    
    @Override
    public void mostrarTablaNotas() {
        System.out.println();
        try {
            stmta = conn.createStatement();
            rs = stmta.executeQuery("SELECT * FROM Notas");
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("idAlumno")+" "+rs.getString("idCurso")+" "+
                        rs.getString("idProfesor")+" "+rs.getString("Nota1")+" "+rs.getString("Nota2")+" "+rs.getString("Nota3"));
            }
            System.out.println(" \n");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLStatus: " + ex.getSQLState());
            System.out.println("EventoError: " + ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                    System.out.println(sqlEx.getMessage());
                }
                rs = null;
    
        }
    }
 }  
    
    @Override
    public void eliminarNotas(){
        System.out.println();
        try {
            stmt = conn.prepareStatement("DELETE FROM Notas WHERE idAlumno = ? AND idCurso = ? AND idProfesor = ?");
            stmt.setString(1, idAlumno);
            stmt.setString(2, idCurso);
            stmt.setString(3, idProfesor);
            int filasEliminadas = stmt.executeUpdate();
            if (filasEliminadas == 1) {
                System.out.println("Las notas del Alumno "+ idAlumno+" ha sido eliminado exitosamente");
                System.out.println("\n");
            }else{
                System.out.println("No se ha encontrado ningun alumno con id "+idAlumno+" para eliminar");
                System.out.println(" \n");
            }
        }catch(SQLException ex){
            System.out.println();
            System.out.println("SQLException: "+ex.getMessage());
            System.out.println("SQLState: "+ex.getSQLState());
            System.out.println("EventoError: "+ex.getErrorCode());
        }if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) {
                System.out.println(sqlEx.getMessage());
            }
            stmt = null;
        }
    }
    
    
    @Override
    public void verPDFNotas(){
        try{
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Jeferson\\JaspersoftWorkspace\\MyReports\\ReporteAlumno.jasper", null, conn);
            JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\Jeferson\\JaspersoftWorkspace\\MyReports\\ReporteAlumno.pdf");
            System.out.println("\n Archivo Alumno creado correctamente");   
            System.out.println(" \n");
        }catch (JRException jre){
            System.out.println(jre.getMessage());
        }finally{
            if (conn!=null) {
                try {
                    conn.close();
                }catch(SQLException sqlEx){
                    System.out.println(sqlEx.getMessage());
                }
                conn = null;
            }
        }
    }
    
    
    @Override
    public void menuGestion(){
        System.out.println("HA SELECCIONADO LA OPCION NOTAS");
        System.out.println("================================");
        System.out.println("I. Insertar");
        System.out.println("E. Eliminar");
        System.out.println("A. Actualizar");
        System.out.println("VT. Ver Tabla");
        System.out.println("PDF. Alumno");
        System.out.println("O. VOLVER");
        System.out.print("SELECIONE UNA OPCION: ");
        tipo = sc.next();
        
        if (tipo.equals("I")) {
            System.out.print("Ingrese id del Alumno: ");
            idAlumno = sc.next();
            System.out.print("Ingrese id del Curso:  ");
            idCurso = sc.next();
            System.out.print("Ingrese id del Profesor: ");
            idProfesor = sc.next();
            System.out.print("Ingrese Nota1: ");
            Nota1 = sc.next();
            System.out.print("Ingrese Nota2: ");
            Nota2 = sc.next();
            System.out.print("Ingrese Nota3: ");
            Nota3 = sc.next();
            insertarNotas();
        }else if (tipo.equals("E")) {
            System.out.print("Ingrese id del Alumno: ");
            idAlumno = sc.next();
            System.out.print("Ingrese id del Curso:  ");
            idCurso = sc.next();
            System.out.print("Ingrese id del Profesor: ");
            idProfesor = sc.next();
            eliminarNotas();
        }else if (tipo.equals("A")) {
            System.out.print("Ingrese id del Alumno: ");
            idAlumno = sc.next();
            System.out.print("Ingrese id del Curso:  ");
            idCurso = sc.next();
            System.out.print("Ingrese id del Profesor: ");
            idProfesor = sc.next();
            System.out.print("Ingrese Nota1: ");
            Nota1 = sc.next();
            System.out.print("Ingrese Nota2: ");
            Nota2 = sc.next();
            System.out.print("Ingrese Nota3: ");
            Nota3 = sc.next();
            actualizarNotas();
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