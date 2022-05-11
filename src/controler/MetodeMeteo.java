/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import model.MeteoPodaci;
import model.Postavke;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import view.jp.JpPrognoza;

/**
 *
 * @author iduras
 */
public class MetodeMeteo {
    
    private static ArrayList<MeteoPodaci> listaMeteoPodataka = new ArrayList<>();
    static SimpleDateFormat formatterSat = new SimpleDateFormat("HH:mm");
    static SimpleDateFormat formatterDatum = new SimpleDateFormat("dd.MM.yyyy.");
//    static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
    
    
    /**
     * Povlači meteo podatke.
     * @param gradDrzava
     * @return lista meteo podataka
     */
    public static ArrayList<MeteoPodaci> povuciPodatkeSve(String gradDrzava){
        listaMeteoPodataka.clear();
        povuciPodatkeDanas(gradDrzava, listaMeteoPodataka);
        
        Datoteke.kopirajDatoteku(Postavke.dajPostavku("putanjaDatotekeMeteoPrognoza"));
//        spajanje i preuzimanje stringa u json formatu koji sadrži meteo podatke
        try {   
            String strURL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=" + 
                    gradDrzava + 
                    "&units=metric&appid=" + 
                    Postavke.dajPostavku("meteoOMWKey");
            Datoteke.upisUDatoteku(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("putanjaDatotekeMeteoPrognoza"), Datoteke.citanjeIzURLa(strURL).toString());
            
        } catch (Exception e) {
//            Pocetna.getjTextFieldPoruka().setText("Greška kod preuzimanja meteo podataka. Pokušaj kliknuti na gumb 'Osvježi'.");
//            MetodeTajmera.dodajULog("MetodeMeteo - povuciPodatkeSve - Greška kod preuzimanja meteo podataka.");
        }
            
        StringBuilder builder = Datoteke.citanjeIzDatoteke(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("putanjaDatotekeMeteoPrognoza"));

        //kreiranje json objekta iz stringa
        JsonObject body = Json.createReader(new StringReader(builder.toString())).readObject();

        //uzimanje liste meteo podataka
        JsonArray list = body.getJsonArray("list");

        //popunjavanje liste meteo podacija za svaki dan
        for (JsonValue jsonValue : list) {
            JsonObject jo = (JsonObject) jsonValue;

            MeteoPodaci mp = new MeteoPodaci();

            mp.setLastUpdate(new Date(jo.getJsonNumber("dt").bigDecimalValue().longValue()*1000));
            mp.setSunRise(new Date(jo.getJsonNumber("sunrise").bigDecimalValue().longValue()*1000));
            mp.setSunSet(new Date(jo.getJsonNumber("sunset").bigDecimalValue().longValue()*1000));

            mp.setTemperatureValue(new Double(jo.getJsonObject("temp").getJsonNumber("day").doubleValue()).floatValue());
            mp.setTemperatureMin(new Double(jo.getJsonObject("temp").getJsonNumber("min").doubleValue()).floatValue());
            mp.setTemperatureMax(new Double(jo.getJsonObject("temp").getJsonNumber("max").doubleValue()).floatValue());
            mp.setTemperatureUnit("celsius");

            mp.setHumidityValue(new Double(jo.getJsonNumber("humidity").doubleValue()).floatValue());
            mp.setHumidityUnit("%");

            mp.setPressureValue(new Double(jo.getJsonNumber("pressure").doubleValue()).floatValue());
            mp.setPressureUnit("hPa");

//                if (jo.getJsonObject("wind").getJsonNumber("speed") == null) {
//                    mp.setWindSpeedValue(new Double(("0")).floatValue());
//                }
//                else{
//                    mp.setWindSpeedValue(new Double(jo.getJsonObject("wind").getJsonNumber("speed").doubleValue()).floatValue());
//                }
//                mp.setWindSpeedName("");

//                if (jo.getJsonObject("wind").getJsonNumber("deg") == null) {
//                    mp.setWindDirectionValue(new Double(("0")).floatValue());
//                }
//                else{
//                    mp.setWindDirectionValue(new Double(jo.getJsonObject("wind").getJsonNumber("deg").doubleValue()).floatValue());
//                }
//                mp.setWindDirectionCode("");
//                mp.setWindDirectionName("");

//                mp.setCloudsValue(jo.getJsonObject("clouds").getInt("all"));
            mp.setCloudsName(jo.getJsonArray("weather").getJsonObject(0).getString("description"));
            mp.setPrecipitationMode("");

            mp.setWeatherNumber(jo.getJsonArray("weather").getJsonObject(0).getInt("id"));
            mp.setWeatherValue(jo.getJsonArray("weather").getJsonObject(0).getString("description"));
            mp.setWeatherIcon(jo.getJsonArray("weather").getJsonObject(0).getString("icon"));

            listaMeteoPodataka.add(mp);
        }
         
        
        Datoteke.brisiKopijuDatoteke(Postavke.dajPostavku("putanjaDatotekeMeteoPrognoza"));
        
        return listaMeteoPodataka;
    }
    
    /**
     * Povlači meteo podatke za danas.
     * @param gradDrzava
     * @param listaMeteoPodataka
     */
    public static void povuciPodatkeDanas(String gradDrzava, List<MeteoPodaci> listaMeteoPodataka){
        
        Datoteke.kopirajDatoteku(Postavke.dajPostavku("putanjaDatotekeMeteoDanas"));

        try {
            String strURL = "http://api.openweathermap.org/data/2.5/weather?q=" + gradDrzava + "&units=metric&appid=" + Postavke.dajPostavku("meteoOMWKey");
                Datoteke.upisUDatoteku(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("putanjaDatotekeMeteoDanas"), Datoteke.citanjeIzURLa(strURL).toString());
        } catch (Exception e) {
//            Pocetna.getjTextFieldPoruka().setText("Greška kod preuzimanja meteo podataka. Pokušaj kliknuti na gumb 'Osvježi'.");
//            MetodeTajmera.dodajULog("MetodeMeteo - povuciPodatkeDanas - Greška kod preuzimanja meteo podataka.");
        }

        StringBuilder builder = Datoteke.citanjeIzDatoteke(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("putanjaDatotekeMeteoDanas"));
            
        JsonObject jo = Json.createReader(new StringReader(builder.toString())).readObject();

        MeteoPodaci mp = new MeteoPodaci();

        mp.setSunRise(new Date(jo.getJsonObject("sys").getJsonNumber("sunrise").bigDecimalValue().longValue()*1000));
        mp.setSunSet(new Date(jo.getJsonObject("sys").getJsonNumber("sunset").bigDecimalValue().longValue()*1000));

        mp.setTemperatureValue(new Double(jo.getJsonObject("main").getJsonNumber("temp").doubleValue()).floatValue());
        mp.setTemperatureMin(new Double(jo.getJsonObject("main").getJsonNumber("temp_min").doubleValue()).floatValue());
        mp.setTemperatureMax(new Double(jo.getJsonObject("main").getJsonNumber("temp_max").doubleValue()).floatValue());
        mp.setTemperatureUnit("°C");

        mp.setHumidityValue(new Double(jo.getJsonObject("main").getJsonNumber("humidity").doubleValue()).floatValue());
        mp.setHumidityUnit("%");

        mp.setPressureValue(new Double(jo.getJsonObject("main").getJsonNumber("pressure").doubleValue()).floatValue());
        mp.setPressureUnit("hPa");

        if (jo.getJsonObject("wind").getJsonNumber("speed") == null) {
            mp.setWindSpeedValue(new Double(("0")).floatValue());
        }
        else{
            mp.setWindSpeedValue(new Double(jo.getJsonObject("wind").getJsonNumber("speed").doubleValue()).floatValue());
        }
        mp.setWindSpeedName("");

        if (jo.getJsonObject("wind").getJsonNumber("deg") == null) {
            mp.setWindDirectionValue(new Double(("0")).floatValue());
        }
        else{
            mp.setWindDirectionValue(new Double(jo.getJsonObject("wind").getJsonNumber("deg").doubleValue()).floatValue());
        }
        mp.setWindDirectionCode("");
        mp.setWindDirectionName("");

        mp.setCloudsValue(jo.getJsonObject("clouds").getInt("all"));
        mp.setCloudsName(jo.getJsonArray("weather").getJsonObject(0).getString("description"));
        mp.setPrecipitationMode("");

        mp.setWeatherNumber(jo.getJsonArray("weather").getJsonObject(0).getInt("id"));
        mp.setWeatherValue(jo.getJsonArray("weather").getJsonObject(0).getString("description"));
        mp.setWeatherIcon(jo.getJsonArray("weather").getJsonObject(0).getString("icon"));

        mp.setLastUpdate(new Date(jo.getJsonNumber("dt").bigDecimalValue().longValue()*1000));

        listaMeteoPodataka.add(mp);

        Datoteke.brisiKopijuDatoteke(Postavke.dajPostavku("putanjaDatotekeMeteoDanas"));
        
    }
    
    /**
     * učitavanje precaca iz dokumenta 
     * @param putanja
     */
    public static void pripremiMeteo(String putanja){
        try {
            
            //otvaranje dokumenta na putanji i popunjavanje precaca
            Document document = (Document) Jsoup.parse(Datoteke.citanjeIzDatoteke(putanja).toString(),"", Parser.xmlParser());
            
            for (Element e : document.select("postavke")) {
                JpPrognoza.getPostavkePanela().pripremiLokacijuPanela(e.select("koordinate").text());
                JpPrognoza.getPostavkePanela().pripremiBojuPanela(e.select("bojaPozadinePanela").text(), e.select("bojaFontaPanela").text(), e.select("bojaFontaNaslovaPanela").text());  
                JpPrognoza.getPostavkePanela().pripremiSlovaPanela(e.select("velicinaFontaNaslovaPanela").text(), 
                                                                     e.select("velicinaFontaTekstaPanela").text(),
                                                                     e.select("naziv").text(), 
                                                                     e.select("vrstaFontaNaslovaPanela").text(), 
                                                                     e.select("vrstaFontaTekstaPanela").text());     
            }
        } catch (Exception e) {
        }
        
    }

    public static void spremiPrognozu() {
//        String strZaUpis = "<root>\n" +
//                           "\t<podsjetnici>\n\n";
//        System.out.println(PodsjetnikPomocni.getAlSviPomocniPodsjetnici().size());
//        for (Podsjetnik podsjetnik : Podsjetnik.getAlSviPodsjetnici()) {
////            System.out.println("odpas - " + podsjetnikPomocni.getJtfOpis().getText());
//            if (!podsjetnik.getJtfOpis().getText().equals("")) {
//                strZaUpis += "\t\t<podsjetnik>\n"+
//                             "\t\t\t<opis>" + podsjetnik.getJtfOpis().getText()+"</opis>\n"+
//                             "\t\t\t<pocetak>" + podsjetnik.getJtfPocetak().getText()+"</pocetak>\n"+
//                             "\t\t\t<kraj>" + podsjetnik.getJtfKraj().getText()+"</kraj>\n"+
//                             "\t\t</podsjetnik>\n\n";
//            }
//        }
        
        String strZaUpis =  "<root>\n" +
                            "\t<postavke>\n"+
                            "\t\t<koordinate>" + JpPrognoza.getPostavkePanela().getJtfKoordinate().getText()+"</koordinate>\n"+
                            "\t\t<bojaPozadinePanela>" + JpPrognoza.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela().getText()+"</bojaPozadinePanela>\n"+
                            "\t\t<bojaFontaPanela>" + JpPrognoza.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela().getText()+"</bojaFontaPanela>\n"+
                            "\t\t<bojaFontaNaslovaPanela>" + JpPrognoza.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela().getText()+"</bojaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaNaslovaPanela>" + JpPrognoza.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela().getText()+"</velicinaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaTekstaPanela>" + JpPrognoza.getPostavkePanela().getJtfVelicinaFontaTekstaPanela().getText()+"</velicinaFontaTekstaPanela>\n"+
                            "\t\t<vrstaFontaNaslovaPanela>" + JpPrognoza.getPostavkePanela().getJtfVrstaFontaNaslovaPanela().getText()+"</vrstaFontaNaslovaPanela>\n"+
                            "\t\t<vrstaFontaTekstaPanela>" + JpPrognoza.getPostavkePanela().getJtfVrstaFontaTekstaPanela().getText()+"</vrstaFontaTekstaPanela>\n"+
                            "\t\t<naziv>" + JpPrognoza.getPostavkePanela().getJtfNazivPanela().getText()+"</naziv>\n"+
                            "\t</postavke>\n"+
                            "</root>";
        
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("datotekaPrognoze")), "UTF-8"));
            try {
                out.write(strZaUpis);
            } finally {
                out.close();
            }
        } catch (Exception e) {
        }
    }
    
    /**
     * Daje ikonu za prikaz vremenskih uvijeta.
     * @param ikonaID
     * @return 
     */
    static public Image dajIkonu(String ikonaID){
//  static public Image dajIkonu(String ikonaID, String velicina){
        Image ikona = null;
        
        try {
            //za prikaz ikone iz URL-a
//            URL url = new URL("http://openweathermap.org/img/wn/" + ikonaID + velicina + ".png");
//            ikona = ImageIO.read(url);

            //za prikaz ikone iz mape
            File file = new File(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("mapaIkona") + ikonaID + ".png");
            ikona = ImageIO.read(file);
        } catch (IOException e) {
//            Pocetna.getjTextFieldPoruka().setText("Greška kod Preuzimanja ikone. Pokušaj kliknuti na gumb 'Osvježi'.");
//            MetodeTajmera.dodajULog("MetodeMeteo - dajIkonu - Greška kod preuzimanja ikone.");
//            e.printStackTrace();
        }
        return ikona;
    }

    public static ArrayList<MeteoPodaci> getListaMeteoPodataka() {
        return listaMeteoPodataka;
    }

    public static void setListaMeteoPodataka(ArrayList<MeteoPodaci> listaMeteoPodataka) {
        MetodeMeteo.listaMeteoPodataka = listaMeteoPodataka;
    }

    public static SimpleDateFormat getFormatterSat() {
        return formatterSat;
    }

    public static void setFormatterSat(SimpleDateFormat formatterSat) {
        MetodeMeteo.formatterSat = formatterSat;
    }

    public static SimpleDateFormat getFormatterDatum() {
        return formatterDatum;
    }

    public static void setFormatterDatum(SimpleDateFormat formatterDatum) {
        MetodeMeteo.formatterDatum = formatterDatum;
    }
}
