/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Scanner;
import Alumno.GestorAlumnos;
import Profesor.GestorProfesor;
import Notas.GestorNotas;
import Promedios.GestorPromedios;

import Cursos.CursoVirtual;
import Cursos.CursoPresencial;


/**
 *
 * @author Jeferson
 */
public class menuPrincipal {
    static String tipoC;
    
    public static void main(String[] args) {
        
        GestorAlumnos gesA = new GestorAlumnos() {};
        GestorProfesor gesP = new GestorProfesor() {};  
        GestorNotas gesN = new GestorNotas() {}; 
        GestorPromedios gesPro = new GestorPromedios() {};
        
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
            System.out.println("5. Promedios");
            System.out.println("6. Salir");
            
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextLine();
            
            switch (opcion) {
                case "1":
                    System.out.println();
                    gesA.menuGestion();
                    break;
                case "2":
                    System.out.println();
                    gesP.menuGestion();
                    break;
                case "3":
                    System.out.println();
                    System.out.println("¿Qué tipo de curso desea insertar?");
                    System.out.println("V. Virtual");
                    System.out.println("P. Presencial");
                    tipoC = sc.nextLine();

                    if (tipoC.equalsIgnoreCase("P")) {
                        CursoPresencial gesC = new CursoPresencial();
                        gesC.menuGestion();
                    } else if (tipoC.equalsIgnoreCase("V")) {
                        CursoVirtual gesC = new CursoVirtual();
                        gesC.menuGestion();
                    } else {
                        System.out.println("Opcion no valida. Por favor seleccione una opción del menu.");
                    }
                    break;
                case "4":
                    System.out.println();
                    gesN.menuGestion();
                    break;
                case "5":
                    System.out.println();
                    gesPro.menuGestion();
                    break;
                case "6":
                    System.out.println();
                    System.out.println("Ha seleccionado SALIR. Saliendo del menu...");
                    break;
                default:
                    System.out.println();
                    System.out.println("Opcion no valida. Por favor seleccione una opción del menu.");
                    break;
            }
            
        } while (!opcion.equals("6"));
        
        sc.close();
    }
    
      
}

