/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos;

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

interface GestorTablaCursos {
    abstract String PRESENCIAL();
    abstract String VIRTUAL();
    void menuGestion();
    
    void insertarCurso();
    void eliminarCurso();      
    void actualizarCurso();
    void verTablaCurso();
}

//Clase padre
public abstract class GestorCursos implements GestorTablaCursos{
    //Variables para la conexion
    public Connection conn = null; 
    public PreparedStatement stmt = null;
    public Statement stmta = null;
    public ResultSet rs = null;
    public Scanner sc = new Scanner(System.in);
    
    //Variables de la tabla Cursos
    public String idCurso;
    public String Descripcion;
    
    //Variables para validar
    public String tipo;
    private String tipoC;
    public int horaI;
    
    public GestorCursos(){
        this.idCurso = "";
        this.Descripcion = "";
        // Establecer una conexion a bd MySQL
        try{
        conn = DriverManager.getConnection("jdbc:mysql://localhost/escuela?" + "user=root&password=admin");
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLStatus: " + ex.getSQLState());
            System.out.println("EventoError: " + ex.getErrorCode());
        }
        
    }
    
    public GestorCursos(String idCurso, String Descripcion){
        this.idCurso = idCurso;
        this.Descripcion = Descripcion;
        // Establecer una conexion a bd MySQL
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/escuela?" + "user=root&password=admin");
        }catch(SQLException ex){
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLStatus: " + ex.getSQLState());
                System.out.println("EventoError: " + ex.getErrorCode());
        }
    }
    
    @Override
    public final String PRESENCIAL(){
        return "PRESENCIAL";
    }
    
    @Override
    public final String VIRTUAL(){
        return "VIRTUAL";
    }
    
    @Override
    public void menuGestion(){
        System.out.println(" \n");
        System.out.println("HA SELECCIONADO LA OPCION CURSOS");
        System.out.println("================================");
        System.out.println("Escoga una opcion: ");
        System.out.println("I. Insertar");
        System.out.println("E. Eliminar");
        System.out.println("A. Actualizar");
        System.out.println("VT. Ver Tabla");
        System.out.println("O. VOLVER");
        tipo = sc.next();
        
        if (tipo.equals("I")) {
            System.out.println("\n");
            System.out.print("Ingrese idCurso: ");
            idCurso = sc.next();
            System.out.print("Ingrese Descripcion del Curso: ");
            Descripcion = sc.next();
            System.out.print("Ingrese Hora o Plataforma: ");
            tipoC = sc.next();
            insertarCurso();
        }else if (tipo.equals("E")) {
            eliminarCurso(); 
        }else if (tipo.equals("A")) {
            actualizarCurso();
        }else if (tipo.equals("VT")) {
            verTablaCurso();
            return;
        }else if (tipo.equals("O")) {
            System.out.println("\n");
        }else{
            System.out.println("ESCOGA UNA OPCION VALIDA");
        }
    }
    
    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }    
}