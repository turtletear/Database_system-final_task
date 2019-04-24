/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author ariq
 */
public class values {
    private double P;
    private double B;
    private ArrayList<table_detail> table = new ArrayList<table_detail>();
    File file = new File("data2.csv");
    private String[] values;
    public values() {
        setP();
        setB();        
        setTabVal();
        setBFR();
        setFR();
        setCB();
        setBlockIndex();
        //menu3();
    }// end constructor

    public void setP() {
        try {
            Scanner data = new Scanner(file);
            data.next();
            String word = data.next();    
            this.values = word.split(",");
            this.P = Double.parseDouble(values[3]);
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }//end method
    
    public void setB() {
        try {
            Scanner data = new Scanner(file);
            data.next();
            String word = data.next();    
            this.values = word.split(",");
            this.B = Double.parseDouble(values[4]);
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }//end method    
    
    public void setTabVal(){
        String name;
        String[] name2;
        
        String R;
        String[] R2;
        
        String n;
        String[] n2;
        
        String V;
        String[] V2;
        
        try {
            Scanner data = new Scanner(file);
            name = data.next();
            name2 = name.split(",");
            int i = 0;
            while (!name2[i].equals("P") ) {
                //System.out.println(name2[i]);
                table_detail td = new table_detail(name2[i]);
                table.add(td);
                
                i++;
            }//end while
            data.next();data.next();data.next();
            
            //add R
            R = data.next();
            R2 = R.split(",");
            i = 0;
            while (i < R2.length ) {
                
                table.get(i).setR(Double.parseDouble(R2[i]));
                
                i++;
            }//end while            
            
            //add n
            n = data.next();
            n2 = n.split(",");
            i = 0;
            while (i < n2.length ) {
                
                table.get(i).setN(Double.parseDouble(n2[i]));
                
                i++;
            }//end while

            //add V
            V = data.next();
            V2 = V.split(",");
            i = 0;
            while (i < V2.length ) {
                
                table.get(i).setV(Double.parseDouble(V2[i]));
                
                i++;
            }//end while
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public void setBFR(){
        for (table_detail t : table) {
                t.setBfr(B);
            }
    }
    
    public void setFR(){
        for (table_detail t : table) {
                t.setFR(B, P);
            }
    }
    
    public void menu1(){
        for (table_detail t : table) {
                t.showM1();
                System.out.println();
            }
    
    }
    
    
    
    public void setCB(){
        for (table_detail t : table) {
                t.setCB();
            }
    }
    
    public void setBlockIndex(){
        for (table_detail t : table) {
                t.setBlockIndex();
            }
    }
    public void menu2(){
        for (table_detail t : table) {
                t.showM2();
                System.out.println();
            }
    }
    
    
    
    public table_detail searchTN(String s){ //search table name
        for (table_detail t : table) {
                if (s.equals(t.getName())) {
                    return t;
                }
            }
        return null;   
    }
    
    public double accessedBlock(double x, double bfr){
        return Math.ceil(x / bfr);
    }
    
    public double accessedIndexBlock(double x, double fr){
        return Math.ceil(x / fr);
    }
    public void menu3(){
        //lanjut sini
        Scanner s = new Scanner(System.in);
        Scanner i = new Scanner(System.in);
        System.out.print("Cari rekord ke- : ");
        double x = s.nextDouble();
        
        System.out.print("Nama table      : ");
        String y = s.next();
        
        //keluarin bfr sama fr
        if (searchTN(y)!=null) {
            double bfr = searchTN(y).getBfr();
            double fr = searchTN(y).getFr();

            System.out.println("bfr : "+bfr+ " | fanout ratio : "+fr);
            double idx = Math.ceil(x/fr); //cari rumus
            double widx = Math.ceil(x/bfr);
            System.out.println("Menggunakan index, jumlah blok yang diakses : "+idx+" block");
            System.out.println("Tanpa index, jumlah blok yang diakses : "+widx+" block");
            
        }
        else
            System.out.println("table not found");
    }
    
//    public static void main(String[] args) {
//        values values1 = new values();
//        
//    }
    
    
    
}
