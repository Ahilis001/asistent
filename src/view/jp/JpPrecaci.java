/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import model.Postavke;
import model.PostavkePanela;
import model.Precac;

/**
 *
 * @author ahilis001
 */
public class JpPrecaci extends JPanel{
    
    static JpPrecaci jpPrecaci = new JpPrecaci();
    static PostavkePanela postavkePanela = new PostavkePanela();
    static JLabel jlTest = new JLabel("Test");
    static ArrayList<String> alListaNazivaAplikacija = new ArrayList<>();
    
    public JpPrecaci() {
        
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        
        catch(Exception e){}
    }
    
    public static JpPrecaci generirajObrazac(){
        
         //postavljanje okvira
        jpPrecaci.setBorder(javax.swing.BorderFactory.createTitledBorder(null, postavkePanela.getJtfNazivPanela().getText(),
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font(postavkePanela.getJtfNazivPanela().getText(), 0, postavkePanela.getIntVelicinaFontaNaslovaPanela()), 
                new java.awt.Color(postavkePanela.getIntBojaFontaNaslovaPanelaR(), postavkePanela.getIntBojaFontaNaslovaPanelaG(), postavkePanela.getIntBojaFontaNaslovaPanelaB())));
        
        jpPrecaci.setBackground(new java.awt.Color(postavkePanela.getIntBojaPozadinePanelaR(), postavkePanela.getIntBojaPozadinePanelaG(), postavkePanela.getIntBojaPozadinePanelaB()));
        
        
        alListaNazivaAplikacija.addAll(Arrays.asList(Postavke.dajPostavku("aplikacijeZaKojeRadi").split(",")));
        
        azurirajObrazac();
        
        return jpPrecaci;
    }
    
    
    /**
     * Azurira obrazac.
     */
    public static void azurirajObrazac() {
        
        jpPrecaci.removeAll();
        
        //postavljanje poravnanja i layouta jPanela
        jpPrecaci.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1;
        
        JPanel jpPomocni = new JPanel(new GridBagLayout());
        
        jpPomocni.setBackground(new java.awt.Color(postavkePanela.getIntBojaPozadinePanelaR(), postavkePanela.getIntBojaPozadinePanelaG(), postavkePanela.getIntBojaPozadinePanelaB()));
        
        
        c.gridx = 0;
        c.gridy = 0;
        
        for (Precac precac : Precac.getAlSviPrecaci()) {
            
            if (precac.getJcbPrikazi().isSelected()) {
                
                try {

                    JLabel jlTipka = new JLabel("<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">"+KeyEvent.getKeyText(Integer.parseInt(precac.getJtfKodTipke().getText()))+"</p></html>");
                    JLabel jlOpis = new JLabel("<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+"> - " + precac.getJtfOpis().getText()+"</p></html>");

                    jpPomocni.add(jlTipka, c);

                    c.gridx++;
                    jpPomocni.add(jlOpis, c);

                    c.gridy++;
                    c.gridx = 0;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        
        jlTest.setForeground(new java.awt.Color(JpPrecaci.getPostavkePanela().getIntBojaFontaPanelaR(), JpPrecaci.getPostavkePanela().getIntBojaFontaPanelaG(), JpPrecaci.getPostavkePanela().getIntBojaFontaPanelaB()));
        jpPomocni.add(jlTest, c);
        
        
        jpPrecaci.add(jpPomocni, c);
        jpPrecaci.revalidate();
    }

    public static PostavkePanela getPostavkePanela() {
        return postavkePanela;
    }

    public static void setPostavkePanela(PostavkePanela postavkePanela) {
        JpPrecaci.postavkePanela = postavkePanela;
    }

    public static JLabel getJlTest() {
        return jlTest;
    }

    public static void setJlTest(JLabel jlTest) {
        JpPrecaci.jlTest = jlTest;
    }

    public static ArrayList<String> getAlListaNazivaAplikacija() {
        return alListaNazivaAplikacija;
    }

    public static void setAlListaNazivaAplikacija(ArrayList<String> alListaNazivaAplikacija) {
        JpPrecaci.alListaNazivaAplikacija = alListaNazivaAplikacija;
    }
}
