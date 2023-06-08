

package com.mycompany.simuladorascensores;


public class Ascensor extends Thread {
    Persona[] Carga;
    int peso;
    int cantPersonas;
    int pisoActual;
    boolean mirando_arriba;
    String numero;
    
    Ascensor(String numero){
        this.Carga = new Persona[5];
        this.peso = 0;
        this.cantPersonas = 0;
        this.pisoActual = 1;
        this.mirando_arriba = true;
        this.numero = numero;
    }
    
    @Override
    public void run() {
        
    }
}
