package com.packing.orders;
import java.io.*;
import java.util.*;

class ProductFinal{

    int id;
    String name;
    Product product;
    int level;

    double coor_x;
    double coor_y;

    ProductFinal(int id, String name, Product product,int level, double coor_x, double coor_y){
        this.id = id;
        this.name = name;
        this.product = product;
        this.level = level;

        this.coor_x = coor_x;
        this.coor_y = coor_y;
    }

    @Override
    public String toString() {
        return "Product [id=" + product.id + ", Name=" + product.name + ", Volume=" + product.volume + ", Weight=" + product.weight +  ", " +
                "size="+product.x+" X "+product.y+" X "+product.z+" ,(X,Y)="+coor_x+","+coor_y+" ,level="+level+"]";
    }
}
