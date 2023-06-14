package com.mycompany.simuladorascensores;

import java.util.*;
import java.util.PriorityQueue;

public class SimuladorAscensores {
    static volatile int maxPisos;

    static volatile PriorityQueue<Ascensor> listaDeAscensores;    //hacer un comparador para la cola en funcion a la cantidad de personas.

    public static void main(String[] args) {

        // Primer archivo
        System.out.println("---------------");
        System.out.println("Primer archivo");
        System.out.println("---------------");

        String[] palabras = ManejadorDeTexto.leerArchivo("/Users/jose/Documents/GitHub/SistOp/SimuladorAscensores/src/main/java/com/mycompany/simuladorascensores/instrucciones.txt");
        String Cant_ascensores = palabras[0];
        String Cant_pisos = palabras[1];
        String espacio = palabras[2];
        String instrucciones = palabras[3];
        int ascensores_disponibles;
        int pisos_edificio;
        for (String s : palabras) {
            if (s == Cant_ascensores) {
                char identificador = ':';

                int index = s.indexOf(identificador); // Encuentra la posición del primer carácter ':'

                if (index != -1) {
                    String charactersAfter = s.substring(index + 1); // Extrae los caracteres posteriores
                    //System.out.println("Caracteres posteriores a '" + identificador + "' son: " + charactersAfter);
                    ascensores_disponibles = Integer.parseInt(charactersAfter);
                    //ascensores_disponibles = ascensores_disponibles + 1;
                    //System.out.println(ascensores_disponibles);

                } else {
                    System.out.println("Las instrucciones no tienen el formato adecuado");
                    System.out.println("Revisar el formato de Cantidad de ascensores");

                }
            } else if (s == Cant_pisos) {
                char identificador = ':';

                int index = s.indexOf(identificador); // Encuentra la posición del primer carácter ':'

                if (index != -1) {
                    String charactersAfter = s.substring(index + 1); // Extrae los caracteres posteriores
                    //System.out.println("Caracteres posteriores a '" + identificador + "' son: " + charactersAfter);
                    pisos_edificio = Integer.parseInt(charactersAfter);
                    //pisos_edificio = pisos_edificio + 1;
                    //System.out.println(pisos_edificio);

                } else {
                    System.out.println("Las instrucciones no tienen el formato adecuado");
                    System.out.println("Revisar el formato de Cantidad de Pisos");

                }
            } else if (s == espacio) {
                System.out.println("");
            } else if (s == instrucciones) {
                //System.out.println("Instrucciones: " + s);
            } else {
                String inputData = s;

                // Crear un objeto Scanner con el String de entrada
                Scanner scanner = new Scanner(inputData);
                scanner.useDelimiter(";"); // Utilizar el ';' como delimitador

                // Declarar variables para almacenar los elementos
                String nombre_persona;
                int peso_persona, piso_origen, piso_destino, tiempo;

                // Iterar a través de los datos separados
                while (scanner.hasNext()) {
                    // Asignar valores a las variables
                    nombre_persona = scanner.next();
                    peso_persona = Integer.parseInt(scanner.next());
                    piso_origen = Integer.parseInt(scanner.next());
                    piso_destino = Integer.parseInt(scanner.next());
                    tiempo = Integer.parseInt(scanner.next());
                    //String data = scanner.next();

                    //System.out.println(data);
                }

                scanner.close(); // Cerrar el Scanner

                //persona = new Persona();
                //System.out.println(s);
            }
        }
    }
}
