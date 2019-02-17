package com.packing.orders;
import java.io.*;
import java.util.*;

class Level{
    int l;
    double ht;

    Level(int l,double ht){
        this.l = l;
        this.ht = ht;
    }

    @Override
    public String toString() {
        return "sheet_adding_info [Sheet_No=" + l + ", height=" + ht +  "]";
    }

}
