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
                    //System.out.println("Planificador " + SimuladorAscensores.Momento + " " + SimuladorAscensores.cola_espera.peek().momento );
                    boolean salir = false;
                    Persona p = SimuladorAscensores.cola_espera.poll();
                    Ascensor AElejido = SimuladorAscensores.listaDeAscensores.peek();
                    Ascensor AElejido2;
                    
                    
                    Iterator<Ascensor> iterator2 = SimuladorAscensores.listaDeAscensores.iterator();
                    

                    if ((AElejido.cantPersonas == 5) || (AElejido.peso > (500 - p.peso))) {

                        Iterator<Ascensor> iterator = SimuladorAscensores.listaDeAscensores.iterator();
                        boolean encontro = false;

                        while (iterator.hasNext() && !encontro) {
                            AElejido = iterator.next();
                            iterator2.next();

                            if ((AElejido.cantPersonas == 5) || (AElejido.peso > (500 - p.peso))) {
                                
                                /*while(iterator.hasNext()) {
                                    if (AElejido.pisoActual != p.inicio) {
                                        System.out.println(AElejido.mirando_arriba);
                                        System.out.println(p.inicio > AElejido.pisoActual);
                                        if (AElejido.mirando_arriba != (p.inicio > AElejido.pisoActual)) {
                                            if ((AElejido2.cantPersonas == AElejido.cantPersonas) || (AElejido2.peso > (500 - p.peso))) {
                                                System.out.println(AElejido2.mirando_arriba);
                                                System.out.println(p.inicio > AElejido2.pisoActual);
                                                if (AElejido2.mirando_arriba == (p.inicio > AElejido2.pisoActual)) {
                                                    AElejido = AElejido2;
                                                }
                                            }
                                        }
                                    }
                                    encontro = true;
                                }*/
                            } else {
                                encontro = true;
                            }
                        }

                        if (!encontro) {
                            System.out.println("salio");
                            salir = true;
                        }

                    }
                    
                    boolean encontro2 = false;
                    
                    while(iterator2.hasNext() && !encontro2) {
                        //System.out.println("LLegue");
                        AElejido2 = iterator2.next();
                        if((AElejido2.cantPersonas > AElejido.cantPersonas)){
                            //System.out.println("Salio por carga");
                            //System.out.println("A1 personas " + AElejido.cantPersonas + " numero " + AElejido.numero);
                            //System.out.println("A2 personas " + AElejido2.cantPersonas + " numero " + AElejido2.numero);
                            encontro2 = true;
                        } else {
                            if (AElejido.pisoActual != p.inicio) {
                                if(AElejido2.pisoActual == p.inicio){
                                    //System.out.println("Salio por piso");
                                    AElejido = AElejido2;
                                    encontro2 = true;
                                } else {
                                    if (AElejido.mirando_arriba != (p.inicio > AElejido.pisoActual)) {
                                        if ((AElejido2.cantPersonas == AElejido.cantPersonas) || (AElejido2.peso > (500 - p.peso))) {
                                            if (AElejido2.mirando_arriba == (p.inicio > AElejido2.pisoActual)) {
                                                AElejido = AElejido2;
                                                //System.out.println("Cambio");
                                            }
                                        }
                                    } 
                                }
                                
                                
                            }
                        }
                    }
                    

                    if (!salir) {  //es como un break para no complicarme la vida
                        
                        AElejido.Carga.addFirst(p);
                        AElejido.peso += p.peso;
                        AElejido.cantPersonas += 1;
                        
                        //System.out.println(AElejido.numero);
                        //System.out.println(AElejido.cantPersonas);
                        
                        if (AElejido.cantPersonas > 1) {
                            Iterator<Persona> iterador = AElejido.Carga.iterator();
                            iterador.next();
                            Persona p2 = iterador.next();
                            boolean aca = false;
                            if (AElejido.mirando_arriba) {       //esto es lo mismo que en el ascensor pero la persona p se esta
                                boolean mayorPA = true;          //cargando en vez de bajandose.
                                while (p2 != null && !aca && mayorPA) {   
                                    if (p2.enAscensor) {
                                        if (p2.destino >= AElejido.pisoActual && p.inicio >=AElejido.pisoActual) {
                                            if (p.inicio > p2.destino) {
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
                                        if (p2.inicio >= AElejido.pisoActual && p.inicio >=AElejido.pisoActual) {
                                            if (p.inicio > p2.inicio) {
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
                                while (p2 != null && !aca && !mayorPA) {
                                    if (p2.enAscensor) {
                                        if (p.inicio < p2.destino) {
                                            if (!iterador.hasNext()) {
                                                p2 = null;
                                            } else {
                                                p2 = iterador.next();
                                            }
                                        } else {
                                            aca = true;
                                        }
                                    } else {
                                        if (p.inicio < p2.inicio) {
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
                                        if (p2.destino <= AElejido.pisoActual && p.inicio <=AElejido.pisoActual) {
                                            if (p.inicio < p2.destino) {
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
                                        if (p2.inicio <= AElejido.pisoActual && p.inicio <=AElejido.pisoActual) {
                                            if (p.inicio < p2.inicio) {
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
                                        if (p.inicio > p2.destino) {
                                            if (!iterador.hasNext()) {
                                                p2 = null;
                                            } else {
                                                p2 = iterador.next();
                                            }
                                        } else {
                                            aca = true;
                                        }
                                    } else {
                                        if (p.inicio > p2.inicio) {
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
