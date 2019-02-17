package com.packing.orders;
import java.io.*;
import java.util.*;

class Case{

    int id;
    String name;
    double volume;
    double weight;
    double wunit;
    double x;
    double y;
    double z;
    double xt;
    double yt;
    double zt;

    int cx;
    int cy;
    int cz;

    double z_orignal;

    boolean flag_rev;
    boolean first_flag;
    boolean flag_level;

    int level;

    List<Level> levels = new ArrayList();

    Case(int id, String name, double x,double z,double y,double w){
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;

        z_orignal = z;

        volume = x*y*z;
        weight = w;
        wunit = weight/volume;
        xt = this.x;
        yt = this.y;
        zt =this.z;
        level = 0;

        flag_rev=false;
        first_flag = true;
        flag_level = false;

        cx=0;
        cy=0;
        cz=0;
    }

    @Override
    public String toString() {
        return "Case [id=" + id + ", Name=" + name + ", Volume=" + volume + ", Weight=" + weight +", Volume_per_unit=" + wunit+  "]";
    }

    public String toStringFinal() {
        return "Case [id=" + id + ", Name=" + name + ", Volume=" + volume + ", Weight=" + weight +  "]";
    }
}
