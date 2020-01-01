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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Holds values of a single trading day 
 * @author Jose Manuel Hernandez
 */
public class Entry {
    
    final String date;
    final int volume;
    final float open, high, low, close;
    Predicatez predicatez;
    Percentz percentz;
    
    public Entry(String date, int volume, float open, float high, float low, 
            float close) {
        
        this.date = date;
        this.volume = volume;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.predicatez = new Predicatez();
        this.percentz = new Percentz();
    }
    
    //Getters----------------------------------------------------------
    public String getDate() {
        return this.date;
    }
    
    public int getVolume() {
        return this.volume;
    }
    
    public float getOpen() {
        return this.open;
    }
    
    public float getHigh() {
        return this.high;
    }
    
    public float getLow() {
        return this.low;
    }
    
    public float getClose() {
        return this.close;
    }
    
    public Predicatez getEntryPred() {
        return this.predicatez;
    }
    
    public Percentz getEntryPerc() {
        return this.percentz;
    }
    
    //--------------------------------------------------------------------------
    /**
     *Get the values stored in a csv file found in the csv folder
     */
    public static ArrayList<Entry> getEntryList(String fileName) {
        String path = System.getProperty("user.dir") + "\\csv\\" + fileName;
        ArrayList<Entry> entryList = new ArrayList<>(); 
        File file = new File(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            //Get first line out of way
            String s = br.readLine();
            List<String> temp;
            while((s = br.readLine()) != null) {
                temp = Arrays.asList(s.split(","));
                String d = temp.get(0);
                float o = Float.valueOf(temp.get(1));
                float h = Float.valueOf(temp.get(2));
                float l = Float.valueOf(temp.get(3));
                float c = Float.valueOf(temp.get(4));
                int v = Integer.valueOf(temp.get(6));
                entryList.add(new Entry(d, v, o, h, l, c));
            }
            br.close();
        } catch(IOException | NumberFormatException ex) {
        } finally {
            System.out.println("CSV file succesfully read");
        }
        return entryList;
    }
    
    /*
    Return a list of entrys containing the specified predicatez/boolean pairs
    Paired by same index 
    predz and bools must have same number of objects
    */
    public static ArrayList<Entry> getEntrysByPredz(ArrayList<Entry> list, 
        ArrayList<Predicatez.Predz> predz, ArrayList<Boolean> bools) {
        
        ArrayList<Entry> ret = new ArrayList<>();
        Predicatez p;
        for(Entry e : list) {
            p = e.getEntryPred();
            //Check whether all predz/boolean pairs are present
            boolean b;       
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
                ret.add(e);
            }    
        }
        return ret;
    }
}
