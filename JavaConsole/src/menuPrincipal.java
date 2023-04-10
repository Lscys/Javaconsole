/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;
import Alumno.GestorAlumnos;

/**
 *
 * @author Jeferson
 */
public class menuPrincipal {
    static String tipo;
    
    public static void main(String[] args) {
        
        GestorAlumnos gesA = new GestorAlumnos() {};
        
        Scanner sc = new Scanner(System.in);
        String opcion;
        
        do {
            System.out.println("1. Alumno");
            System.out.println("2. Profesor");
            System.out.println("3. Cursos");
            System.out.println("4. Notas");
            System.out.println("5. Salir");
            
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextLine();
            
            switch (opcion) {
                case "1":
                    System.out.println(" \n");
                    gesA.menuGestion();
                break;
                case "2":
                    // Agrega aquí el código que deseas ejecutar para la opción 2
                    System.out.println("Ha seleccionado la opción Profesor");
                    System.out.println("Escoga una");
                    break;
                case "3":
                    // Agrega aquí el código que deseas ejecutar para la opción 3
                    System.out.println("Ha seleccionado la opción Cursos");
                    System.out.println("Escoga una");
                    break;
                case "4":
                    // Si se selecciona la opción "SALIR", se sale del bucle y se finaliza el menú
                    System.out.println("Ha seleccionado la opción Notas");
                    System.out.println("Escoga una");
                    break;
                case "5":
                    // Si se selecciona la opción "SALIR", se sale del bucle y se finaliza el menú
                    System.out.println("Ha seleccionado SALIR. Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor seleccione una opción del menú.");
                    break;
            }
            
        } while (!opcion.equals("5"));
        
        sc.close();
    }
    
      
}

