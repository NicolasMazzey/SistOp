

package com.mycompany.simuladorascensores;


import java.util.PriorityQueue;


public class SimuladorAscensores {
    static volatile int maxPisos;
    
    static volatile PriorityQueue<Ascensor> listaDeAscensores;    //hacer un comparador para la cola en funcion a la cantidad de personas.
    
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
