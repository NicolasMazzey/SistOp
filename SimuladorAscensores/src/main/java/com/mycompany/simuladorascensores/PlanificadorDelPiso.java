package com.mycompany.simuladorascensores;

import java.util.Iterator;
import java.util.LinkedList;

public class PlanificadorDelPiso extends Thread {

    LinkedList<Persona> cola_espera;
    int piso_planificando;
    String numero;

    PlanificadorDelPiso(String numero, int piso) {
        this.cola_espera = new LinkedList<>();
        this.piso_planificando = piso;
        this.numero = numero;
    }

    @Override
    public void run() {
        while (true) {
            if (!cola_espera.isEmpty()) {
                
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
                    }
                }
                //signal

            }

        }
    }
}
