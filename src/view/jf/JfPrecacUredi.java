/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jf;

import controler.MetodeFrame;
import controler.MetodePrecac;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Postavke;
import model.Precac;
import view.jp.JpPrecaci;

/**
 *
 * @author Ahilis
 */
public class JfPrecacUredi extends JFrame{

    public JfPrecacUredi() {
    }
    
    public static void generirajObrazac(){
        //inicijalizacija pocetnog jframea
        JfPrecacUredi jfPrecacUredi = new JfPrecacUredi();
        
        //postavljanje poravnanja i layouta jPanela
        jfPrecacUredi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
    
        JPanel jpPrecaci = new JPanel(new GridBagLayout());
        
        azurirajObrazac(jpPrecaci);
        
        //jpanel koji sadrži gumbove
        JPanel jpGumbovi = new JPanel(new GridBagLayout());
        
        //gumb za novi precac
        JButton jbNoviPrecac = new JButton();
        jbNoviPrecac.setText("Novi prečac");
        jbNoviPrecac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //dodaje nove precace
                MetodePrecac.dodajNoviPrecac();
                azurirajObrazac(jpPrecaci);
                jfPrecacUredi.pack();
            }
        });
        
        //gumb za brisanje oznacenih
        JButton jbObrisiOznacene = new JButton();
        jbObrisiOznacene.setText("Obriši označene");
        jbObrisiOznacene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema precac u datoteku
                MetodePrecac.obrisiPrecace();
                MetodePrecac.spremiPrecace();
                azurirajObrazac(jpPrecaci);
            }
        });
        
        //gumb za spremanje promjena
        JButton jbSpremiPrecace = new JButton();
        jbSpremiPrecace.setText("Spremi i zatvori");
        jbSpremiPrecace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema precac u datoteku
                MetodePrecac.spremiPrecace();
                jfPrecacUredi.dispose();
                JfPocetna.getJfPocetna().pack();
            }
        });
        
        jpGumbovi.add(jbNoviPrecac);
        jpGumbovi.add(jbObrisiOznacene);
        jpGumbovi.add(jbSpremiPrecace);
        
        //dodavanje jfPrecacUredi u jfPrecacUredi
        c.gridx = 0;
        c.gridy = 0;
        
        jfPrecacUredi.add(jpPrecaci, c);
        
        //dodavanje jpGumbovi u jfPrecacUredi
        c.gridx = 0;
        c.gridy = 1;
        
        jfPrecacUredi.add(jpGumbovi, c);
        
        MetodeFrame.postaviJf(jfPrecacUredi, Postavke.dajPostavku("vrstaObrubaOstalihFrameova"), Postavke.dajPostavku("datotekaPrecaca"), false);
    
    }
    
    /**
     * azurira jpanel precaca
     * @param jpPrecaci 
     */
    public static void azurirajObrazac(JPanel jpPrecaci){
        
        jpPrecaci.removeAll();
        
        //postavljanje poravnanja i layouta jPanela
        jpPrecaci.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        
        c.gridx = 0;
        c.gridy = 0;
        
        //dodavanje opisa
        jpPrecaci.add(new JLabel("Tipka"), c);
        c.gridx++;

        //dodavanje kod tipke prečaca
        jpPrecaci.add(new JLabel("Kod tipke"), c);
        c.gridx++;

        //dodavanje izvršavanja
        jpPrecaci.add(new JLabel("Kod tipke / izraz / kombinacija tipki"), c);
        c.gridx++;

        //dodavanje prikaza
        jpPrecaci.add(new JLabel("Omogući"), c);
        c.gridx++;

        //dodavanje prikaza
        jpPrecaci.add(new JLabel("Sve app"), c);
        c.gridx++;

        //dodavanje kvačice
        jpPrecaci.add(new JLabel("Obriši"), c);
        c.gridx = 0;
        c.gridy++;
        
        //popunjavanje jpPrecaca
        for (Precac precac : Precac.getAlSviPrecaci()) {
//            PrecacPomocni precacPomocni = new PrecacPomocni(precac.getStrOpis(), precac.getStrKodTipke(), precac.getAlStrKodTipki());
            
            //dodavanje opisa
            jpPrecaci.add(precac.getJtfOpis(), c);
            c.gridx++;
            
            //dodavanje dodavanje kod tipke prečaca
            jpPrecaci.add(precac.getJtfKodTipke(), c);
            c.gridx++;
            
            //dodavanje  izvršavanja
            jpPrecaci.add(precac.getJtfKodTipki(), c);
            c.gridx++;
            
            //dodavanje prikaza
            jpPrecaci.add(precac.getJcbPrikazi(), c);
            c.gridx++;
            
            //dodavanje prikaza
            jpPrecaci.add(precac.getJcbSveAplikacije(), c);
            c.gridx++;
            
            //dodavanje kvačice
            jpPrecaci.add(precac.getJcbObrisi(), c);
            c.gridx = 0;
            c.gridy++;
        }
        
        jpPrecaci.add(new JLabel("Koordinate (X,Y,broj stupaca, broj redova)"), c);
        
        c.gridx++;
        jpPrecaci.add(JpPrecaci.getPostavkePanela().getJtfKoordinate(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrecaci.add(new JLabel("Boja pozadine panela (R,G,B,A)"), c);
        
        c.gridx++;
        jpPrecaci.add(JpPrecaci.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrecaci.add(new JLabel("Boja fonta naslova panela (R,G,B)"), c);
        
        c.gridx++;
        jpPrecaci.add(JpPrecaci.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrecaci.add(new JLabel("Boja fonta panela (R,G,B)"), c);
        
        c.gridx++;
        jpPrecaci.add(JpPrecaci.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrecaci.add(new JLabel("Velicina fonta naslova panela (cijeli broj)"), c);
        
        c.gridx++;
        jpPrecaci.add(JpPrecaci.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrecaci.add(new JLabel("Velicina fonta teksta panela (cijeli broj)"), c);
        
        c.gridx++;
        jpPrecaci.add(JpPrecaci.getPostavkePanela().getJtfVelicinaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrecaci.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpPrecaci.add(JpPrecaci.getPostavkePanela().getJtfVrstaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrecaci.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpPrecaci.add(JpPrecaci.getPostavkePanela().getJtfVrstaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrecaci.add(new JLabel("Naslov panela"), c);
        
        c.gridx++;
        jpPrecaci.add(JpPrecaci.getPostavkePanela().getJtfNazivPanela(), c);
        c.gridy++;
        
        jpPrecaci.revalidate();
    }
}
