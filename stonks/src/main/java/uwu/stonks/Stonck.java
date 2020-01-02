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

public class Stonck {
    //The csv file name
    final String fileName;
    
    //This will be number of lines in the file excluding first line
    //Total number of trade days
    final int tradeDays;
    
    //Average number of trading days
    static final int DAYSYEAR = 253;
    static final int DAYSMONTH = 21;
    static final int DAYSWEEK = 5;
    
    EntryList entrys = new EntryList();

    
    public Stonck(String fileName) {
        this.fileName = fileName;
        this.entrys.initByFile(fileName);
        this.tradeDays = this.entrys.size();
        initAll();
    }
    
    public EntryList getEntryList() {
        return this.entrys;
    }
    
    /**
     * Used to test features
     * 
     */
    public void test() {
        //Print
        this.entrys.printPredicatez();
        
        //Predz and bools are parameters to create an entry list
        PredicatezEnum[] e = {PredicatezEnum.SDG, PredicatezEnum.PDH};
        boolean[] b = {true, true};
        
        //New EntryList
        EntryList list = new EntryList(); 
        list.initByPredicatez(this.entrys, e, b);
        list.printPredicatez();
        
        //Count predicatez by boolean
        PredicatezCount count = new PredicatezCount(list, true);
        count.printCount(); 
       
        
    }
    
    
    /**
     * Initialize values of Predicatez/Percentz for all entries in Stonck
     * 
     */
    private void initAll() {
        //Current and previous entry
        Entry curr = this.entrys.get(0);
        Entry prev;  
        //First entry doesn't have previous
        curr.getPredicatez().setSameDayGain(curr);
        curr.getPercentz().setSameDayGain(curr);
        //Set values for rest of entrys
        for(int i = 1; i < this.entrys.size() - 1; i ++) {
            curr = this.entrys.get(i);
            prev = this.entrys.get(i - 1);
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
}
