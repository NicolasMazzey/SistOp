package com.mycompany.simuladorascensores;

import java.util.*;
import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

public class SimuladorAscensores {

    static volatile int maxPisos;
    static volatile int ascensores_disponibles;
    static volatile int Momento = 10;
    static volatile PriorityQueue<Ascensor> listaDeAscensores;
    static volatile PriorityQueue<Persona> cola_espera;

    static Semaphore SemaforoAscensor = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {

        ComparadorAscensores CA = new ComparadorAscensores();
        listaDeAscensores = new PriorityQueue<>(CA);
        List<Persona> lista_personas = new ArrayList<>(); // Lista para almacenar las personas creadas
        lista_personas = CargarInstrucciones();
        
        
        for(int i = 1; i <= ascensores_disponibles ; i++){
            Ascensor a;
            a = new Ascensor(Integer.toString(i));
            listaDeAscensores.add(a);
            new Thread(a, Integer.toString(i+1)).start();
            System.out.println("Se creo el Ascensor numero " + i);
        }
        
        new Thread(new PlanificadorDelPiso("1"), "1").start();
        
        //Despues de que este todo inicializado y corriendo
        //va contando los momentos cada 1 seg "arbitrario"
        for (int i = 0; i < 5; i++) {
            Momento += 1;
            Thread.sleep(1000);
        }

        
    }//CierreMAIN

    public static List<Persona> CargarInstrucciones() {
        List<Persona> lista_personas_temp = new ArrayList<>(); // Lista para almacenar las personas creadas
        
        ComparadorPlanificador CP = new ComparadorPlanificador();
        cola_espera = new PriorityQueue<>(CP);
        
        
        // Primer archivo
        String[] palabras = ManejadorDeTexto.leerArchivo("src/main/java/com/mycompany/simuladorascensores/instrucciones.txt");
        String Cant_ascensores = palabras[0];
        String Cant_pisos = palabras[1];
        String espacio = palabras[2];
        String instrucciones = palabras[3];
        //int pisos_edificio;

        List<Persona> lista_personas = new ArrayList<>(); // Lista para almacenar las personas creadas
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
                    maxPisos = Integer.parseInt(charactersAfter);
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

                    if ((piso_origen <= 10 && 0 <= piso_origen) && (piso_destino <= 10 && 0 <= piso_destino)) {
                        Persona person = new Persona(nombre_persona, piso_destino, piso_origen, peso_persona, tiempo);
                        // Agregar la persona a la lista
                        lista_personas_temp.add(person);
                    }
                    Persona person = new Persona(nombre_persona, piso_destino, piso_origen, peso_persona, tiempo);
                    // Agregar la persona a la lista
                    cola_espera.add(person);
                }
                scanner.close(); // Cerrar el Scanner
            }
        }
        /*
        // Imprimir los elementos de la lista
        for (Persona persona : lista_personas_temp) {
            System.out.println(persona.getNombre() + persona.getInicio() + persona.getDestino() + persona.getPeso() + persona.getMomento());
        }
        */

        return lista_personas_temp;
    }
}
