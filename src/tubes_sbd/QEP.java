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
    private ArrayList<String> projection = new ArrayList();
    private String tbl_name;
    //private String join;
    private double cost;
    private values v1; //table2 yang ada bfr dkk
    

    public QEP() {
        
        v1 = new values();
    
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public void setTbl_name(String tbl_name) {
        this.tbl_name = tbl_name;
    }
    
    public void showProjection(){
        for (String x : projection) {
            System.out.print(x+" ");
        }
    }
    
    public void QEPshow(){
        System.out.print("Projection ");
        showProjection(); System.out.println(" -- on the fly");
        System.out.println("Selection " + selection + " A1/A2");
        System.out.println(tbl_name);
        System.out.println("Cost : "+cost);
    }
    
    public void addProjection(String x){
        projection.add(x);
    }
    
}
