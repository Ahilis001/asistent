/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author ahilis001
 */
public class Precac {
    
    JTextField jtfOpis = new JTextField();
    JTextField jtfKodTipke = new JTextField();
    JTextField jtfKodTipki = new JTextField();
    JCheckBox jcbObrisi = new JCheckBox();
    JCheckBox jcbPrikazi = new JCheckBox();
    JCheckBox jcbSveAplikacije = new JCheckBox();
    ArrayList<String> alStrKodTipki = new ArrayList<>();
    
    static ArrayList<Precac> alSviPrecaci = new ArrayList<>();

    public Precac(String strKodTipke, String strKodTipki, String strOpis, String strPrikazi, String strSveAplikacije) {
        this.jtfKodTipke.setText(strKodTipke);
        this.jtfKodTipki.setText(strKodTipki);
        this.jtfOpis.setText(strOpis);
        this.alStrKodTipki = pretvoriStringUListu(strKodTipki);
        
        if (strPrikazi.equals("1") || strPrikazi.equals("true")) {
            this.jcbPrikazi.setSelected(true);
        } 
        
        else {
            this.jcbPrikazi.setSelected(false);
        }
        
        if (strSveAplikacije.equals("1") || strSveAplikacije.equals("true")) {
            this.jcbSveAplikacije.setSelected(true);
        } 
        
        else {
            this.jcbSveAplikacije.setSelected(false);
        }
    }

    public JTextField getJtfKodTipke() {
        return jtfKodTipke;
    }

    public void setJtfKodTipke(JTextField jtfKodTipke) {
        this.jtfKodTipke = jtfKodTipke;
    }

    public JTextField getJtfKodTipki() {
        return jtfKodTipki;
    }

    public void setJtfKodTipki(JTextField jtfKodTipki) {
        this.jtfKodTipki = jtfKodTipki;
    }
    
    public ArrayList<String> getAlStrKodTipki() {
        return alStrKodTipki;
    }

    public void setAlStrKodTipki(ArrayList<String> alStrKodTipki) {
        this.alStrKodTipki = alStrKodTipki;
    }

    public static ArrayList<Precac> getAlSviPrecaci() {
        return alSviPrecaci;
    }

    public static void setAlSviPrecaci(ArrayList<Precac> alSviPrecaci) {
        Precac.alSviPrecaci = alSviPrecaci;
    }

    public JTextField getJtfOpis() {
        return jtfOpis;
    }

    public void setJtfOpis(JTextField jtfOpis) {
        this.jtfOpis = jtfOpis;
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

    public JCheckBox getJcbSveAplikacije() {
        return jcbSveAplikacije;
    }

    public void setJcbSveAplikacije(JCheckBox jcbSveAplikacije) {
        this.jcbSveAplikacije = jcbSveAplikacije;
    }
    
    /**
     * pretvaranje stringa u al
     * @param string
     * @return 
     */
    ArrayList<String> pretvoriStringUListu(String string){
        ArrayList<String> alListaStringova = new ArrayList<>();
        alListaStringova.addAll(Arrays.asList(string.split(";")));
    
        return alListaStringova;
    }
    
    
    String dajKodoveIzListe(ArrayList<String> alStrKodTipki){
        String strKodovi = "";
    
        for (String string : alStrKodTipki) {
            strKodovi += string + ";";
        }
        
        return strKodovi.substring(0, strKodovi.length() - 1);
    }
    
    /**
     * sortiranje PrecacPomocnih po kodu tipke
     */
    public static void sortiranjeListe() {

        Collections.sort(alSviPrecaci, new Comparator() {

            public int compare(Object o1, Object o2) {

                String x1 = ((Precac) o1).getJtfKodTipke().getText();
                String x2 = ((Precac) o2).getJtfKodTipke().getText();
                int sComp = x1.compareTo(x2);

                return sComp;
        }});
    }
}
