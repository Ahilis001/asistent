/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jf;

import controler.MetodeFrame;
import controler.MetodeKontakt;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import model.Kontakt;
import model.Postavke;
import view.jp.JpKontakt;

/**
 *
 * @author Ahilis
 */
public class JfKontaktUredi extends JFrame{

    public JfKontaktUredi() {
        
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        
        catch(Exception e){}
    }
    
    public static void generirajObrazac(){
        //inicijalizacija pocetnog jframea
        JfKontaktUredi jfKontaktUredi = new JfKontaktUredi();
        
        //postavljanje poravnanja i layouta jPanela
        jfKontaktUredi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
    
        JPanel jpKontakti = new JPanel(new GridBagLayout());
        azurirajObrazac(jpKontakti);
        
        //jpanel koji sadrži gumbove
        JPanel jpGumbovi = new JPanel(new GridBagLayout());
        
        //gumb za novi kotakt
        JButton jbNoviKontakt = new JButton();
        jbNoviKontakt.setText("Novi kontakt");
        jbNoviKontakt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //dodaje nove podsjetnike
                MetodeKontakt.dodajNoviKontakt();
                azurirajObrazac(jpKontakti);
                jfKontaktUredi.pack();
            }
        });
        
        //gumb za brisanje oznacenih
        JButton jbObrisiOznacene = new JButton();
        jbObrisiOznacene.setText("Obriši označene");
        jbObrisiOznacene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema precac u datoteku
                MetodeKontakt.obrisiKontakte();
                MetodeKontakt.spremiKontakte();
                azurirajObrazac(jpKontakti);
            }
        });
        
        //gumb za spremanje promjena
        JButton jbSpremiPodsjetnike = new JButton();
        jbSpremiPodsjetnike.setText("Spremi i zatvori");
        jbSpremiPodsjetnike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sprema podsjetnike u datoteku
                MetodeKontakt.spremiKontakte();
                jfKontaktUredi.dispose();
                JfPocetna.getJfPocetna().pack();
            }
        });
        
        jpGumbovi.add(jbNoviKontakt);
        jpGumbovi.add(jbObrisiOznacene);
        jpGumbovi.add(jbSpremiPodsjetnike);
        
        
        //dodavanje jpPodsjetnici u jfPodsjetnikUredi
        c.gridx = 0;
        c.gridy = 0;
        
        jfKontaktUredi.add(jpKontakti, c);
        
        //dodavanje jpGumbovi u jfPodsjetnikUredi
        c.gridx = 0;
        c.gridy = 1;
        
        jfKontaktUredi.add(jpGumbovi, c);
        
        MetodeFrame.postaviJf(jfKontaktUredi, Postavke.dajPostavku("vrstaObrubaOstalihFrameova"), Postavke.dajPostavku("datotekaKontakata"), false);
    
    }
    
    /**
     * azurira panel podsjetnika 
     * @param jpKontaktiUredi
     */
    public static void azurirajObrazac(JPanel jpKontaktiUredi){
    
        //brisanje sadrzaja jPanela
        jpKontaktiUredi.removeAll();
        
        jpKontaktiUredi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        
        c.gridx = 0;
        c.gridy = 0;
        
        //dodavanje uprave
        jpKontaktiUredi.add(new JLabel("Uprava"), c);
        c.gridx++;

        //dodavanje postaje
        jpKontaktiUredi.add(new JLabel("Postaja"), c);
        c.gridx++;

        //dodavanje imena ili naziva
        jpKontaktiUredi.add(new JLabel("Ime/naziv"), c);
        c.gridx++;

        //dodavanje kontakta
        jpKontaktiUredi.add(new JLabel("Kontakt"), c);
        c.gridx++;


        //dodavanje prikaza
        jpKontaktiUredi.add(new JLabel("Prikaži"), c);
        c.gridx++;


        //dodavanje kvačice
        jpKontaktiUredi.add(new JLabel("Obriši"), c);
        c.gridx = 0;
        c.gridy++;
        
        //popunjavanje jpPodsjetnici
        for (Kontakt kontaktPomocni : Kontakt.getAlSviKontakti()) {
            
            //dodavanje uprave
            jpKontaktiUredi.add(kontaktPomocni.getJtfUprava(), c);
            c.gridx++;
            
            //dodavanje postaje
            jpKontaktiUredi.add(kontaktPomocni.getJtfPostaja(), c);
            c.gridx++;
            
            //dodavanje naziva kontakta
            jpKontaktiUredi.add(kontaktPomocni.getJtfImeNaziv(), c);
            c.gridx++;
            
            //dodavanje broja
            jpKontaktiUredi.add(kontaktPomocni.getJtfBroj(), c);
            c.gridx++;
            
            //dodavanje kvačice
            jpKontaktiUredi.add(kontaktPomocni.getJcbPrikazi(), c);
            c.gridx++;
            
            //dodavanje kvačice
            jpKontaktiUredi.add(kontaktPomocni.getJcbObrisi(), c);
            c.gridx = 0;
            c.gridy++;
        }
        
        jpKontaktiUredi.add(new JLabel("Koordinate (X,Y,broj stupaca, broj redova)"), c);
        
        c.gridx++;
        jpKontaktiUredi.add(JpKontakt.getPostavkePanela().getJtfKoordinate(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpKontaktiUredi.add(new JLabel("Boja pozadine panela (R,G,B,A)"), c);
        
        c.gridx++;
        jpKontaktiUredi.add(JpKontakt.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpKontaktiUredi.add(new JLabel("Boja fonta naslova panela (R,G,B)"), c);
        
        c.gridx++;
        jpKontaktiUredi.add(JpKontakt.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpKontaktiUredi.add(new JLabel("Boja fonta panela (R,G,B)"), c);
        
        c.gridx++;
        jpKontaktiUredi.add(JpKontakt.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpKontaktiUredi.add(new JLabel("Velicina fonta naslova panela (cijeli broj)"), c);
        
        c.gridx++;
        jpKontaktiUredi.add(JpKontakt.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpKontaktiUredi.add(new JLabel("Velicina fonta teksta panela (cijeli broj)"), c);
        
        c.gridx++;
        jpKontaktiUredi.add(JpKontakt.getPostavkePanela().getJtfVelicinaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpKontaktiUredi.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpKontaktiUredi.add(JpKontakt.getPostavkePanela().getJtfVrstaFontaNaslovaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpKontaktiUredi.add(new JLabel("Vrsta fonta naslova panela"), c);
        
        c.gridx++;
        jpKontaktiUredi.add(JpKontakt.getPostavkePanela().getJtfVrstaFontaTekstaPanela(), c);
        c.gridy++;
        
        c.gridx = 0;
        jpKontaktiUredi.add(new JLabel("Naslov panela"), c);
        
        c.gridx++;
        jpKontaktiUredi.add(JpKontakt.getPostavkePanela().getJtfNazivPanela(), c);
        c.gridy++;
        
        jpKontaktiUredi.revalidate();
    }
}
