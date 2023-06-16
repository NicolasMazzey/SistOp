package com.mycompany.simuladorascensores;

import static com.mycompany.simuladorascensores.SimuladorAscensores.SemaforoAscensor;
import java.util.Iterator;

public class PlanificadorDelPiso extends Thread {

    
    String numero;

    PlanificadorDelPiso(String numero){ //, int piso) {
        
        this.numero = numero;
    }

    @Override
    public void run() {
        while (true) {
            if (!SimuladorAscensores.cola_espera.isEmpty() && SimuladorAscensores.cola_espera.peek().momento <= SimuladorAscensores.Momento) {
                try {
                    SemaforoAscensor.acquire();
                    boolean salir = false;
                    Persona p = SimuladorAscensores.cola_espera.poll();
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

                        if (!encontro) {
                            salir = true;
                        }

                    }

                    if (!salir) {  //es como un break para no complicarme la vida

                        AElejido.Carga.addFirst(p);
                        AElejido.peso += p.peso;
                        AElejido.cantPersonas += 1;

                        if (AElejido.cantPersonas > 1) {
                            Persona p2 = AElejido.Carga.get(1);
                            boolean aca = false;
                            if (AElejido.mirando_arriba) {       //esto es lo mismo que en el ascensor pero la persona p se esta
                                boolean mayorPA = true;          //cargando en vez de bajandose.
                                while (p2 != null && !aca && mayorPA) {   
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
                                                if (AElejido.Carga.indexOf(AElejido.Carga.indexOf(p2)+1) == -1){
                                                    p2 = null;
                                                } else {
                                                    p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
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
                                        if (p.inicio > p2.destino) {
                                            if (AElejido.Carga.indexOf(AElejido.Carga.indexOf(p2) + 1) == -1) {
                                                p2 = null;
                                            } else {
                                                p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
                                            }
                                        } else {
                                            aca = true;
                                        }
                                    } else {
                                        if (p.inicio > p2.inicio) {
                                            if (AElejido.Carga.indexOf(AElejido.Carga.indexOf(p2) + 1) == -1) {
                                                p2 = null;
                                            } else {
                                                p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
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
                                        if (p2.destino <= AElejido.pisoActual) {
                                            if (p.inicio < p2.destino) {
                                                if (AElejido.Carga.indexOf(AElejido.Carga.indexOf(p2) + 1) == -1) {
                                                    p2 = null;
                                                } else {
                                                    p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
                                                }
                                            } else {
                                                aca = true;
                                            }
                                        } else {
                                            menorPA = false;
                                        }
                                    } else {
                                        if (p2.inicio <= AElejido.pisoActual) {
                                            if (p.inicio < p2.inicio) {
                                                if (AElejido.Carga.indexOf(AElejido.Carga.indexOf(p2) + 1) == -1) {
                                                    p2 = null;
                                                } else {
                                                    p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
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
                                        if (p.inicio < p2.destino) {
                                            if (AElejido.Carga.indexOf(AElejido.Carga.indexOf(p2) + 1) == -1) {
                                                p2 = null;
                                            } else {
                                                p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
                                            }
                                        } else {
                                            aca = true;
                                        }
                                    } else {
                                        if (p.inicio < p2.inicio) {
                                            if (AElejido.Carga.indexOf(AElejido.Carga.indexOf(p2) + 1) == -1) {
                                                p2 = null;
                                            } else {
                                                p2 = AElejido.Carga.get(AElejido.Carga.indexOf(p2) + 1);
                                            }
                                        } else {
                                            aca = true;
                                        }
                                    }
                                }
                            }
                            AElejido.Carga.remove(p);
                            if (p2 != null){
                                AElejido.Carga.add(AElejido.Carga.indexOf(p2), p);
                            } else {
                                AElejido.Carga.addLast(p);
                            }
                        }
                        System.out.println("Planificador " + this.numero + " asgino cargar a " + p.nombre
                                + " al Ascensor " + AElejido.numero + " en el piso " + p.inicio);
                    }
                    SimuladorAscensores.listaDeAscensores.remove(AElejido);
                    SimuladorAscensores.listaDeAscensores.add(AElejido);
                    SemaforoAscensor.release();
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
               
                }

            }

        }
    }
}
