/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Profesor;

import java.util.Scanner;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jeferson
 */

interface GestorTablaProfesor{
    abstract void insertarProfesor();
    abstract void eliminarProfesor();
    abstract void actualizarProfesor();
    abstract void mostrarTablaProfesor();
    abstract void menuGestion();
}

public abstract class GestorProfesor implements GestorTablaProfesor {
    
    /*------Variables globales---------*/
    
    //Inicializacion de la conexion bd mysql
    private Connection conn = null; 
    private PreparedStatement stmt = null;
    private Statement stmta = null;
    private ResultSet rs = null;
    private Scanner sc = new Scanner(System.in);
    
    
    //Variables de la tabla Alumno
    private String idProfesor;
    private String Nombres;
    private String Apellido;
    private String Dni;
    private String Edad;
    private String Estado;
    private String tipo;
    
    /*------------------------------------*/
    
    
    public GestorProfesor(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/escuela?" + "user=root&password=admin");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLStatus: " + ex.getSQLState());
            System.out.println("EventoError: " + ex.getErrorCode());
        }
    }
    
    @Override
    public void insertarProfesor(){
        try {
            stmt = conn.prepareStatement("INSERT INTO Profesor (idProfesor, Nombres, Apellido, dni, Edad, Estado) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, idProfesor);
            stmt.setString(2, Nombres);
            stmt.setString(3, Apellido);
            stmt.setString(4, Dni);
            stmt.setString(5, Edad);
            stmt.setString(6, Estado);
            stmt.executeUpdate();
            System.out.println(" \n");
            System.out.println("El Profesor " + idProfesor + " ha sido agregado exitosamente. \n");
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
    public void eliminarProfesor(){
        try {
            stmt = conn.prepareStatement("DELETE FROM Profesor WHERE idProfesor = ?");
            stmt.setString(1, idProfesor);
            int filasEliminadas = stmt.executeUpdate();
            if (filasEliminadas == 1) {
                System.out.println("El Profesor con id "+ idProfesor+" ha sido eliminado exitosamente");
                System.out.println("\n");
            }else{
                System.out.println("No se ha encontrado ningun alumno con id "+idProfesor+" para eliminar");
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
    public void actualizarProfesor(){
        try {
            stmt = conn.prepareStatement("UPDATE Profesor SET Nombres = ?, Apellido = ?, dni = ?, Edad = ?, Estado = ? WHERE idProfesor = ?");
            stmt.setString(6, idProfesor);
            stmt.setString(1, Nombres);
            stmt.setString(2, Apellido);
            stmt.setString(3, Dni);
            stmt.setString(4, Edad);
            stmt.setString(5, Estado);
            stmt.executeUpdate();
            System.out.println("El Profesor con id " + idProfesor + " ha sido actualizado exitosamente.");
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
    public void mostrarTablaProfesor(){
        try {
            stmta = conn.createStatement();
            rs = stmta.executeQuery("SELECT * FROM Profesor");
            
            while (rs.next()) {
                System.out.println(" \n");
                System.out.println("ID: " + rs.getString("idProfesor")+" "+rs.getString("Nombres")+" "+
                        rs.getString("Apellido")+" "+rs.getString("dni")+" "+rs.getString("Edad")+" "+rs.getString("Estado"));
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
    public void menuGestion(){
        System.out.println("Ha seleccionado la opción Profesor");
        System.out.println("--------------------------------");
        System.out.println("Escoga una opcion: ");
        System.out.println("I. Insertar");
        System.out.println("E. Eliminar");
        System.out.println("A. Actualizar");
        System.out.println("VT. Ver Tabla");
        System.out.println("O. VOLVER");
        tipo = sc.next();
        
        if (tipo.equals("I")) {
            System.out.print("Ingrese id del Profesor: ");
            idProfesor = sc.next();
            System.out.print("Ingrese Nombre del Profesor: ");
            Nombres = sc.next();
            System.out.print("Ingrese Apellido del Profesor: ");
            Apellido = sc.next();
            System.out.print("Ingrese dni del Profesor: ");
            Dni = sc.next();
            System.out.print("Ingrese Edad del Profesor: ");
            Edad = sc.next();
            System.out.print("Ingrese Estado del Profesor: ");
            Estado = sc.next();
            insertarProfesor();
        }else if (tipo.equals("E")) {
            System.out.print("Ingrese id del Alumno: ");
            idProfesor = sc.next();
            eliminarProfesor();
        }else if (tipo.equals("A")) {
            System.out.print("Ingrese id del Profesor: ");
            idProfesor = sc.next();
            System.out.print("Ingrese Nombre del Profesor: ");
            Nombres = sc.next();
            System.out.print("Ingrese Apellido del Profesor: ");
            Apellido = sc.next();
            System.out.print("Ingrese dni del Profesor: ");
            Dni = sc.next();
            System.out.print("Ingrese Edad del Profesor: ");
            Edad = sc.next();
            System.out.print("Ingrese Estado del Profesor: ");
            Estado = sc.next();
            actualizarProfesor();
        }else if (tipo.equals("VT")) {
            mostrarTablaProfesor();
        }else if (tipo.equals("O")) {
            System.out.println("\n");
            return;
        }else{
            System.out.println("ESCOGA UNA OPCION VALIDA");
        }
    }
}
