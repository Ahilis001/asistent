/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.net.URI;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import model.PostavkePanela;
import model.Poveznica;

/**
 *
 * @author Ahilis
 */
public class JpPoveznice extends JPanel{
    
    static JpPoveznice jpPoveznice = new JpPoveznice();
    static PostavkePanela postavkePanela = new PostavkePanela();

    public JpPoveznice() {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        
        catch(Exception e){}
    }
    
    /**
     * generira obrazac podsjetnika
     * @return 
     */
    public static JpPoveznice generirajObrazac() {
        
        //postavljanje okvira
        jpPoveznice.setBorder(javax.swing.BorderFactory.createTitledBorder(null, postavkePanela.getJtfNazivPanela().getText(),
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font(postavkePanela.getJtfNazivPanela().getText(), 0, postavkePanela.getIntVelicinaFontaNaslovaPanela()), 
                new java.awt.Color(postavkePanela.getIntBojaFontaNaslovaPanelaR(), postavkePanela.getIntBojaFontaNaslovaPanelaG(), postavkePanela.getIntBojaFontaNaslovaPanelaB())));
        
        jpPoveznice.setBackground(new java.awt.Color(postavkePanela.getIntBojaPozadinePanelaR(), postavkePanela.getIntBojaPozadinePanelaG(), postavkePanela.getIntBojaPozadinePanelaB()));
        
        //azuriranje obrasca
        JpPoveznice.azurirajObrazac();
        
        return jpPoveznice;
    }
    
    /**
     * azurira obrazac podsjetnika
     */
    public static void azurirajObrazac(){
    
        //micanje svega iz jpPodsjetnika
        jpPoveznice.removeAll();
        
        //postavljanje layouta
        jpPoveznice.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1;
        
        c.gridy = 0;
        
        //dodavanje poveznica
        for (Poveznica poveznica : Poveznica.getAlSvePoveznice()) {
            
            if (poveznica.getJcbPrikazi().isSelected()) {
            
                c.gridx = 0;
                
                //dodavanje opisa poveznice
                JLabel jlURL = new JLabel(poveznica.getJtfOpis().getText());
                jlURL.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
        
                //postavljanje "rukice"
                jlURL.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                //dodavanje funkcionalnosti "na klik", "enter" i "exit"
                jlURL.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e){
                        try {
                            
                            Desktop.getDesktop().browse(new URI(poveznica.getJtfURL().getText()));
                        } catch (Exception ex) {
                            JpObavijestiIPostavke.getJtaObavijesti().setText("Greška kod otvaranja URL-a.");
                        }
                    }
                    
                    public void mouseEntered(MouseEvent e) {
                        
                        //on enter, promjena boje u plavo i podcrtano
                        Font font = jlURL.getFont();
                        Map attributes = font.getAttributes();
                        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                        jlURL.setFont(font.deriveFont(attributes));
                        jlURL.setForeground(Color.blue);
                    }
                    
                    public void mouseExited(MouseEvent e) {
                        
                        //on exit, promjena boje u crno i makivanje podcrtavanja
                        Font font = jlURL.getFont();
                        Map attributes = font.getAttributes();
                        attributes.put(TextAttribute.UNDERLINE, -1);
                        jlURL.setFont(font.deriveFont(attributes));
                        jlURL.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
                    }
                });
                
                jpPoveznice.add(jlURL, c);
                
                c.gridy++;
            }
        }
        jpPoveznice.revalidate();
    }

    public static PostavkePanela getPostavkePanela() {
        return postavkePanela;
    }

    public static void setPostavkePanela(PostavkePanela postavkePanela) {
        JpPoveznice.postavkePanela = postavkePanela;
    }
}
