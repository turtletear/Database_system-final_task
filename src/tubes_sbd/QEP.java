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
public class QEP {
    private String selection;
    private String selction_sprt;
    private ArrayList<String> projection = new ArrayList();
    private String tbl_name;
    private String query;
    //private String join;
    private double cost;
    private values v1; //table2 yang ada bfr dkk
    private table t;

    public QEP(String tbl_name, String selection) {
        
        v1 = new values();
        this.tbl_name = tbl_name;
        this.selection = selection;
        String[] x = new String[2];
        x = selection.split("=");
        this.selction_sprt = x[0];
    }

    public void setT(table t) {
        this.t = t;
    }
    
    public void showProjection(){
        for (String x : projection) {
            System.out.print(x+" ");
        }
    }
    
    public void QEPshow(){
        System.out.print("Projection ");
        showProjection(); System.out.println(" -- on the fly");
        System.out.print("Selection " + selection); selectFormula(this.selction_sprt);
        System.out.println(tbl_name);
        System.out.println("Cost : "+cost);
    }
    
    public void saveQEP(){
        
    
    }
    
    public void addProjection(String x){
        projection.add(x);
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    
    public void selectFormula(String x){
        if (t.cekPK(x)) {
            A1key();
        }
        else
            A2();
    }
    
    public void A1key(){
        System.out.println(" -- A1 key");
    }
    
    public void A2(){
        System.out.println(" -- A2");
    }
    
    
}
