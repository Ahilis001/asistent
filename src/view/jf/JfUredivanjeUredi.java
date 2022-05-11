/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jf;

import controler.MetodeFrame;
import controler.MetodeUredivanje;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Postavke;
import view.jp.JpUredivanje;

/**
 *
 * @author Ahilis
 */
public class JfUredivanjeUredi extends JFrame{

    public JfUredivanjeUredi() {
    }
    
    public static void generirajObrazac() {
        
        //inicijalizacija pocetnog jframea
        JfUredivanjeUredi jfUredivanjeUredi = new JfUredivanjeUredi();
        
        //postavljanje poravnanja i layouta jPanela
        jfUredivanjeUredi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
    
        c.gridx = 0;
        c.gridy = 0;
        
        JPanel jpUredivanje = new JPanel(new GridBagLayout());
        
        jpUredivanje.add(new JLabel("Koordinate (X,Y,broj stupaca, broj redova)"), c);
        
        c.gridx++;
        jpUredivanje.add(JpUredivanje.getPostavkePanela().getJtfKoordinate(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpUredivanje.add(new JLabel("Boja pozadine panela (R,G,B,A)"), c);
        
        c.gridx++;
        jpUredivanje.add(JpUredivanje.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpUredivanje.add(new JLabel("Boja fonta naslova panela (R,G,B)"), c);
        
        c.gridx++;
        jpUredivanje.add(JpUredivanje.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpUredivanje.add(new JLabel("Boja fonta panela (R,G,B)"), c);
        
        c.gridx++;
        jpUredivanje.add(JpUredivanje.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpUredivanje.add(new JLabel("Velicina fonta naslova panela (cijeli broj)"), c);
        
        c.gridx++;
        jpUredivanje.add(JpUredivanje.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpUredivanje.add(new JLabel("Velicina fonta teksta panela (cijeli broj)"), c);
        
        c.gridx++;
        jpUredivanje.add(JpUredivanje.getPostavkePanela().getJtfVelicinaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpUredivanje.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpUredivanje.add(JpUredivanje.getPostavkePanela().getJtfVrstaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpUredivanje.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpUredivanje.add(JpUredivanje.getPostavkePanela().getJtfVrstaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpUredivanje.add(new JLabel("Naslov panela"), c);
        
        c.gridx++;
        jpUredivanje.add(JpUredivanje.getPostavkePanela().getJtfNazivPanela(), c);
        c.gridy++;
        
        //jpanel koji sadrži gumbove
        JPanel jpGumbovi = new JPanel(new GridBagLayout());
        
        //gumb za spremanje promjena
        JButton jbSpremiPrognozu = new JButton();
        jbSpremiPrognozu.setText("Spremi i zatvori");
        jbSpremiPrognozu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema postavke uredivanja
                MetodeUredivanje.spremiUredivanje();
                jfUredivanjeUredi.dispose();
                JfPocetna.getJfPocetna().pack();
            }
        });
        
        jpGumbovi.add(jbSpremiPrognozu);
        
        c.gridx = 0;
        c.gridy = 0;
        jfUredivanjeUredi.add(jpUredivanje, c);
        
        c.gridy++;
        jfUredivanjeUredi.add(jpGumbovi, c);
        
        MetodeFrame.postaviJf(jfUredivanjeUredi, Postavke.dajPostavku("vrstaObrubaOstalihFrameova"), Postavke.dajPostavku("datotekaUredivanja"), false);
    }
    
}
