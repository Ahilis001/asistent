/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jf;

import controler.MetodeFrame;
import controler.MetodeRacunanjaDana;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Postavke;
import view.jp.JpRacunanjeDana;

/**
 *
 * @author Ahilis
 */
public class JfRacunanjeDanaUredi extends JFrame{

    public JfRacunanjeDanaUredi() {
    }
    
    public static void generirajObrazac() {
        
        //inicijalizacija pocetnog jframea
        JfRacunanjeDanaUredi jfRacunanjeDanaUredi = new JfRacunanjeDanaUredi();
        
        //postavljanje poravnanja i layouta jPanela
        jfRacunanjeDanaUredi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
    
        c.gridx = 0;
        c.gridy = 0;
        
        JPanel jpRacunanjeDanaUredi = new JPanel(new GridBagLayout());
        jpRacunanjeDanaUredi.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Uređivanje računanja dana", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        
        jpRacunanjeDanaUredi.add(new JLabel("Format datuma i vremena"), c);
        
        c.gridx++;
        JTextField jtfFormatDana = new JTextField(MetodeRacunanjaDana.getFormatter().toPattern());
        jpRacunanjeDanaUredi.add(jtfFormatDana, c);
        
        c.gridx = 0;
        c.gridy++;
        jpRacunanjeDanaUredi.add(new JLabel("Koordinate (X,Y,broj stupaca, broj redova)"), c);
        
        c.gridx++;
        jpRacunanjeDanaUredi.add(JpRacunanjeDana.getPostavkePanela().getJtfKoordinate(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpRacunanjeDanaUredi.add(new JLabel("Boja pozadine panela (R,G,B,A)"), c);
        
        c.gridx++;
        jpRacunanjeDanaUredi.add(JpRacunanjeDana.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela(), c);
        c.gridy++;
        
        
        c.gridx = 0;
        jpRacunanjeDanaUredi.add(new JLabel("Boja fonta naslova panela (R,G,B)"), c);
        
        c.gridx++;
        jpRacunanjeDanaUredi.add(JpRacunanjeDana.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela(), c);
        c.gridy++;
        
        
        c.gridx = 0;
        jpRacunanjeDanaUredi.add(new JLabel("Boja fonta panela (R,G,B)"), c);
        
        c.gridx++;
        jpRacunanjeDanaUredi.add(JpRacunanjeDana.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela(), c);
        c.gridy++;
        
        
        c.gridx = 0;
        jpRacunanjeDanaUredi.add(new JLabel("Velicina fonta naslova panela (cijeli broj)"), c);
        
        c.gridx++;
        jpRacunanjeDanaUredi.add(JpRacunanjeDana.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela(), c);
        c.gridy++;
        
        
        c.gridx = 0;
        jpRacunanjeDanaUredi.add(new JLabel("Velicina fonta teksta panela (cijeli broj)"), c);
        
        c.gridx++;
        jpRacunanjeDanaUredi.add(JpRacunanjeDana.getPostavkePanela().getJtfVelicinaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpRacunanjeDanaUredi.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpRacunanjeDanaUredi.add(JpRacunanjeDana.getPostavkePanela().getJtfVrstaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpRacunanjeDanaUredi.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpRacunanjeDanaUredi.add(JpRacunanjeDana.getPostavkePanela().getJtfVrstaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpRacunanjeDanaUredi.add(new JLabel("Naslov panela"), c);
        
        c.gridx++;
        jpRacunanjeDanaUredi.add(JpRacunanjeDana.getPostavkePanela().getJtfNazivPanela(), c);
        c.gridy++;
        
        
        //jpanel koji sadrži gumbove
        JPanel jpGumbovi = new JPanel(new GridBagLayout());
        
        
        //todo promijeniti
        //gumb za spremanje promjena
        JButton jbSpremiDan = new JButton();
        jbSpremiDan.setText("Spremi i zatvori");
        jbSpremiDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema postavke prognoze u datoteke
                MetodeRacunanjaDana.spremiDan(jtfFormatDana.getText());
                jfRacunanjeDanaUredi.dispose();
                JfPocetna.getJfPocetna().pack();
            }
        });
        
        jpGumbovi.add(jbSpremiDan);
        
        c.gridx = 0;
        c.gridy = 0;
        jfRacunanjeDanaUredi.add(jpRacunanjeDanaUredi, c);
        
        c.gridy++;
        jfRacunanjeDanaUredi.add(jpGumbovi, c);
        
        MetodeFrame.postaviJf(jfRacunanjeDanaUredi, Postavke.dajPostavku("vrstaObrubaOstalihFrameova"), Postavke.dajPostavku("datotekaRacunanjaDana"), false);
    }
    
}
