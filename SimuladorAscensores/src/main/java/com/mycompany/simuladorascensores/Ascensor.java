

package com.mycompany.simuladorascensores;

import static com.mycompany.simuladorascensores.SimuladorAscensores.maxPisos;
import java.util.PriorityQueue;


public class Ascensor extends Thread {
    PriorityQueue<Persona> Carga; 
    int peso;
    int cantPersonas;
    int pisoActual;
    boolean mirando_arriba;
    String numero;
    
    Ascensor(String numero){
        this.Carga = new PriorityQueue<>();
        this.peso = 0;
        this.cantPersonas = 0;
        this.pisoActual = 0;
        this.mirando_arriba = true;
        this.numero = numero;
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
                            /*
                            Reajustar las prioridades de la cola en funcion al
                            piso y segun para donde quedo mirando.
                            */
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
