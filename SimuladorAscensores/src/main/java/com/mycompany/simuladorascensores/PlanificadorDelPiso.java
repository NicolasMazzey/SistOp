package com.mycompany.simuladorascensores;

import java.util.Iterator;
import java.util.PriorityQueue;

public class PlanificadorDelPiso extends Thread {

    PriorityQueue<Persona> cola_espera;
    int piso_planificando;
    String numero;

    PlanificadorDelPiso(String numero, int piso) {
        ComparadorPlanificador CP = new ComparadorPlanificador();
        this.cola_espera = new PriorityQueue<>(CP);
        this.piso_planificando = piso;
        this.numero = numero;
    }

    @Override
    public void run() {
        while (true) {
            if (!cola_espera.isEmpty() && cola_espera.peek().momento <= SimuladorAscensores.Momento) {               
                //Wait                                                                                  
                boolean salir = false;
                Persona p = cola_espera.poll();
                Ascensor AElejido = SimuladorAscensores.listaDeAscensores.peek();

                if ((AElejido.cantPersonas == 5) || (AElejido.peso > (500 - p.peso))) {
                    
                    Iterator<Ascensor> iterator = SimuladorAscensores.listaDeAscensores.iterator();
                    boolean encontro = false;

                    while (iterator.hasNext() && !encontro) {
                        AElejido = iterator.next();
                        
                        if ((AElejido.cantPersonas == 5) || (AElejido.peso > (500 - p.peso))) {
                            encontro = true;
                        }
                    }
                    
                    if(!encontro){
                       salir = true;
                    }

                }
                
                if (!salir) {  //es como un break para no complicarme la vida

                    AElejido.Carga.addFirst(p);
                    AElejido.peso += p.peso;
                    AElejido.cantPersonas += 1;

                    if (AElejido.Carga.size() > 1) {
                        Persona p2 = AElejido.Carga.get(2);    
                        boolean aca = false;
                        if (AElejido.mirando_arriba) {       //esto es lo mismo que en el ascensor pero la persona p se esta
                            boolean mayorPA = true;          //cargando en vez de bajandose.
                            while (AElejido.Carga.get(AElejido.Carga.indexOf(p2)) != null && !aca && mayorPA) {
                                if (p2.enAscensor) {
                                    if (p2.destino >= AElejido.pisoActual) {
                                        if (p.inicio > p2.destino) {
                                            p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
                                        } else {
                                            aca = true;
                                        }
                                    } else {
                                        mayorPA = false;
                                    }
                                } else {
                                    if (p2.inicio >= AElejido.pisoActual) {
                                        if (p.inicio > p2.inicio) {
                                            p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
                                        } else {
                                            aca = true;
                                        }
                                    } else {
                                        mayorPA = false;
                                    }
                                }
                            }
                            while (AElejido.Carga.get(AElejido.Carga.indexOf(p2)) != null && !aca && !mayorPA) {
                                if (p2.enAscensor) {
                                    if (p.inicio > p2.destino) {
                                        p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
                                    } else {
                                        aca = true;
                                    }
                                } else {
                                    if (p.inicio > p2.inicio) {
                                        p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
                                    } else {
                                        aca = true;
                                    }
                                }
                            }
                        } else {
                            boolean menorPA = true;
                            while (AElejido.Carga.get(AElejido.Carga.indexOf(p2)) != null && !aca && menorPA) {
                                if (p2.enAscensor) {
                                    if (p2.destino <= AElejido.pisoActual) {
                                        if (p.inicio < p2.destino) {
                                            p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
                                        } else {
                                            aca = true;
                                        }
                                    } else {
                                        menorPA = false;
                                    }
                                } else {
                                    if (p2.inicio <= AElejido.pisoActual) {
                                        if (p.inicio < p2.inicio) {
                                            p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
                                        } else {
                                            aca = true;
                                        }
                                    } else {
                                        menorPA = false;
                                    }
                                }
                            }
                            while (AElejido.Carga.get(AElejido.Carga.indexOf(p2)) != null && !aca && !menorPA) {
                                if (p2.enAscensor) {
                                    if (p.inicio < p2.destino) {
                                        p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
                                    } else {
                                        aca = true;
                                    }
                                } else {
                                    if (p.inicio < p2.inicio) {
                                        p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
                                    } else {
                                        aca = true;
                                    }
                                }
                            }
                        }
                        AElejido.Carga.remove(p);
                        AElejido.Carga.add(AElejido.Carga.indexOf(p2), p);
                    }
                    System.out.println("Planificador " + this.numero + " asgino cargar a " + p.nombre + 
                                       " al Ascensor " + AElejido.numero + " en el piso " + p.inicio);
                }
                //signal

            }

        }
    }
}
