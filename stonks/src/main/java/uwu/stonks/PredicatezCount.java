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

public class PredicatezCount {
    boolean countType;
    int SDG = 0;
    int PDC = 0;
    int PDO = 0;
    int PDV = 0;
    int PDH = 0;
    int PDL = 0;
    
    public PredicatezCount(EntryList list, boolean countType) {
        this.countType = countType;
        for(Entry e : list.getList()) {
            Predicatez p = e.getPredicatez();
            if(p.getSameDayGain() == countType) {
                this.SDG++;
            }
            if(p.getPrevDayClose() == countType) {
                this.PDC++;
            }
            if(p.getPrevDayOpen() == countType) {
                this.PDO++;
            }
            if(p.getPrevDayVolume() == countType) {
                this.PDV++;
            }
            if(p.getPrevDayHigh() == countType) {
                this.PDH++;
            }
            if(p.getPrevDayLow() == countType) {
                this.PDL++;
            }
        }
    }
    
    public void printCount() {
        String s = String.format("SDG: %s, PDC: %s, PDO: %s, PDV: %s, PDH: %s,"
                + " PDL: %s", this.SDG, this.PDC, this.PDO, this.PDV, this.PDH, 
                this.PDL);
        
        System.out.println(s);
    }
}
