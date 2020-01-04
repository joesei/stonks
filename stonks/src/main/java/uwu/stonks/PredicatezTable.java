/*
 * The MIT License
 *
 * Copyright 2020 Jose Manuel Hernandez.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uwu.stonks;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Jose Manuel Hernandez
 */
public class PredicatezTable {
    ArrayList<boolean[]> predicatez;
    ArrayList<Integer> ints;
    
    public PredicatezTable() {
        
    }
    
    public void initByEntryGroups(EntryGroups eg) {
        this.predicatez = new ArrayList();
        this.ints = new ArrayList();
        if(eg.getID() == 1) {
            for(EntryList list : eg.getLists()) {
                Entry e = list.get(0);
                Predicatez p = e.getPredicatez();
                boolean[] b = p.predicatezToArray();
                if(this.predicatez.isEmpty()) {
                    this.predicatez.add(b);
                    this.ints.add(1);
                } else {
                    int check = checkArray(b);
                    if(check == -1) {
                        this.predicatez.add(b);
                        this.ints.add(1);
                    } else {
                        //System.out.println(check);
                        //System.out.println()
                        this.ints.add(check, this.ints.get(check) + 1);
                        this.ints.remove(check + 1);
                    }
                }
            }
        }
    }
    
    private int checkArray(boolean[] b) {
        for(boolean[] p : this.predicatez) {
            if(Arrays.equals(p, b)) {       
                return this.predicatez.lastIndexOf(p);
            }
        }
        return -1;
    }
    
    public void printTable() {
        for(boolean[] b : this.predicatez) {
            int c =  ints.get(this.predicatez.lastIndexOf(b));
            String s = String.format("SDG: %s, PDC: %s, PDO: %s, PDV: %s, PDH: %s,"
                + " PDL: %s, Count: %d", b[0], b[1], b[2], b[3], b[4], b[5], c);
            
            System.out.println(s);
        }       
    }
}
