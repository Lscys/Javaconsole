/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;


import Alumno.Alumno;
import Alumno.Alumno;
import Alumno.Alumno;
import Alumno.Alumno;

/**
 *
 * @author Jeferson
 */
public class menuPrincipal {
    static String tipo;
    
    static String idAlumno;
    static String Nombre;
    static String Apellido;
    static String Sexo;
    static String Edad;
    static String Estado;
    
    public static void main(String[] args) {
        
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
                    System.out.println("Ha seleccionado la opci�n Alumno");
                    System.out.println("Escoga una: ");
                    System.out.println("I. Insertar");
                    System.out.println("E. Eliminar");
                    System.out.println("A. Actualizar");
                    tipo = sc.next();
                    switch (tipo) {
                        case "I":
                            System.out.print("Ingrese id del Alumno: ");
                            idAlumno = sc.next();
                            System.out.print("Ingrese Nombre del Alumno: ");
                            Nombre = sc.next();
                            System.out.print("Ingrese Apellido del Alumno: ");
                            Apellido = sc.next();
                            System.out.print("Ingrese Sexo del Alumno: ");
                            Sexo = sc.next();
                            System.out.print("Ingrese Edad del Alumno: ");
                            Edad = sc.next();
                            System.out.print("Ingrese Estado del Alumno: ");
                            Estado = sc.next();
                            Alumno.insertarAlumno(idAlumno, Nombre, Apellido, Sexo, Edad, Estado);
                            System.out.println("FIN DEL PROCESO DE INSERTAR ALUMNO");
                            break;
                        case "E":
                            System.out.print("Ingrese id del Alumno: ");
                            idAlumno = sc.next();
                            Alumno.eliminarAlumno(idAlumno);
                            System.out.println("FIN DEL PROCESO DE ELIMINAR ALUMNO");
                            break;
                        case "A":
                            System.out.print("Ingrese id del Alumno: ");
                            idAlumno = sc.next();
                            System.out.print("Ingrese Nombre del Alumno: ");
                            Nombre = sc.next();
                            System.out.print("Ingrese Apellido del Alumno: ");
                            Apellido = sc.next();
                            System.out.print("Ingrese Sexo del Alumno: ");
                            Sexo = sc.next();
                            System.out.print("Ingrese Edad del Alumno: ");
                            Edad = sc.next();
                            System.out.print("Ingrese Estado del Alumno: ");
                            Estado = sc.next();
                            Alumno.actualizarAlumno(idAlumno, Nombre, Apellido, Sexo, Edad, Estado);
                            System.out.println("FIN DEL PROCESO DE ACTUALIZAR ALUMNO");
                            break;
                        default:
                            System.out.println("SELECIONE ALGUNA DE LAS OPCIONES MOSTRADAS");
                            break;
                }
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

