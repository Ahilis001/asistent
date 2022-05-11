/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.dretve;

import controler.MetodeSat;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.util.Date;
import view.jp.JpPodsjetnici;
import view.jp.JpSat;

/**
 *
 * @author iduras
 */
public class DretvaSat extends Thread{
    
    private static DretvaSat dretva = new DretvaSat();
//    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
    
    private DretvaSat() {
    }
            
    public static DretvaSat getInstanca(){
        return dretva;
    }
    
    @Override
    public void interrupt() {
        System.out.println("DretvaSat - Dretva je završila s radom.");
//        Pocetna.getjTextFieldPoruka().setText("Greška u dretvi sata.");
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
//        pripremiSat
        int brojSekundi = 0;
        super.run(); //To change body of generated methods, choose Tools | Templates.
        try {
            
            //kreiranje Robota za upravljanje mišem
            Robot robot = new Robot();
            while (true) {
                try {
                    
                    //svaku minutu se "pomiče" miš
                    if(brojSekundi % 60 == 0){
                    
                        Point point = MouseInfo.getPointerInfo().getLocation();
                        robot.mouseMove(point.x, point.y);
                        brojSekundi = 0;
                    }

                    //ažuriranje sata
                    JpSat.getJlDatumIVrijeme().setText(MetodeSat.getFormatter().format(new Date(System.currentTimeMillis())));

                    Thread.sleep(1*1000);
                    brojSekundi++;
                    
                    //svaki sat azurira podsjetnike
                    if (brojSekundi % (60 * 60) == 0) {

                        JpPodsjetnici.azurirajObrazac(JpPodsjetnici.getIntAktivniBrojStranice());
                    }
                } 

                catch (Exception ex) {
                    interrupt();
                }
            }
        } 

        catch (Exception e) {
            interrupt();
        }
        
    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    public static DretvaSat getDretva() {
        return dretva;
    }
}
