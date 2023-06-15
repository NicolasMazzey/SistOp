package com.mycompany.simuladorascensores;

public class Persona {

    int destino;
    int inicio;
    String nombre;
    boolean enAscensor;
    int peso;
    int momento;

    Persona(String Nombre, int Destino, int Inicio, int peso, int Momento) {
        this.nombre = Nombre;
        this.destino = Destino;
        this.inicio = Inicio;
        this.enAscensor = false;
        this.momento = Momento;
    }

    // Métodos get
    public int getDestino() {
        return destino;
    }

    public int getInicio() {
        return inicio;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isEnAscensor() {
        return enAscensor;
    }

    public int getPeso() {
        return peso;
    }

    public int getMomento() {
        return momento;
    }

}
