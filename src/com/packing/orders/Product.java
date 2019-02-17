package com.packing.orders;
import java.io.*;
import java.util.*;

class Product{

    int id;
    String name;
    double x;
    double y;
    double z;
    double volume;
    double weight;
    double wunit;

    Product(int id, String name, double x,double z,double y,double w){
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        volume = x*y*z;
        weight = w;
        wunit = weight/volume;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", Name=" + name + ", Volume=" + volume + ", Weight=" + weight +", X =" + x+", Y =" + y+", Z =" + z+ "]";
    }

    public String toStringFinal() {
        return "Product [id=" + id + ", Name=" + name + ", Volume=" + volume + ", Weight=" + weight +  "]";
    }
}