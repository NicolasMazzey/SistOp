

package com.mycompany.simuladorascensores;


public class Persona {
    int destino;
    int inicio;
    String nombre;
    boolean enAscensor;
    int peso;
    
    Persona(String Nombre, int Destino, int Inicio, int peso) {
            this.nombre = Nombre;
            this.destino = Destino;
            this.inicio = Inicio;
            this.enAscensor = false;
    }
}
