/*
 * The MIT License
 *
 * Copyright 2020 Jose Manuel Hernandez.
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

/**
 * A list of EntryList(s) grouped by certain characteristics 
 * @author Jose Manuel Hernandez
 */
public class EntryGroups {
    String type;
    ArrayList<EntryList> entryLists = new ArrayList<>();
    
    public EntryGroups() {
        
    }
    
    public EntryList get(int index) {
        return this.entryLists.get(index);
    }
    
    public ArrayList<EntryList> getLists() {
        return this.entryLists;
    }
    
    public void printPredicatez() {
        if(this.entryLists == null || this.entryLists.isEmpty()) {
            System.out.println("No EntryList(s) in EntryGroups");
        } else {
            for(int i = 0; i < this.entryLists.size(); i++) {
                System.out.println(String.format("Group %d", i + 1));
                this.entryLists.get(i).printPredicatez();
            }
        }
    }
    
    public void printPredicatezCount() {
        System.out.println(String.format("Type: %s", this.type));
        for(int i = 0; i < this.entryLists.size(); i++) {
            System.out.println(String.format("PredicatezCount #%d", i + 1));
            this.entryLists.get(i).printPredicatezCount();
        }
    }
    
    /**
     * Entry(s) will be grouped based on the Predicatez SDG, starting at false and 
     * will keep adding to a group until a true is found, if next is also true it
     * will also be added until another false is found
     * @param list the list of Entry(s)
     */
    public void decreaseToIncrease(EntryList list) {
        this.type = "Decrease to increase SDG multiple";
        //Checking for the first false -- Is it still trying to find the first
        //false. Changed to false when the first SDG false value is found. Changed
        //back to true when a SDG true value is found.
        boolean firstFalse = true;
        EntryList temp = new EntryList();
        for(Entry e : list.getList()) {
            Predicatez p = e.getPredicatez();
            //If SDG false is found
            if(!p.getSameDayGain()) {
                if(firstFalse) {
                    if(!temp.getList().isEmpty()) {
                        temp.initPredicatezCount(true);
                        this.entryLists.add(temp);
                        temp = new EntryList();
                    }
                    firstFalse = false;
                }
                temp.add(e);
            //If SDG true is found
            } else { 
                if(!temp.getList().isEmpty()) {
                    temp.add(e);
                }       
                firstFalse = true;
            }          
        }
    }
    
    /**
     * Similar to decreaseToIncrease but only looks for one false SDG before one 
     * true SDG
     * @param list 
     */
    public void decreaseToIncreaseS(EntryList list) {
        this.type = "Decrease to increase SDG single";
        EntryList temp = new EntryList();
        boolean falseFound = false;
        for(Entry e : list.getList()) {
            Predicatez p = e.getPredicatez();
            if(falseFound && p.getSameDayGain()) {
                temp.add(e);
                temp.initPredicatezCount(true);
                this.entryLists.add(temp);
                temp = new EntryList();
                falseFound = false;
            } else if(!falseFound && !p.getSameDayGain()) {
                temp.add(e);
                falseFound = true;
            }
        }
    }
}
