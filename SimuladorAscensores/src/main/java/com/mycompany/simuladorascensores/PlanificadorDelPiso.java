

package com.mycompany.simuladorascensores;

import java.util.PriorityQueue;


public class PlanificadorDelPiso extends Thread {
    PriorityQueue<Persona> cola_espera;
    int piso_planificando;
    String numero;
    
    PlanificadorDelPiso(String numero, int piso){
        this.cola_espera = new PriorityQueue<>();
        this.piso_planificando = piso;
        this.numero = numero;
    }
    
    @Override
    public void run() {
        while (true){
            
        }
    }
}
