/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.BufferedWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import model.Kontakt;
import model.Postavke;
import view.jp.JpKontakt;

/**
 *
 * @author Ahilis
 */
public class MetodeKontakt {
    
    static boolean boolSortirajUzlaznoUprava = true;
    static boolean boolSortirajUzlaznoPostaja = true;
    static boolean boolSortirajUzlaznoImeNaziv = true;
    static boolean boolSortirajUzlaznoBroj = true;
    
    /**
     * učitavanje precaca iz dokumenta 
     * @param putanja
     */
    public static void pripremiKontakt(String putanja){
        try {
            //ciscenje liste svih precaca
            Kontakt.getAlSviKontakti().clear();

            //otvaranje dokumenta na putanji i popunjavanje precaca
            Document document = (Document) Jsoup.parse(Datoteke.citanjeIzDatoteke(putanja).toString(),"", Parser.xmlParser());

            //popunjavanje liste mogućnosti
            for (Element e : document.select("kontakt")) {
                Kontakt kontakt = new Kontakt(e.select("uprava").text(), e.select("postaja").text(), e.select("imeNaziv").text(), e.select("broj").text(), e.select("prikazi").text());
                Kontakt.getAlSviKontakti().add(kontakt);
            }
            
            for (Element e : document.select("postavke")) {
                JpKontakt.getPostavkePanela().pripremiLokacijuPanela(e.select("koordinate").text());
                JpKontakt.getPostavkePanela().pripremiBojuPanela(e.select("bojaPozadinePanela").text(), e.select("bojaFontaPanela").text(), e.select("bojaFontaNaslovaPanela").text());  
                JpKontakt.getPostavkePanela().pripremiSlovaPanela(e.select("velicinaFontaNaslovaPanela").text(), 
                                                                     e.select("velicinaFontaTekstaPanela").text(),
                                                                     e.select("naziv").text(), 
                                                                     e.select("vrstaFontaNaslovaPanela").text(), 
                                                                     e.select("vrstaFontaTekstaPanela").text());    
            }
            
            
        } catch (Exception e) {
            Kontakt kontakt = new Kontakt("<html><p style=color:red>Greška kod učitavanja!</p></html>", "", "", "", "");
            Kontakt.getAlSviKontakti().add(kontakt);
            e.printStackTrace();
        }
        
    }
    
    /**
     * sprema podsjetnike u datoteku
     */
    public static void spremiKontakte(){
        
        String strZaUpis = "<root>\n" +
                           "\t<kontakti>\n\n";
        
        for (Kontakt kontakt : Kontakt.getAlSviKontakti()) {
            
            if (!kontakt.getJtfUprava().getText().equals("")) {
                strZaUpis += "\t\t<kontakt>\n"+
                             "\t\t\t<uprava>" + kontakt.getJtfUprava().getText()+"</uprava>\n"+
                             "\t\t\t<postaja>" + kontakt.getJtfPostaja().getText()+"</postaja>\n"+
                             "\t\t\t<imeNaziv>" + kontakt.getJtfImeNaziv().getText()+"</imeNaziv>\n"+
                             "\t\t\t<broj>" + kontakt.getJtfBroj().getText()+"</broj>\n"+
                             "\t\t\t<prikazi>" + kontakt.getJcbPrikazi().isSelected()+"</prikazi>\n"+
                             "\t\t</kontakt>\n\n";
            }
        }
        
        strZaUpis +=        "\t</kontakti>\n\n"+
                            "\t<postavke>\n"+
                            "\t\t<koordinate>" + JpKontakt.getPostavkePanela().getJtfKoordinate().getText()+"</koordinate>\n"+
                            "\t\t<bojaPozadinePanela>" + JpKontakt.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela().getText()+"</bojaPozadinePanela>\n"+
                            "\t\t<bojaFontaPanela>" + JpKontakt.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela().getText()+"</bojaFontaPanela>\n"+
                            "\t\t<bojaFontaNaslovaPanela>" + JpKontakt.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela().getText()+"</bojaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaNaslovaPanela>" + JpKontakt.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela().getText()+"</velicinaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaTekstaPanela>" + JpKontakt.getPostavkePanela().getJtfVelicinaFontaTekstaPanela().getText()+"</velicinaFontaTekstaPanela>\n"+
                            "\t\t<vrstaFontaNaslovaPanela>" + JpKontakt.getPostavkePanela().getJtfVrstaFontaNaslovaPanela().getText()+"</vrstaFontaNaslovaPanela>\n"+
                            "\t\t<vrstaFontaTekstaPanela>" + JpKontakt.getPostavkePanela().getJtfVrstaFontaTekstaPanela().getText()+"</vrstaFontaTekstaPanela>\n"+
                            "\t\t<naziv>" + JpKontakt.getPostavkePanela().getJtfNazivPanela().getText()+"</naziv>\n"+
                            "\t</postavke>\n"+
                            "</root>";
        
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("datotekaKontakata")), "UTF-8"));
            try {
                out.write(strZaUpis);
            } finally {
                out.close();
            }
        } catch (Exception e) {
        }
        
        //učitavanje podsjetnika
        JpKontakt.azurirajObrazac();
    }
    
    /**
     * dodaje novi prazni podsjetnik u listu
     */
    public static void dodajNoviKontakt(){
        
        Kontakt prazniKontakt = new Kontakt("", "", "", "", "");
        
        Kontakt.getAlSviKontakti().add(prazniKontakt);

//        PodsjetnikPomocni.getAlSviPomocniPodsjetnici().add(new PodsjetnikPomocni(prazniPodsjetnik.getStrOpis(), prazniPodsjetnik.getStrPocetak(), prazniPodsjetnik.getStrKraj()));
    }
    
    /**
     * brise oznacene podsjetnike
     */
    public static void obrisiKontakte() {
        //prolazi kroz listu uredjaja i ukoliko je koji označen za brisanje, briše ga
        for (Iterator<Kontakt> iterator = Kontakt.getAlSviKontakti().iterator(); iterator.hasNext();) {
            Kontakt next = iterator.next();
            if (next.getJcbObrisi().isSelected()){
                iterator.remove();
            }
        }
    }
    
    /**
     * sortiranje po upravi
     */
    public static void sortirajPoUpravi(){
        
        if (boolSortirajUzlaznoUprava) {
            //sortiranje postavki po upravi uzlazno
            Collections.sort(Kontakt.getAlSviKontakti(), new Comparator<Kontakt>() {

                public int compare(Kontakt o1, Kontakt o2) {
                    return o1.getJtfUprava().getText().compareTo(o2.getJtfUprava().getText());
                }
            });
            boolSortirajUzlaznoUprava = false;
//            JpKontakt.getJlUprava().setText("Uprava /\\");
        }
        
        else{
            //sortiranje postavki po upravi silazno
            Collections.sort(Kontakt.getAlSviKontakti(), new Comparator<Kontakt>() {

                public int compare(Kontakt o1, Kontakt o2) {
                    return o2.getJtfUprava().getText().compareTo(o1.getJtfUprava().getText());
                }
            });
            boolSortirajUzlaznoUprava = true;
//            JpKontakt.getJlUprava().setText("Uprava \\/");
        }
    }
    
    /**
     * sortiranje po upravi
     */
    public static void sortirajPoPostaji(){
        
        if (boolSortirajUzlaznoPostaja) {
            //sortiranje postavki po upravi uzlazno
            Collections.sort(Kontakt.getAlSviKontakti(), new Comparator<Kontakt>() {

                public int compare(Kontakt o1, Kontakt o2) {
                    return o1.getJtfPostaja().getText().compareTo(o2.getJtfPostaja().getText());
                }
            });
            boolSortirajUzlaznoPostaja = false;
        }
        
        else{
            //sortiranje postavki po upravi silazno
            Collections.sort(Kontakt.getAlSviKontakti(), new Comparator<Kontakt>() {

                public int compare(Kontakt o1, Kontakt o2) {
                    return o2.getJtfPostaja().getText().compareTo(o1.getJtfPostaja().getText());
                }
            });
            boolSortirajUzlaznoPostaja = true;
        }
    }
    
    /**
     * sortiranje po upravi
     */
    public static void sortirajPoImenuNazivu(){
        
        if (boolSortirajUzlaznoImeNaziv) {
            //sortiranje postavki po upravi uzlazno
            Collections.sort(Kontakt.getAlSviKontakti(), new Comparator<Kontakt>() {

                public int compare(Kontakt o1, Kontakt o2) {
                    return o1.getJtfImeNaziv().getText().compareTo(o2.getJtfImeNaziv().getText());
                }
            });
            boolSortirajUzlaznoImeNaziv = false;
        }
        
        else{
            //sortiranje postavki po upravi silazno
            Collections.sort(Kontakt.getAlSviKontakti(), new Comparator<Kontakt>() {

                public int compare(Kontakt o1, Kontakt o2) {
                    return o2.getJtfImeNaziv().getText().compareTo(o1.getJtfImeNaziv().getText());
                }
            });
            boolSortirajUzlaznoImeNaziv = true;
        }
    }
    
    /**
     * sortiranje po upravi
     */
    public static void sortirajPoBroju(){
        
        if (boolSortirajUzlaznoBroj) {
            //sortiranje postavki po upravi uzlazno
            Collections.sort(Kontakt.getAlSviKontakti(), new Comparator<Kontakt>() {

                public int compare(Kontakt o1, Kontakt o2) {
                    return o1.getJtfBroj().getText().compareTo(o2.getJtfBroj().getText());
                }
            });
            boolSortirajUzlaznoBroj = false;
        }
        
        else{
            //sortiranje postavki po upravi silazno
            Collections.sort(Kontakt.getAlSviKontakti(), new Comparator<Kontakt>() {

                public int compare(Kontakt o1, Kontakt o2) {
                    return o2.getJtfBroj().getText().compareTo(o1.getJtfBroj().getText());
                }
            });
            boolSortirajUzlaznoBroj = true;
        }
    }

    public static boolean isBoolSortirajUzlaznoUprava() {
        return boolSortirajUzlaznoUprava;
    }

    public static void setBoolSortirajUzlaznoUprava(boolean boolSortirajUzlaznoUprava) {
        MetodeKontakt.boolSortirajUzlaznoUprava = boolSortirajUzlaznoUprava;
    }

    public static boolean isBoolSortirajUzlaznoPostaja() {
        return boolSortirajUzlaznoPostaja;
    }

    public static void setBoolSortirajUzlaznoPostaja(boolean boolSortirajUzlaznoPostaja) {
        MetodeKontakt.boolSortirajUzlaznoPostaja = boolSortirajUzlaznoPostaja;
    }

    public static boolean isBoolSortirajUzlaznoImeNaziv() {
        return boolSortirajUzlaznoImeNaziv;
    }

    public static void setBoolSortirajUzlaznoImeNaziv(boolean boolSortirajUzlaznoImeNaziv) {
        MetodeKontakt.boolSortirajUzlaznoImeNaziv = boolSortirajUzlaznoImeNaziv;
    }

    public static boolean isBoolSortirajUzlaznoBroj() {
        return boolSortirajUzlaznoBroj;
    }

    public static void setBoolSortirajUzlaznoBroj(boolean boolSortirajUzlaznoBroj) {
        MetodeKontakt.boolSortirajUzlaznoBroj = boolSortirajUzlaznoBroj;
    }
    
    
}
