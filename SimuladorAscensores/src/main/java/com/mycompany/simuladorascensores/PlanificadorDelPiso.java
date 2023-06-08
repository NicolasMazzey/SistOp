

package com.mycompany.simuladorascensores;


public class PlanificadorDelPiso extends Thread {
    Persona[] cola_espera;
    int piso_planificando;
    String numero;
    
    PlanificadorDelPiso(String numero, int piso){
        this.cola_espera = new Persona[10];
        this.piso_planificando = piso;
        this.numero = numero;
    }
    
    @Override
    public void run() {
        
    }
}
