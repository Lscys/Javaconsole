/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Scanner;
import Alumno.GestorAlumnos;
import Profesor.GestorProfesor;

import Cursos.CursoVirtual;
import Cursos.CursoPresencial;
import Cursos.GestorCursos;

/**
 *
 * @author Jeferson
 */
public class menuPrincipal {
    static String tipoC;
    
    public static void main(String[] args) {
        
        GestorAlumnos gesA = new GestorAlumnos() {};
        GestorProfesor gesP = new GestorProfesor() {};  
        // gesC = new CursoPresencial();
        Scanner sc = new Scanner(System.in);
        
        String idCurso;
        String Descripcion;
        String tipo;
        String plataforma;
        String lugar;
        
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
                    // Agrega aquí el código que deseas ejecutar para la opción 2
                    System.out.println(" \n");
                    gesP.menuGestion();
                    break;
                case "3":
                    // Agrega aquí el código que deseas ejecutar para la opción 3
                    System.out.println("\n");
                    System.out.print("Que tipo de Curso desea insertar? \n V. VIRTUAL \n P. PRESENCIAL: \n");
                    tipoC = sc.next();
                    if (tipoC.equals("P")) {
                        CursoPresencial gesC = new CursoPresencial();
                        gesC.menuGestion();
                    }else if (tipoC.equals("V")) {
                        CursoVirtual gesC = new CursoVirtual();
                        gesC.menuGestion();
                    }
                    break;
                case "4":
                    // Si se selecciona la opción "SALIR", se sale del bucle y se finaliza el menú
                    System.out.println("Ha seleccionado la opcion Notas");
                    break;
                case "5":
                    // Si se selecciona la opción "SALIR", se sale del bucle y se finaliza el menú
                    System.out.println("Ha seleccionado SALIR. Saliendo del menu...");
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor seleccione una opción del menu.");
                    break;
            }
            
        } while (!opcion.equals("5"));
        
        sc.close();
    }
    
      
}

