/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simuladorascensores;

import java.util.Comparator;

/**
 *
 * @author Nicolas
 */
public class ComparadorPersonas implements Comparator<Persona> {
    public boolean invertido;

    public ComparadorPersonas(){
        invertido = false;
    }
    
    @Override
    public int compare(Persona p1, Persona p2) { //Quedo imundo pero deberia andar
        if (!invertido) {
            if (p1.enAscensor) {
                if (p2.enAscensor) {
                    if (p1.destino < p2.destino) {
                        return -1;
                    }
                    if (p1.destino > p2.destino) {
                        return 1;
                    }
                    return 0;
                } else {
                    if (p1.destino < p2.inicio) {
                        return -1;
                    }
                    if (p1.destino > p2.inicio) {
                        return 1;
                    }
                    return 0;
                }
            } else {
                if (p2.enAscensor) {
                    if (p1.inicio < p2.destino) {
                        return -1;
                    }
                    if (p1.inicio > p2.destino) {
                        return 1;
                    }
                    return 0;
                } else {
                    if (p1.inicio < p2.inicio) {
                        return -1;
                    }
                    if (p1.inicio > p2.inicio) {
                        return 1;
                    }
                    return 0;
                }
            }
        } else {
            if (p1.enAscensor) {
                if (p2.enAscensor) {
                    if (p1.destino < p2.destino) {
                        return 1;
                    }
                    if (p1.destino > p2.destino) {
                        return -1;
                    }
                    return 0;
                } else {
                    if (p1.destino < p2.inicio) {
                        return 1;
                    }
                    if (p1.destino > p2.inicio) {
                        return -1;
                    }
                    return 0;
                }
            } else {
                if (p2.enAscensor) {
                    if (p1.inicio < p2.destino) {
                        return 1;
                    }
                    if (p1.inicio > p2.destino) {
                        return -1;
                    }
                    return 0;
                } else {
                    if (p1.inicio < p2.inicio) {
                        return 1;
                    }
                    if (p1.inicio > p2.inicio) {
                        return -1;
                    }
                    return 0;
                }
            }
        }
    }
            
    
    @Override
     public ComparadorPersonas reversed(){
         this.invertido = !this.invertido;
         return this;
     }
}
