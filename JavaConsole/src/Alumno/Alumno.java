/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alumno;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jeferson
 */
public class Alumno {
    
    public static void insertarAlumno(String idAlumno, String nombres, String apellido, String sexo, String edad, String estado) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    try {
    conn = DriverManager.getConnection("jdbc:mysql://localhost/escuela?" + "user=root&password=admin");
    stmt = conn.prepareStatement("INSERT INTO Alumno (idAlumno, Nombres, Apellido, Sexo, Edad, Estado) VALUES (?, ?, ?, ?, ?, ?)");
    
    if(idAlumno != null) {
        stmt.setString(1, idAlumno);
    } else {
        stmt.setNull(1, java.sql.Types.VARCHAR);
    }
    
    if(nombres != null) {
        stmt.setString(2, nombres);
    } else {
        stmt.setNull(2, java.sql.Types.VARCHAR);
    }
    
    if(apellido != null) {
        stmt.setString(3, apellido);
    } else {
        stmt.setNull(3, java.sql.Types.VARCHAR);
    }
    
    if(sexo != null) {
        stmt.setString(4, sexo);
    } else {
        stmt.setNull(4, java.sql.Types.VARCHAR);
    }
    
    if(edad != null) {
        stmt.setString(5, edad);
    } else {
        stmt.setNull(5, java.sql.Types.VARCHAR);
    }
    
    if(estado != null) {
        stmt.setString(6, estado);
    } else {
        stmt.setNull(6, java.sql.Types.VARCHAR);
    }

    stmt.executeUpdate();
    rs = stmt.executeQuery("SELECT idAlumno, Nombres, Apellido, Sexo, Edad, Estado FROM Alumno");

    while (rs.next()) {
        System.out.println(rs.getString("idAlumno") + " " + rs.getString("Nombres") + " "
                + rs.getString("Apellido") + " " + rs.getString("Sexo") + " " + rs.getString("Edad") + " "
                + rs.getString("Estado"));
    }
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

    if (stmt != null) {
        try {
            stmt.close();
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        stmt = null;
    }

    if (conn != null) {
        try {
            conn.close();
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        conn = null;
    }
    
    }
    }
    
    
    public static void eliminarAlumno(String idAlumno) {
    Connection conn = null;
    PreparedStatement stmt = null;
    
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/escuela?" + "user=root&password=admin");
        stmt = conn.prepareStatement("DELETE FROM Alumno WHERE idAlumno = ?");
        stmt.setString(1, idAlumno);
        stmt.executeUpdate();
        System.out.println("El alumno con id " + idAlumno + " ha sido eliminado exitosamente.");
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
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException sqlEx) {
                System.out.println(sqlEx.getMessage());
            }
            conn = null;
        }
    }
}
    
    
    public static void actualizarAlumno(String idAlumno, String nombres, String apellido, String sexo, String edad, String estado) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/escuela?" + "user=root&password=admin");
        stmt = conn.prepareStatement("UPDATE Alumno SET Nombres = ?, Apellido = ?, Sexo = ?, Edad = ?, Estado = ? WHERE idAlumno = ?");
        stmt.setString(6, idAlumno);
        
        if(nombres != null) {
            stmt.setString(1, nombres);
        } else {
            stmt.setNull(1, java.sql.Types.VARCHAR);
        }
        
        if(apellido != null) {
            stmt.setString(2, apellido);
        } else {
            stmt.setNull(2, java.sql.Types.VARCHAR);
        }
        
        if(sexo != null) {
            stmt.setString(3, sexo);
        } else {
            stmt.setNull(3, java.sql.Types.VARCHAR);
        }
        
        if(edad != null) {
            stmt.setString(4, edad);
        } else {
            stmt.setNull(4, java.sql.Types.VARCHAR);
        }
        
        if(estado != null) {
            stmt.setString(5, estado);
        } else {
            stmt.setNull(5, java.sql.Types.VARCHAR);
        }

        stmt.executeUpdate();
        rs = stmt.executeQuery("SELECT idAlumno, Nombres, Apellido, Sexo, Edad, Estado FROM Alumno");

        while (rs.next()) {
            System.out.println(rs.getString("idAlumno") + " " + rs.getString("Nombres") + " "
                    + rs.getString("Apellido") + " " + rs.getString("Sexo") + " " + rs.getString("Edad") + " "
                    + rs.getString("Estado"));
        }
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

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) {
                System.out.println(sqlEx.getMessage());
            }
            stmt = null;
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException sqlEx) {
                System.out.println(sqlEx.getMessage());
            }
            conn = null;
        }
    }
}
}
    

