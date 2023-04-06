/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Notas;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jeferson
 */
public class registrarNotas {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/escuela?" + 
                    "user=root&password=admin");
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Notas VALUES ('A0001','C0001','P0001',15,18,20)");
            stmt.executeUpdate("INSERT INTO Notas VALUES ('A0001','C0002','P0003',15,18,20)");
            stmt.executeUpdate("INSERT INTO Notas VALUES ('A0002','C0001','P0002',12,15,18)");
            stmt.executeUpdate("INSERT INTO Notas VALUES ('A0003','C0002','P0003',16,15,17)");
            rs = stmt.executeQuery("SELECT idAlumno, idCurso, idProfesor, Nota1, Nota2, Nota3 FROM Notas");
            
            while(rs.next()){
                System.out.println(rs.getString("idAlumno")+" "+rs.getString("idCurso")+" "+rs.getString("idProfesor")+
                        " "+rs.getString("Nota1")+" "+rs.getString("Nota2")+" "+rs.getString("Nota3"));
            }
        }catch(SQLException ex){
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
