/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jf;

import controler.MetodeFrame;
import controler.MetodePoveznice;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Postavke;
import model.Poveznica;
import view.jp.JpPoveznice;

/**
 *
 * @author Ahilis
 */
public class JfPoveznicaUredi extends JFrame{

    public JfPoveznicaUredi() {
    }
    
    public static void generirajObrazac(){
        //inicijalizacija pocetnog jframea
        JfPoveznicaUredi jfPoveznicaUredi = new JfPoveznicaUredi();
        
        //postavljanje poravnanja i layouta jPanela
        jfPoveznicaUredi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
    
        JPanel jpPoveznice = new JPanel(new GridBagLayout());
        
        azurirajObrazac(jpPoveznice);
        
        //jpanel koji sadrži gumbove
        JPanel jpGumbovi = new JPanel(new GridBagLayout());
        
        //gumb za novu poveznicu
        JButton jbNovaPoveznica = new JButton();
        jbNovaPoveznica.setText("Nova poveznica");
        jbNovaPoveznica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //dodaje nove poveznice
                MetodePoveznice.dodajNovuPoveznicu();
                azurirajObrazac(jpPoveznice);
                jfPoveznicaUredi.pack();
            }
        });
        
        //gumb za brisanje oznacenih
        JButton jbObrisiOznacene = new JButton();
        jbObrisiOznacene.setText("Obriši označene");
        jbObrisiOznacene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema poveznice u datoteku
                MetodePoveznice.obrisiPoveznice();
                MetodePoveznice.spremiPoveznice();
                azurirajObrazac(jpPoveznice);
            }
        });
        
        //gumb za spremanje promjena
        JButton jbSpremiPodsjetnike = new JButton();
        jbSpremiPodsjetnike.setText("Spremi i zatvori");
        jbSpremiPodsjetnike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema poveznice u datoteku
                MetodePoveznice.spremiPoveznice();
                jfPoveznicaUredi.dispose();
                JfPocetna.getJfPocetna().pack();
            }
        });
        
        jpGumbovi.add(jbNovaPoveznica);
        jpGumbovi.add(jbObrisiOznacene);
        jpGumbovi.add(jbSpremiPodsjetnike);
        
        
        //dodavanje jpPodsjetnici u jfPodsjetnikUredi
        c.gridx = 0;
        c.gridy = 0;
        
        jfPoveznicaUredi.add(jpPoveznice, c);
        
        //dodavanje jpGumbovi u jfPodsjetnikUredi
        c.gridx = 0;
        c.gridy = 1;
        
        jfPoveznicaUredi.add(jpGumbovi, c);
        
        MetodeFrame.postaviJf(jfPoveznicaUredi, Postavke.dajPostavku("vrstaObrubaOstalihFrameova"), Postavke.dajPostavku("datotekaPoveznica"), false);
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
        jpPoveznice.add(new JLabel("Opis"), c);
        c.gridx++;

        //dodavanje pocetka
        jpPoveznice.add(new JLabel("URL"), c);
        c.gridx++;

        //dodavanje prikaza
        jpPoveznice.add(new JLabel("Prikaži"), c);
        c.gridx++;

        //dodavanje kvačice
        jpPoveznice.add(new JLabel("Obriši"), c);
        c.gridx = 0;
        c.gridy++;
        
        //popunjavanje jpPodsjetnici
        for (Poveznica poveznica : Poveznica.getAlSvePoveznice()) {
            
            //dodavanje opisa
            jpPoveznice.add(poveznica.getJtfOpis(), c);
            c.gridx++;
            
            //dodavanje pocetka
            jpPoveznice.add(poveznica.getJtfURL(), c);
            c.gridx++;
            
            //dodavanje prikaza
            jpPoveznice.add(poveznica.getJcbPrikazi(), c);
            c.gridx++;
            
            //dodavanje kvačice
            jpPoveznice.add(poveznica.getJcbObrisi(), c);
            c.gridx = 0;
            c.gridy++;
        }
        
        jpPoveznice.add(new JLabel("Koordinate (X,Y,broj stupaca, broj redova)"), c);
        
        c.gridx++;
        jpPoveznice.add(JpPoveznice.getPostavkePanela().getJtfKoordinate(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Boja pozadine panela (R,G,B,A)"), c);
        
        c.gridx++;
        jpPoveznice.add(JpPoveznice.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Boja fonta naslova panela (R,G,B)"), c);
        
        c.gridx++;
        jpPoveznice.add(JpPoveznice.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Boja fonta panela (R,G,B)"), c);
        
        c.gridx++;
        jpPoveznice.add(JpPoveznice.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Velicina fonta naslova panela (cijeli broj)"), c);
        
        c.gridx++;
        jpPoveznice.add(JpPoveznice.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Velicina fonta teksta panela (cijeli broj)"), c);
        
        c.gridx++;
        jpPoveznice.add(JpPoveznice.getPostavkePanela().getJtfVelicinaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpPoveznice.add(JpPoveznice.getPostavkePanela().getJtfVrstaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpPoveznice.add(JpPoveznice.getPostavkePanela().getJtfVrstaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpPoveznice.add(new JLabel("Naslov panela"), c);
        
        c.gridx++;
        jpPoveznice.add(JpPoveznice.getPostavkePanela().getJtfNazivPanela(), c);
        c.gridy++;
        
        jpPoveznice.revalidate();
    }
}
