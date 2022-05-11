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
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
import model.Postavke;
import model.PostavkePanela;

/**
 *
 * @author Ahilis
 */
public class JpKorisneDatoteke extends JPanel{
    
    static JpKorisneDatoteke jpKorisneDatoteke = new JpKorisneDatoteke();
    static PostavkePanela postavkePanela = new PostavkePanela();
    static String strPoruka = "", strBoja = "";

    public JpKorisneDatoteke() {
    }
    
    /**
     * generira obrazac
     * @return 
     */
    public static JpKorisneDatoteke generirajObrazac() {
        
        //postavljanje okvira
        jpKorisneDatoteke.setBorder(javax.swing.BorderFactory.createTitledBorder(null, postavkePanela.getJtfNazivPanela().getText(),
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font(postavkePanela.getJtfNazivPanela().getText(), 0, postavkePanela.getIntVelicinaFontaNaslovaPanela()), 
                new java.awt.Color(postavkePanela.getIntBojaFontaNaslovaPanelaR(), postavkePanela.getIntBojaFontaNaslovaPanelaG(), postavkePanela.getIntBojaFontaNaslovaPanelaB())));
        
        jpKorisneDatoteke.setBackground(new java.awt.Color(postavkePanela.getIntBojaPozadinePanelaR(), postavkePanela.getIntBojaPozadinePanelaG(), postavkePanela.getIntBojaPozadinePanelaB()));
        
        //azuriranje obrasca
        JpKorisneDatoteke.azurirajObrazac();
        
        return jpKorisneDatoteke;
    }
    
    /**
     * azurira obrazac
     */
    public static void azurirajObrazac(){
        
        //brisanje sadržaja
        jpKorisneDatoteke.removeAll();
        
        //postavljanje poravnanja, layouta i naslova jPanela
        jpKorisneDatoteke.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1;
//        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 10;
        
        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        File f = new File(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("mapaKorisnihDatoteka"));
        
        c.gridx = 0;
        c.gridy = 0;
        
        JPanel jpPomocniPanelDatoteka = new JPanel();
        jpPomocniPanelDatoteka.setLayout(new GridBagLayout());
        jpPomocniPanelDatoteka.setBackground(new java.awt.Color(postavkePanela.getIntBojaPozadinePanelaR(), postavkePanela.getIntBojaPozadinePanelaG(), postavkePanela.getIntBojaPozadinePanelaB()));
        
        //sortiranje po ekstenziji
        File[] poljeDatoteka = f.listFiles();
        Arrays.sort(poljeDatoteka, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().substring(o1.getName().lastIndexOf(".")).compareTo(o2.getName().substring(o2.getName().lastIndexOf(".")));
            }
        });
        
        //za svaku datoteku iz liste
        for (File datoteka : poljeDatoteka) {
            
            String strNazivDatoteke = datoteka.getName();
            //novi jLabel za datoteku
            JLabel jlDatoteka = new JLabel(strNazivDatoteke.substring(0, datoteka.getName().lastIndexOf(".")));
            jlDatoteka.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));

            //dodavanje funkcionalnosti "na klik", "enter" i "exit"
            jlDatoteka.addMouseListener(new MouseAdapter() {

                //otvaranje pdf datoteke
                public void mouseClicked(MouseEvent e){
                    try {
                        Desktop.getDesktop().open(new File(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("mapaKorisnihDatoteka") + "/" + strNazivDatoteke));
                    } catch (Exception ex) {
                        JpObavijestiIPostavke.getJtaObavijesti().setText("Greška kod otvaranja datoteke.");
                    }
                }

                public void mouseEntered(MouseEvent e) {

                    //on enter, promjena boje u plavo i podcrtano
                    Font font = jlDatoteka.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    jlDatoteka.setFont(font.deriveFont(attributes));
                    jlDatoteka.setForeground(Color.blue);
                }

                public void mouseExited(MouseEvent e) {

                    //on exit, promjena boje u crno i makivanje podcrtavanja
                    Font font = jlDatoteka.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, -1);
                    jlDatoteka.setFont(font.deriveFont(attributes));
                    jlDatoteka.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
                }
            });

//            c.gridx++;

            //postavljanje ikone za vrstu datoteke
            jpPomocniPanelDatoteka.add(new JLabel(FileSystemView.getFileSystemView().getSystemIcon(new File(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("mapaKorisnihDatoteka") + "/" + strNazivDatoteke))), c);

            c.gridx++;

            //postavljanje "rukice"
            jlDatoteka.setCursor(new Cursor(Cursor.HAND_CURSOR));

            jpPomocniPanelDatoteka.add(jlDatoteka,c);

            c.gridx = 0;
            c.gridy++;
        }
        
        c.gridx = 0;
        c.gridy = 0;
        
        jpKorisneDatoteke.add(new JLabel("<html><p style=color:" + strBoja + ">" + strPoruka + "</p></html>"), c);
        
        c.gridx = 0;
        c.gridy = 1;
        jpKorisneDatoteke.add(jpPomocniPanelDatoteka,c);
    }

    public static PostavkePanela getPostavkePanela() {
        return postavkePanela;
    }

    public static void setPostavkePanela(PostavkePanela postavkePanela) {
        JpPodsjetnici.postavkePanela = postavkePanela;
    }

    public static String getStrPoruka() {
        return strPoruka;
    }

    public static void setStrPoruka(String strPoruka) {
        JpKorisneDatoteke.strPoruka = strPoruka;
    }

    public static String getStrBoja() {
        return strBoja;
    }

    public static void setStrBoja(String strBoja) {
        JpKorisneDatoteke.strBoja = strBoja;
    }
}
