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
    
    public Predicatez getPredicatez() {
        return this.predicatez;
    }
    
    public Percentz getPercentz() {
        return this.percentz;
    }
    
    /**
     * Compares this entry with another one based on Predicatez values
     * @param e the other Entry
     * @return true if Predicatez values are the same else false
     */
    public boolean compareByPredicatez(Entry e) {
        return this.predicatez.comparePredicatez(e.getPredicatez());
    }
}
