/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alumno;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jeferson
 */
public class actualizarAlumno {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/escuela?" + 
                    "user=root&password=admin");
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE Alumno SET Nombres='Giorge' WHERE idAlumno = 'A0003'");
            rs = stmt.executeQuery("SELECT idAlumno, Nombres, Apellido, Sexo, Edad, Estado FROM Alumno");
            
            while(rs.next()){
                System.out.println(rs.getString("idAlumno")+" "+rs.getString("Nombres")+" "+rs.getString("Apellido")+
                        " "+rs.getString("Sexo")+" "+rs.getString("Edad")+" "+rs.getString("Estado"));
            }
        } catch(SQLException ex){
            System.out.println("SQLException: "+ex.getMessage());
            System.out.println("SQLStatus: "+ex.getSQLState());
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
