/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jf;

import controler.MetodeMisc;
import controler.MetodeFrame;
import controler.MetodePrecac;
import controler.MetodeRadio;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import jdk.nashorn.internal.objects.NativeDebug;
import model.Postavke;
import static view.jf.JfPocetna.jfPocetna;
import view.jp.JpKontakt;
import view.jp.JpKorisneDatoteke;
import view.jp.JpPodsjetnici;
import view.jp.JpPoveznice;
import view.jp.JpPrecaci;
import view.jp.JpPrognoza;
import view.jp.JpRacunanjeDana;
import view.jp.JpRadio;
import view.jp.JpSat;
import view.jp.JpUredivanje;

/**
 *
 * @author ahilis001
 */
public class JfPocetna extends JFrame{
    
    static JfPocetna jfPocetna = new JfPocetna();
    static JPanel jpPocetna = new JPanel();
    
    public JfPocetna() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("resursi/appData/ikonaAplikacije.png"));
    }
    
    public static void generirajObrazac(){
        
        //postavljanje poravnanja i layouta jPanela
        jpPocetna.setLayout(new GridBagLayout());
        jpPocetna.setBackground(new Color(255,255,255,0));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        
        jfPocetna.add(jpPocetna);
        
        MetodeFrame.postaviJf(jfPocetna, Postavke.dajPostavku("vrstaObrubaPocetnogFramea"), Postavke.dajPostavku("nazivAplikacije"), true);
        
        //postavljanje ikone aplikacije
//        ImageIcon iiIkonaAplikacije = new ImageIcon("resursi/appData/ikonaAplikacije.png");
        
        azurirajObrazac();
    }
    
    public static void azurirajObrazac(){
        
        MetodeRadio.zaustaviSveRadije();
        
        MetodeMisc.azurirajSadrzaj();
        
        jpPocetna.removeAll();
        
        //postavljanje poravnanja i layouta jPanela
        jpPocetna.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        
        //dodavanje 
//        c.gridx = 0;
//        c.gridy = 0;
        
        JPanel jpSat = JpSat.generirajObrazac();
        
        c.gridx = JpSat.getPostavkePanela().getIntKoordinataX();
        c.gridy = JpSat.getPostavkePanela().getIntKoordinataY();
        c.gridwidth = JpSat.getPostavkePanela().getIntBrojStupacaPanela();
        c.gridheight = JpSat.getPostavkePanela().getIntBrojRedovaPanela();
        
        jpPocetna.add(jpSat, c);
        
        //dodavanje jpObavijestiIPostavki
//        if (Postavke.dajPostavku("prikaziObavijestiIPostavke").equals("1") || Postavke.dajPostavku("prikaziObavijestiIPostavke").equals("true")) {
//
//            c.gridx = 0;
//            c.gridy++;
//
//            jpPocetna.add(JpObavijestiIPostavke.generirajObrazac(), c);
//        }
        
        //dodavanje jpPrecaci
        if (Postavke.dajPostavku("prikaziPrecace").equals("1") || Postavke.dajPostavku("prikaziPrecace").equals("true")) {
            
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(JfPocetna.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JPanel jpPrecaci = JpPrecaci.generirajObrazac();
            
            c.gridx = JpPrecaci.getPostavkePanela().getIntKoordinataX();
            c.gridy = JpPrecaci.getPostavkePanela().getIntKoordinataY();
            c.gridwidth = JpPrecaci.getPostavkePanela().getIntBrojStupacaPanela();
            c.gridheight = JpPrecaci.getPostavkePanela().getIntBrojRedovaPanela();

            jpPocetna.add(jpPrecaci, c);
            
            MetodePrecac.registracijaTipki();
        }
        
        //dodavanje jpPodsjetnici
        if (Postavke.dajPostavku("prikaziPodsjetnike").equals("1") || Postavke.dajPostavku("prikaziPodsjetnike").equals("true")) {
            
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(JfPocetna.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            JPanel jpPodsjetnici = JpPodsjetnici.generirajObrazac();

            c.gridx = JpPodsjetnici.getPostavkePanela().getIntKoordinataX();
            c.gridy = JpPodsjetnici.getPostavkePanela().getIntKoordinataY();
            c.gridwidth = JpPodsjetnici.getPostavkePanela().getIntBrojStupacaPanela();
            c.gridheight = JpPodsjetnici.getPostavkePanela().getIntBrojRedovaPanela();

            jpPocetna.add(jpPodsjetnici, c);
        }
            
        //dodavanje jpPoveznice
        if (Postavke.dajPostavku("prikaziPoveznice").equals("1") || Postavke.dajPostavku("prikaziPoveznice").equals("true")) {
            
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(JfPocetna.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            JPanel jpPoveznice = JpPoveznice.generirajObrazac();

            c.gridx = JpPoveznice.getPostavkePanela().getIntKoordinataX();
            c.gridy = JpPoveznice.getPostavkePanela().getIntKoordinataY();
            c.gridwidth = JpPoveznice.getPostavkePanela().getIntBrojStupacaPanela();
            c.gridheight = JpPoveznice.getPostavkePanela().getIntBrojRedovaPanela();

            jpPocetna.add(jpPoveznice, c);
        }
            
        //dodavanje jpKorisneDatoteke
        if (Postavke.dajPostavku("prikaziKorisneDatoteke").equals("1") || Postavke.dajPostavku("prikaziKorisneDatoteke").equals("true")) {
            
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(JfPocetna.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            JPanel jpkorisneDatoteke = JpKorisneDatoteke.generirajObrazac();

            c.gridx = JpKorisneDatoteke.getPostavkePanela().getIntKoordinataX();
            c.gridy = JpKorisneDatoteke.getPostavkePanela().getIntKoordinataY();
            c.gridwidth = JpKorisneDatoteke.getPostavkePanela().getIntBrojStupacaPanela();
            c.gridheight = JpKorisneDatoteke.getPostavkePanela().getIntBrojRedovaPanela();

            jpPocetna.add(jpkorisneDatoteke, c);
        }
            
        //dodavanje jpRadio
        if (Postavke.dajPostavku("prikaziRadije").equals("1") || Postavke.dajPostavku("prikaziRadije").equals("true")) {
            
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(JfPocetna.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            JPanel jpRadio = JpRadio.generirajObrazac();

            c.gridx = JpRadio.getPostavkePanela().getIntKoordinataX();
            c.gridy = JpRadio.getPostavkePanela().getIntKoordinataY();
            c.gridwidth = JpRadio.getPostavkePanela().getIntBrojStupacaPanela();
            c.gridheight = JpRadio.getPostavkePanela().getIntBrojRedovaPanela();

            jpPocetna.add(jpRadio, c);
        }
            
        //dodavanje jpRadio
        if (Postavke.dajPostavku("prikaziPrognozu").equals("1") || Postavke.dajPostavku("prikaziPrognozu").equals("true")) {
            
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(JfPocetna.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            JPanel jpPrognoza = JpPrognoza.generirajObrazac();

            c.gridx = JpPrognoza.getPostavkePanela().getIntKoordinataX();
            c.gridy = JpPrognoza.getPostavkePanela().getIntKoordinataY();
            c.gridwidth = JpPrognoza.getPostavkePanela().getIntBrojStupacaPanela();
            c.gridheight = JpPrognoza.getPostavkePanela().getIntBrojRedovaPanela();
            
            jpPocetna.add(jpPrognoza, c);
        }

        //dodavanje jpKontakt
        if (Postavke.dajPostavku("prikaziKontakte").equals("1") || Postavke.dajPostavku("prikaziKontakte").equals("true")) {
            
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(JfPocetna.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            JPanel jpKontakt = JpKontakt.generirajObrazac();

            c.gridx = JpKontakt.getPostavkePanela().getIntKoordinataX();
            c.gridy = JpKontakt.getPostavkePanela().getIntKoordinataY();
            c.gridwidth = JpKontakt.getPostavkePanela().getIntBrojStupacaPanela();
            c.gridheight = JpKontakt.getPostavkePanela().getIntBrojRedovaPanela();

            jpPocetna.add(jpKontakt, c);
        }
        
        //dodavanje jpGumbovi
        if (Postavke.dajPostavku("prikaziUredivanje").equals("1") || Postavke.dajPostavku("prikaziUredivanje").equals("true")) {
            
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(JfPocetna.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            JPanel jpUredivanje = JpUredivanje.generirajObrazac();

            c.gridx = JpUredivanje.getPostavkePanela().getIntKoordinataX();
            c.gridy = JpUredivanje.getPostavkePanela().getIntKoordinataY();
            c.gridwidth = JpUredivanje.getPostavkePanela().getIntBrojStupacaPanela();
            c.gridheight = JpUredivanje.getPostavkePanela().getIntBrojRedovaPanela();

            jpPocetna.add(jpUredivanje, c);
        }
        
        //dodavanje jpRacunanjeDana
        if (Postavke.dajPostavku("prikaziRacunanjeDana").equals("1") || Postavke.dajPostavku("prikaziRacunanjeDana").equals("true")) {
            
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(JfPocetna.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            JPanel jpRacunanjeDana = JpRacunanjeDana.generirajObrazac();

            c.gridx = JpRacunanjeDana.getPostavkePanela().getIntKoordinataX();
            c.gridy = JpRacunanjeDana.getPostavkePanela().getIntKoordinataY();
            c.gridwidth = JpRacunanjeDana.getPostavkePanela().getIntBrojStupacaPanela();
            c.gridheight = JpRacunanjeDana.getPostavkePanela().getIntBrojRedovaPanela();

            jpPocetna.add(jpRacunanjeDana, c);
        }
        
        jfPocetna.revalidate();
        jfPocetna.pack();
    }

    public static JfPocetna getJfPocetna() {
        return jfPocetna;
    }

    public static void setJfPocetna(JfPocetna jfPocetna) {
        JfPocetna.jfPocetna = jfPocetna;
    }
}


            
        