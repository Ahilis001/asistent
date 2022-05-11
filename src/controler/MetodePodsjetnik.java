/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.BufferedWriter;
import model.Podsjetnik;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import model.Postavke;
import view.jp.JpPodsjetnici;

/**
 *
 * @author Ahilis
 */
public class MetodePodsjetnik {
    
    /**
     * učitavanje precaca iz dokumenta 
     * @param putanja
     */
    public static void pripremiPodsjetnik(String putanja){
        try {
            //ciscenje liste svih precaca
            Podsjetnik.getAlSviPodsjetnici().clear();

            //otvaranje dokumenta na putanji i popunjavanje precaca
            Document document = (Document) Jsoup.parse(Datoteke.citanjeIzDatoteke(putanja).toString(),"", Parser.xmlParser());

            //popunjavanje liste podsjetnika
            for (Element e : document.select("podsjetnik")) {
                Podsjetnik podsjetnik = new Podsjetnik(e.select("opis").text(), e.select("pocetak").text(), e.select("kraj").text());
                Podsjetnik.getAlSviPodsjetnici().add(podsjetnik);
            }
            
            for (Element e : document.select("postavke")) {
                JpPodsjetnici.getPostavkePanela().pripremiLokacijuPanela(e.select("koordinate").text());
                JpPodsjetnici.getPostavkePanela().pripremiBojuPanela(e.select("bojaPozadinePanela").text(), e.select("bojaFontaPanela").text(), e.select("bojaFontaNaslovaPanela").text());  
                JpPodsjetnici.getPostavkePanela().pripremiSlovaPanela(e.select("velicinaFontaNaslovaPanela").text(), 
                                                                     e.select("velicinaFontaTekstaPanela").text(),
                                                                     e.select("naziv").text(), 
                                                                     e.select("vrstaFontaNaslovaPanela").text(), 
                                                                     e.select("vrstaFontaTekstaPanela").text());      
            }
        } catch (Exception e) {
            Podsjetnik podsjetnik = new Podsjetnik("<html><p style=color:red>Greška kod učitavanja!</p></html>", "", "");
            Podsjetnik.getAlSviPodsjetnici().add(podsjetnik);
        }
        
    }
    
    /**
     * sprema podsjetnike u datoteku
     */
    public static void spremiPodsjetnike(){
        
        String strZaUpis = "<root>\n" +
                           "\t<podsjetnici>\n\n";
//        System.out.println(PodsjetnikPomocni.getAlSviPomocniPodsjetnici().size());
        for (Podsjetnik podsjetnik : Podsjetnik.getAlSviPodsjetnici()) {
//            System.out.println("odpas - " + podsjetnikPomocni.getJtfOpis().getText());
            if (!podsjetnik.getJtfOpis().getText().equals("")) {
                strZaUpis += "\t\t<podsjetnik>\n"+
                             "\t\t\t<opis>" + podsjetnik.getJtfOpis().getText()+"</opis>\n"+
                             "\t\t\t<pocetak>" + podsjetnik.getJtfPocetak().getText()+"</pocetak>\n"+
                             "\t\t\t<kraj>" + podsjetnik.getJtfKraj().getText()+"</kraj>\n"+
                             "\t\t</podsjetnik>\n\n";
            }
        }
        
        strZaUpis +=        "\t</podsjetnici >\n"+
                            "\t<postavke>\n"+
                            "\t\t<koordinate>" + JpPodsjetnici.getPostavkePanela().getJtfKoordinate().getText()+"</koordinate>\n"+
                            "\t\t<bojaPozadinePanela>" + JpPodsjetnici.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela().getText()+"</bojaPozadinePanela>\n"+
                            "\t\t<bojaFontaPanela>" + JpPodsjetnici.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela().getText()+"</bojaFontaPanela>\n"+
                            "\t\t<bojaFontaNaslovaPanela>" + JpPodsjetnici.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela().getText()+"</bojaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaNaslovaPanela>" + JpPodsjetnici.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela().getText()+"</velicinaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaTekstaPanela>" + JpPodsjetnici.getPostavkePanela().getJtfVelicinaFontaTekstaPanela().getText()+"</velicinaFontaTekstaPanela>\n"+
                            "\t\t<vrstaFontaNaslovaPanela>" + JpPodsjetnici.getPostavkePanela().getJtfVrstaFontaNaslovaPanela().getText()+"</vrstaFontaNaslovaPanela>\n"+
                            "\t\t<vrstaFontaTekstaPanela>" + JpPodsjetnici.getPostavkePanela().getJtfVrstaFontaTekstaPanela().getText()+"</vrstaFontaTekstaPanela>\n"+
                            "\t\t<naziv>" + JpPodsjetnici.getPostavkePanela().getJtfNazivPanela().getText()+"</naziv>\n"+
                            "\t</postavke>\n"+
                            "</root>";
        
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("datotekaPodsjetnika")), "UTF-8"));
            try {
                out.write(strZaUpis);
            } finally {
                out.close();
            }
        } catch (Exception e) {
        }
        
        //učitavanje podsjetnika
        JpPodsjetnici.azurirajObrazac(JpPodsjetnici.getIntAktivniBrojStranice());
    }
    
    /**
     * dodaje novi prazni podsjetnik u listu
     */
    public static void dodajNoviPodsjetnik(){
        
        Podsjetnik prazniPodsjetnik = new Podsjetnik("", "", "");
        
        Podsjetnik.getAlSviPodsjetnici().add(prazniPodsjetnik);

//        PodsjetnikPomocni.getAlSviPomocniPodsjetnici().add(new PodsjetnikPomocni(prazniPodsjetnik.getStrOpis(), prazniPodsjetnik.getStrPocetak(), prazniPodsjetnik.getStrKraj()));
    }
    
    /**
     * brise oznacene podsjetnike
     */
    public static void obrisiPodsjetnike() {
        //prolazi kroz listu uredjaja i ukoliko je koji označen za brisanje, briše ga
        for (Iterator<Podsjetnik> iterator = Podsjetnik.getAlSviPodsjetnici().iterator(); iterator.hasNext();) {
            Podsjetnik next = iterator.next();
            if (next.getJcbObrisi().isSelected()){
                iterator.remove();
            }
        }
    }
}
