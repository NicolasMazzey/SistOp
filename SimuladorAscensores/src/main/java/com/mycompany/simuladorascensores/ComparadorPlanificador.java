
package com.mycompany.simuladorascensores;

import java.util.Comparator;


public class ComparadorPlanificador implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Persona p1 = (Persona) o1;
        Persona p2 = (Persona) o2;
        
        if (p1.momento == p2.momento) {
            return 0;
        } else if (p1.momento > p2.momento) {
            return 1;
        } else {
            return -1;
        }
        
    }
    
}
