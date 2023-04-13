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
    abstract void menuGestion();
}

//Clase padre
abstract sealed class GestorCursos implements GestorTablaCursos {
    public Connection conn = null; 
    public PreparedStatement stmt = null;
    public Statement stmta = null;
    public ResultSet rs = null;
    public Scanner sc = new Scanner(System.in);
    
    public String idCurso;
    public String Descripcion;
    
    public String tipo;
    public String tipoC;
    public int horaI;
    
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
            System.out.print("Ingrese id del Curso: ");
            idCurso = sc.next();
            System.out.print("Ingrese descripcion del Curso: ");
            idCurso = sc.next();
            CursoPresencial gesC = new CursoPresencial(idCurso, Descripcion, horaI);
        }else if (tipo.equals("E")) {
            System.out.print("Ingrese id del Curso: ");
            idCurso = sc.next();
            
        }else if (tipo.equals("A")) {
            System.out.print("Ingrese id del Curso: ");
            idCurso = sc.next();
            System.out.print("Ingrese Descripcion del Curso: ");
            Descripcion = sc.next();
            
        }else if (tipo.equals("VT")) {
            
        }else if (tipo.equals("O")) {
            System.out.println("\n");
            return;
        }else{
            System.out.println("ESCOGA UNA OPCION VALIDA");
        }
    }
    
    abstract void insertarCurso();
    abstract void eliminarCurso();
    abstract void actualizarCurso();
    abstract void verTablaCurso();
    
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



non-sealed class CursoPresencial extends GestorCursos{
    private int horas;
    
    public CursoPresencial(String idCurso, String Descripcion, int horas){
       super(idCurso, Descripcion);     
       this.horas = horas;
    }
    
    @Override
    public void insertarCurso(){
        try {
            stmt = conn.prepareStatement("INSERT INTO Cursos (idCurso, Descripcon) VALUES (?, ?)");
            stmt.setString(1, idCurso);
            stmt.setString(2, Descripcion);
            stmt.executeUpdate();
            System.out.println(" \n");
            System.out.println("El Curso "+ idCurso+" "+ this.PRESENCIAL()+" ha sido agregado exitosamente. \n");
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
    public void eliminarCurso(){
        try {
            stmt = conn.prepareStatement("DELETE FROM Cursos WHERE idCurso = ?");
            stmt.setString(1, idCurso);
            int filasEliminadas = stmt.executeUpdate();
            if (filasEliminadas == 1) {
                System.out.println("El Curso con id "+ idCurso+" "+ this.PRESENCIAL()+" ha sido eliminado exitosamente");
                System.out.println("\n");
            }else{
                System.out.println("No se ha encontrado ningun curso con id "+ idCurso+" "+ this.PRESENCIAL()+" para eliminar");
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
    public void actualizarCurso(){
        try {
            stmt = conn.prepareStatement("UPDATE Cursos SET Descripcon = ? WHERE idCurso = ?");
            stmt.setString(6, idCurso);
            stmt.setString(1, Descripcion);
            stmt.executeUpdate();
            System.out.println("El Curso con id "+ idCurso+" "+ this.PRESENCIAL()+" ha sido actualizado exitosamente.");
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
    public void verTablaCurso(){
        try {
            stmta = conn.createStatement();
            rs = stmta.executeQuery("SELECT * FROM Cursos");
            
            while (rs.next()) {
                System.out.println(" \n");
                System.out.println("ID: " + rs.getString("idCurso")+" "+rs.getString("Descripcion")+" "+
                        rs.getString(this.horas)+" "+rs.getString(this.PRESENCIAL()));
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
    
    

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
    
}

final class CursoVirtual extends GestorCursos{
    private int duracion;
    
    public CursoVirtual(String idCurso, String Descripcion, int duracion){
        super(idCurso, Descripcion);
        this.duracion = duracion;
    }
    
    @Override
    public void insertarCurso(){
        try {
            stmt = conn.prepareStatement("INSERT INTO Cursos (idCurso, Descripcon) VALUES (?, ?)");
            stmt.setString(1, idCurso);
            stmt.setString(2, Descripcion);
            stmt.executeUpdate();
            System.out.println(" \n");
            System.out.println("El Curso "+ idCurso+" "+ this.VIRTUAL()+" ha sido agregado exitosamente. \n");
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
    public void eliminarCurso(){
        try {
            stmt = conn.prepareStatement("DELETE FROM Cursos WHERE idCurso = ?");
            stmt.setString(1, idCurso);
            int filasEliminadas = stmt.executeUpdate();
            if (filasEliminadas == 1) {
                System.out.println("El Curso con id "+ idCurso+" "+ this.PRESENCIAL()+" ha sido eliminado exitosamente");
                System.out.println("\n");
            }else{
                System.out.println("No se ha encontrado ningun curso con id "+ idCurso+" "+ this.VIRTUAL()+" para eliminar");
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
    public void actualizarCurso(){
        try {
            stmt = conn.prepareStatement("UPDATE Cursos SET Descripcon = ? WHERE idCurso = ?");
            stmt.setString(6, idCurso);
            stmt.setString(1, Descripcion);
            stmt.executeUpdate();
            System.out.println("El Curso con id "+ idCurso+" "+ this.VIRTUAL()+" ha sido actualizado exitosamente.");
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
    public void verTablaCurso(){
        try {
            stmta = conn.createStatement();
            rs = stmta.executeQuery("SELECT * FROM Cursos");
            
            while (rs.next()) {
                System.out.println(" \n");
                System.out.println("ID: " + rs.getString("idCurso")+" "+rs.getString("Descripcion")+" "+
                        rs.getString(this.duracion)+" "+rs.getString(this.VIRTUAL()));
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
    

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    
    
}