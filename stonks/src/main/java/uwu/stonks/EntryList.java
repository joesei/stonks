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
import java.util.Arrays;
import java.util.List;

/**
 *Holds a list of Entry(s)
 * 
 */
public class EntryList {
    ArrayList<Entry> list = new ArrayList<>();
    
    public EntryList(String fileName) {
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
}
