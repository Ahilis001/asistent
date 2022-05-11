/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

//import controler.misc.MetodeMisc;
//import controler.tajmer.MetodeTajmera;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import model.Postavke;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import view.jp.JpSat;

/**
 *
 * @author iduras
 */
public class MetodeSat {
    
    static SimpleDateFormat formatter;
    
    /**
     * učitavanje precaca iz dokumenta 
     * @param putanja
     */
    public static void pripremiSat(String putanja){
        try {
            
            //otvaranje dokumenta na putanji i popunjavanje precaca
            Document document = (Document) Jsoup.parse(Datoteke.citanjeIzDatoteke(putanja).toString(),"", Parser.xmlParser());
            
            for (Element e : document.select("postavke")) {
                JpSat.getPostavkePanela().pripremiLokacijuPanela(e.select("koordinate").text());
                JpSat.getPostavkePanela().pripremiBojuPanela(e.select("bojaPozadinePanela").text(), e.select("bojaFontaPanela").text(), e.select("bojaFontaNaslovaPanela").text());  
                JpSat.getPostavkePanela().pripremiSlovaPanela(e.select("velicinaFontaNaslovaPanela").text(), 
                                                                     e.select("velicinaFontaTekstaPanela").text(),
                                                                     e.select("naziv").text(), 
                                                                     e.select("vrstaFontaNaslovaPanela").text(), 
                                                                     e.select("vrstaFontaTekstaPanela").text());              
            }
            
            for (Element e : document.select("sat")) {
                formatter = new SimpleDateFormat(e.select("format").text());
            }
            
            
        } catch (Exception e) {
            
        }
    }

    public static void spremiSat(String strFormat) {
        
        String strZaUpis =  "<root>\n" +
                            "\t<sat>\n"+
                            "\t\t<format>" + strFormat + "</format>\n" +
                            "\t</sat>\n"+
                            "\t<postavke>\n"+
                            "\t\t<koordinate>" + JpSat.getPostavkePanela().getJtfKoordinate().getText()+"</koordinate>\n"+
                            "\t\t<bojaPozadinePanela>" + JpSat.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela().getText()+"</bojaPozadinePanela>\n"+
                            "\t\t<bojaFontaPanela>" + JpSat.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela().getText()+"</bojaFontaPanela>\n"+
                            "\t\t<bojaFontaNaslovaPanela>" + JpSat.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela().getText()+"</bojaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaNaslovaPanela>" + JpSat.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela().getText()+"</velicinaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaTekstaPanela>" + JpSat.getPostavkePanela().getJtfVelicinaFontaTekstaPanela().getText()+"</velicinaFontaTekstaPanela>\n"+
                            "\t\t<vrstaFontaNaslovaPanela>" + JpSat.getPostavkePanela().getJtfVrstaFontaNaslovaPanela().getText()+"</vrstaFontaNaslovaPanela>\n"+
                            "\t\t<vrstaFontaTekstaPanela>" + JpSat.getPostavkePanela().getJtfVrstaFontaTekstaPanela().getText()+"</vrstaFontaTekstaPanela>\n"+
                            "\t\t<naziv>" + JpSat.getPostavkePanela().getJtfNazivPanela().getText()+"</naziv>\n"+
                            "\t</postavke>\n"+
                            "</root>";
        
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("datotekaSata")), "UTF-8"));
            try {
                out.write(strZaUpis);
            } finally {
                out.close();
            }
        } catch (Exception e) {
        }
    }

    public static SimpleDateFormat getFormatter() {
        return formatter;
    }

    public static void setFormatter(SimpleDateFormat formatter) {
        MetodeSat.formatter = formatter;
    }
}
