/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jf;

import controler.MetodeFrame;
import controler.MetodeRadio;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Postavke;
import model.Radio;
import view.jp.JpRadio;

/**
 *
 * @author Ahilis
 */
public class JfRadioUredi extends JFrame{

    public JfRadioUredi() {
    }
    
    public static void generirajObrazac(){
        
        //inicijalizacija pocetnog jframea
        JfRadioUredi jfRadioUredi = new JfRadioUredi();
        
        //postavljanje poravnanja i layouta jPanela
        jfRadioUredi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
    
        JPanel jpRadioUredi = new JPanel(new GridBagLayout());
        
        azurirajObrazac(jpRadioUredi);
        
        //jpanel koji sadrži gumbove
        JPanel jpGumbovi = new JPanel(new GridBagLayout());
        
        //gumb za novi podsjetnik
        JButton jbNovaPoveznica = new JButton();
        jbNovaPoveznica.setText("Novi radio");
        jbNovaPoveznica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //dodaje nove podsjetnike
                MetodeRadio.dodajNoviRadio();
                azurirajObrazac(jpRadioUredi);
                jfRadioUredi.pack();
            }
        });
        
        //gumb za brisanje oznacenih
        JButton jbObrisiOznacene = new JButton();
        jbObrisiOznacene.setText("Obriši označene");
        jbObrisiOznacene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema radio u datoteku
                MetodeRadio.obrisiRadio();
                MetodeRadio.spremiRadio();
                azurirajObrazac(jpRadioUredi);
            }
        });
        
        //gumb za spremanje promjena
        JButton jbSpremiPodsjetnike = new JButton();
        jbSpremiPodsjetnike.setText("Spremi i zatvori");
        jbSpremiPodsjetnike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema radio u datoteku
                MetodeRadio.spremiRadio();
                jfRadioUredi.dispose();
                azurirajObrazac(jpRadioUredi);
                JfPocetna.getJfPocetna().pack();
            }
        });
        
        jpGumbovi.add(jbNovaPoveznica);
        jpGumbovi.add(jbObrisiOznacene);
        jpGumbovi.add(jbSpremiPodsjetnike);
        
        //dodavanje jpRadio u jfRadioUredi
        c.gridx = 0;
        c.gridy = 0;
        
        jfRadioUredi.add(jpRadioUredi, c);
        
        //dodavanje jpGumbovi u jfRadioUredi
        c.gridx = 0;
        c.gridy = 1;
        
        jfRadioUredi.add(jpGumbovi, c);
        
        MetodeFrame.postaviJf(jfRadioUredi, Postavke.dajPostavku("vrstaObrubaOstalihFrameova"), Postavke.dajPostavku("datotekaRadija"), false);
    }
    
    /**
     * azurira panel podsjetnika 
     * @param jpRadioUredi
     */
    public static void azurirajObrazac(JPanel jpRadioUredi){
    
        //brisanje sadrzaja jPanela
        jpRadioUredi.removeAll();
        
        jpRadioUredi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
    
//        JPanel jpRadioUredi = new JPanel(new GridBagLayout());
        
        c.gridx = 0;
        c.gridy = 0;
        
        //dodavanje opisa
        jpRadioUredi.add(new JLabel("Naziv"), c);
        c.gridx++;

        //dodavanje pocetka
        jpRadioUredi.add(new JLabel("URL streama"), c);
        c.gridx++;

        //dodavanje prikaza
        jpRadioUredi.add(new JLabel("Prikaži"), c);
        c.gridx++;

        //dodavanje kvačice
        jpRadioUredi.add(new JLabel("Obriši"), c);
        c.gridx = 0;
        c.gridy++;
        
        //popunjavanje jpRadio
        for (Radio radio : Radio.getAlSviRadiji()) {
            
            //dodavanje opisa
            jpRadioUredi.add(radio.getJtfNaziv(), c);
            c.gridx++;
            
            //dodavanje pocetka
            jpRadioUredi.add(radio.getJtfURL(), c);
            c.gridx++;
            
            //dodavanje prikaza
            jpRadioUredi.add(radio.getJcbPrikazi(), c);
            c.gridx++;
            
            //dodavanje kvačice
            jpRadioUredi.add(radio.getJcbObrisi(), c);
            c.gridx = 0;
            c.gridy++;
        }
        
        jpRadioUredi.add(new JLabel("Koordinate (X,Y,broj stupaca, broj redova)"), c);
        
        c.gridx++;
        jpRadioUredi.add(JpRadio.getPostavkePanela().getJtfKoordinate(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpRadioUredi.add(new JLabel("Boja pozadine panela (R,G,B,A)"), c);
        
        c.gridx++;
        jpRadioUredi.add(JpRadio.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpRadioUredi.add(new JLabel("Boja fonta naslova panela (R,G,B)"), c);
        
        c.gridx++;
        jpRadioUredi.add(JpRadio.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpRadioUredi.add(new JLabel("Boja fonta panela (R,G,B)"), c);
        
        c.gridx++;
        jpRadioUredi.add(JpRadio.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpRadioUredi.add(new JLabel("Velicina fonta naslova panela (cijeli broj)"), c);
        
        c.gridx++;
        jpRadioUredi.add(JpRadio.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpRadioUredi.add(new JLabel("Velicina fonta teksta panela (cijeli broj)"), c);
        
        c.gridx++;
        jpRadioUredi.add(JpRadio.getPostavkePanela().getJtfVelicinaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpRadioUredi.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpRadioUredi.add(JpRadio.getPostavkePanela().getJtfVrstaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpRadioUredi.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpRadioUredi.add(JpRadio.getPostavkePanela().getJtfVrstaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpRadioUredi.add(new JLabel("Naslov panela"), c);
        
        c.gridx++;
        jpRadioUredi.add(JpRadio.getPostavkePanela().getJtfNazivPanela(), c);
        c.gridy++;
        
        jpRadioUredi.revalidate();
    }
}
