/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos;

import java.sql.SQLException;

/**
 *
 * @author Jeferson
 */
public class CursoVirtual extends GestorCursos{
    private int horas;
    
    public CursoVirtual(){
        this.horas = 0;
        
    }
    
    public CursoVirtual(String idCurso, String Descripcion, int horas){
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
            stmt.setString(2, idCurso);
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
                System.out.println("ID: " + rs.getString("idCurso")+" "+rs.getString("Descripcon"));
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
    

    public int gethoras() {
        return horas;
    }

    public void sethoras(int horas) {
        this.horas = horas;
    }
    
}
