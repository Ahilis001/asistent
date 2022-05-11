/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jp;

import controler.MetodeKontakt;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import model.Kontakt;
import model.PostavkePanela;

/**
 *
 * @author Ahilis
 */
public class JpKontakt extends JPanel{
    
    static JpKontakt jpKontakti = new JpKontakt();
    static PostavkePanela postavkePanela = new PostavkePanela();

    public JpKontakt() {
        
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        
        catch(Exception e){}
    }
    
    
    
    /**
     * generira obrazac kontakta
     * @return 
     */
    public static JpKontakt generirajObrazac() {
        
        //postavljanje okvira
        jpKontakti.setBorder(javax.swing.BorderFactory.createTitledBorder(null, postavkePanela.getJtfNazivPanela().getText(),
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font(postavkePanela.getJtfNazivPanela().getText(), 0, postavkePanela.getIntVelicinaFontaNaslovaPanela()), 
                new java.awt.Color(postavkePanela.getIntBojaFontaNaslovaPanelaR(), postavkePanela.getIntBojaFontaNaslovaPanelaG(), postavkePanela.getIntBojaFontaNaslovaPanelaB())));
        
        jpKontakti.setBackground(new java.awt.Color(postavkePanela.getIntBojaPozadinePanelaR(), postavkePanela.getIntBojaPozadinePanelaG(), postavkePanela.getIntBojaPozadinePanelaB()));
        
        //azuriranje obrasca
        JpKontakt.azurirajObrazac();
        
        return jpKontakti;
    }
    
    /**
     * azurira obrazac kontakta
     */
    public static void azurirajObrazac(){
        
        //micanje svega iz jpKontakti
        jpKontakti.removeAll();
        
        //postavljanje layouta
        jpKontakti.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1;
        
        //dodavanje opisa stupaca
        c.gridx = 0;
        c.gridy = 0;
        jpKontakti.add(kreirajZaglavlje(), c);

        c.gridy++;
        
        //dodavanje kontakta
        for (Kontakt kontakt : Kontakt.getAlSviKontakti()) {
            
            if (kontakt.getJcbPrikazi().isSelected()) {
                
                c.gridx = 0;
                c.ipadx = 10;
                jpKontakti.add(new JLabel("<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">"+kontakt.getJtfUprava().getText()+"</p></html>"), c);

                c.gridx++;
                jpKontakti.add(new JLabel("<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">"+kontakt.getJtfPostaja().getText()+"</p></html>"), c);

                c.gridx++;
                jpKontakti.add(new JLabel("<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">"+kontakt.getJtfImeNaziv().getText()+"</p></html>"), c);

                c.gridx++;
                c.ipadx = 0;
                jpKontakti.add(new JLabel("<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">"+kontakt.getJtfBroj().getText()+"</p></html>"), c);

                c.gridy++;

            }
        }
        jpKontakti.repaint();
        jpKontakti.revalidate();
    }
    
    /**
     * vraća zaglavlje s funkcijom sortiranja
     * @return 
     */
    static JPanel kreirajZaglavlje(){
    
        JPanel jpZaglavlje = new JPanel();
        
        jpZaglavlje.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1;
        
        //dodavanje opisa stupaca
        c.gridx = 0;
        c.gridy = 0;
        
        c.ipadx = 10;
        JLabel jlUprava = new JLabel("Uprava");
        
        jlUprava.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
        jpKontakti.add(jlUprava, c);
        
        //dodavanje funkcionalnosti "na klik", "enter" i "exit"
        jlUprava.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                MetodeKontakt.sortirajPoUpravi();
                azurirajObrazac();

            }

            public void mouseEntered(MouseEvent e) {

                //on enter, promjena boje u plavo i podcrtano
                Font font = jlUprava.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                jlUprava.setFont(font.deriveFont(attributes));
                jlUprava.setForeground(Color.blue);
            }

            public void mouseExited(MouseEvent e) {

                //on exit, promjena boje u crno i makivanje podcrtavanja
                Font font = jlUprava.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, -1);
                jlUprava.setFont(font.deriveFont(attributes));
                jlUprava.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
            }
        });
               
                
        c.gridx++;
        JLabel jlPostaja = new JLabel("Postaja");
        jlPostaja.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
        jpKontakti.add(jlPostaja, c);
        
        jlPostaja.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                MetodeKontakt.sortirajPoPostaji();
                azurirajObrazac();
            }

            public void mouseEntered(MouseEvent e) {

                //on enter, promjena boje u plavo i podcrtano
                Font font = jlPostaja.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                jlPostaja.setFont(font.deriveFont(attributes));
                jlPostaja.setForeground(Color.blue);
            }

            public void mouseExited(MouseEvent e) {

                //on exit, promjena boje u crno i makivanje podcrtavanja
                Font font = jlPostaja.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, -1);
                jlPostaja.setFont(font.deriveFont(attributes));
                jlPostaja.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
            }
        });

        c.gridx++;
        c.ipadx = 0;
        JLabel jlKontakt = new JLabel("Kontakt");
        jlKontakt.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
        jpKontakti.add(jlKontakt, c);
        
        jlKontakt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                MetodeKontakt.sortirajPoImenuNazivu();
                azurirajObrazac();
            }

            public void mouseEntered(MouseEvent e) {

                //on enter, promjena boje u plavo i podcrtano
                Font font = jlKontakt.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                jlKontakt.setFont(font.deriveFont(attributes));
                jlKontakt.setForeground(Color.blue);
            }

            public void mouseExited(MouseEvent e) {

                //on exit, promjena boje u crno i makivanje podcrtavanja
                Font font = jlKontakt.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, -1);
                jlKontakt.setFont(font.deriveFont(attributes));
                jlKontakt.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
            }
        });

        c.gridx++;
        c.ipadx = 0;
        JLabel jlBroj = new JLabel("Broj");
        jlBroj.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
        jpKontakti.add(jlBroj, c);
        
        jlBroj.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                MetodeKontakt.sortirajPoBroju();
                azurirajObrazac();

            }

            public void mouseEntered(MouseEvent e) {

                //on enter, promjena boje u plavo i podcrtano
                Font font = jlBroj.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                jlBroj.setFont(font.deriveFont(attributes));
                jlBroj.setForeground(Color.blue);
            }

            public void mouseExited(MouseEvent e) {

                //on exit, promjena boje u crno i makivanje podcrtavanja
                Font font = jlBroj.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, -1);
                jlBroj.setFont(font.deriveFont(attributes));
                jlBroj.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
            }
        });
        
        return jpZaglavlje;
    }

    public static PostavkePanela getPostavkePanela() {
        return postavkePanela;
    }

    public static void setPostavkePanela(PostavkePanela postavkePanela) {
        JpKontakt.postavkePanela = postavkePanela;
    }
}
