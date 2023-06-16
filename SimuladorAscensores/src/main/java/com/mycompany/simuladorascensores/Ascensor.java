

package com.mycompany.simuladorascensores;

import static com.mycompany.simuladorascensores.SimuladorAscensores.SemaforoAscensor;
import static com.mycompany.simuladorascensores.SimuladorAscensores.maxPisos;
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
                                System.out.println("Baja " + primero.nombre + " en el piso " + this.pisoActual +
                                        " del Ascensor " + this.numero);
                                Carga.remove(primero);
                            } else {
                                System.out.println("Sube " + primero.nombre + " en el piso " + this.pisoActual +
                                        " del Ascensor " + this.numero);
                                Carga.peek().enAscensor = true;
                                if (cantPersonas > 1) {
                                    Persona p = Carga.get(0);
                                    Persona p2 = Carga.get(1);
                                    boolean aca = false;
                                    if (mirando_arriba) {
                                        boolean mayorPA = true;
                                        while (p2 != null && !aca && mayorPA) {
                                            if (p2.enAscensor) {
                                                if (p2.destino >= pisoActual){
                                                    if (p.destino > p2.destino) {
                                                        if (Carga.indexOf(Carga.indexOf(p2) + 1) == -1) {
                                                            p2 = null;
                                                        } else {
                                                            p2 = Carga.get(Carga.indexOf(p2) + 1);
                                                        }
                                                    } else {
                                                        aca = true;
                                                    }
                                                } else {
                                                    mayorPA = false;
                                                }
                                            } else {
                                                if (p2.inicio >= pisoActual){
                                                    if (p.destino > p2.inicio) {
                                                        if (Carga.indexOf(Carga.indexOf(p2) + 1) == -1) {
                                                            p2 = null;
                                                        } else {
                                                            p2 = Carga.get(Carga.indexOf(p2) + 1);
                                                        }
                                                    } else {
                                                        aca = true;
                                                    }
                                                } else {
                                                    mayorPA = false;
                                                }
                                            }
                                        }
                                        while (p2 != null && !aca && !mayorPA) {
                                            if (p2.enAscensor) {
                                                if (p.destino > p2.destino) {
                                                    if (Carga.indexOf(Carga.indexOf(p2) + 1) == -1) {
                                                        p2 = null;
                                                    } else {
                                                        p2 = Carga.get(Carga.indexOf(p2) + 1);
                                                    }
                                                } else {
                                                    aca = true;
                                                }
                                            } else {
                                                if (p.destino > p2.inicio) {
                                                    if (Carga.indexOf(Carga.indexOf(p2) + 1) == -1) {
                                                        p2 = null;
                                                    } else {
                                                        p2 = Carga.get(Carga.indexOf(p2) + 1);
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
                                                if (p2.destino <= pisoActual){
                                                    if (p.destino < p2.destino) {
                                                        if (Carga.indexOf(Carga.indexOf(p2) + 1) == -1) {
                                                            p2 = null;
                                                        } else {
                                                            p2 = Carga.get(Carga.indexOf(p2) + 1);
                                                        }
                                                    } else {
                                                        aca = true;
                                                    }
                                                } else {
                                                    menorPA = false;
                                                }
                                            } else {
                                                if (p2.inicio <= pisoActual){
                                                    if (p.destino < p2.inicio) {
                                                        if (Carga.indexOf(Carga.indexOf(p2) + 1) == -1) {
                                                            p2 = null;
                                                        } else {
                                                            p2 = Carga.get(Carga.indexOf(p2) + 1);
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
                                                if (p.destino < p2.destino) {
                                                    if (Carga.indexOf(Carga.indexOf(p2) + 1) == -1) {
                                                        p2 = null;
                                                    } else {
                                                        p2 = Carga.get(Carga.indexOf(p2) + 1);
                                                    }
                                                } else {
                                                    aca = true;
                                                }
                                            } else {
                                                if (p.destino < p2.inicio) {
                                                    if (Carga.indexOf(Carga.indexOf(p2) + 1) == -1) {
                                                        p2 = null;
                                                    } else {
                                                        p2 = Carga.get(Carga.indexOf(p2) + 1);
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
                    Thread.sleep(10);
                } else {
                    //proceso de inactividad
                    if(pisoActual != 0 && pisoActual != maxPisos){
                        if (((maxPisos - pisoActual) < (maxPisos/2))){
                            pisoActual += 1;
                            System.out.println("Ascensor " + this.numero + " sube un piso por proceso inactivo al piso "
                                                + this.pisoActual);
                        } else {
                            pisoActual -= 1;
                            System.out.println("Ascensor " + this.numero + " baja un piso por proceso inactivo al piso "
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
