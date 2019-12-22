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

public class Percentz {
    
    float sameDayGain;
    float prevDayClose;
    float prevDayOpen;
    float prevDayVolume;
    float prevDayHigh;
    float prevDayLow;
    
    public Percentz() {
    }
    
    //Setters-------------------------------------------------------------------
    public void setSameDayGain(Entry same) {
        this.sameDayGain = ((same.getClose() - same.getOpen()) / same.getClose()) * 100;
    }
    
    public void setPrevDayClose(Entry same, Entry prev) {
        this.prevDayClose = ((same.getClose() - prev.getClose()) / prev.getClose()) * 100;
    }
    
    public void setPrevDayOpen(Entry same, Entry prev) {
        this.prevDayOpen = ((same.getOpen() - prev.getOpen()) / prev.getOpen()) * 100;
    }
    
    public void setPrevDayVolume(Entry same, Entry prev) {
        this.prevDayVolume = ((same.getVolume() - prev.getVolume()) / prev.getVolume()) * 100;
    }
    
    public void setPrevDayHigh(Entry same, Entry prev) {
        this.prevDayHigh = ((same.getHigh() - prev.getHigh()) / prev.getHigh()) * 100;
    }
    
    public void setPrevDayLow(Entry same, Entry prev) {
        this.prevDayLow = ((same.getLow() - prev.getLow()) / prev.getLow()) * 100;
    }
    
    //Getters-------------------------------------------------------------------
    public float getSameDayGain() {
        return this.sameDayGain;
    }
    
    public float getPrevDayClose() {
        return this.prevDayClose;
    }
    
    public float getPrevDayOpen() {
        return this.prevDayOpen;
    }
    
    public float getPrevDayVolume() {
        return this.prevDayVolume;
    }
    
    public float getPrevDayHigh() {
        return this.prevDayHigh;
    }
    
    public float getPrevDayLow() {
        return this.prevDayLow;
    }
    
}
