package com.mycompany.simuladorascensores;

import java.util.*;
import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

public class SimuladorAscensores {

    static volatile int maxPisos;
    static volatile int Momento = 0;
    static volatile PriorityQueue<Ascensor> listaDeAscensores;

    static Semaphore SemaforoAscensor = new Semaphore(1);
    //static Semaphore SemaforoPlanificador = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {

        ComparadorAscensores CA = new ComparadorAscensores();
        listaDeAscensores = new PriorityQueue<>(CA);
        List<Persona> lista_personas = new ArrayList<>(); // Lista para almacenar las personas creadas
        lista_personas = CargarInstrucciones();
        
        

        /*
        Ascensor a1 = new Ascensor("1");
        Ascensor a2 = new Ascensor("2");
        listaDeAscensores.add(a1);
        listaDeAscensores.add(a2);             
        
        System.out.println(listaDeAscensores);
         */
        
        /*
                //Despues de que este todo inicializado y corriendo
                //va contando los momentos cada 1 seg "arbitrario"
                for (int i = 0; i < 5; i++) {
                    Momento += 1;
                    Thread.sleep(1000);
                }
         */
    }//CierreMAIN

    public static List<Persona> CargarInstrucciones() {
        List<Persona> lista_personas_temp = new ArrayList<>(); // Lista para almacenar las personas creadas

        String[] palabras = ManejadorDeTexto.leerArchivo("src/main/java/com/mycompany/simuladorascensores/instrucciones.txt");
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

                    Persona person = new Persona(nombre_persona, piso_destino, piso_origen, peso_persona, tiempo);
                    // Agregar la persona a la lista
                    lista_personas_temp.add(person);
                }

                scanner.close(); // Cerrar el Scanner

            }
        }

        // Imprimir los elementos de la lista
        for (Persona persona : lista_personas_temp) {
            System.out.println(persona.getNombre() + persona.getInicio() + persona.getDestino() + persona.getPeso() + persona.getMomento());
        }

        return lista_personas_temp;
    }
}
