/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jf;

import controler.MetodeFrame;
import controler.MetodeMeteo;
import controler.MetodePostavki;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Postavke;
import view.jp.JpPrognoza;

/**
 *
 * @author Ahilis
 */
public class JfPrognozaUredi extends JFrame{

    public JfPrognozaUredi() {
    }
    
    public static void generirajObrazac() {
        
        //inicijalizacija pocetnog jframea
        JfPrognozaUredi jfPrognozaUredi = new JfPrognozaUredi();
        
        //postavljanje poravnanja i layouta jPanela
        jfPrognozaUredi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
    
        c.gridx = 0;
        c.gridy = 0;
        
        JPanel jpPrognoza = new JPanel(new GridBagLayout());
        
        jpPrognoza.add(new JLabel("Mjesto,država (bez razmaka)"), c);
        
        c.gridx++;
        JTextField jtfMjestoDrzava = new JTextField(Postavke.dajPostavku("meteoGradDrzava"));
        jpPrognoza.add(jtfMjestoDrzava, c);
//        
        c.gridx = 0;
        c.gridy++;
        jpPrognoza.add(new JLabel("Koordinate (X,Y,broj stupaca, broj redova)"), c);
        
        c.gridx++;
        jpPrognoza.add(JpPrognoza.getPostavkePanela().getJtfKoordinate(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrognoza.add(new JLabel("Boja pozadine panela (R,G,B,A)"), c);
        
        c.gridx++;
        jpPrognoza.add(JpPrognoza.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrognoza.add(new JLabel("Boja fonta naslova panela (R,G,B)"), c);
        
        c.gridx++;
        jpPrognoza.add(JpPrognoza.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrognoza.add(new JLabel("Boja fonta panela (R,G,B)"), c);
        
        c.gridx++;
        jpPrognoza.add(JpPrognoza.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrognoza.add(new JLabel("Velicina fonta naslova panela (cijeli broj)"), c);
        
        c.gridx++;
        jpPrognoza.add(JpPrognoza.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrognoza.add(new JLabel("Velicina fonta teksta panela (cijeli broj)"), c);
        
        c.gridx++;
        jpPrognoza.add(JpPrognoza.getPostavkePanela().getJtfVelicinaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrognoza.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpPrognoza.add(JpPrognoza.getPostavkePanela().getJtfVrstaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrognoza.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpPrognoza.add(JpPrognoza.getPostavkePanela().getJtfVrstaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPrognoza.add(new JLabel("Naslov panela"), c);
        
        c.gridx++;
        jpPrognoza.add(JpPrognoza.getPostavkePanela().getJtfNazivPanela(), c);
        c.gridy++;
        
        
        //jpanel koji sadrži gumbove
        JPanel jpGumbovi = new JPanel(new GridBagLayout());
                
        //gumb za spremanje promjena
        JButton jbSpremiPrognozu = new JButton();
        jbSpremiPrognozu.setText("Spremi i zatvori");
        jbSpremiPrognozu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema postavke prognoze u datoteke
                MetodeMeteo.spremiPrognozu();
                MetodePostavki.postaviOdredenuPostavku("meteoGradDrzava", jtfMjestoDrzava.getText());
                jfPrognozaUredi.dispose();
                JfPocetna.getJfPocetna().pack();
            }
        });
        
        jpGumbovi.add(jbSpremiPrognozu);
        
        c.gridx = 0;
        c.gridy = 0;
        jfPrognozaUredi.add(jpPrognoza, c);
        
        c.gridy++;
        jfPrognozaUredi.add(jpGumbovi, c);
        
        MetodeFrame.postaviJf(jfPrognozaUredi, Postavke.dajPostavku("vrstaObrubaOstalihFrameova"), Postavke.dajPostavku("datotekaPrognoze"), false);
    }
    
}
