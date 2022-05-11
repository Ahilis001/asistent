package controler;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import model.Postavke;
import model.Radio;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import view.jp.JpRadio;

public class MetodeRadio{
    
    /**
     * učitavanje radija iz dokumenta 
     * @param putanja
     */
    public static void pripremiRadio(String putanja){
        try {
            //ciscenje liste svih precaca
            Radio.getAlSviRadiji().clear();

            //otvaranje dokumenta na putanji i popunjavanje precaca
            Document document = (Document) Jsoup.parse(Datoteke.citanjeIzDatoteke(putanja).toString(),"", Parser.xmlParser());

            //popunjavanje liste mogućnosti
            for (Element e : document.select("radio")) {
                Radio radio = new Radio(e.select("naziv").text(), e.select("URL").text(), e.select("prikazi").text());
                Radio.getAlSviRadiji().add(radio);
            }
            
            for (Element e : document.select("postavke")) {
                JpRadio.getPostavkePanela().pripremiLokacijuPanela(e.select("koordinate").text());
                JpRadio.getPostavkePanela().pripremiBojuPanela(e.select("bojaPozadinePanela").text(), e.select("bojaFontaPanela").text(), e.select("bojaFontaNaslovaPanela").text());  
                JpRadio.getPostavkePanela().pripremiSlovaPanela(e.select("velicinaFontaNaslovaPanela").text(), 
                                                                     e.select("velicinaFontaTekstaPanela").text(),
                                                                     e.select("naziv").text(), 
                                                                     e.select("vrstaFontaNaslovaPanela").text(), 
                                                                     e.select("vrstaFontaTekstaPanela").text());     
            }
        } catch (Exception e) {
            Radio radio = new Radio("<html><p style=color:red>Greška kod učitavanja!</p></html>", "", "1");
            Radio.getAlSviRadiji().add(radio);
        }
        
    }
    
    /**
     * sprema radio u datoteku
     */
    public static void spremiRadio(){
        
        String strZaUpis = "<root>\n" +
                           "\t<radiji>\n\n";
        
        for (Radio radio : Radio.getAlSviRadiji()) {
            
            if (!radio.getJtfNaziv().getText().equals("")) {
                strZaUpis += "\t\t<radio>\n"+
                             "\t\t\t<naziv>" + radio.getJtfNaziv().getText()+"</naziv>\n"+
                             "\t\t\t<URL>" + radio.getJtfURL().getText()+"</URL>\n"+
                             "\t\t\t<prikazi>" + radio.getJcbPrikazi().isSelected() +"</prikazi>\n"+
                             "\t\t</radio>\n\n";
            }
        }
        
        strZaUpis +=        "\t</radiji>\n\n"+
                            "\t<postavke>\n"+
                            "\t\t<koordinate>" + JpRadio.getPostavkePanela().getJtfKoordinate().getText()+"</koordinate>\n"+
                            "\t\t<bojaPozadinePanela>" + JpRadio.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela().getText()+"</bojaPozadinePanela>\n"+
                            "\t\t<bojaFontaPanela>" + JpRadio.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela().getText()+"</bojaFontaPanela>\n"+
                            "\t\t<bojaFontaNaslovaPanela>" + JpRadio.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela().getText()+"</bojaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaNaslovaPanela>" + JpRadio.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela().getText()+"</velicinaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaTekstaPanela>" + JpRadio.getPostavkePanela().getJtfVelicinaFontaTekstaPanela().getText()+"</velicinaFontaTekstaPanela>\n"+
                            "\t\t<vrstaFontaNaslovaPanela>" + JpRadio.getPostavkePanela().getJtfVrstaFontaNaslovaPanela().getText()+"</vrstaFontaNaslovaPanela>\n"+
                            "\t\t<vrstaFontaTekstaPanela>" + JpRadio.getPostavkePanela().getJtfVrstaFontaTekstaPanela().getText()+"</vrstaFontaTekstaPanela>\n"+
                            "\t\t<naziv>" + JpRadio.getPostavkePanela().getJtfNazivPanela().getText()+"</naziv>\n"+
                            "\t</postavke>\n"+
                            "</root>";
        
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("datotekaRadija")), "UTF-8"));
            try {
                out.write(strZaUpis);
            } finally {
                out.close();
            }
        } catch (Exception e) {
        }
        
        //učitavanje podsjetnika
        JpRadio.azurirajObrazac();
    }
    
    /**
     * dodaje novu praznu poveznica u listu
     */
    public static void dodajNoviRadio(){
        
        Radio prazniRadio = new Radio("", "", "true");
        
        Radio.getAlSviRadiji().add(prazniRadio);
    }
    
    /**
     * brise oznacene radije
     */
    public static void obrisiRadio() {
        //prolazi kroz listu uredjaja i ukoliko je koji označen za brisanje, briše ga
        for (Iterator<Radio> iterator = Radio.getAlSviRadiji().iterator(); iterator.hasNext();) {
            Radio next = iterator.next();
            if (next.getJcbObrisi().isSelected()){
                iterator.remove();
            }
        }
    }
    
    /**
     * zaustavlja sve radije
     */
    public static void zaustaviSveRadije(){
        
        for (Radio radio : Radio.getAlSviRadiji()) {
            radio.getDrRadio().stop();
        }
        
        JpRadio.getJlTrenutnoSvira().setText("Trenutno ništa ne svira");
    }
}