

package com.mycompany.simuladorascensores;

import java.util.*;

public class SimuladorAscensores {
    static volatile int maxPisos;
    
    public static void main(String[] args) {
        
        // Primer archivo
        System.out.println("---------------");
        System.out.println("Primer archivo");
        System.out.println("---------------");
        
        String[] palabras = ManejadorDeTexto.leerArchivo("/Users/jose/Documents/GitHub/SistOp/SimuladorAscensores/src/main/java/com/mycompany/simuladorascensores/instrucciones.txt");
        String Cant_ascensores=palabras[0];
        String Cant_pisos=palabras[1];
        String espacio=palabras[2];
        String instrucciones=palabras[3];
        for (String s : palabras) {
            if(s==Cant_ascensores){
            System.out.println("Prueba1: "+ s);
        }
            else if(s==Cant_pisos){
           System.out.println("Prueba2: "+ s);
        }
            else if(s==espacio){
           System.out.println("");
            }
            else if(s==instrucciones){
           System.out.println("Instrucciones: "+ s);
            }
            else{
                System.out.println(s);
            }
        }
        
        
    }
}
