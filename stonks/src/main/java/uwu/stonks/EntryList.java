/*
 * The MIT License
 *
 * Copyright 2019-2020 Jose Manuel Hernandez.
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
import java.util.List;

/**
 * Holds a list of Entry(s)
 * @author Jose Manuel Hernandez
 */
public class EntryList {
    ArrayList<Entry> list = new ArrayList<>();
    PredicatezCount count;
    
    public EntryList() {

    }
    
    public void add(Entry e) {
        this.list.add(e);
    }
    
    public boolean remove(Entry e) {
        return this.list.remove(e);
    }
    
    public Entry get(int index) {
        return this.list.get(index);
    }
    
    public ArrayList<Entry> getList() {
        return this.list;
    }
    
    public int size() {
        return this.list.size();
    }
    
    /**
     * Prints all the Predicatez in EntryList
     */
    public void printPredicatez() {
        if(this.list == null || this.list.isEmpty()) {
            System.out.println("No Entry(s) in EntryList");
        } else {
            System.out.println("Printing Predicatez");
            for(Entry e : this.list) {
                e.getPredicatez().printPredicate();
            }
        }
    }
    
    /**
     * Initialize PredicatezCount values. 
     * @param countType 
     */
    public void initPredicatezCount(boolean countType) {
        this.count = new PredicatezCount(this, countType);
    }
    
    /**
     * Initialize Predicatez and Percentz for each Entry in this list.
     * Used when creating EntryList from a file.
     */
    private void initEntryObjects() {
        //Current and previous entry
        Entry curr = this.list.get(0);
        Entry prev;  
        //First entry doesn't have previous
        curr.getPredicatez().setSameDayGain(curr);
        curr.getPercentz().setSameDayGain(curr);
        //Set values for rest of entrys
        for(int i = 1; i < this.list.size() - 1; i ++) {
            curr = this.list.get(i);
            prev = this.list.get(i - 1);
            //Set the predicatez
            curr.getPredicatez().setSameDayGain(curr);
            curr.getPredicatez().setPrevDayClose(curr, prev);
            curr.getPredicatez().setPrevDayOpen(curr, prev);
            curr.getPredicatez().setPrevDayVolume(curr, prev);
            curr.getPredicatez().setPrevDayHigh(curr, prev);
            curr.getPredicatez().setPrevDayLow(curr, prev);         
            //Set the percentz
            curr.getPercentz().setSameDayGain(curr);
            curr.getPercentz().setPrevDayClose(curr, prev);
            curr.getPercentz().setPrevDayOpen(curr, prev);
            curr.getPercentz().setPrevDayVolume(curr, prev);
            curr.getPercentz().setPrevDayHigh(curr, prev);
            curr.getPercentz().setPrevDayLow(curr, prev);      
        }
    }
    
    /**
     * Initialize this EntryList with Entry(s) from a CSV file.
     * @param fileName name of CSV file 
     */
    public void initByFile(String fileName) {
        this.list = new ArrayList<>();
        List<String> entrys = FileIO.getLines(System.getProperty("user.dir") + "\\csv\\" + fileName);
        //First line doesn't hold data. So it is skipped
        for(int i = 1; i < entrys.size(); i++) {
            List<String> temp = Arrays.asList(entrys.get(i).split(","));
            String d = temp.get(0);
            float o = Float.valueOf(temp.get(1));
            float h = Float.valueOf(temp.get(2));
            float l = Float.valueOf(temp.get(3));
            float c = Float.valueOf(temp.get(4));
            int v = Integer.valueOf(temp.get(6));
            this.list.add(new Entry(d, v, o, h, l, c));
        }
        initEntryObjects();
    }
    
    /**
     * Initialize this EntryList with Entry(s) from another EntryList that have
     * Predicatez with the same Predicatez/Boolean pairs.Both arrays have to be
     * the same length.
     * ex: [SDG, PVH] , [true, false]
     * It will look through all the Entry(s) in list to find Predicatez that have
     * sameDayGain = true and prevDayVolume = false.
     * 
     * @param list list to be searched
     * @param e 
     * @param b 
     */
    public void initByPredicatez(EntryList list, PredicatezEnum[] e, boolean[] b) {
        if(e.length == b.length) {
            this.list = new ArrayList<>();
            //Loop through Predicatez
            for(int i = 0; i < list.size(); i++) {
                //Check must equal e.length so that entry can be added to this EntryList
                int check = 0;
                Predicatez p = list.get(i).getPredicatez();
                //Loop through array
                for(int j = 0; j < e.length; j++) {
                    switch(e[j]) {
                        case SDG:
                            if(p.getSameDayGain() == b[j]) {
                                check++;
                            }
                            break;
                        case PDC:
                            if(p.getPrevDayClose() == b[j]) {
                                check++;
                            }
                            break;
                        case PDO:
                            if(p.getPrevDayOpen() == b[j]) {
                                check++;
                            }
                            break;
                        case PDV:
                            if(p.getPrevDayVolume() == b[j]) {
                                check++;
                            }
                            break;
                        case PDH:
                            if(p.getPrevDayHigh() == b[j]) {
                                check++;
                            }
                            break;
                        case PDL:
                            if(p.getPrevDayLow() == b[j]) {
                                check++;
                            }
                            break;
                        default:
                            break;
                    }
                    
                }
                if(check == e.length) {
                    this.list.add(list.get(i));
                }
            }
        }
    }
}
