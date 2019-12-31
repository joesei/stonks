/*
 * The MIT License
 *
 * Copyright 2019 Jose Manuel Hernandez.
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

public class Predicatez {
    
    //true == higher 
    //false == lower 
    boolean sameDayGain;
    boolean prevDayClose;
    boolean prevDayOpen;
    boolean prevDayVolume;
    boolean prevDayHigh;
    boolean prevDayLow;
    
    public enum Predz {
        SDG, PDC, PDO, PDV, PDH, PDL;
    }
    
    public Predicatez() {
    }
    
    //Setters-------------------------------------------------------------------
    public void setSameDayGain(Entry same) {
        this.sameDayGain = (same.getClose() > same.getOpen());
    }
    
    public void setPrevDayClose(Entry same, Entry prev) {
        this.prevDayClose = (same.getClose() > prev.getClose());
    }
    
    public void setPrevDayOpen(Entry same, Entry prev) {
        this.prevDayOpen = (same.getOpen() > prev.getOpen());
    }
    
    public void setPrevDayVolume(Entry same, Entry prev) {
        this.prevDayVolume = (same.getVolume() > prev.getVolume());
    }
    
    public void setPrevDayHigh(Entry same, Entry prev) {
        this.prevDayHigh = (same.getHigh() > prev.getHigh());
    }
    
    public void setPrevDayLow(Entry same, Entry prev) {
        this.prevDayLow = (same.getLow() > prev.getLow());
    }
    
    //Getters-------------------------------------------------------------------
    public boolean getSameDayGain() {
        return this.sameDayGain;
    }
    
    public boolean getPrevDayClose() {
        return this.prevDayClose;
    }
    
    public boolean getPrevDayOpen() {
        return this.prevDayOpen;
    }
    
    public boolean getPrevDayVolume() {
        return this.prevDayVolume;
    }
    
    public boolean getPrevDayHigh() {
        return this.prevDayHigh;
    }
    
    public boolean getPrevDayLow() {
        return this.prevDayLow;
    }
    //--------------------------------------------------------------------------
    public void printPredicate() {
        String s = String.format("SDG: %s, PDC: %s, PDO: %s, PDV: %s, PDH: %s,"
                + " PDL: %s", this.sameDayGain, this.prevDayClose, 
                this.prevDayOpen, this.prevDayVolume, this.prevDayHigh, 
                this.prevDayLow);
        
        System.out.println(s);
    }
    
    /*
    Counts the number of boolean/parameter values in each entry predicatez
    */
    public static PredicatezCount countPredicatez(ArrayList<Entry> list, boolean b) {
        Predicatez p;
        PredicatezCount count = new PredicatezCount(b);
        for(Entry e : list) {
            p = e.getEntryPred();
            if(p.getSameDayGain() == b) {
                count.SDG++;
            }
            if(p.getPrevDayClose() == b) {
                count.PDC++;
            }
            if(p.getPrevDayOpen() == b) {
                count.PDO++;
            }
            if(p.getPrevDayVolume() == b) {
                count.PDV++;
            }
            if(p.getPrevDayHigh() == b) {
                count.PDH++;
            }
            if(p.getPrevDayLow() == b) {
                count.PDL++;
            }
        }
        return count;
    }
    
    /*
    Return a list of predicatez containing the specified predicatez/boolean pairs
    Paired by same index 
    predz and bools must have same number of objects
    */
    public static ArrayList<Predicatez> getPredicatezByValue(ArrayList<Entry> list, 
            ArrayList<Predz> predz, ArrayList<Boolean> bools) {
        
        ArrayList<Predicatez> ret = new ArrayList<>();
        Predicatez p;
        for(Entry e : list) {
            p = e.getEntryPred();
            //Check whether all predz/boolean pairs are present
            boolean b;
            if(predz.size() == 1) {
                b = bools.get(0);
                switch(predz.get(0)) {
                        case SDG:
                            if(p.getSameDayGain() == b) {
                                ret.add(p);
                            }
                            break;
                        case PDC:
                            if(p.getPrevDayClose() == b) {
                                ret.add(p);
                            }
                            break;
                        case PDO:
                            if(p.getPrevDayOpen() == b) {
                                ret.add(p);
                            }
                            break;
                        case PDV:
                            if(p.getPrevDayVolume() == b) {
                                ret.add(p);
                            }
                            break;
                        case PDH:
                            if(p.getPrevDayHigh() == b) {
                                ret.add(p);
                            }
                            break;
                        case PDL:
                            if(p.getPrevDayLow() == b) {
                                ret.add(p);
                            }
                            break;
                        default:
                            break;
                    }
            } else {
                int count = 0;
                for(int i = 0; i < predz.size(); i++) {
                    //Will break and not add predicate to return list if check failed
                    if(i != count) {
                        break;
                    }
                    b = bools.get(i);
                    switch(predz.get(i)) {
                        case SDG:
                            if(p.getSameDayGain() == b) {
                                count++;
                            }
                            break;
                        case PDC:
                            if(p.getPrevDayClose() == b) {
                                count++;
                            }
                            break;
                        case PDO:
                            if(p.getPrevDayOpen() == b) {
                                count++;
                            }
                            break;
                        case PDV:
                            if(p.getPrevDayVolume() == b) {
                                count++;
                            }
                            break;
                        case PDH:
                            if(p.getPrevDayHigh() == b) {
                                count++;
                            }
                            break;
                        case PDL:
                            if(p.getPrevDayLow() == b) {
                                count++;
                            }
                            break;
                        default:
                            break;
                    }    
                }
                //Appends predicate to return list if succesfully looped through
                //all predz
                if(count == predz.size()) {
                    ret.add(p);
                }
            }
        }
        return ret;
    }
}
