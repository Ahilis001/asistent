/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author Ahilis
 */
public class Kontakt{
    
    JTextField jtfUprava = new JTextField();
    JTextField jtfPostaja = new JTextField();
    JTextField jtfImeNaziv = new JTextField();
    JTextField jtfBroj = new JTextField();
    JCheckBox jcbObrisi = new JCheckBox();
    JCheckBox jcbPrikazi = new JCheckBox();
    
    static ArrayList<Kontakt> alSviKontakti = new ArrayList<>();

    public Kontakt(String strUprava, String strPostaja, String strImeNaziv, String strBroj, String strPrikazi) {
        this.jtfUprava.setText(strUprava);
        this.jtfPostaja.setText(strPostaja);
        this.jtfImeNaziv.setText(strImeNaziv);
        this.jtfBroj.setText(strBroj);
        this.jcbObrisi.setSelected(false);
        this.jcbPrikazi.setSelected(Boolean.valueOf(strPrikazi));        
    }
    
    public JTextField getJtfUprava() {
        return jtfUprava;
    }

    public void setJtfUprava(JTextField jtfUprava) {
        this.jtfUprava = jtfUprava;
    }

    public JTextField getJtfPostaja() {
        return jtfPostaja;
    }

    public void setJtfPostaja(JTextField jtfPostaja) {
        this.jtfPostaja = jtfPostaja;
    }

    public JTextField getJtfImeNaziv() {
        return jtfImeNaziv;
    }

    public void setJtfImeNaziv(JTextField jtfImeNaziv) {
        this.jtfImeNaziv = jtfImeNaziv;
    }
    
    public JTextField getJtfBroj() {
        return jtfBroj;
    }

    public void setJtfBroj(JTextField jtfBroj) {
        this.jtfBroj = jtfBroj;
    }

    public JCheckBox getJcbObrisi() {
        return jcbObrisi;
    }

    public void setJcbObrisi(JCheckBox jcbObrisi) {
        this.jcbObrisi = jcbObrisi;
    }

    public static ArrayList<Kontakt> getAlSviKontakti() {
        return alSviKontakti;
    }

    public static void setAlSviKontakti(ArrayList<Kontakt> alSviKontakti) {
        Kontakt.alSviKontakti = alSviKontakti;
    }

    public JCheckBox getJcbPrikazi() {
        return jcbPrikazi;
    }

    public void setJcbPrikazi(JCheckBox jcbPrikazi) {
        this.jcbPrikazi = jcbPrikazi;
    }
}
