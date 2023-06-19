

package com.mycompany.simuladorascensores;

import static com.mycompany.simuladorascensores.SimuladorAscensores.SemaforoAscensor;
import static com.mycompany.simuladorascensores.SimuladorAscensores.maxPisos;
import java.util.Iterator;
import java.util.LinkedList;


public class Ascensor extends Thread {
    LinkedList<Persona> Carga; 
    int peso;
    int cantPersonas;
    int pisoActual;
    boolean mirando_arriba;
    String numero;
    
    Ascensor(String numero){
        this.Carga = new LinkedList();
        this.peso = 0;
        this.cantPersonas = 0;
        this.pisoActual = 0;
        this.mirando_arriba = true;
        this.numero = numero;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                SemaforoAscensor.acquire();
                if (!Carga.isEmpty()) {
                    //proceso Activo
                    Persona primero = Carga.peek();
                    int PisoSiguiente;
                    if(primero.enAscensor){
                        PisoSiguiente = primero.destino;
                    } else {
                        PisoSiguiente = primero.inicio;
                    }
                    if (PisoSiguiente > pisoActual) {
                        this.mirando_arriba = true;
                        pisoActual += 1;
                        System.out.println("Ascensor " + this.numero + " sube 1 piso al " + this.pisoActual);
                    } else {
                        if (PisoSiguiente == pisoActual) {
                            if (primero.enAscensor){
                                cantPersonas -= 1;
                                peso = peso - primero.peso;
                                SimuladorAscensores.listaDeAscensores.remove(this);
                                SimuladorAscensores.listaDeAscensores.add(this);
                                System.out.println("");
                                System.out.println("Baja " + primero.nombre + " en el piso " + this.pisoActual +
                                        " del Ascensor " + this.numero);
                                System.out.println("");
                                Carga.remove(primero);
                            } else {
                                System.out.println("Sube " + primero.nombre + " en el piso " + this.pisoActual +
                                        " del Ascensor " + this.numero);
                                Carga.peek().enAscensor = true;
                                if (cantPersonas > 1) {
                                    Iterator<Persona> iterador = Carga.iterator();
                                    Persona p = iterador.next();
                                    Persona p2 = iterador.next();
                                    boolean aca = false;
                                    if (mirando_arriba) {                    
                                        boolean mayorPA = true;
                                        while (p2 != null && !aca && mayorPA) {
                                            if (p2.enAscensor) {
                                                if (p2.destino >= pisoActual && p.destino > pisoActual){
                                                    if (p.destino > p2.destino) {
                                                        if (!iterador.hasNext()) {
                                                            p2 = null;
                                                        } else {
                                                            p2 = iterador.next();
                                                        }
                                                   } else {
                                                        aca = true;
                                                    }
                                                } else {
                                                    mayorPA = false;
                                                }
                                            } else {
                                                if (p2.inicio >= pisoActual && p.destino >= pisoActual){
                                                    if (p.destino > p2.inicio) {
                                                        if (!iterador.hasNext()) {
                                                            p2 = null;
                                                        } else {
                                                            p2 = iterador.next();
                                                        }
                                                    } else {
                                                        aca = true;
                                                    }
                                                } else {
                                                    mayorPA = false;
                                                }
                                            }
                                        }
                                        while (p2 != null && !aca && !mayorPA) {    //   6/0/9 5
                                            if (p2.enAscensor) {
                                                if (p.destino < p2.destino) {
                                                    if (!iterador.hasNext()) {
                                                        p2 = null;
                                                    } else {
                                                        p2 = iterador.next();
                                                    }
                                                } else {
                                                    aca = true;
                                                }
                                            } else {
                                                if (p.destino < p2.inicio) {
                                                    if (!iterador.hasNext()) {
                                                        p2 = null;
                                                    } else {
                                                        p2 = iterador.next();
                                                    }
                                                } else {
                                                    aca = true;
                                                }
                                            }
                                        }
                                    } else {
                                        boolean menorPA = true;
                                        while (p2 != null && !aca && menorPA) {
                                            if (p2.enAscensor) {
                                                if (p2.destino <= pisoActual && p.destino <= pisoActual){
                                                    if (p.destino < p2.destino) {
                                                        if (!iterador.hasNext()) {
                                                            p2 = null;
                                                        } else {
                                                            p2 = iterador.next();
                                                        }
                                                    } else {
                                                        aca = true;
                                                    }
                                                } else {
                                                    menorPA = false;
                                                }
                                            } else {
                                                if (p2.inicio <= pisoActual && p.destino <= pisoActual){
                                                    if (p.destino < p2.inicio) {
                                                        if (!iterador.hasNext()) {
                                                            p2 = null;
                                                        } else {
                                                            p2 = iterador.next();
                                                        }
                                                    } else {
                                                        aca = true;
                                                    }
                                                } else {
                                                    menorPA = false;
                                                }
                                            }
                                        }
                                        while (p2 != null && !aca && !menorPA) {
                                            if (p2.enAscensor) {
                                                if (p.destino > p2.destino) {
                                                    if (!iterador.hasNext()) {
                                                        p2 = null;
                                                    } else {
                                                        p2 = iterador.next();
                                                    }
                                                } else {
                                                    aca = true;
                                                }
                                            } else {
                                                if (p.destino >p2.inicio) {
                                                    if (!iterador.hasNext()) {
                                                        p2 = null;
                                                    } else {
                                                        p2 = iterador.next();
                                                    }
                                                } else {
                                                    aca = true;
                                                }
                                            }
                                        }
                                    }
                                    Carga.remove(p);
                                    if (p2 != null) {
                                        Carga.add(Carga.indexOf(p2), p);
                                    } else {
                                        Carga.addLast(p);
                                    }
                                }
                            }
                        } else {
                            this.mirando_arriba = false;
                            pisoActual -= 1;
                            System.out.println("Ascensor " + this.numero + " baja 1 piso al " + this.pisoActual);
                        }
                    }
                    SemaforoAscensor.release();
                    Thread.sleep(1);
                } else {
                    //proceso de inactividad
                    if(pisoActual != 0 && pisoActual != maxPisos){
                        if (((maxPisos - pisoActual) > (maxPisos/2))){
                            pisoActual -= 1;
                            System.out.println("Ascensor " + this.numero + " baja un piso por proceso inactivo al piso "
                                                + this.pisoActual);
                        } else {
                            pisoActual += 1;
                            System.out.println("Ascensor " + this.numero + " sube un piso por proceso inactivo al piso "
                                                + this.pisoActual);
                        }
                    }
                    SemaforoAscensor.release();
                    Thread.sleep(1);
                }
            } catch (InterruptedException ex) {
            
            }
        }
    }
}
