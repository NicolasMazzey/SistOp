
package com.mycompany.simuladorascensores;

import java.util.Comparator;


public class ComparadorAscensores implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        Ascensor a1 = (Ascensor) o1;
        Ascensor a2 = (Ascensor) o2;
        
        if (a1.cantPersonas == a2.cantPersonas) {
            if (a1.peso == a2.peso) {
                return 0;
            } else if (a1.peso > a2.peso) {
                return 1;
            } else {
                return -1;
            }
        } else if (a1.cantPersonas > a2.cantPersonas) {
            return 1;
        } else {
            return -1;
        }
    }
}
