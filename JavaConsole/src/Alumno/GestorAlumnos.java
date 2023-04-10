/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alumno;

import java.util.Scanner;

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


interface GestorTablaAlumno{
    abstract void insertarAlumno();
    abstract void actualizarAlumno();
    abstract void mostrarTablaAlumnos();
    abstract void eliminarAlumno();
    abstract void menuGestion();
}

public abstract class GestorAlumnos implements GestorTablaAlumno{
    /*------Variables globales---------*/
    
    //Inicializacion de la conexion bd mysql
    private Connection conn = null; 
    private PreparedStatement stmt = null;
    private Statement stmta = null;
    private ResultSet rs = null;
    private Scanner sc = new Scanner(System.in);
    
    
    //Variables de la tabla Alumno
    private String idAlumno;
    private String Nombres;
    private String Apellido;
    private String sexo;
    private String Edad;
    private String Estado;
    private String tipo;
    
    /*------------------------------------*/
    
    //Constructor
    public GestorAlumnos() {
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/escuela?" + "user=root&password=admin");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLStatus: " + ex.getSQLState());
            System.out.println("EventoError: " + ex.getErrorCode());
        }
        
        
    }
    
    
    @Override
    public void insertarAlumno() {    
        try {
            stmt = conn.prepareStatement("INSERT INTO Alumno (idAlumno, Nombres, Apellido, Sexo, Edad, Estado) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, idAlumno);
            stmt.setString(2, Nombres);
            stmt.setString(3, Apellido);
            stmt.setString(4, sexo);
            stmt.setString(5, Edad);
            stmt.setString(6, Estado);
            stmt.executeUpdate();
            System.out.println("El alumno " + idAlumno + " ha sido agregado exitosamente. \n");
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
    public void actualizarAlumno() {     
        try {
            stmt = conn.prepareStatement("UPDATE Alumno SET Nombres = ?, Apellido = ?, Sexo = ?, Edad = ?, Estado = ? WHERE idAlumno = ?");
            stmt.setString(6, idAlumno);
            stmt.setString(1, Nombres);
            stmt.setString(2, Apellido);
            stmt.setString(3, sexo);
            stmt.setString(4, Edad);
            stmt.setString(5, Estado);
            stmt.executeUpdate();
            System.out.println("El alumno con id " + idAlumno + " ha sido actualizado exitosamente.");
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
    public void mostrarTablaAlumnos() {
        try {
            stmta = conn.createStatement();
            rs = stmta.executeQuery("SELECT * FROM Alumno");
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("idAlumno")+" "+rs.getString("Nombres")+" "+
                        rs.getString("Apellido")+" "+rs.getString("Sexo")+" "+rs.getString("Edad")+" "+rs.getString("Estado"));
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
    public void eliminarAlumno(){
        try {
            stmt = conn.prepareStatement("DELETE FROM Alumno WHERE idAlumno = ?");
            stmt.setString(1, idAlumno);
            int filasEliminadas = stmt.executeUpdate();
            if (filasEliminadas == 1) {
                System.out.println("El alumno con id "+ idAlumno+" ha sido eliminado exitosamente");
                System.out.println("\n");
            }else{
                System.out.println("No se ha encontrado ningun alumno con id "+idAlumno+" para eliminar");
                System.out.println(" \n");
            }
        }catch(SQLException ex){
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
    public void menuGestion(){
        System.out.println("Ha seleccionado la opci�n Alumno");
        System.out.println("--------------------------------");
        System.out.println("Escoga una: ");
        System.out.println("I. Insertar");
        System.out.println("E. Eliminar");
        System.out.println("A. Actualizar");
        System.out.println("VT. Ver Tabla");
        tipo = sc.next();
        
        if (tipo.equals("I")) {
            System.out.print("Ingrese id del Alumno: ");
            idAlumno = sc.next();
            System.out.print("Ingrese Nombre del Alumno: ");
            Nombres = sc.next();
            System.out.print("Ingrese Apellido del Alumno: ");
            Apellido = sc.next();
            System.out.print("Ingrese Sexo del Alumno: ");
            sexo = sc.next();
            System.out.print("Ingrese Edad del Alumno: ");
            Edad = sc.next();
            System.out.print("Ingrese Estado del Alumno: ");
            Estado = sc.next();
            insertarAlumno();
        }else if (tipo.equals("E")) {
            System.out.print("Ingrese id del Alumno: ");
            idAlumno = sc.next();
            eliminarAlumno();
        }else if (tipo.equals("A")) {
            System.out.print("Ingrese id del Alumno: ");
            idAlumno = sc.next();
            System.out.print("Ingrese Nombre del Alumno: ");
            Nombres = sc.next();
            System.out.print("Ingrese Apellido del Alumno: ");
            Apellido = sc.next();
            System.out.print("Ingrese Sexo del Alumno: ");
            sexo = sc.next();
            System.out.print("Ingrese Edad del Alumno: ");
            Edad = sc.next();
            System.out.print("Ingrese Estado del Alumno: ");
            Estado = sc.next();
            actualizarAlumno();
        }else if (tipo.equals("VT")) {
            mostrarTablaAlumnos();
        }else{
            System.out.println("INGRESE UNO DE LOS PARAMETROS");
        }
    }
   
    
}