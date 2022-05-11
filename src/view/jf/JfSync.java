/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jf;

import controler.Datoteke;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import model.Postavke;

/**
 *
 * @author ahilis001
 */
public class JfSync extends JFrame{
    
    static JLabel jlObavijestDatoteke = new JLabel("");
    static JLabel jlObavijestMape = new JLabel("");
    

    public JfSync() {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        
        catch(Exception e){}
    }
    
    /**
     * generira obrazac
     */
    public static void generirajObrazac(){
    
        JfSync jfSync = new JfSync();
        getJlObavijestDatoteke().setText("");
        getJlObavijestMape().setText("");
        
        //jpanel za login
        JPanel jpLogin = new JPanel();
        jpLogin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Login", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));

        //postavljanje poravnanja i layouta jPanela
        jpLogin.setLayout(new GridBagLayout());
        jfSync.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        
        //dodavanje jlabela i jtexfielda
        c.gridx = 0;
        c.gridy = 0;
        
        jpLogin.add(new JLabel("Username: "), c);
        
        JTextField jtfUsr = new JTextField();
//        JTextField jtfUsr = new JTextField("ahilisaplikacije");
        jtfUsr.setColumns(10);
        
        c.gridx = 1;
        c.gridy = 0;
        jpLogin.add(jtfUsr, c);

        c.gridx = 0;
        c.gridy = 1;
        jpLogin.add(new JLabel("Password"), c);
        
        JTextField jtfPass = new JPasswordField();
//        JTextField jtfPass = new JPasswordField("Siliha001");
        
        c.gridx = 1;
        c.gridy = 1;
        jpLogin.add(jtfPass, c);
        
        //jpanel za popis datoteka koje se sinkroniziraju
        JPanel jpDatotekeZaSync = new JPanel();
        jpDatotekeZaSync.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datoteke za sinkronizaciju", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        
        //za svaku postavku koja kao ključ sadrži dat, odn. koja predstavlja datoteku
        for (Postavke postavke : Postavke.getAlListaPostavki()) {
            if (postavke.getJtbKey().getText().contains("dat")) {
                getJlObavijestDatoteke().setText(getJlObavijestDatoteke().getText() + postavke.getJtbValue().getText() + "<br>");
            }
        }
        
        //postavljanje boje i popisa u jlabel
        getJlObavijestDatoteke().setText("<html><p style=color:black>" + getJlObavijestDatoteke().getText() + "</html>");
        
        jpDatotekeZaSync.add(getJlObavijestDatoteke());
        
        //jpanel za popis datoteka koje se sinkroniziraju
        JPanel jpMapeZaSync = new JPanel();
        jpMapeZaSync.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mape za sinkronizaciju", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        
        //za svaku postavku koja kao ključ sadrži map, odn. koja predstavlja datoteku
        for (Postavke postavke : Postavke.getAlListaPostavki()) {
            if (postavke.getJtbKey().getText().contains("map") && !postavke.getJtbKey().getText().contains("Resurs")) {
                getJlObavijestMape().setText(getJlObavijestMape().getText() + postavke.getJtbValue().getText() + "<br>");
            }
        }
        
        //postavljanje boje i popisa u jlabel
        getJlObavijestMape().setText("<html><p style=color:black>" + getJlObavijestMape().getText() + "</html>");
        
        jpMapeZaSync.add(getJlObavijestMape());
        
        //panel za gumbove
        JPanel jpGumbovi = new JPanel();
        
        //jbutton za zatvaranje
        JButton jbZatvori = new JButton();
        jbZatvori.setText("Zatvori");
        jbZatvori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                //zatvaranje jsync i azuriranje pocetnog obrasca
                jfSync.dispose();
                JfPocetna.azurirajObrazac();
            }
        });
        
        //jbutton za upload datoteka
        JButton jbUp = new JButton();
        jbUp.setText("Upload");
        jbUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
//                String strPoruka = "";
                
                //ako usr i pass nisu prazni
                if (!jtfUsr.getText().isEmpty() && !jtfPass.getText().isEmpty()) {
                    
                    getJlObavijestDatoteke().setText("");
                    getJlObavijestMape().setText("");
                    
                    for (Postavke postavke : Postavke.getAlListaPostavki()) {
                        
                        //upload datoteka iz postavki i azuriranje poruke
                        if (postavke.getJtbKey().getText().contains("dat")) {
                            getJlObavijestDatoteke().setText(getJlObavijestDatoteke().getText() + Datoteke.uploadDatoteke(
                                jtfUsr.getText(), 
                                jtfPass.getText(), 
                                Postavke.dajPostavku("mapaResursa"),
                                postavke.getJtbValue().getText(),
                                Postavke.dajPostavku("nazivAplikacije") + "/"
                            ));
                        }
                    }
                    
                    for (Postavke postavke : Postavke.getAlListaPostavki()) {
                        
                        //upload datoteka iz postavki i azuriranje poruke
                        if (postavke.getJtbKey().getText().contains("map") && !postavke.getJtbKey().getText().contains("Resurs")) {
                            
                            // Creates a new File instance by converting the given pathname string
                            // into an abstract pathname
                            File f = new File(Postavke.dajPostavku("mapaResursa") + postavke.getJtbValue().getText());
                            
                            for (String strNazivDatoteke : f.list()) {
                                
                                getJlObavijestMape().setText(getJlObavijestMape().getText() + Datoteke.uploadDatoteke(
                                    jtfUsr.getText(), 
                                    jtfPass.getText(), 
                                    Postavke.dajPostavku("mapaResursa") + postavke.getJtbValue().getText(),
                                    strNazivDatoteke,
                                    Postavke.dajPostavku("nazivAplikacije") + "/" + postavke.getJtbValue().getText()
                                ));
                            }
                        }
                    }
                    
                    jpDatotekeZaSync.revalidate();
                    jpMapeZaSync.revalidate();
                    jfSync.revalidate();
                }
                
                //ako je jedan od jtf prazni
                else{
                    getJlObavijestDatoteke().setText("<html><p style=color:red>Popuni user i pass!</p></html>");
                    getJlObavijestMape().setText("<html><p style=color:red>Popuni user i pass!</p></html>");
                }
                
                getJlObavijestDatoteke().setText("<html>" + getJlObavijestDatoteke().getText() + "</html>");
                getJlObavijestMape().setText("<html>" + getJlObavijestMape().getText() + "</html>");
                jpDatotekeZaSync.revalidate();
                jpMapeZaSync.revalidate();
                jfSync.revalidate();
            }
        });
        
        //jbutton za download datoteka
        JButton jbDown = new JButton();
        jbDown.setText("Download");
        jbDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                //prikaz poruke uspjesnosti downloada
                getJlObavijestDatoteke().setText("<html>" + Datoteke.downloadSvihDatoteka() + "</html>");
                if (Postavke.dajPostavku("sinkronizacijaIkona").equals("true")) {
                   getJlObavijestMape().setText("<html>" +
                                                Datoteke.downloadSvihDatotekaMapeSURLa(Postavke.dajPostavku("URLStraniceResursa") + Postavke.dajPostavku("nazivAplikacije") + "/" + Postavke.dajPostavku("mapaIkona"), Postavke.dajPostavku("mapaResursa")+Postavke.dajPostavku("mapaIkona")) + "</br>" +
                                                Datoteke.downloadSvihDatotekaMapeSURLa(Postavke.dajPostavku("URLStraniceResursa") + Postavke.dajPostavku("nazivAplikacije") + "/" + Postavke.dajPostavku("mapaKorisnihDatoteka"), Postavke.dajPostavku("mapaResursa")+Postavke.dajPostavku("mapaKorisnihDatoteka") +
                                             "</html>"));
                }
                
                else{
                    getJlObavijestMape().setText("<html>" +
                                                Datoteke.downloadSvihDatotekaMapeSURLa(Postavke.dajPostavku("URLStraniceResursa") + Postavke.dajPostavku("nazivAplikacije") + "/" + Postavke.dajPostavku("mapaKorisnihDatoteka"), Postavke.dajPostavku("mapaResursa")+Postavke.dajPostavku("mapaKorisnihDatoteka") +
                                             "</html>"));
                }
                
            }
        });
        
        //------------------------------------------------------------------------------------
        //jbutton za download datoteka
        JButton jbtest1 = new JButton();
        jbtest1.setText("1");
        jbtest1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                String strPoruka = "";
                
                //ako usr i pass nisu prazni
                if (!jtfUsr.getText().isEmpty() && !jtfPass.getText().isEmpty()) {
                    for (Postavke postavke : Postavke.getAlListaPostavki()) {
                        
                        //upload datoteka iz postavki i azuriranje poruke
                        if (postavke.getJtbKey().getText().contains("dat")) {
                            strPoruka += Datoteke.test1(
                                jtfUsr.getText(), 
                                jtfPass.getText(), 
                                Postavke.dajPostavku("mapaResursa"),
                                postavke.getJtbValue().getText()
                            );
                        }
                    }
                    jfSync.revalidate();
                }
                
                //ako je jedaj od jtf prazni
                else{
                    strPoruka = "<p style=color:red>Popuni user i pass!</p>";
                }
                
                getJlObavijestDatoteke().setText("<html>" + strPoruka + "</html>");
                jfSync.revalidate();
                
            }
        });
        
        //-------------------------------------------------------------------------------------
        
        
        //jbutton za download datoteka
        JButton jbtest2 = new JButton();
        jbtest2.setText("2");
        jbtest2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                String strPoruka = "";
                
                //ako usr i pass nisu prazni
                if (!jtfUsr.getText().isEmpty() && !jtfPass.getText().isEmpty()) {
                    for (Postavke postavke : Postavke.getAlListaPostavki()) {
                        
                        //upload datoteka iz postavki i azuriranje poruke
                        if (postavke.getJtbKey().getText().contains("dat")) {
                            strPoruka += Datoteke.test2(
                                jtfUsr.getText(), 
                                jtfPass.getText(), 
                                Postavke.dajPostavku("mapaResursa"),
                                postavke.getJtbValue().getText()
                            );
                        }
                    }
                    jfSync.revalidate();
                }
                
                //ako je jedaj od jtf prazni
                else{
                    strPoruka = "<p style=color:red>Popuni user i pass!</p>";
                }
                
                getJlObavijestDatoteke().setText("<html>" + strPoruka + "</html>");
                jfSync.revalidate();
                
            }
        });
        
        
        //-------------------------------------------------------------------------------------
        
        
        //jbutton za download datoteka
        JButton jbtest3 = new JButton();
        jbtest3.setText("3");
        jbtest3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                String strPoruka = "";
                
                //ako usr i pass nisu prazni
                if (!jtfUsr.getText().isEmpty() && !jtfPass.getText().isEmpty()) {
                    for (Postavke postavke : Postavke.getAlListaPostavki()) {
                        
                        //upload datoteka iz postavki i azuriranje poruke
                        if (postavke.getJtbKey().getText().contains("dat")) {
                            strPoruka += Datoteke.test3(
                                jtfUsr.getText(), 
                                jtfPass.getText(), 
                                Postavke.dajPostavku("mapaResursa"),
                                postavke.getJtbValue().getText()
                            );
                        }
                    }
                    jfSync.revalidate();
                }
                
                //ako je jedaj od jtf prazni
                else{
                    strPoruka = "<p style=color:red>Popuni user i pass!</p>";
                }
                
                getJlObavijestDatoteke().setText("<html>" + strPoruka + "</html>");
                jfSync.revalidate();
                
            }
        });
        
        
        
        //-----------------------------------------------------------------------------------
        
        
        
        
        
        
        
        
        
        
        
        //dodavanje jb na jp
        jpGumbovi.add(jbZatvori);
        jpGumbovi.add(jbUp);
        jpGumbovi.add(jbDown);
//        jpGumbovi.add(jbtest1);
//        jpGumbovi.add(jbtest2);
//        jpGumbovi.add(jbtest3);
        
        //dodavanje jp na jf
        c.gridx = 0;
        c.gridy = 0;
        jfSync.add(jpLogin, c);
        
        c.gridx = 0;
        c.gridy = 1;
        jfSync.add(jpDatotekeZaSync, c);
        
        c.gridx = 0;
        c.gridy = 2;
        jfSync.add(jpMapeZaSync, c);
        
        c.gridx = 0;
        c.gridy = 3;
        jfSync.add(jpGumbovi, c);
        
        jfSync.setUndecorated(true);
        jfSync.revalidate();
        jfSync.pack();
        jfSync.setVisible(true);
    }

    public static JLabel getJlObavijestDatoteke() {
        return jlObavijestDatoteke;
    }

    public static void setJlObavijestDatoteke(JLabel jlObavijestDatoteke) {
        JfSync.jlObavijestDatoteke = jlObavijestDatoteke;
    }

    public static JLabel getJlObavijestMape() {
        return jlObavijestMape;
    }

    public static void setJlObavijestMape(JLabel jlObavijestMape) {
        JfSync.jlObavijestMape = jlObavijestMape;
    }
    
    
}
