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
 * Like a python dictionary of key Boolean[] and value int
 * @author Jose Manuel Hernandez
 */
public class PredicatezTable {
    ArrayList<boolean[]> predicatez;
    ArrayList<Integer> ints;
    
    
    ArrayList<Integer> total;
    
    //Used with initByEntryList when searching for SDG True/False by Predicatez
    ArrayList<Integer> True;
    ArrayList<Integer> False;
    
    int totalInt = 0;
    
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
                    this.totalInt++;
                } else {
                    int check = checkArray(b);
                    if(check == -1) {
                        this.predicatez.add(b);
                        this.ints.add(1);
                        this.totalInt++;
                    } else {
                        this.ints.add(check, this.ints.get(check) + 1);
                        this.ints.remove(check + 1);
                        this.totalInt++;
                    }
                }
            }
        }
    }
    
    /**
     * Preferred method for next day chances
     * @param list 
     */
    public void initByEntryList(EntryList list) {
        this.predicatez = new ArrayList();
        this.ints = new ArrayList();
        this.True = new ArrayList();
        this.False = new ArrayList();

        for(int i = 0; i < list.size() - 1; i++) {
            Entry e1 = list.get(i);
            Entry e2 = list.get(i + 1);
            boolean[] b1 = e1.getPredicatez().predicatezToArray();
            boolean[] b2 = e2.getPredicatez().predicatezToArray();
            if(this.predicatez.isEmpty()) {
                this.predicatez.add(b1);
                this.ints.add(1);
                this.totalInt++;
                if(b2[0]) {
                    this.True.add(1);
                    this.False.add(0);
                } else {
                    this.True.add(0);
                    this.False.add(1);
                }
            } else {
                int index = checkArray(b1);
                //If not in the list
                if(index == -1) {
                    this.predicatez.add(b1);
                    this.ints.add(1);
                    this.totalInt++;  
                    if(b2[0]) {
                        this.True.add(1);
                        this.False.add(0);
                    } else {
                        this.True.add(0);
                        this.False.add(1);
                    }
                //If it is in the list
                } else {
                    this.ints.add(index, this.ints.get(index) + 1);
                    this.ints.remove(index + 1);
                    this.totalInt++;
                    if(b2[0]) {
                        this.True.add(index, this.True.get(index) + 1);
                        this.True.remove(index + 1);
                    } else {
                        this.False.add(index, this.False.get(index) + 1);
                        this.False.remove(index + 1);
                    }
                }    
            }
        }      
    }
    
    public void printChances() {
        System.out.println("Printing chances");
        for(int i = 0; i < this.predicatez.size(); i++) {
            boolean[] b = this.predicatez.get(i);
            float tru = (float) this.True.get(i);
            float tot = (float) this.True.get(i) + (float) this.False.get(i);
            float p = (float) tru / tot;
            String s = String.format("SDG: %s , PDC: %s, PDO: %s, PDV: %s, PDH: %s,"
                + " PDL: %s, Percent: %f, Total: %f", b[0], b[1], b[2], b[3], 
                b[4], b[5], p, tot);
            
            System.out.println(s);
        }     
        System.out.println(this.totalInt);
    }
    
    /**
     * Check whether the same Boolean[] b is in this Predicatez 
     * 
     * @param b
     * @return index if it is in Predicatez and -1 if it isn't
     */
    private int checkArray(boolean[] b) {
        for(boolean[] p : this.predicatez) {
            if(Arrays.equals(p, b)) {       
                return this.predicatez.lastIndexOf(p);
            }
        }
        return -1;
    }
    
    private void initZeroTotal() {
        this.total = new ArrayList();
        for(Integer i : this.ints) {
            this.total.add(0);
        }
    }
    
    public void printTable() {
        for(boolean[] b : this.predicatez) {
            int c =  ints.get(this.predicatez.lastIndexOf(b));
            float p = (float)c / (float)this.totalInt;
            String s = String.format("SDG: %s, PDC: %s, PDO: %s, PDV: %s, PDH: %s,"
                + " PDL: %s, Count: %d, Percent: %f", b[0], b[1], b[2], b[3], 
                b[4], b[5], c, p);
            
            System.out.println(s);
        }       
    }
    
    /**
     * Search EntryList for SDG param that is true after a false SDG
     * Not working
     * @param list 
     */
    public void findOpp(EntryList list) {
        initZeroTotal();
        for(int i = 0; i < list.size() - 1; i++) {
            Predicatez p = list.get(i).getPredicatez();
            boolean[] b = p.predicatezToArray();
            for(boolean[] b2 : this.predicatez) {
                if(Arrays.equals(b, b2)) {
                    System.out.println(true);
                    if(list.get(i + 1).getPredicatez().getSameDayGain()) {
                        System.out.println(true);
                        int index = this.predicatez.lastIndexOf(b2);
                        this.total.add(index, this.total.get(index) + 1);
                        this.total.remove(index + 1);
                    }
                }
            }
        }
    }
}
