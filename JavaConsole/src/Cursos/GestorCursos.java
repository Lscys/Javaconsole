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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

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
        System.out.println();
        System.out.println("HA SELECCIONADO LA OPCION CURSOS");
        System.out.println("================================");
        System.out.println("Escoga una opcion: ");
        System.out.println("I. Insertar");
        System.out.println("E. Eliminar");
        System.out.println("A. Actualizar");
        System.out.println("VT. Ver Tabla");
        System.out.println("PDF. CURSO");
        System.out.println("O. VOLVER");
        tipo = sc.next();
        
        if (tipo.equalsIgnoreCase("I")) {
            System.out.println("\n");
            System.out.print("Ingrese idCurso: ");
            idCurso = sc.next();
            System.out.print("Ingrese Descripcion del Curso: ");
            Descripcion = sc.next();
            System.out.print("Ingrese Hora o Plataforma: ");
            tipoC = sc.next();
            insertarCurso();
        }else if (tipo.equalsIgnoreCase("E")) {
            System.out.println("\n");
            System.out.print("Ingrese idCurso: ");
            idCurso = sc.next();
            eliminarCurso(); 
        }else if (tipo.equalsIgnoreCase("A")) {
            System.out.println("\n");
            System.out.print("Ingrese idCurso: ");
            idCurso = sc.next();
            System.out.print("Ingrese Descripcion del Curso: ");
            Descripcion = sc.next();
            System.out.print("Ingrese Hora o Plataforma: ");
            tipoC = sc.next();
            actualizarCurso();
        }else if (tipo.equalsIgnoreCase("VT")) {
            verTablaCurso();
            return;
        }else if (tipo.equalsIgnoreCase("PDF")) {
            verPDFCursos();
            return;
        }
        else if (tipo.equalsIgnoreCase("O")) {
            System.out.println();
        }else{
            System.out.println("ESCOGA UNA OPCION VALIDA");
        }
    }
    
    public void verPDFCursos(){
        try{
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Jeferson\\JaspersoftWorkspace\\MyReports\\ReporteCursos.jasper", null, conn);
            JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\Jeferson\\JaspersoftWorkspace\\MyReports\\ReporteCursos.pdf");
            System.out.println("\nArchivo Alumno creado correctamente");   
            System.out.println();
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