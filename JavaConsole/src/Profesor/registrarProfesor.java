/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Profesor;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jeferson
 */
public class registrarProfesor {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/escuela?" + 
                    "user=root&password=admin");
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Profesor VALUES ('P0001','Dani','Gonzales','9874563',28,'ACTIVO')");
            stmt.executeUpdate("INSERT INTO Profesor VALUES ('P0002','Rosales','Cantaro','5556842',45,'ACTIVO')");
            stmt.executeUpdate("INSERT INTO Profesor VALUES ('P0003','Quike','Oasi','2214453',36,'ACTIVO')");
            rs = stmt.executeQuery("SELECT idProfesor, Nombres, Apellido, dni, Edad, Estado FROM Profesor");
            
            while(rs.next()){
                System.out.println(rs.getString("idProfesor")+" "+rs.getString("Nombres")+" "+rs.getString("Apellido")+
                        " "+rs.getString("dni")+" "+rs.getString("Edad")+" "+rs.getString("Estado"));
            }
        } catch(SQLException ex){
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
