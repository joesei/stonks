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

import java.util.ArrayList;
import uwu.stonks.Predicatez.Predz;

public class Stonck {
    
    final String fileName;
    ArrayList<Entry> entrys;
    ArrayList<Predicatez> predicatez;
    ArrayList<Percentz> percentz;
    
    public Stonck(String fileName) {
        this.fileName = fileName;
        this.entrys = Entry.getEntryList(fileName);
        this.predicatez = Predicatez.setEntryPredicatez(this.entrys);
        this.percentz = Percentz.setEntryPercentz(this.entrys);
        
    }
}
