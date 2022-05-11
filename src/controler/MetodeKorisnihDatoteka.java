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
import model.Postavke;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import view.jf.JfKorisneDatotekeUredi;
import view.jp.JpKorisneDatoteke;

/**
 *
 * @author Ahilis
 */
public class MetodeKorisnihDatoteka {
    
    public static void pripremiKorisnuDatoteku(String putanja){
        
        //otvaranje dokumenta na putanji i popunjavanje precaca
        Document document = (Document) Jsoup.parse(Datoteke.citanjeIzDatoteke(putanja).toString(),"", Parser.xmlParser());

        //popunjavanje liste mogućnosti
        for (Element e : document.select("poruka")) {
            JpKorisneDatoteke.setStrBoja(e.select("boja").text());
            JpKorisneDatoteke.setStrPoruka(e.select("sadrzaj").text());
        }

        for (Element e : document.select("postavke")) {
            JpKorisneDatoteke.getPostavkePanela().pripremiLokacijuPanela(e.select("koordinate").text());
            JpKorisneDatoteke.getPostavkePanela().pripremiBojuPanela(e.select("bojaPozadinePanela").text(), e.select("bojaFontaPanela").text(), e.select("bojaFontaNaslovaPanela").text());  
            JpKorisneDatoteke.getPostavkePanela().pripremiSlovaPanela(e.select("velicinaFontaNaslovaPanela").text(), 
                                                                 e.select("velicinaFontaTekstaPanela").text(),
                                                                 e.select("naziv").text(), 
                                                                 e.select("vrstaFontaNaslovaPanela").text(), 
                                                                 e.select("vrstaFontaTekstaPanela").text());     
        }
    }
    
    
    /**
     * sprema poveznice u datoteku
     */
    public static void spremiKorisneDatoteke(){
        
        String strZaUpis =  "<root>\n" +
                            "\t<korisneDatoteke>\n" +
                            "\t\t<poruka>\n"+
                            "\t\t\t<sadrzaj>" + JfKorisneDatotekeUredi.getJtfPoruka().getText()+ "</sadrzaj>\n"+
                            "\t\t\t<boja>" + JfKorisneDatotekeUredi.getJtfBoja().getText()+ "</boja>\n"+
                            "\t\t</poruka>\n"+
                            "\t</korisneDatoteke>\n"+
                            "\t<postavke>\n"+
                            "\t\t<koordinate>" + JpKorisneDatoteke.getPostavkePanela().getJtfKoordinate().getText()+"</koordinate>\n"+
                            "\t\t<bojaPozadinePanela>" + JpKorisneDatoteke.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela().getText()+"</bojaPozadinePanela>\n"+
                            "\t\t<bojaFontaPanela>" + JpKorisneDatoteke.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela().getText()+"</bojaFontaPanela>\n"+
                            "\t\t<bojaFontaNaslovaPanela>" + JpKorisneDatoteke.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela().getText()+"</bojaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaNaslovaPanela>" + JpKorisneDatoteke.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela().getText()+"</velicinaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaTekstaPanela>" + JpKorisneDatoteke.getPostavkePanela().getJtfVelicinaFontaTekstaPanela().getText()+"</velicinaFontaTekstaPanela>\n"+
                            "\t\t<vrstaFontaNaslovaPanela>" + JpKorisneDatoteke.getPostavkePanela().getJtfVrstaFontaNaslovaPanela().getText()+"</vrstaFontaNaslovaPanela>\n"+
                            "\t\t<vrstaFontaTekstaPanela>" + JpKorisneDatoteke.getPostavkePanela().getJtfVrstaFontaTekstaPanela().getText()+"</vrstaFontaTekstaPanela>\n"+
                            "\t\t<naziv>" + JpKorisneDatoteke.getPostavkePanela().getJtfNazivPanela().getText()+"</naziv>\n"+
                            "\t</postavke>\n"+
                            "</root>";
        
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("datotekaKorisnihDatoteka")), "UTF-8"));
            try {
                out.write(strZaUpis);
            } finally {
                out.close();
            }
        } catch (Exception e) {
        }
        
        //učitavanje podsjetnika
        JpKorisneDatoteke.azurirajObrazac();
    }
}
