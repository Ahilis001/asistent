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
 * @author ahilis001
 */
public class Poveznica{
    
    JTextField jtfOpis = new JTextField();
    JTextField jtfURL = new JTextField();
    JCheckBox jcbObrisi = new JCheckBox();
    JCheckBox jcbPrikazi = new JCheckBox();
    
    static ArrayList<Poveznica> alSvePoveznice = new ArrayList<>();

    public Poveznica(String strOpis, String strURL, String strPrikazi) {
        this.jtfOpis.setText(strOpis);
        this.jtfURL.setText(strURL);
        this.jcbObrisi.setSelected(false);
        
        if (strPrikazi.equals("1") || strPrikazi.equals("true")) {
            this.jcbPrikazi.setSelected(true);
        } 
        
        else {
            this.jcbPrikazi.setSelected(false);
        }
    }
    
    public JTextField getJtfOpis() {
        return jtfOpis;
    }

    public void setJtfOpis(JTextField jtfOpis) {
        this.jtfOpis = jtfOpis;
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

    public JCheckBox getJcbPrikazi() {
        return jcbPrikazi;
    }

    public void setJcbPrikazi(JCheckBox jcbPrikazi) {
        this.jcbPrikazi = jcbPrikazi;
    }

    public static ArrayList<Poveznica> getAlSvePoveznice() {
        return alSvePoveznice;
    }

    public static void setAlSvePoveznice(ArrayList<Poveznica> alSvePoveznice) {
        Poveznica.alSvePoveznice = alSvePoveznice;
    }
}
