

package com.mycompany.simuladorascensores;

import static com.mycompany.simuladorascensores.SimuladorAscensores.maxPisos;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Ascensor extends Thread {
    PriorityQueue<Persona> Carga; 
    int peso;
    int cantPersonas;
    int pisoActual;
    boolean mirando_arriba;
    String numero;
    boolean invertido; // para el comparador
    
    Ascensor(String numero){
        Comparator<Persona> comparador;
        comparador = new ComparadorPersonas();
        this.Carga = new PriorityQueue<>(comparador.reversed());
        this.peso = 0;
        this.cantPersonas = 0;
        this.pisoActual = 0;
        this.mirando_arriba = true;
        this.numero = numero;
        this.invertido = true;
    }
    
    @Override
    public void run() {
        while (true) {
            //wait 
            if (!Carga.isEmpty()) {
                //proceso Activo
                Persona primero = Carga.peek();
                //signal 
                if (primero.destino > pisoActual) {
                    this.mirando_arriba = true;
                    pisoActual += 1;
                } else {
                    if (primero.destino == pisoActual) {
                        if (primero.enAscensor){
                            //wait
                            cantPersonas -= 1;
                            peso = peso - primero.peso;
                            Carga.remove(primero);
                            //print con la persona que se bajo
                            //signal 
                        } else {
                            //wait
                            Carga.peek().enAscensor = true;
                            //print con la persona que sube al ascensor
                            if (!mirando_arriba && invertido){
                                Comparator<Persona> comparador;
                                comparador = new ComparadorPersonas();
                                PriorityQueue<Persona> Carga2;
                                Carga2 = new PriorityQueue<>(comparador);
                                Carga2.addAll(Carga);
                                Carga = Carga2;
                                invertido = false;
                            }
                            //falta el otro caso de invertir las prioridades
                            
                            //signal
                        }
                    } else {
                        this.mirando_arriba = false;
                        pisoActual -= 1;
                    }
                }
            } else {
                //signal
                //proceso de inactividad
                if(pisoActual != 0 && pisoActual != maxPisos){
                    if (((maxPisos - pisoActual) < (maxPisos/2))){
                        pisoActual -= 1;
                    } else {
                        pisoActual += 1;
                    }
                }
            }
        }
    }
}
