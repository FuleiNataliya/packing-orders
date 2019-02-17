package com.packing.orders;

import java.util.Comparator;
import java.io.*;
import java.util.*;

class ProductComparator implements Comparator<Product> {
    public int compare(Product b1, Product b2) {
        return (int)(b1.wunit - b2.wunit);
    }
}

class ProductComparatorHt implements Comparator<Product> {
    public int compare(Product b1, Product b2) {
        return (int)(b1.z - b2.z);
    }
}

class ProductComparatorVol implements Comparator<Product> {
    public int compare(Product b1, Product b2) {
        return (int)(b1.volume - b2.volume);
    }
}

class ProductComparatorWt implements Comparator<Product> {
    public int compare(Product b1, Product b2) {
        return (int)(b1.weight - b2.weight);
    }
}

class ProductFinalComparatorId implements Comparator<ProductFinal> {
    public int compare(ProductFinal b1, ProductFinal b2) {
        return (b1.id - b2.id);
    }
}
