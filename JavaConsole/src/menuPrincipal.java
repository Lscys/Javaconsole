/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Scanner;
import Alumno.GestorAlumnos;
import Profesor.GestorProfesor;
import Cursos.GestorCursos;

/**
 *
 * @author Jeferson
 */
public class menuPrincipal {
    static String tipo;
    
    public static void main(String[] args) {
        
        GestorAlumnos gesA = new GestorAlumnos() {};
        GestorProfesor gesP = new GestorProfesor() {};  
        Scanner sc = new Scanner(System.in);
        
        String opcion;
        do {
            System.out.println("TABLAS DE LA BASE DE DATOS");
            System.out.println("SELECCIONE UNA OPCION");
            System.out.println("---------------------------");
            System.out.println("1. Alumno");
            System.out.println("2. Profesor");
            System.out.println("3. Cursos");
            System.out.println("4. Notas");
            System.out.println("5. Salir");
            
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextLine();
            
            switch (opcion) {
                case "1":
                    System.out.println(" \n");
                    gesA.menuGestion();
                    break;
                case "2":
                    // Agrega aqu� el c�digo que deseas ejecutar para la opci�n 2
                    System.out.println(" \n");
                    gesP.menuGestion();
                    break;
                case "3":
                    // Agrega aqu� el c�digo que deseas ejecutar para la opci�n 3
                    break;
                case "4":
                    // Si se selecciona la opci�n "SALIR", se sale del bucle y se finaliza el men�
                    System.out.println("Ha seleccionado la opcion Notas");
                    System.out.println("Escoga una");
                    break;
                case "5":
                    // Si se selecciona la opci�n "SALIR", se sale del bucle y se finaliza el men�
                    System.out.println("Ha seleccionado SALIR. Saliendo del menu...");
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor seleccione una opci�n del menu.");
                    break;
            }
            
        } while (!opcion.equals("5"));
        
        sc.close();
    }
    
      
}

