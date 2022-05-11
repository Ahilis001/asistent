/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import model.Postavke;
import model.Poveznica;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import view.jp.JpPoveznice;

/**
 *
 * @author ahilis001
 */
public class MetodePoveznice {
    
    /**
     * učitavanje poveznice iz dokumenta 
     * @param putanja
     */
    public static void pripremiPoveznicu(String putanja){
        try {
            //ciscenje liste svih precaca
            Poveznica.getAlSvePoveznice().clear();

            //otvaranje dokumenta na putanji i popunjavanje precaca
            Document document = (Document) Jsoup.parse(Datoteke.citanjeIzDatoteke(putanja).toString(),"", Parser.xmlParser());

            //popunjavanje liste mogućnosti
            for (Element e : document.select("poveznica")) {
                Poveznica poveznica = new Poveznica(e.select("opis").text(), e.select("URL").text(), e.select("prikazi").text());
                Poveznica.getAlSvePoveznice().add(poveznica);
            }
            
            for (Element e : document.select("postavke")) {
                JpPoveznice.getPostavkePanela().pripremiLokacijuPanela(e.select("koordinate").text());
                JpPoveznice.getPostavkePanela().pripremiBojuPanela(e.select("bojaPozadinePanela").text(), e.select("bojaFontaPanela").text(), e.select("bojaFontaNaslovaPanela").text());  
                JpPoveznice.getPostavkePanela().pripremiSlovaPanela(e.select("velicinaFontaNaslovaPanela").text(), 
                                                                     e.select("velicinaFontaTekstaPanela").text(),
                                                                     e.select("naziv").text(), 
                                                                     e.select("vrstaFontaNaslovaPanela").text(), 
                                                                     e.select("vrstaFontaTekstaPanela").text());     
            }
        } catch (Exception e) {
            Poveznica poveznica = new Poveznica("<html><p style=color:red>Greška kod učitavanja!</p></html>", "", "1");
            Poveznica.getAlSvePoveznice().add(poveznica);
        }
        
    }
    
    /**
     * sprema poveznice u datoteku
     */
    public static void spremiPoveznice(){
        
        String strZaUpis = "<root>\n" +
                           "\t<poveznice>\n\n";
//        System.out.println(PodsjetnikPomocni.getAlSviPomocniPodsjetnici().size());
        for (Poveznica poveznica : Poveznica.getAlSvePoveznice()) {
//            System.out.println("odpas - " + podsjetnikPomocni.getJtfOpis().getText());
            if (!poveznica.getJtfOpis().getText().equals("")) {
                strZaUpis += "\t\t<poveznica>\n"+
                             "\t\t\t<opis>" + poveznica.getJtfOpis().getText()+"</opis>\n"+
                             "\t\t\t<URL>" + poveznica.getJtfURL().getText()+"</URL>\n"+
                             "\t\t\t<prikazi>" + poveznica.getJcbPrikazi().isSelected() +"</prikazi>\n"+
                             "\t\t</poveznica>\n\n";
            }
        }
        
        strZaUpis +=        "\t</poveznice>\n\n"+
                            "\t<postavke>\n"+
                            "\t\t<koordinate>" + JpPoveznice.getPostavkePanela().getJtfKoordinate().getText()+"</koordinate>\n"+
                            "\t\t<bojaPozadinePanela>" + JpPoveznice.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela().getText()+"</bojaPozadinePanela>\n"+
                            "\t\t<bojaFontaPanela>" + JpPoveznice.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela().getText()+"</bojaFontaPanela>\n"+
                            "\t\t<bojaFontaNaslovaPanela>" + JpPoveznice.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela().getText()+"</bojaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaNaslovaPanela>" + JpPoveznice.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela().getText()+"</velicinaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaTekstaPanela>" + JpPoveznice.getPostavkePanela().getJtfVelicinaFontaTekstaPanela().getText()+"</velicinaFontaTekstaPanela>\n"+
                            "\t\t<vrstaFontaNaslovaPanela>" + JpPoveznice.getPostavkePanela().getJtfVrstaFontaNaslovaPanela().getText()+"</vrstaFontaNaslovaPanela>\n"+
                            "\t\t<vrstaFontaTekstaPanela>" + JpPoveznice.getPostavkePanela().getJtfVrstaFontaTekstaPanela().getText()+"</vrstaFontaTekstaPanela>\n"+
                            "\t\t<naziv>" + JpPoveznice.getPostavkePanela().getJtfNazivPanela().getText()+"</naziv>\n"+
                            "\t</postavke>\n"+
                            "</root>";
        
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("datotekaPoveznica")), "UTF-8"));
            try {
                out.write(strZaUpis);
            } finally {
                out.close();
            }
        } catch (Exception e) {
        }
        
        //učitavanje podsjetnika
        JpPoveznice.azurirajObrazac();
    }
    
    /**
     * dodaje novu praznu poveznica u listu
     */
    public static void dodajNovuPoveznicu(){
        
        Poveznica praznaPoveznica = new Poveznica("", "", "true");
        
        Poveznica.getAlSvePoveznice().add(praznaPoveznica);

//        PoveznicaPomocna.getAlSvePomocnePoveznice().add(new PoveznicaPomocna(praznaPoveznica.getStrOpis(), praznaPoveznica.getStrURL()));
    }
    
    /**
     * brise oznacene poveznice
     */
    public static void obrisiPoveznice() {
        //prolazi kroz listu uredjaja i ukoliko je koji označen za brisanje, briše ga
        for (Iterator<Poveznica> iterator = Poveznica.getAlSvePoveznice().iterator(); iterator.hasNext();) {
            Poveznica next = iterator.next();
            if (next.getJcbObrisi().isSelected()){
                iterator.remove();
            }
        }
    }
}
