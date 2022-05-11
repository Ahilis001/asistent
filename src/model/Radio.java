/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controler.MetodeRadio;
import controler.dretve.DretvaRadio;
import controler.dretve.DretvaZaPokretanjeRadija;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import view.jp.JpRadio;
import static view.jp.JpRadio.postavkePanela;

/**
 *
 * @author Ahilis
 */
public class Radio {
    
    JTextField jtfNaziv = new JTextField();
    JTextField jtfURL = new JTextField();
    JCheckBox jcbObrisi = new JCheckBox();
    JCheckBox jcbPrikazi = new JCheckBox();
    JButton jbSviraj = new JButton();   
    DretvaRadio drRadio;
    
    static ArrayList<Radio> alSviRadiji = new ArrayList<>();

    public Radio(String jtfNaziv, String strURL, String strPrikazi) {
        this.jtfNaziv.setText(jtfNaziv);
        this.jtfURL.setText(strURL);
        this.drRadio = new DretvaRadio(strURL);
        
        if (strPrikazi.equals("1") || strPrikazi.equals("true")) {
            this.jcbPrikazi.setSelected(true);
        } 
        
        else {
            this.jcbPrikazi.setSelected(false);
        }
        
        jbSviraj.setText("Sviraj");
        jbSviraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                MetodeRadio.zaustaviSveRadije();
                DretvaZaPokretanjeRadija dretvaZaPokretanjeRadija = new DretvaZaPokretanjeRadija();
                dretvaZaPokretanjeRadija.start(drRadio);
//                drRadio.play();

                JpRadio.getJlTrenutnoSvira().setText("<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">Trenutno svira: " + jtfNaziv + "</p></html>");
            }
        });
        
    }
    
    public Radio() {
    }

    public JTextField getJtfNaziv() {
        return jtfNaziv;
    }

    public void setJtfNaziv(JTextField jtfNaziv) {
        this.jtfNaziv = jtfNaziv;
    }

    public JTextField getJtfURL() {
        return jtfURL;
    }

    public void setJtfURL(JTextField jtfURL) {
        this.jtfURL = jtfURL;
    }

    public JCheckBox getJcbObrisi() {
        return jcbObrisi;
    }

    public void setJcbObrisi(JCheckBox jcbObrisi) {
        this.jcbObrisi = jcbObrisi;
    }

    public DretvaRadio getDrRadio() {
        return drRadio;
    }

    public void setDrRadio(DretvaRadio drRadio) {
        this.drRadio = drRadio;
    }
    
    public JButton getJbSviraj() {
        return jbSviraj;
    }

    public void setJbSviraj(JButton jbSviraj) {
        this.jbSviraj = jbSviraj;
    }

    public static ArrayList<Radio> getAlSviRadiji() {
        return alSviRadiji;
    }

    public static void setAlSviRadiji(ArrayList<Radio> alSviRadiji) {
        Radio.alSviRadiji = alSviRadiji;
    }
    
    public JCheckBox getJcbPrikazi() {
        return jcbPrikazi;
    }

    public void setJcbPrikazi(JCheckBox jcbPrikazi) {
        this.jcbPrikazi = jcbPrikazi;
    }
}
