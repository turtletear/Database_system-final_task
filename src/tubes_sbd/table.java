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
public class table {
    private String name;
    private String alias;
    private ArrayList<String> coulmns = new ArrayList<String>();

    public table(String name) {
        this.name = name;
        this.alias = "none";
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    public boolean cekPK(String x){
        if (x.equals(coulmns.get(0))) {
            return true;
        }
        return false;
    }
    
    public void addCoulmn(String x){
        coulmns.add(x);
    }

    public String getName() {
        return name;
    }
    
    public boolean cekCoulmn(String x){
        boolean ret = false;
        for (String t : coulmns) {
            if (t.equals(x)) {
                ret = true;
            }
        }
        return ret;
    }
    //lengkapi fungsi di table parse
    public void showCoulmn(){
        System.out.println("nama tabel : "+ this.name);
        System.out.println("Alias      : "+this.alias);
        System.out.println("atribut    : ");
        for (String t : coulmns) {
            System.out.println(t);
        }
    }
    
}
