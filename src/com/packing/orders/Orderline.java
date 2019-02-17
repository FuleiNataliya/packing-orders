package com.packing.orders;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;

class Orderline {

    List<Product> products = new ArrayList();
    List<Case> cases = new ArrayList();

    double arr[][] = new double[1000][1000];
    int n=0;

    private static final String COMMA_DELIMITER = ",";

    private static final int PRODUCT_ID_IDX = 0;
    private static final int PRODUCT_NAME_IDX = 1;
    private static final int PRODUCT_X_IDX = 2;
    private static final int PRODUCT_Y_IDX = 3;
    private static final int PRODUCT_Z_IDX = 4;
    private static final int PRODUCT_W_IDX = 5;
    private static final int PRODUCT_NO_IDX = 6;

    private static final int CASE_ID_IDX = 0;
    private static final int CASE_NAME_IDX = 1;
    private static final int CASE_X_IDX = 2;
    private static final int CASE_Y_IDX = 3;
    private static final int CASE_Z_IDX = 4;
    private static final int CASE_W_IDX = 5;
    private static final int CASE_NO_IDX = 6;

    void read_products_file(){

        BufferedReader fileReader = null;
        try{
            String line = "";

            fileReader = new BufferedReader(new FileReader("Products.csv"));

            fileReader.readLine();

            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(COMMA_DELIMITER);
                if(tokens.length > 0) {
                    int tmp = Integer.parseInt(tokens[PRODUCT_NO_IDX]);
                    while(tmp>0){
                        Product product = new Product(Integer.parseInt(tokens[PRODUCT_ID_IDX]), tokens[PRODUCT_NAME_IDX],
                                Double.parseDouble(tokens[PRODUCT_X_IDX]), Double.parseDouble(tokens[PRODUCT_Y_IDX]),
                                Double.parseDouble(tokens[PRODUCT_Z_IDX]), Double.parseDouble(tokens[PRODUCT_W_IDX]));
                        product.add(product);
                        tmp--;
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        }
        finally {
            try {
                fileReader.close();
            }
            catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }

    }

    void read_cases_file(){

        BufferedReader fileReader = null;
        try{
            String line = "";

            fileReader = new BufferedReader(new FileReader("Case.csv"));

            fileReader.readLine();

            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(COMMA_DELIMITER);
                if(tokens.length > 0) {
                    try{
                        int tmp = Integer.parseInt(tokens[CASE_NO_IDX]);
                        while(tmp>0){
                            Case aCase = new Case(Integer.parseInt(tokens[CASE_ID_IDX]), tokens[CASE_NAME_IDX],
                                    Double.parseDouble(tokens[CASE_X_IDX]),Double.parseDouble(tokens[CASE_Y_IDX]),
                                    Double.parseDouble(tokens[CASE_Z_IDX]), Double.parseDouble(tokens[CASE_W_IDX]));
                            aCase.add(aCase);
                            tmp--;
                        }
                    }
                    catch(Exception e){
                        Case aCase = new Case(Integer.parseInt(tokens[CASE_ID_IDX]), tokens[CASE_NAME_IDX],
                                Double.parseDouble(tokens[CASE_X_IDX]),Double.parseDouble(tokens[CASE_Y_IDX]),
                                Double.parseDouble(tokens[CASE_Z_IDX]), Double.parseDouble(tokens[CASE_W_IDX]));
                        aCase.add(aCase);
                    }
                }

            }
        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        }
        finally {
            try {
                fileReader.close();
            }
            catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }

    }

    void least_no_products(){
        List<ProductFinal> productsfinal = new ArrayList();

        for(Case cont2:cases){
            arr[n][0]=cont2.volume;
            arr[n][1]=cont2.weight;
            n++;
        }

        System.out.println("###---LEAST NUMBER OF PRODUCTS---###");

        Collections.sort(products, new ProductComparatorVol());

        Collections.sort(cases, new CaseComparatorVol());

        int c = products.size();

        System.out.println("List of products may or maynot fit:-");
        try{
            for(Product box: products){
                Iterator itr = cases.iterator();

                boolean flag_tmp = false;

                OUTER:
                while(itr.hasNext()){
                    Case aCase = (Case)itr.next();

                    double tp1 = aCase.xt;
                    double tp2 = aCase.yt;

                    if(aCase.flag_rev == false){
                        aCase.zt = box.z;
                        aCase.flag_rev = true;
                    }

                    if((aCase.volume>=products.volume) && (aCase.weight>=products.weight)){
                        int cff = 0;

                        while(cff<=9){
                            if((aCase.zt>=products.z) || (aCase.z>=products.z)){
                                int cfk = 0;

                                while(cfk<=6){
                                    if((aCase.xt>=products.x) && (aCase.yt>=products.y)){
                                        aCase.volume-=products.volume;
                                        aCase.weight-=products.weight;

                                        double trp = aCase.zt;
                                        aCase.zt = Math.max(aCase.zt,products.z);

                                        if(aCase.zt!=trp){
                                            aCase.z = aCase.z - aCase.zt + trp;
                                        }

                                        if(aCase.first_flag){
                                            aCase.z-=aCase.zt;
                                            aCase.first_flag = false;
                                            aCase.level++;
                                        }

                                        ProductFinal tp = new ProductFinal(aCase.id,aCase.name,products,aCase.level,
                                                aCase.x-aCase.xt,aCase.y-aCase.yt);
                                        productsfinal.add(tp);
                                        c--;
                                        cff++;
                                        cfk++;

                                        flag_tmp = true;

                                        tp1-=products.x;
                                        tp2-=products.y;

                                        if(tp1>tp2){
                                            aCase.xt = tp1;
                                        }
                                        else if(tp1<tp2){
                                            aCase.yt = tp2;
                                        }

                                        break OUTER;
                                    }
                                    else if(aCase.z>=products.z && (aCase.x>=products.x && aCase.y>=products.y) && (aCase.xt<products.x || aCase.yt<products.y)){
                                        Level tlevel = new Level(aCase.level,aCase.z_orignal - aCase.z);
                                        aCase.levels.add(tlevel);

                                        aCase.level++;
                                        aCase.xt = aCase.x;
                                        aCase.yt = aCase.y;

                                        tp1 = aCase.x;
                                        tp2 = aCase.y;

                                        aCase.zt = products.z;
                                        aCase.z -= aCase.zt;

                                        aCase.flag_level = true;

                                        cff++;
                                        cfk++;
                                    }
                                    else{
                                        double t5 = products.x;
                                        products.x = products.y;
                                        products.y = t5;

                                        cff++;
                                        cfk++;
                                    }
                                }
                            }
                            else if((aCase.z>=products.z) && (products.y<products.x)){
                                aCase.zt = Math.max(aCase.zt,products.y);

                                double th = products.z;
                                products.z = products.y;
                                products.y = th;

                                cff++;
                            }
                            else if((aCase.z>=products.z) && (products.x<products.y)){
                                aCase.zt = Math.max(aCase.zt,products.x);

                                double th = products.z;
                                products.z = products.x;
                                products.x = th;

                                cff++;
                            }
                            else{
                                cff++;
                            }
                        }
                    }
                    else{
                        continue OUTER;
                    }
                }

                if(flag_tmp==false){
                    System.out.println(products.toString());
                }

                Collections.sort(cases, new CaseComparatorVol());
            }

            System.out.println();

            Collections.sort(productsfinal, new ProductFinalComparatorId());
            System.out.println("The products and cases are:-");
            int tp_prev_id = 1;

            for(ProductFinal tp : productsfinal){
                if(tp_prev_id!=tp.id){
                    System.out.println();
                }
                System.out.println("Case ID:"+tp.id+" Case Name:"+tp.name+"\tProductsDetails:"+tp.toString());
                tp_prev_id = tp.id;
            }

            System.out.println("Total number of products="+products.size());
            System.out.println("Number of products left="+c);
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    void display_stuff(){
        System.out.println("\nChecking the efficiency of packing:-");
        System.out.println("Number of case: "+cases.size());
        for(Case cont:cases){
            System.out.println("Case ID:"+cases.id);
            System.out.println("\tCase_name:"+cases.name);
            System.out.printf("\tCase_wt=%.2f\tcase_volume=%.2f\n",(arr[cases.id-1][1]-cases.weight),(arr[cases.id-1][0]-cases.volume));
            System.out.printf("\tCase_wt_total=%.2f\tcase_vol_total=%.2f\n",arr[cases.id-1][1],arr[cases.id-1][0]);
            System.out.printf("\twt%%=%.2f\tvol%%=%.2f\n",((arr[cases.id-1][1]-cases.weight)/arr[cases.id-1][1])*100,((arr[cases.id-1][0]-cases.volume)/arr[cases.id-1][0])*100);

            for(Level plevel: cont.levels){
                System.out.println("\t\t"+plevel.toString());
            }
        }
    }
}
