/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jf;

import controler.MetodeFrame;
import controler.MetodeKorisnihDatoteka;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Postavke;
import view.jp.JpKorisneDatoteke;

/**
 *
 * @author Ahilis
 */
public class JfKorisneDatotekeUredi extends JFrame{
    
    static JTextField jtfPoruka = new JTextField();
    static JTextField jtfBoja = new JTextField();

    public JfKorisneDatotekeUredi() {
    }
    
    public static void generirajObrazac(){
        
        //inicijalizacija pocetnog jframea
        JfKorisneDatotekeUredi jfKorisneDatotekeUredi = new JfKorisneDatotekeUredi();
        
        //postavljanje poravnanja i layouta jPanela
        jfKorisneDatotekeUredi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
    
        JPanel jpKorisneDatoteke = new JPanel(new GridBagLayout());
        
        azurirajObrazac(jpKorisneDatoteke);
        
        //jpanel koji sadrži gumbove
        JPanel jpGumbovi = new JPanel(new GridBagLayout());
                        
        //gumb za spremanje promjena
        JButton jbSpremiPromjene = new JButton();
        jbSpremiPromjene.setText("Spremi i zatvori");
        jbSpremiPromjene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema poveznice u datoteku
                MetodeKorisnihDatoteka.spremiKorisneDatoteke();
                jfKorisneDatotekeUredi.dispose();
                JfPocetna.getJfPocetna().pack();
            }
        });
        
        jpGumbovi.add(jbSpremiPromjene);
        
        
        //dodavanje jpPodsjetnici u jfPodsjetnikUredi
        c.gridx = 0;
        c.gridy = 0;
        
        jfKorisneDatotekeUredi.add(jpKorisneDatoteke, c);
        
        //dodavanje jpGumbovi u jfPodsjetnikUredi
        c.gridx = 0;
        c.gridy = 1;
        
        jfKorisneDatotekeUredi.add(jpGumbovi, c);
        
        MetodeFrame.postaviJf(jfKorisneDatotekeUredi, Postavke.dajPostavku("vrstaObrubaOstalihFrameova"), Postavke.dajPostavku("datotekaPoveznica"), false);
    }
    
    /**
     * azurira jpanel
     * @param jpPoveznice 
     */
    public static void azurirajObrazac(JPanel jpPoveznice){
    
        //brisanje sadrzaja jPanela
        jpPoveznice.removeAll();
        
        jpPoveznice.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        
        c.gridx = 0;
        c.gridy = 0;
        
        //dodavanje opisa
        jpPoveznice.add(new JLabel("Poruka"), c);
        c.gridx++;

        //dodavanje pocetka
        jtfPoruka.setText(JpKorisneDatoteke.getStrPoruka());
        jpPoveznice.add(jtfPoruka, c);
        c.gridx = 0;
        c.gridy++;
        
        //dodavanje opisa
        jpPoveznice.add(new JLabel("Boja"), c);
        c.gridx++;

        //dodavanje pocetka
        jtfBoja.setText(JpKorisneDatoteke.getStrBoja());
        jpPoveznice.add(jtfBoja, c);
        c.gridx = 0;
        c.gridy++;
        
        jpPoveznice.add(new JLabel("Koordinate (X,Y,broj stupaca, broj redova)"), c);
        
        c.gridx++;
        jpPoveznice.add(JpKorisneDatoteke.getPostavkePanela().getJtfKoordinate(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Boja pozadine panela (R,G,B,A)"), c);
        
        c.gridx++;
        jpPoveznice.add(JpKorisneDatoteke.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Boja fonta naslova panela (R,G,B)"), c);
        
        c.gridx++;
        jpPoveznice.add(JpKorisneDatoteke.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Boja fonta panela (R,G,B)"), c);
        
        c.gridx++;
        jpPoveznice.add(JpKorisneDatoteke.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Velicina fonta naslova panela (cijeli broj)"), c);
        
        c.gridx++;
        jpPoveznice.add(JpKorisneDatoteke.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Velicina fonta teksta panela (cijeli broj)"), c);
        
        c.gridx++;
        jpPoveznice.add(JpKorisneDatoteke.getPostavkePanela().getJtfVelicinaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpPoveznice.add(JpKorisneDatoteke.getPostavkePanela().getJtfVrstaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpPoveznice.add(JpKorisneDatoteke.getPostavkePanela().getJtfVrstaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Naslov panela"), c);
        
        c.gridx++;
        jpPoveznice.add(JpKorisneDatoteke.getPostavkePanela().getJtfNazivPanela(), c);
        c.gridy++;
        
        jpPoveznice.revalidate();
    }

    public static JTextField getJtfPoruka() {
        return jtfPoruka;
    }

    public static void setJtfPoruka(JTextField jtfPoruka) {
        JfKorisneDatotekeUredi.jtfPoruka = jtfPoruka;
    }

    public static JTextField getJtfBoja() {
        return jtfBoja;
    }

    public static void setJtfBoja(JTextField jtfBoja) {
        JfKorisneDatotekeUredi.jtfBoja = jtfBoja;
    }
}
