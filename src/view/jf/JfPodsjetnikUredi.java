/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jf;

import controler.MetodeFrame;
import controler.MetodePodsjetnik;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Podsjetnik;
import model.Postavke;
import view.jp.JpPodsjetnici;

/**
 *
 * @author Ahilis
 */
public class JfPodsjetnikUredi extends JFrame{

    public JfPodsjetnikUredi() {
    }
    
    public static void generirajObrazac(){
        //inicijalizacija pocetnog jframea
        JfPodsjetnikUredi jfPodsjetnikUredi = new JfPodsjetnikUredi();
        
        //postavljanje poravnanja i layouta jPanela
        jfPodsjetnikUredi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
    
        JPanel jpPodsjetnici = new JPanel(new GridBagLayout());
        
        azurirajObrazac(jpPodsjetnici);
        
        //jpanel koji sadrži gumbove
        JPanel jpGumbovi = new JPanel(new GridBagLayout());
        
        //gumb za novi podsjetnik
        JButton jbNoviPodsjetnik = new JButton();
        jbNoviPodsjetnik.setText("Novi podsjetnik");
        jbNoviPodsjetnik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //dodaje nove podsjetnike
                MetodePodsjetnik.dodajNoviPodsjetnik();
                azurirajObrazac(jpPodsjetnici);
                jfPodsjetnikUredi.pack();
            }
        });
        
        //gumb za brisanje oznacenih
        JButton jbObrisiOznacene = new JButton();
        jbObrisiOznacene.setText("Obriši označene");
        jbObrisiOznacene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema precac u datoteku
                MetodePodsjetnik.obrisiPodsjetnike();
                MetodePodsjetnik.spremiPodsjetnike();
                azurirajObrazac(jpPodsjetnici);
            }
        });
        
        //gumb za spremanje promjena
        JButton jbSpremiPodsjetnike = new JButton();
        jbSpremiPodsjetnike.setText("Spremi i zatvori");
        jbSpremiPodsjetnike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema podsjetnike u datoteku
                MetodePodsjetnik.spremiPodsjetnike();
                jfPodsjetnikUredi.dispose();
                JfPocetna.getJfPocetna().pack();
            }
        });
        
        jpGumbovi.add(jbNoviPodsjetnik);
        jpGumbovi.add(jbObrisiOznacene);
        jpGumbovi.add(jbSpremiPodsjetnike);
        
        
        //dodavanje jpPodsjetnici u jfPodsjetnikUredi
        c.gridx = 0;
        c.gridy = 0;
        
        jfPodsjetnikUredi.add(jpPodsjetnici, c);
        
        //dodavanje jpGumbovi u jfPodsjetnikUredi
        c.gridx = 0;
        c.gridy = 1;
        
        jfPodsjetnikUredi.add(jpGumbovi, c);
        
        MetodeFrame.postaviJf(jfPodsjetnikUredi, Postavke.dajPostavku("vrstaObrubaOstalihFrameova"), Postavke.dajPostavku("datotekaPodsjetnika"), false);
    
    }
    
    /**
     * azurira panel podsjetnika
     * @param jpPodsjetnici 
     */
    public static void azurirajObrazac(JPanel jpPodsjetnici){
    
        //brisanje sadrzaja jPanela
        jpPodsjetnici.removeAll();
        
        jpPodsjetnici.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        
        
        c.gridx = 0;
        c.gridy = 0;
        
        //popunjavanje jpPodsjetnici
        for (Podsjetnik podsjetnikPomocni : Podsjetnik.getAlSviPodsjetnici()) {
            
            //dodavanje opisa
            jpPodsjetnici.add(podsjetnikPomocni.getJtfOpis(), c);
            c.gridx++;
            
            //dodavanje pocetka
            jpPodsjetnici.add(podsjetnikPomocni.getJtfPocetak(), c);
            c.gridx++;
            
            //dodavanje kraja
            jpPodsjetnici.add(podsjetnikPomocni.getJtfKraj(), c);
            c.gridx++;
            
            //dodavanje kvačice
            jpPodsjetnici.add(podsjetnikPomocni.getJcbObrisi(), c);
            c.gridx = 0;
            c.gridy++;
            
        }
        
        jpPodsjetnici.add(new JLabel("Koordinate (X,Y,broj stupaca, broj redova)"), c);
        
        c.gridx++;
        jpPodsjetnici.add(JpPodsjetnici.getPostavkePanela().getJtfKoordinate(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPodsjetnici.add(new JLabel("Boja pozadine panela (R,G,B,A)"), c);
        
        c.gridx++;
        jpPodsjetnici.add(JpPodsjetnici.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPodsjetnici.add(new JLabel("Boja fonta naslova panela (R,G,B)"), c);
        
        c.gridx++;
        jpPodsjetnici.add(JpPodsjetnici.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPodsjetnici.add(new JLabel("Boja fonta panela (R,G,B)"), c);
        
        c.gridx++;
        jpPodsjetnici.add(JpPodsjetnici.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPodsjetnici.add(new JLabel("Velicina fonta naslova panela (cijeli broj)"), c);
        
        c.gridx++;
        jpPodsjetnici.add(JpPodsjetnici.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPodsjetnici.add(new JLabel("Velicina fonta teksta panela (cijeli broj)"), c);
        
        c.gridx++;
        jpPodsjetnici.add(JpPodsjetnici.getPostavkePanela().getJtfVelicinaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPodsjetnici.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpPodsjetnici.add(JpPodsjetnici.getPostavkePanela().getJtfVrstaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPodsjetnici.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpPodsjetnici.add(JpPodsjetnici.getPostavkePanela().getJtfVrstaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPodsjetnici.add(new JLabel("Naslov panela"), c);
        
        c.gridx++;
        jpPodsjetnici.add(JpPodsjetnici.getPostavkePanela().getJtfNazivPanela(), c);
        c.gridy++;
        
        jpPodsjetnici.revalidate();
    }
}
