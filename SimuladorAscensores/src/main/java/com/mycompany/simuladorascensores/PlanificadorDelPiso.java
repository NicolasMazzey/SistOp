

package com.mycompany.simuladorascensores;

import java.util.LinkedList;
import java.util.Queue;


public class PlanificadorDelPiso extends Thread {
    Queue<Persona> cola_espera;
    int piso_planificando;
    String numero;
    
    PlanificadorDelPiso(String numero, int piso){
        this.cola_espera = new LinkedList<>();
        this.piso_planificando = piso;
        this.numero = numero;
    }
    
    @Override
    public void run() {
        while (true){
            
        }
    }
}
