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

public class Predicatez {
    
    //true == higher 
    //false == lower 
    boolean sameDayGain;
    boolean prevDayClose;
    boolean prevDayOpen;
    boolean prevDayVolume;
    boolean prevDayHigh;
    boolean prevDayLow;
    
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
    
    public boolean comparePredicatez(Predicatez p) {
        if(this.sameDayGain != p.getSameDayGain()) {
            return false;
        } else if(this.prevDayClose != p.getPrevDayClose()) {
            return false;
        } else if(this.prevDayOpen != p.getPrevDayOpen()) {
            return false;
        } else if(this.prevDayVolume != p.getPrevDayVolume()) {
            return false;
        } else if(this.prevDayHigh != p.getPrevDayHigh()) {
            return false;
        } else if(this.prevDayLow != p.getPrevDayLow()) {
            return false;
        } 
        return true; 
    }
    
    /**
     * 
     * @return an array of the Boolean values
     */
    public boolean[] predicatezToArray() {
        boolean[] ret = {this.sameDayGain, this.prevDayClose, this.prevDayOpen, 
            this.prevDayVolume, this.prevDayHigh, this.prevDayLow};
        return ret;
    }
}
