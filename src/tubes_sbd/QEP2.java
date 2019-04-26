/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;

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
        
        System.out.println("Cost (worst case) : "+this.cost1);
    }
    
    public void qep2(){
        System.out.println("QEP #2");
        System.out.println("Projection "+ this.projection + " --on the fly");
        System.out.println("              JOIN "+this.join+" --BLNJ");
        System.out.print(tables.get(1).getName());System.out.println("                    "+tables.get(0).getName());
        
        System.out.println("Cost (worst case) : "+this.cost2);        
    }

    public void saveQEPjoin(){
        if (this.cost1<this.cost2) {
            //save QEP1
            
        }
        else{
            //save QEP2
        }
    }
    
    
}
