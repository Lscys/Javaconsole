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
            
            System.out.print("Seleccione una opci�n: ");
            opcion = sc.nextLine();
            
            switch (opcion) {
                case "1":
                    System.out.println(" \n");
                    gesA.menuGestion();
                break;
                case "2":
                    // Agrega aqu� el c�digo que deseas ejecutar para la opci�n 2
                    System.out.println("Ha seleccionado la opci�n Profesor");
                    System.out.println("Escoga una");
                    break;
                case "3":
                    // Agrega aqu� el c�digo que deseas ejecutar para la opci�n 3
                    System.out.println("Ha seleccionado la opci�n Cursos");
                    System.out.println("Escoga una");
                    break;
                case "4":
                    // Si se selecciona la opci�n "SALIR", se sale del bucle y se finaliza el men�
                    System.out.println("Ha seleccionado la opci�n Notas");
                    System.out.println("Escoga una");
                    break;
                case "5":
                    // Si se selecciona la opci�n "SALIR", se sale del bucle y se finaliza el men�
                    System.out.println("Ha seleccionado SALIR. Saliendo del men�...");
                    break;
                default:
                    System.out.println("Opci�n no v�lida. Por favor seleccione una opci�n del men�.");
                    break;
            }
            
        } while (!opcion.equals("5"));
        
        sc.close();
    }
    
      
}

