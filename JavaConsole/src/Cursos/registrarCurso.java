/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jeferson
 */
public class registrarCurso {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/escuela?" + 
                    "user=root&password=admin");
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Cursos VALUES ('C0001', 'Matematica')");
            stmt.executeUpdate("INSERT INTO Cursos VALUES ('C0002', 'Comunicación')");
            stmt.executeUpdate("INSERT INTO Cursos VALUES ('C0003', 'Historia')");
            stmt.executeUpdate("INSERT INTO cursos VALUES ('C0004', 'Ciencia')");
            rs = stmt.executeQuery("SELECT idCurso, Descripcon FROM Cursos");
            
            while(rs.next()){
                System.out.println(rs.getString("idCurso")+ " "+rs.getString("Descripcon"));
            }
        }catch (SQLException ex){
            System.out.println("SQLException: "+ex.getMessage());
            System.out.println("SQLState: "+ex.getSQLState());
            System.out.println("EventoError: "+ex.getErrorCode());
        }finally{
            
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx){
                    System.out.println(sqlEx.getMessage());
                }
                rs = null;
            }
            
            if (stmt != null) {
                try{
                    stmt.close();
                } catch (SQLException sqlEx){
                    System.out.println(sqlEx.getMessage());
                }
                stmt = null;
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlEx){
                    System.out.println(sqlEx.getMessage());
                }
                conn = null;
            }
            
        }
    }
}
