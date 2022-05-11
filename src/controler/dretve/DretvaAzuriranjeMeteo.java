/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.dretve;

import view.jp.JpPrognoza;

/**
 *
 * @author iduras
 */
public class DretvaAzuriranjeMeteo extends Thread{
    
    private static DretvaAzuriranjeMeteo dretva = new DretvaAzuriranjeMeteo();
    
    private DretvaAzuriranjeMeteo() {
    }
            
    public static DretvaAzuriranjeMeteo getInstanca(){
        return dretva;
    }
    
    @Override
    public void interrupt() {
        System.out.println("Dretva je završila s radom.");
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
//        long brojSekundi = 0;
        super.run(); //To change body of generated methods, choose Tools | Templates.
        while (true) {  
            
            try {
                Thread.sleep(1000 * 60 * 30);
            } 

            catch (Exception e) {
//                Pocetna.getjTextFieldPoruka().setText("Greška u dretvi ažuriranja.");
//                MetodeTajmera.dodajULog("DretvaAzuriranjeMeteo - Greška u dretvi ažuriranja meteo");
            }
                JpPrognoza.azurirajObrazac();
        }
    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    public static DretvaAzuriranjeMeteo getDretva() {
        return dretva;
    }
}
