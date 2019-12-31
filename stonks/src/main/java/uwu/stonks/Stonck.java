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
import java.util.Arrays;
import uwu.stonks.Predicatez.Predz;

public class Stonck {
    
    final String fileName;
    final int tradeDays = 253;
    ArrayList<Entry> entrys;
    
    public Stonck(String fileName) {
        this.fileName = fileName;
        this.entrys = Entry.getEntryList(fileName);
        initAll();
    }
    
    public ArrayList<Entry> getEntrys() {
        return this.entrys;
    }
    
    /*
    Used to test features
    */
    public void test() {
        printPredz();
        ArrayList<Predz> predz = new ArrayList<>(Arrays.asList(Predz.SDG, Predz.PDH));
        ArrayList<Boolean> bools = new ArrayList<>(Arrays.asList(true, true));
        printPredz(getPredicatezByValue(predz, bools));
    }
    
    private void printPredz() {
        System.out.println("Printing predicatez");
        for(Entry e : this.getEntrys()) {
            e.getEntryPred().printPredicate();
        }
    }
    
    private void printPredz(ArrayList<Predicatez> list) {
        System.out.println("Printing predicatez");
        for(Predicatez p : list) {
            p.printPredicate();
        }
    }
    
    /*
    Initialize values of predicatez/percentz for all entries in stonck
    */
    private void initAll() {
        //Current and previous entry
        Entry curr = this.entrys.get(0);
        Entry prev;  
        //First entry doesn't have previous
        curr.getEntryPred().setSameDayGain(curr);
        curr.getEntryPerc().setSameDayGain(curr);
        //Set values for rest of entrys
        for(int i = 1; i < this.entrys.size() - 1; i ++) {
            curr = this.entrys.get(i);
            prev = this.entrys.get(i - 1);
            //Set the predicatez
            curr.getEntryPred().setSameDayGain(curr);
            curr.getEntryPred().setPrevDayClose(curr, prev);
            curr.getEntryPred().setPrevDayOpen(curr, prev);
            curr.getEntryPred().setPrevDayVolume(curr, prev);
            curr.getEntryPred().setPrevDayHigh(curr, prev);
            curr.getEntryPred().setPrevDayLow(curr, prev);         
            //Set the percentz
            curr.getEntryPerc().setSameDayGain(curr);
            curr.getEntryPerc().setPrevDayClose(curr, prev);
            curr.getEntryPerc().setPrevDayOpen(curr, prev);
            curr.getEntryPerc().setPrevDayVolume(curr, prev);
            curr.getEntryPerc().setPrevDayHigh(curr, prev);
            curr.getEntryPerc().setPrevDayLow(curr, prev);      
        }
    }
    
    /*
    Return a list of predicatez containing the specified predicatez/boolean pairs
    Paired by same index 
    predz and bools must have same number of objects
    */
    private ArrayList<Predicatez> getPredicatezByValue(ArrayList<Predz> predz,
            ArrayList<Boolean> bools) {
        
        ArrayList<Predicatez> ret = new ArrayList<>();
        Predicatez p;
        for(Entry e : this.entrys) {
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
                            //check = false;
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
