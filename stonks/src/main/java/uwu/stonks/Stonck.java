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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import uwu.stonks.Predicatez.Predz;

public class Stonck {
    //The csv file name
    final String fileName;
    
    //This will be number of lines in the file excluding first line
    final int tradeDays;
    
    //Average number of trading days
    static final int DAYSYEAR = 253;
    static final int DAYSMONTH = 21;
    static final int DAYSWEEK = 5;
    
    EntryList Entrys;
    ArrayList<Entry> entrys;
    
    public Stonck(String fileName) {
        this.fileName = fileName;
        this.Entrys = new EntryList(fileName);
        this.entrys = Entry.getEntryList(fileName);
        this.tradeDays = this.entrys.size();
        initAll();
    }
    
    public ArrayList<Entry> getEntrys() {
        return this.entrys;
    }
    
    /**
     *Used to test features
     */
    public void test() {
        //Prints all predicatez
        printPredz();
        //Predz and bools are parameters to create an entry list
        ArrayList<Predz> predz = new ArrayList<>(Arrays.asList(Predz.PDC));
        ArrayList<Boolean> bools = new ArrayList<>(Arrays.asList(true));
        ArrayList<Entry> elist = Entry.getEntrysByPredz(this.entrys, predz, bools);
        printPredzFromEntrys(elist);
        //Count predicatez by boolean
        PredicatezCount count = Predicatez.countPredicatez(elist, true);
        count.printCount(); 
        
        
        //printPredz(plist);
        
    }
    
    //Print methods-------------------------------------------------------------
    public void printPredz() {
        System.out.println("Printing predicatez");
        for(Entry e : this.getEntrys()) {
            e.getEntryPred().printPredicate();
        }
    }

    public void printPredz(ArrayList<Predicatez> list) {
        System.out.println("Printing predicatez");
        for(Predicatez p : list) {
            p.printPredicate();
        }
        
    }
    
    public void printPredzFromEntrys(ArrayList<Entry> list) {
        System.out.println("Printing predicatez");
        Predicatez p;
        for(Entry e : list) {
            p = e.getEntryPred();
            p.printPredicate();
        }
    }
    //--------------------------------------------------------------------------
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
}
