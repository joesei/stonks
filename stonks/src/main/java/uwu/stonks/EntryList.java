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
    
    public int size() {
        return this.list.size();
    }
    
    /**
     * Initialize this EntryList with Entry(s) from a CSV file.
     * @param fileName name of CSV file 
     */
    public void initByFile(String fileName) {
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
            //Check must equal e.length so that entry can be added to this EntryList
            int check = 0;
            //Loop through Predicatez
            for(int i = 0; i < list.size(); i++) {
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
