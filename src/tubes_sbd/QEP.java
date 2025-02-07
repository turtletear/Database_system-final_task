/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;

import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ariq
 */
public class QEP {
    private String selection;
    private String selction_sprt;
    private ArrayList<String> projection = new ArrayList();
    private String prj="";
    private String tbl_name;
    private String query;
    private double cost;
    private values v1; //table2 yang ada bfr dkk
    private table t;
    
    private table_detail t2;
    private double fanout;
    public QEP(String tbl_name, String selection) {
        
        v1 = new values();
        this.tbl_name = tbl_name;
        this.selection = selection;
        String[] x = new String[2];
        x = selection.split("=");
        this.selction_sprt = x[0];
        this.t2 = v1.searchByname(this.tbl_name);
        this.fanout = this.t2.getFr();
        
    }

    public table_detail getT2() {
        return t2;
    }

    public String getSelction_sprt() {
        return selction_sprt;
    }
    
    public void setT(table t) {
        this.t = t;
    }
    
    public void mergeProjection(){
        for (String x : projection) {
            prj+=x+",";
        }
    }
    
    public void QEPshow(double cost, String formula){ //kasih parameter untuk A1/A2
        System.out.print("Projection ");
        System.out.println(this.prj+" -- on the fly");
        System.out.print("Selection " + selection+ "  --"); System.out.println(formula);
        System.out.println(tbl_name);
        System.out.println("Cost : "+cost);
    }
    
    public void saveQEP(double cost, String formula){ //kasih parameter untuk A1/A2
        try {
            FileWriter f = new FileWriter ("shared_pool.txt",true);
            f.write("Query : "+query+"@"+"Projection "+prj+" -- on the fly"+"@"+"Selection "
            +selection+ this.selction_sprt + " --"+formula+"@"+tbl_name+"@"+"cost : "+cost);
            f.write(System.getProperty("line.separator"));
            
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    public void addProjection(String x){
        projection.add(x);
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    public boolean isKey (String x){
        if (t.cekPK(x)) {
            return true;
        }
        else
           return false;
    }
    
    public double A1key(double b){
        return b/2;
    }
    
    public double A1Nonkey(double b){
        return b;
    }
    
    public double A2(double b){
        double hi = Math.ceil(LogB(this.fanout,b));
        return hi+1;
    }
    
    public double LogB(double y , double b){ //y is base
        return Math.log(y) / Math.log(b);
    }
   

    public double getFanout() {
        return fanout;
    }
    
    
}
