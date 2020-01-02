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

/**\
 * A list of EntryList(s) grouped by certain characteristics 
 * @author Jose Manuel Hernandez
 */
public class EntryGroups {
    ArrayList<EntryList> lists = new ArrayList<>();
    
    public EntryGroups() {
        
    }
    
    public EntryList get(int index) {
        return this.lists.get(index);
    }
    
    /**
     * Entry(s) will be grouped based on the Predicatez SDG, starting at false and 
     * will keep adding to a group until a true is found.
     * @param list the list of Entry(s)
     */
    public void decreaseToIncrease(EntryList list) {
        //Whether Predicatez is first false in chain
        boolean firstFalse = true;
        EntryList temp = new EntryList();
        for(Entry e : list.getList()) {
            Predicatez p = e.getPredicatez();
            if(!p.getSameDayGain()) {
                if(firstFalse) {
                    if(!temp.getList().isEmpty()) {
                        this.lists.add(temp);
                        temp = new EntryList();
                    }
                    firstFalse = false;
                }
                temp.add(e);
            } else { 
                if(!temp.getList().isEmpty()) {
                    temp.add(e);
                }       
                firstFalse = true;
            }          
        }
    }
    
    public void printPredicatez() {
        if(this.lists == null || this.lists.isEmpty()) {
            System.out.println("No EntryList(s) in EntryGroups");
        } else {
            for(int i = 0; i < this.lists.size(); i++) {
                System.out.println(String.format("Group %d", i + 1));
                this.lists.get(i).printPredicatez();
            }
        }
    }
}
