package com.packing.orders;
import java.io.*;
import java.util.*;

class CaseComparator implements Comparator<Case> {
    public int compare(Case c1, Case c2) {
        return (int)(c1.wunit - c2.wunit);
    }
}

class CaseComparatorHt implements Comparator<Case> {
    public int compare(Case c1, Case c2) {
        return (int)(c1.z - c2.z);
    }
}

class CaseComparatorVol implements Comparator<Case> {
    public int compare(Case c1, Case c2) {
        return (int)(c1.volume - c2.volume);
    }
}
