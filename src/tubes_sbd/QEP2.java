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
    private double cost;

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
    
    
    
    
}
