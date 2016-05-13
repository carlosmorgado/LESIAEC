package com.example.android.cccportable;

import java.util.Arrays;

/**
 * Created by Diogo Baptista on 11/05/2016.
 */
public class Str { // extends Object
    private static final int START_DIM = 8; // = 2^N
    protected char[] txt = new char[START_DIM];
    protected int len = 0;

    public Str() {
        this("");
    }

    public Str(String s) {
        txt = new char[START_DIM];
        resize(s.length());
        for (int i = 0; i < s.length(); i++) {
            txt[i]=s.charAt(i);
        }
        len = s.length();
    }

    public Str(Str s) {
        txt = new char[s.txt.length];
        len = s.len;
        System.arraycopy(s.txt,0,txt,0,len);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Str))
            return false;
        Str other = (Str)obj;
        return len==other.len && Arrays.equals(txt, other.txt);
    }

    private void resize(int minSize) {
        if (minSize<=txt.length)
            return;
        char[] newTxt = new char[power2(minSize)];
        if (len>0)
            System.arraycopy(txt,0,newTxt,0,len);
        txt = newTxt;
    }

    private static int power2(int min) {
        int val;
        for (val = START_DIM*2; val < min ; val*=2)
            ;
        return val;
    }

    @Override
    public String toString() {
        return new String(txt,0,len);
    }
    public String change(int idx, char c){
        txt[idx]=c;
        return this.toString();
    }

    public Str append(String s) {
        resize(len+s.length());
        copyFromString(s,len);
        return this;
    }

    private void copyFromString(String s, int toIdx) {
        for (int i = 0; i < s.length(); i++)
            txt[toIdx++] = s.charAt(i);
        len += s.length();
    }

    public Str insert(int idx, String s) {
        resize(len+s.length());
        System.arraycopy(txt,idx,txt,idx+s.length(),len-idx);
        copyFromString(s,idx);
        return this;
    }

    public String m() { return "sou Str"; }
}
