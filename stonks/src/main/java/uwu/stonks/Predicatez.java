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
    
    //Set predicatez for each entry and return arraylist of pure predicatez
    public static ArrayList<Predicatez> setEntryPredicatez(ArrayList<Entry> list) {
        ArrayList<Predicatez> ret = new ArrayList<>();
        Entry same, prev;    
        for(int i = 1; i < (list.size() - 1); i++) {
            same = list.get(i);
            prev = list.get(i - 1);
            same.getEntryPred().setSameDayGain(same);
            same.getEntryPred().setPrevDayClose(same, prev);
            same.getEntryPred().setPrevDayOpen(same, prev);
            same.getEntryPred().setPrevDayVolume(same, prev);
            same.getEntryPred().setPrevDayHigh(same, prev);
            same.getEntryPred().setPrevDayLow(same, prev);
            ret.add(same.getEntryPred());
        }
        return ret;
    }
    
    //Create a list of predicatez with a certain Pred that has boolean value b
    public static ArrayList<Predicatez> createListByPred(ArrayList<Predicatez> list, 
            Predz type, boolean b) {
        
        ArrayList<Predicatez> ret = new ArrayList<>();
        for(Predicatez p : list) {
            switch(type) {
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
        }
        return ret;
    }
    
}
