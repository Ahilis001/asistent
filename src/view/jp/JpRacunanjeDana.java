/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jp;

import controler.MetodeRacunanjaDana;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;
import model.PostavkePanela;

/**
 *
 * @author Ahilis
 */
public class JpRacunanjeDana extends JPanel{
    

    static JpRacunanjeDana jpRacunanjeDana = new JpRacunanjeDana();
    static JLabel jlProtekloDana = new JLabel();
    static PostavkePanela postavkePanela = new PostavkePanela();
    
    public JpRacunanjeDana() {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        
        catch(Exception e){}
    }
    
    public static JpRacunanjeDana generirajObrazac(){
        
        jpRacunanjeDana.removeAll();
        
        jpRacunanjeDana.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        jpRacunanjeDana.setBorder(javax.swing.BorderFactory.createTitledBorder(null, postavkePanela.getJtfNazivPanela().getText(),
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font(postavkePanela.getJtfNazivPanela().getText(), 0, postavkePanela.getIntVelicinaFontaNaslovaPanela()), 
                new java.awt.Color(postavkePanela.getIntBojaFontaNaslovaPanelaR(), postavkePanela.getIntBojaFontaNaslovaPanelaG(), postavkePanela.getIntBojaFontaNaslovaPanelaB())));
        
        jpRacunanjeDana.setBackground(new java.awt.Color(postavkePanela.getIntBojaPozadinePanelaR(), postavkePanela.getIntBojaPozadinePanelaG(), postavkePanela.getIntBojaPozadinePanelaB()));
        
        jlProtekloDana.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));

        JPanel jpPomocni = new JPanel(new GridBagLayout());
        jpPomocni.setBackground(new java.awt.Color(postavkePanela.getIntBojaPozadinePanelaR(), postavkePanela.getIntBojaPozadinePanelaG(), postavkePanela.getIntBojaPozadinePanelaB()));
        
        //inicijalizacija jtf i oblika datuma
        //kao i placeholder izgleda: _ _._ _._ _ _ _.
        MaskFormatter mfIzgledDatuma = new MaskFormatter();
        String strKonacniIzgled = "";
        try {
            String[] straIzgledFormata = MetodeRacunanjaDana.getFormatter().toPattern().split("");
            for (String string : straIzgledFormata) {
                //ako nije "." ili ":", znak se pretvara u " #" i
                //sluzi kao placeholder
                if (!string.equals(".") && !string.equals(":") && !string.equals(" ")) {
                    
                    string = string.replace(string, " #");
                }
                strKonacniIzgled += string;
            }
            
            mfIzgledDatuma = new MaskFormatter(strKonacniIzgled);
            mfIzgledDatuma.setPlaceholderCharacter('_');
            
        } catch (ParseException ex) {
            System.out.println("nekaj");
            mfIzgledDatuma.setPlaceholder("Greška u formatu datuma.");
            
        }
        
        JFormattedTextField jtfDanZaIzracun = new JFormattedTextField(mfIzgledDatuma);
                
        //dodavanje key listenera za jtf koji "sluša" pritisak na enter i ostale tipke
        jtfDanZaIzracun.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent keyEvent) {
                int keyCode = keyEvent.getKeyCode();
                String keyText = KeyEvent.getKeyText(keyCode);
                
                //za "Enter"
                if(keyText.equals("Enter")){
                    
                    jlProtekloDana.setText(MetodeRacunanjaDana.dajBrojDana(jtfDanZaIzracun.getText().replace(" ", "")));
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        //bumb za računanje na pritisak na gumb
        JButton jbIzracunaj = new JButton();
        jbIzracunaj.setText("Izračunaj");
        jbIzracunaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //računanje dana
                jlProtekloDana.setText(MetodeRacunanjaDana.dajBrojDana(jtfDanZaIzracun.getText().replace(" ", "")));
            }
        });
        
        //dodavanje u jpanel
        c.gridx = 0;
        c.gridy = 0;
        jpPomocni.add(jtfDanZaIzracun, c);
        
        c.gridy++;
        jpPomocni.add(jbIzracunaj, c);
        
        
        
        c.gridx = 0;
        c.gridy = 0;
        jpRacunanjeDana.add(jpPomocni, c);
                
        c.gridy++;
        jpRacunanjeDana.add(jlProtekloDana, c);
        
        return jpRacunanjeDana;
    }
    
    public static PostavkePanela getPostavkePanela() {
        return postavkePanela;
    }

    public static void setPostavkePanela(PostavkePanela postavkePanela) {
        JpRacunanjeDana.postavkePanela = postavkePanela;
    }
    
}
