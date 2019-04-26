/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author ariq
 */
public class table_detail {
    private String name;
    private double R;
    private double n;
    private double V;
    
    private double bfr;
    private double fr;
    private double cb; //jumlah block / table
    private double bi;
    
    public table_detail(String name) {
    this.name = name;
    
    }

    public String getName() {
        return name;
    }

    public void setR(double R) {
        this.R = R;
    }

    public void setN(double n) {
        this.n = n;
    }

    public void setV(double V) {
        this.V = V;
    }

    public void setBfr(double B) {
        this.bfr = Math.floor(B/this.R);
    }
    
    public void setFR(double B, double P){
        this.fr = Math.ceil(B/(this.V + P));
    }
    
    public void setCB(){
        this.cb = Math.ceil(this.n / this.bfr);
    }
    
    public void setBlockIndex(){
        this.bi = this.n / this.fr;
    }

    public double getBfr() {
        return bfr;
    }

    public double getFr() {
        return fr;
    }

    public double getCb() {
        return cb;
    }
    
    
    
    
    
    public void showM1(){
        System.out.println("Tabel         : "+ this.name);
//        System.out.println("R             : "+ this.R);
////        System.out.println("n     : "+ this.n);
//        System.out.println("V             : "+ this.V);
        System.out.println("BFR           : "+this.bfr);
        System.out.println("Fan Out Ratio : "+ this.fr);
    }
    
    public void showM2(){
//        setCB();
//        setBlockIndex();
        System.out.println("Tabel      : "+this.name);
        System.out.println("Blok data  : "+Math.ceil(this.cb) + " blok");
        System.out.println("Blok index : "+Math.ceil(this.bi) + " blok");
        
    }
    
    
    
    
    
    
}
