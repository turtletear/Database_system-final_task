/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import tubes_sbd.query;

/**
 *
 * @author ariq
 */
public class tableParse {
    
    private String[] table;
    File file = new File("data2.csv");
    private String[] values; //kayanya ga penting
    private String[] tablename;
    
    private table oneTable;
//    private table[] manyTable;
    private ArrayList<table> manyTable = new ArrayList<table>();
    
    private String temp = "";
    
    public tableParse(String[] table) {
    
        try {
            Scanner data = new Scanner(file);
            tablename = data.next().split(",");
//            int i =0;
//            while (data.hasNext()) {
//                String word = data.next();
//                //System.out.println(word);
//                this.values = word.split(",");
////                System.out.println(values[2]);
//                i++;
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    if (table.length > 1) {
        int i = 0;
        while (i < table.length) {
            if (!table[i].equals("join")) {
                int t = searchTable(table[i]);
                if (t != 99) {
                    //newo(table[i])
                    table tr = new table(table[i]);
                    //add kolom2nya
                    try {
                        Scanner data = new Scanner(file);
                        data.next();
                        while (data.hasNext()) {
                            String word = data.next();
                            this.values = word.split(",");
                            tr.addCoulmn(values[t]); //t -> index
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    System.out.println("table found"); // masukin kolom2nya
                    if (table[i+1].length() < 2) {
                        //add alias to newo(table[i])
                        tr.setAlias(table[i+1]);
//                        System.out.println("add "+ table[i+1] +" to "+ table[i]+" alias");
                    }
                    manyTable.add(tr);
                }
                else if(table[i].charAt(0) == '('){
//                    System.out.println(table[i]);
                    for (int j = 1; j < table[i].length()-1; j++) {
                        temp += table[i].charAt(j);
                    }
//                    System.out.println(temp);//tanpa ()
                    
                    
                }
                else if (t == 99 && table[i].length() > 2){ // kalo ga ketemu
//                    System.out.println(table[i]);
                      
//                    System.out.println("Table not found");
                }
            }// close if join
        i++;
        }//endwhile
    }//end if table.length > 1
    
    else { //table.length <=1
        int i = 0;
        int t = searchTable(table[i]);
        if (t != 99) {
            //newo(table[i])
            //add kolom2nya     
            table td = new table(table[i]);
            //System.out.println(table[i]);
        try {
            Scanner data = new Scanner(file);
//            tablename = data.next().split(",");
            data.next();
            while (data.hasNext()) {
                String word = data.next();
                //System.out.println(word);
                this.values = word.split(",");
                td.addCoulmn(values[t]); //t -> index
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            this.oneTable = td;   
        }
        else
            System.out.println("table not found");
        
    }
    
//    System.out.println(table[table.length-1]);

}//end constructor
    
    public int searchTable(String x){
        int i = 0;
        int res = 99;
        while (i < tablename.length) {
            if (x.equals(tablename[i])) {
                res = i;
            }
            i++;
        }
        return res; // 99 kalo ga ketemu
    }//clode method
    
    
    public table getOneTable(){
        return this.oneTable;
    }
    
    public ArrayList<table> getManyTable(){
        return this.manyTable;
    }
    
    public String getTemp(){
        return this.temp;
    }
    
}//end class
