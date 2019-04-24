/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author ariq
 */
public class Tubes_sbd {

    /**
     * @param args the command line arguments
     */
    public static void exeQuery(){
        Scanner s1 = new Scanner(System.in);
        System.out.print("Input Query : ");
        String g = s1.nextLine();
        
        System.out.println();
        
        query q1 = new query(g);
        if (!q1.cekLength() || !q1.cekFrom() || !q1.cekSelect() || !q1.cekCoulmn()) {
            System.out.println("Syntax Error");
        }
        else if (!q1.cekSemicolon()){
            System.out.println("Missing ;");
        }
        else
            q1.cekTable();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
//select t.idtiket,m.idtiket,judul from tiket t join memesan m ON (t.idtiket=m.idtiket);
        System.out.println("MENU UTAMA : ");
        System.out.println("---------------------------");
        System.out.println("1. Tampilkan BFR dan Fan Out Ratio setiap tabel");
        System.out.println("");
        System.out.println("2. Tampilkan Total Blok Data + Blok Index Setiap Tabel");
        System.out.println("");
        System.out.println("3. Tampilkan Jumlah Blok yang diakses untuk pencarian Rekord");
        System.out.println("");
        System.out.println("4. Tampilkan QEP dan Cost");
        System.out.println("");
        System.out.println("5. Tampilkan Isi File Shared Pool");
        Scanner s1 = new Scanner(System.in);
        System.out.print("Pilih menu : ");
        String g = s1.nextLine();    
        values v = new values();
        
        switch(g){
            case "1": v.menu1();
            break;
            case "2": v.menu2();
            break;
            case "3": v.menu3();
            break;
            case "4": exeQuery();
            break;
//            case "5": menu5();
//            break;
            
            
            
        }

    }
    
    
  
}
