/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;

import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author ariq
 */
public class QEP2 {
    private String projection;
    private String join;
    private ArrayList<String> tbl_name = new ArrayList();
    private double cost1;
    private double cost2;
    private ArrayList<table> tables = new ArrayList();
    private values v = new values();
    
    private table_detail t0;
    private table_detail t1;        
    public QEP2() {

    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    public void setJoin(String join) {
        this.join = join;
    }
    
    public void addTbl_name(String x){
        tbl_name.add(x);
    }
    
    public void addTbl(table x){
        tables.add(x);
    }
    
    public void qep1(){
        System.out.println("QEP #1");
        System.out.println("Projection "+ this.projection + " --on the fly");
        System.out.println("              JOIN "+this.join+" --BLNJ");
        System.out.print(tables.get(0).getName());System.out.println("                    "+tables.get(1).getName());
        
        this.t0 = v.searchByname(this.tables.get(0).getName());
        this.t1 = v.searchByname(this.tables.get(1).getName());
        
        double br = t0.getCb(); //0 kanan
        double bs = t1.getCb(); //1 kiri
        this.cost1 = br*(bs+br);
        System.out.println("Cost (worst case) : "+this.cost1);
    }
    
    public void qep2(){
        System.out.println("QEP #2");
        System.out.println("Projection "+ this.projection + " --on the fly");
        System.out.println("              JOIN "+this.join+" --BLNJ");
        System.out.print(tables.get(1).getName());System.out.println("                    "+tables.get(0).getName());
        
        double br = t1.getCb(); //0 kanan
        double bs = t0.getCb(); //1 kiri
        this.cost2 = br*(bs+br);
        System.out.println("Cost (worst case) : "+this.cost2);        
    }

    public void saveQEPjoin(String query){
        if (this.cost1<this.cost2) {
            //save QEP1
            System.out.println("QEP optimal : QEP #1");
            try {
            FileWriter f = new FileWriter ("shared_pool.txt",true);
            f.write("Query : "+query+"@"+"PROJECTION : "+this.projection+" --on the fly"+"@"+
                    "                   JOIN "+this.join+" -- Block Nested Loop Join"+"@"+
                    tables.get(0).getName()+"                 "+tables.get(1).getName()+"@"+
                    "Cost (worst case) : "+this.cost1+" block");
            
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        else{
            //save QEP2
            System.out.println("QEP optimal : QEP #2");
            try {
            FileWriter f = new FileWriter ("shared_pool.txt",true);
            f.write("Query : "+query+"@"+"PROJECTION : "+this.projection+" --on the fly"+"@"+
                    "                   JOIN "+this.join+" -- Block Nested Loop Join"+"@"+
                    tables.get(1).getName()+"                 "+tables.get(0).getName()+"@"+
                    "Cost (worst case) : "+this.cost2+" block");
            
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }
    
    
}
