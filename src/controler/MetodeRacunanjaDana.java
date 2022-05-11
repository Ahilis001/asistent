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
import java.util.Date;
import model.Postavke;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import view.jp.JpRacunanjeDana;

/**
 *
 * @author iduras
 */
public class MetodeRacunanjaDana {
    
    static SimpleDateFormat formatter;
    
    /**
     * učitavanje precaca iz dokumenta 
     * @param putanja
     */
    public static void pripremiDan(String putanja){
        try {
            
            //otvaranje dokumenta na putanji i popunjavanje precaca
            Document document = (Document) Jsoup.parse(Datoteke.citanjeIzDatoteke(putanja).toString(),"", Parser.xmlParser());
            
            for (Element e : document.select("postavke")) {
                JpRacunanjeDana.getPostavkePanela().pripremiLokacijuPanela(e.select("koordinate").text());
                JpRacunanjeDana.getPostavkePanela().pripremiBojuPanela(e.select("bojaPozadinePanela").text(), e.select("bojaFontaPanela").text(), e.select("bojaFontaNaslovaPanela").text());  
                JpRacunanjeDana.getPostavkePanela().pripremiSlovaPanela(e.select("velicinaFontaNaslovaPanela").text(), 
                                                                     e.select("velicinaFontaTekstaPanela").text(),
                                                                     e.select("naziv").text(), 
                                                                     e.select("vrstaFontaNaslovaPanela").text(), 
                                                                     e.select("vrstaFontaTekstaPanela").text());              
            }
            
            for (Element e : document.select("dan")) {
                formatter = new SimpleDateFormat(e.select("format").text());
            }
            
            
        } catch (Exception e) {
            
        }
    }

    public static void spremiDan(String strFormat) {
        
        String strZaUpis =  "<root>\n" +
                            "\t<dan>\n"+
                            "\t\t<format>" + strFormat + "</format>\n" +
                            "\t</dan>\n"+
                            "\t<postavke>\n"+
                            "\t\t<koordinate>" + JpRacunanjeDana.getPostavkePanela().getJtfKoordinate().getText()+"</koordinate>\n"+
                            "\t\t<bojaPozadinePanela>" + JpRacunanjeDana.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela().getText()+"</bojaPozadinePanela>\n"+
                            "\t\t<bojaFontaPanela>" + JpRacunanjeDana.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela().getText()+"</bojaFontaPanela>\n"+
                            "\t\t<bojaFontaNaslovaPanela>" + JpRacunanjeDana.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela().getText()+"</bojaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaNaslovaPanela>" + JpRacunanjeDana.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela().getText()+"</velicinaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaTekstaPanela>" + JpRacunanjeDana.getPostavkePanela().getJtfVelicinaFontaTekstaPanela().getText()+"</velicinaFontaTekstaPanela>\n"+
                            "\t\t<vrstaFontaNaslovaPanela>" + JpRacunanjeDana.getPostavkePanela().getJtfVrstaFontaNaslovaPanela().getText()+"</vrstaFontaNaslovaPanela>\n"+
                            "\t\t<vrstaFontaTekstaPanela>" + JpRacunanjeDana.getPostavkePanela().getJtfVrstaFontaTekstaPanela().getText()+"</vrstaFontaTekstaPanela>\n"+
                            "\t\t<naziv>" + JpRacunanjeDana.getPostavkePanela().getJtfNazivPanela().getText()+"</naziv>\n"+
                            "\t</postavke>\n"+
                            "</root>";
        
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("datotekaRacunanjaDana")), "UTF-8"));
            try {
                out.write(strZaUpis);
            } finally {
                out.close();
            }
        } catch (Exception e) {
        }
    }
    
    /**
     * računa broj dana od proslijeđenog datuma do trenutnog
     * @param strDatum
     * @return 
     */
    public static String dajBrojDana(String strDatum){
        
        try {
            
            //datumi za računanje
            Date dtPocetak = new SimpleDateFormat("dd.MM.yyyy.").parse(strDatum);
            Date dtTrenutno = new SimpleDateFormat("dd.MM.yyyy.").parse(formatter.format(new Date(System.currentTimeMillis())));
            
            //računanje razlike u datumima, vraća broj dana        
            String strProtekloDana = String.valueOf((dtTrenutno.getTime() - dtPocetak.getTime()) / (24 * 60 * 60 * 1000));
            
            //provjera datuma
            if (strProtekloDana.contains("-")) {
                return "Datum je u budućnosti.";
            } 
            
            else {
                return "Proteklo je " + strProtekloDana + " dana.";
            }

        } catch (Exception e) {
            return "Greška kod računanja broja dana!";
        }
    }

    public static SimpleDateFormat getFormatter() {
        return formatter;
    }

    public static void setFormatter(SimpleDateFormat formatter) {
        MetodeRacunanjaDana.formatter = formatter;
    }
}
