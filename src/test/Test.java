package test;
import java.io.*;
import java.util.*;


class Test{
    public static void main(String args[]){
        try{
            Orderline ob1 = new Orderline();
            ob1.read_products_file();
            System.out.println();

            ob1.read_cases_file();
            System.out.println();

            ob1.least_no_products();
            System.out.println();
            ob1.display_stuff();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
