/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jp;

import controler.MetodeMeteo;
import static controler.MetodeMeteo.dajIkonu;
import controler.dretve.DretvaAzuriranjeMeteo;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import model.PostavkePanela;
import model.MeteoPodaci;
import model.Postavke;

/**
 *
 * @author Ahilis
 */
public class JpPrognoza extends JPanel{

    static JpPrognoza jpPrognoza = new JpPrognoza();
    static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
    static PostavkePanela postavkePanela = new PostavkePanela();
    
    public JpPrognoza() {
        
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        
        catch(Exception e){}
    }
    
    /**
     * generira obrazac prognoze
     * @return 
     */
    public static JpPrognoza generirajObrazac() {
        
        //azuriranje obrasca
        JpPrognoza.azurirajObrazac();
        
        //postavljanje okvira
        jpPrognoza.setBorder(javax.swing.BorderFactory.createTitledBorder(null, postavkePanela.getJtfNazivPanela().getText() + " " + Postavke.dajPostavku("meteoGradDrzava").replace(",", ", "),
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font(postavkePanela.getJtfNazivPanela().getText(), 0, postavkePanela.getIntVelicinaFontaNaslovaPanela()), 
                new java.awt.Color(postavkePanela.getIntBojaFontaNaslovaPanelaR(), postavkePanela.getIntBojaFontaNaslovaPanelaG(), postavkePanela.getIntBojaFontaNaslovaPanelaB())));
        
        jpPrognoza.setBackground(new java.awt.Color(postavkePanela.getIntBojaPozadinePanelaR(), postavkePanela.getIntBojaPozadinePanelaG(), postavkePanela.getIntBojaPozadinePanelaB()));
        
        if (!DretvaAzuriranjeMeteo.getInstanca().isAlive()) {
            DretvaAzuriranjeMeteo.getInstanca().start();
        }
        
        return jpPrognoza;
    }
    
    /**
     * prikazuje podatke u prosljeđenoj formi
     */
    public static void azurirajObrazac(){
        
        jpPrognoza.removeAll();
        
        jpPrognoza.setLayout(new GridBagLayout()); 
        
        ArrayList<MeteoPodaci> listaMeteoPodataka =  MetodeMeteo.povuciPodatkeSve(Postavke.dajPostavku("meteoGradDrzava"));
        
        JPanel jpDanas = new JPanel();
        
        jpDanas.setBackground(new java.awt.Color(postavkePanela.getIntBojaPozadinePanelaR(), postavkePanela.getIntBojaPozadinePanelaG(), postavkePanela.getIntBojaPozadinePanelaB()));
        jpDanas.setLayout(new GridBagLayout()); 
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,15,0,0);
        
        //meteo podaci za danas
        MeteoPodaci mp = listaMeteoPodataka.get(0);
        
        //dodavanje ikone za danas u panel
        c.gridx = 0;
        c.gridy = 0;
        jpDanas.add(new JLabel(new ImageIcon(dajIkonu(mp.getWeatherIcon()))), c);
        
        //dodavanje ostalih podataka za danas u panel
        c.gridx = 1;
        c.gridy = 0;
        jpDanas.add(new JLabel(
                "<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">Ažurirano: " + formatter.format(new Date(System.currentTimeMillis())) + "<br/>"+
                "Trenutno: " + mp.getWeatherValue() + "<br/>"+
                "Temperatura: " + mp.getTemperatureValue().toString() + " " + mp.getTemperatureUnit() + "<br/>" +
                "Min: " + mp.getTemperatureMin().toString() + " " + mp.getTemperatureUnit() + "<br/>" +
                "Max: " + mp.getTemperatureMax().toString() + " " + mp.getTemperatureUnit() + "<br/>" +
                "Izlazak sunca: " + MetodeMeteo.getFormatterSat().format(mp.getSunRise()) + "<br/>" +
                "Zalazak sunca: " + MetodeMeteo.getFormatterSat().format(mp.getSunSet()) + "</p></html>"), c);

        JPanel jpOstalaPrognoza = new JPanel();
        
        jpOstalaPrognoza.setBackground(new java.awt.Color(postavkePanela.getIntBojaPozadinePanelaR(), postavkePanela.getIntBojaPozadinePanelaG(), postavkePanela.getIntBojaPozadinePanelaB()));;
        jpOstalaPrognoza.setLayout(new GridBagLayout()); 
        c.fill = GridBagConstraints.CENTER;
        c.insets = new Insets(1,8,1,15);
        
        //dodavanje ostatka prognoze u panel
        int gridxIkonaTekst = 0, gridyTekstDatum = 0, gridyIkona = 1, gridyTekst = 2;
        
        for (int i = 0; i <= Integer.parseInt(Postavke.dajPostavku("brojDanaZaPrikazPrognoze")); i++) {
            MeteoPodaci meteoPodaci = listaMeteoPodataka.get(i);
            if(meteoPodaci != listaMeteoPodataka.get(0)){
                
                //dodavanje datuma prognoze
                c.gridx = gridxIkonaTekst;
                c.gridy = gridyTekstDatum;
                jpOstalaPrognoza.add(new JLabel("<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">" + MetodeMeteo.getFormatterDatum().format(meteoPodaci.getSunRise()) +"</p></html>"), c);
                
                //dodavanje ikone prognoze
                c.gridx = gridxIkonaTekst;
                c.gridy = gridyIkona;
                
                //dodavanje skalirane ikone
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(dajIkonu(meteoPodaci.getWeatherIcon())).getImage().getScaledInstance(30, -1, Image.SCALE_DEFAULT));
                jpOstalaPrognoza.add(new JLabel(imageIcon), c);

                //dodavanje info prognoze
                c.gridx = gridxIkonaTekst;
                c.gridy = gridyTekst;
                jpOstalaPrognoza.add(new JLabel(
                    "<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+
                    ">Min: " + meteoPodaci.getTemperatureMin().toString()  + " " + mp.getTemperatureUnit() + "<br/>" +  
                    "Max: " + meteoPodaci.getTemperatureMax().toString() + " " + mp.getTemperatureUnit() +"</p></html>"), c);
                gridxIkonaTekst++;
            }  
        }
        
//        JPanel jpAnimirano = new JPanel();
//        JEditorPane jepAnimirano = new JEditorPane();
//        jepAnimirano.setEditable(false);
//        try {
//            jepAnimirano.setPage("https://stackoverflow.com/questions/22994647/show-webpage-in-java-editorpane/23224994#23224994");
//            
//////            jpAnimirano.add(ncw);
//        } catch (IOException ex) {
//            Logger.getLogger(JpPrognoza.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        jpAnimirano.add(new JLabel("<html><iframe width=\"400\" height=\"450\" src=\"https://embed.windy.com/embed2.html?lat=46.300&lon=16.383&detailLat=46.300&detailLon=16.383&width=650&height=450&zoom=8&level=surface&overlay=wind&product=ecmwf&menu=&message=&marker=&calendar=now&pressure=&type=map&location=coordinates&detail=&metricWind=default&metricTemp=default&radarRange=-1\" frameborder=\"0\"></iframe></html>"));
        
//        https://embed.windy.com/embed2.html?lat=46.300&lon=16.383&detailLat=46.300&detailLon=16.383&width=650&height=450&zoom=8&level=surface&overlay=wind&product=ecmwf&menu=&message=&marker=&calendar=now&pressure=&type=map&location=coordinates&detail=&metricWind=default&metricTemp=default&radarRange=-1
        
        
        
        
        jpDanas.revalidate();
        jpOstalaPrognoza.revalidate();
//        jepAnimirano.revalidate();
        
        c.insets = new Insets(0,0,0,0);
        c.gridx = 0;
        c.gridy = 0;
        jpPrognoza.add(jpDanas, c);
        
        c.insets = new Insets(10,0,0,0);
        c.gridx = 0;
        c.gridy = 1;
        jpPrognoza.add(jpOstalaPrognoza, c);
        
//        c.insets = new Insets(10,0,0,0);
//        c.gridx = 0;
//        c.gridy = 2;
//        jpPrognoza.add(jepAnimirano, c);
        
        jpPrognoza.repaint();
        jpPrognoza.revalidate();
    }

    public static PostavkePanela getPostavkePanela() {
        return postavkePanela;
    }

    public static void setPostavkePanela(PostavkePanela postavkePanela) {
        JpPrognoza.postavkePanela = postavkePanela;
    }
    
    
}
