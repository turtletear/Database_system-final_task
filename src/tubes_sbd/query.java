/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;
import java.util.ArrayList;
import tubes_sbd.tableParse;
import tubes_sbd.table;
/**
 *
 * @author ariq
 */
public class query {
    private String input;
    private String[] tokens;
    
    

    //constructor
    public query(String input) {
        this.input = input;
        //
        // ; ditinggal kalo masuk tokens
        String m = "";
        for (int i = 0; i < input.length()-1; i++) {
            m += input.charAt(i);
        }
        this.tokens = m.split(" ");
        
    }//close constructor

    public String[] getInput() {
        return tokens;
    }
    
    public boolean cekLength(){
        if (this.tokens.length > 3) {
            return true;
        }
        else
            return false;
    }
    
    public boolean cekSelect(){  
        String chk = this.tokens[0];
        if (chk.equals("select") || chk.equals("Select") || chk.equals("SELECT")){
            return true;
        }
        else{
            return false;
        }
    }//close method
    
    public boolean cekFrom(){    
        String chk = this.tokens[2];
        if (chk.equals("from") || chk.equals("FROM") || chk.equals("From")) {
            return true;
        }
        else
            return false;    
    }
    
    public boolean cekSemicolon(){
        if (this.input.charAt(this.input.length()-1) == ';') {
            return true;
        }
        else
            return false;
    }
    
    public void cekTable(){
    String[] chk = new String[this.tokens.length-3]; //ngambil setelah 'from'
    int i = 3;
    int j = 0;
        while (i < this.tokens.length) {
            chk[j] = this.tokens[i];
            j++;
            i++;
        }
        
        tableParse t1 = new tableParse(chk); //parsing tableParse disini
        
        if (chk.length == 1) {
            //panggil method buat yg 1 tabel
            table x = t1.getOneTable();
            oneTable(x); //menampilkan 1 tabel setelah parsing
        }
        else if(chk.length == 3){
            String[] chk2 = new String[1];
            chk2[0] = chk[0];
            tableParse t2 = new tableParse(chk2);
            table x = t2.getOneTable();
            oneTable(x);
            
            oneTableQEP(x, chk[2]);
        }
        
        else{
            //panggil method buat yg >1 tabel
            ArrayList<table> x = t1.getManyTable();

            manyTable(x,t1.getTemp());
            //disini
        }
    }
    
    public boolean cekCoulmn(){
        if (tokens[1].charAt(tokens[1].length()-1) == ',') {
            return false;
        }
        else
            return true;
        
    }//end method
    
    
    public String[] parseToken1(String x){
        String[] parse = x.split(",");
        return parse;
    }
    
    public void oneTable(table t){ //menampilkan 1 tabel setelah parse
        String[] parse = parseToken1(tokens[1]);
        
        int count_fnd = 0;
        for (int i = 0; i < parse.length; i++) {
            if (t.cekCoulmn(parse[i])) {
                count_fnd++;
            }
        }        
        if (count_fnd != 0) {
            System.out.println("Table       : " + t.getName());
            System.out.println("Column List : ");        
            for (int i = 0; i < parse.length; i++) {
                if (t.cekCoulmn(parse[i])) {
                    System.out.println((i+1)+". "+parse[i]);
                }
            }        
        }
        
        else
            System.out.println("column not found !");
    }//close method
    
    public void oneTableQEP(table t, String select){
        String[] parse = parseToken1(tokens[1]);
        int count_fnd = 0;
        for (int i = 0; i < parse.length; i++) {
            if (t.cekCoulmn(parse[i])) {
                count_fnd++;
            }
        }        
        if (count_fnd != 0) {
            QEP q1 = new QEP(t.getName(),select);
            
            for (int i = 0; i < parse.length; i++) {
                if (t.cekCoulmn(parse[i])) {
                    q1.addProjection(parse[i]);
                }
            }
            q1.setT(t);
            q1.setQuery(input);
            q1.mergeProjection();
            System.out.println("---------------------");
            if (q1.isKey(q1.getSelction_sprt())) {
                double b  = q1.getT2().getCb();
                double a1 = q1.A1key(b);
                double a2 = q1.A2(b);
                System.out.println("QEP#1");
                q1.QEPshow(a1,"A1 key");
                System.out.println("");
                System.out.println("QEP#2");
                q1.QEPshow(a2,"A2");
                if (a1 > a2) {
                    System.out.println("QEP Optimal : #QEP2");
                    q1.saveQEP(a2, "A2");
                }
                else{
                    System.out.println("QEP Optimal : #QEP1");
                    q1.saveQEP(a1, "A1 key");
                } 
            }
            else{
                //langsung A1 nonkey
                double b  = q1.getT2().getCb();
                q1.QEPshow(b,"A1 nonkey");
                q1.saveQEP(b, "A1 nonkey");
                //save
            }
        }
        else
            System.out.println("column not found !");
        
        
    }//close method
    
    public void manyTable(ArrayList<table> t , String x){
        String[] tm = x.split("=");
        int count_true = 0;
        if (kurang2(tm[0]).equals(kurang2(tm[1]))) { //ngecek (b.blabla = b.blabla) sama
            for (table z : t) {
                if (z.cekCoulmn(kurang2(tm[0]))) {
                    count_true++;
                }    
            }// mencek keberadaan key yang sama antar '=', kalau 2 benar
            
            QEP2 q = new QEP2();
            
            if (count_true == 2) {
                String[] parse = parseToken1(tokens[1]);
                for (table z : t) {
                    System.out.println("Table       : " + z.getName());
                    q.addTbl_name(z.getName()); //nambah nama2 tabel
                    q.addTbl(z);
                    System.out.println("Column List : ");
                        for (int i = 0; i < parse.length; i++) {
                            String g = String.valueOf(parse[i].charAt(1));
                            String h = String.valueOf(parse[i].charAt(0));
                            if (g.equals(".") && h.equals(z.getAlias())) {
                                System.out.println(kurang2(tm[0]));
                            }
                    }
                    for (int i = 0; i < parse.length; i++) {
                        if (z.cekCoulmn(parse[i])) {
                            System.out.println(parse[i]);
                        }
                    }//buat array untuk table name
                        
                }
                System.out.println("----------------");
                q.setProjection(tokens[1]);
                q.setJoin(x);
                // sambung sini many table
                q.qep1();
                System.out.println("");
                q.qep2();
                System.out.println("");
                q.saveQEPjoin(this.input);
                
            }//close if
        
            
        }
        else
            System.out.println("key not found");
        
        
    }//close method
    
    public String kurang2(String x){
        String y = "";
        for (int i = 2; i < x.length(); i++) {
            y += x.charAt(i);
        }
        return y;
    }
    

    
}
