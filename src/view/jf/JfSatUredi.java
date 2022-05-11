/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jf;

import controler.MetodeFrame;
import controler.MetodeSat;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Postavke;
import view.jp.JpSat;

/**
 *
 * @author Ahilis
 */
public class JfSatUredi extends JFrame{

    public JfSatUredi() {
    }
    
    public static void generirajObrazac() {
        
        //inicijalizacija pocetnog jframea
        JfSatUredi jfSatUredi = new JfSatUredi();
        
        //postavljanje poravnanja i layouta jPanela
        jfSatUredi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
    
        c.gridx = 0;
        c.gridy = 0;
        
        JPanel jpSatUredi = new JPanel(new GridBagLayout());
        jpSatUredi.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Uređivanje sata", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        
        jpSatUredi.add(new JLabel("Format datuma i vremena"), c);
        
        c.gridx++;
        JTextField jtfFormatSata = new JTextField(MetodeSat.getFormatter().toPattern());
        jpSatUredi.add(jtfFormatSata, c);
//        
        c.gridx = 0;
        c.gridy++;
        jpSatUredi.add(new JLabel("Koordinate (X,Y,broj stupaca, broj redova)"), c);
        
        c.gridx++;
        jpSatUredi.add(JpSat.getPostavkePanela().getJtfKoordinate(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpSatUredi.add(new JLabel("Boja pozadine panela (R,G,B,A)"), c);
        
        c.gridx++;
        jpSatUredi.add(JpSat.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpSatUredi.add(new JLabel("Boja fonta naslova panela (R,G,B)"), c);
        
        c.gridx++;
        jpSatUredi.add(JpSat.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpSatUredi.add(new JLabel("Boja fonta panela (R,G,B)"), c);
        
        c.gridx++;
        jpSatUredi.add(JpSat.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpSatUredi.add(new JLabel("Velicina fonta naslova panela (cijeli broj)"), c);
        
        c.gridx++;
        jpSatUredi.add(JpSat.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpSatUredi.add(new JLabel("Velicina fonta teksta panela (cijeli broj)"), c);
        
        c.gridx++;
        jpSatUredi.add(JpSat.getPostavkePanela().getJtfVelicinaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpSatUredi.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpSatUredi.add(JpSat.getPostavkePanela().getJtfVrstaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpSatUredi.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpSatUredi.add(JpSat.getPostavkePanela().getJtfVrstaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpSatUredi.add(new JLabel("Naslov panela"), c);
        
        c.gridx++;
        jpSatUredi.add(JpSat.getPostavkePanela().getJtfNazivPanela(), c);
        c.gridy++;
        
        
        //jpanel koji sadrži gumbove
        JPanel jpGumbovi = new JPanel(new GridBagLayout());
        
        
        
        //gumb za spremanje promjena
        JButton jbSpremiSat = new JButton();
        jbSpremiSat.setText("Spremi i zatvori");
        jbSpremiSat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema postavke prognoze u datoteke
                MetodeSat.spremiSat(jtfFormatSata.getText());
                jfSatUredi.dispose();
                JfPocetna.getJfPocetna().pack();
            }
        });
        
        jpGumbovi.add(jbSpremiSat);
        
        c.gridx = 0;
        c.gridy = 0;
        jfSatUredi.add(jpSatUredi, c);
        
        c.gridy++;
        jfSatUredi.add(jpGumbovi, c);
        
        MetodeFrame.postaviJf(jfSatUredi, Postavke.dajPostavku("vrstaObrubaOstalihFrameova"), Postavke.dajPostavku("datotekaSata"), false);
    }
    
}
