package controler;

import com.sun.jna.Native;
import com.sun.jna.PointerType;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Postavke;
import model.Precac;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import view.jf.JfTestKodovaTipki;
import view.jp.JpPrecaci;
import view.jp.JpSat;

public class MetodePrecac implements NativeKeyListener{
    
    static boolean boolTipkeRegistrirane = false;

    public MetodePrecac() {
    }

    public interface User32 extends StdCallLibrary {
       
        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
        HWND GetForegroundWindow();  // add this
        int GetWindowTextA(PointerType hWnd, byte[] lpString, int nMaxCount);
   }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
//        System.out.println("typed");
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.dh
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
        
        try {
            JfTestKodovaTipki.getJlTest().setText(Integer.toString(nke.getRawCode()));
            JpPrecaci.getJlTest().setText(Integer.toString(nke.getRawCode()));
            
            byte[] windowText = new byte[512];

            //"uzimanje" naziva aktivnog prozora
            PointerType hwnd = User32.INSTANCE.GetForegroundWindow();
            User32.INSTANCE.GetWindowTextA(hwnd, windowText, 512);

            //bool za provjeru ako postoji aplikacija na popisu
            boolean boolIzvrsiZaAplikaciju = false;
      
            for (String string : JpPrecaci.getAlListaNazivaAplikacija()) {
                if (Native.toString(windowText).contains(string.replace(" ", ""))) {
                    boolIzvrsiZaAplikaciju = true;
                }
            }
            
            //trazenje i izvrsavanje precaca
            for (Precac precac : Precac.getAlSviPrecaci()) {
                
                if (precac.getJtfKodTipke().getText().equals(Integer.toString(nke.getRawCode())) 
                        && precac.getJcbPrikazi().isSelected() 
                        && (boolIzvrsiZaAplikaciju || precac.getJcbSveAplikacije().isSelected())) {

                    //inicijalizacija robota za pritisak gumbova
                    Robot robot = new Robot();

                    for (String string : precac.getAlStrKodTipki()) {

                        try {
                            //pritiskanje i otpuštanje gumbova
                            robot.keyPress(Integer.parseInt(string)); 
                            robot.keyRelease(Integer.parseInt(string));
                            Thread.sleep(Integer.parseInt(Postavke.dajPostavku("cekanjeIzmeduPritiskaTipkeUDesetkamaSekundi")) * 100);

                        } catch (NumberFormatException e) {
                            //ako sadrzi znak "-:-", koji predstavlja kombinaciju gumbova
                            if (string.contains("-:-")) {

                                //radi se nova lista koja se popunjava na način da se 
                                //tipke razdvajaju prema znaku
                                ArrayList<String> alListaStringova = new ArrayList<>();
                                alListaStringova.addAll(Arrays.asList(string.split("-:-")));

                                //pritiskanje svake tipke
                                for (int i = 0; i < alListaStringova.size(); i++) {
                                    robot.keyPress(Integer.parseInt(alListaStringova.get(i)));

                                }

                                //otpuštanje svake tipke obrnutim redoslijedom
                                for (int i = alListaStringova.size() - 1; i >= 0; i--) {
                                    robot.keyRelease(Integer.parseInt(alListaStringova.get(i)));
                                }

                            } 
                            
                            else if (string.contains("x")) {
                                
                                try {
                                    
                                    Integer intBrojPonavljanja = Integer.valueOf(string.substring(0, string.indexOf("x")));
                                    Integer intKodTipke = Integer.valueOf(string.substring(string.indexOf("x") + 1, string.length()));

                                    for (int i = 0; i < intBrojPonavljanja; i++) {
                                        
                                        robot.keyPress(intKodTipke); 
                                        robot.keyRelease(intKodTipke);
                                        Thread.sleep(Integer.parseInt(Postavke.dajPostavku("cekanjeIzmeduPritiskaTipkeUDesetkamaSekundi")) * 100);

                                    }
                                    
                                    
                                } catch (Exception ex) {
                                    
                                    //kopira i lijepi tekst
                                    StringSelection stringSelection = new StringSelection(string);
                                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                    clipboard.setContents(stringSelection, stringSelection);

                                    robot.keyPress(KeyEvent.VK_CONTROL); 
                                    //za mac
    //                                robot.keyPress(KeyEvent.VK_META);
                                    robot.keyPress(KeyEvent.VK_V);
                                    robot.keyRelease(KeyEvent.VK_V);
                                    robot.keyRelease(KeyEvent.VK_CONTROL);
                                    //za mac
    //                                robot.keyRelease(KeyEvent.VK_META);
                                    
                                    
                                }
                                
                                //radi se nova lista koja se popunjava na način da se 
                                //tipke razdvajaju prema znaku
//                                ArrayList<String> alListaStringova = new ArrayList<>();
//                                alListaStringova.addAll(Arrays.asList(string.split("-:-")));
//
//                                //pritiskanje svake tipke
//                                for (int i = 0; i < alListaStringova.size(); i++) {
//                                    robot.keyPress(Integer.parseInt(alListaStringova.get(i)));
//
//                                }
//
//                                //otpuštanje svake tipke obrnutim redoslijedom
//                                for (int i = alListaStringova.size() - 1; i >= 0; i--) {
//                                    robot.keyRelease(Integer.parseInt(alListaStringova.get(i)));
//                                }

                            } 

//                            inače kopira i lijepi tekst
                            else {
                                StringSelection stringSelection = new StringSelection(string);
                                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                clipboard.setContents(stringSelection, stringSelection);

                                robot.keyPress(KeyEvent.VK_CONTROL); 
//                                //za mac
//                                robot.keyPress(KeyEvent.VK_META);
                                robot.keyPress(KeyEvent.VK_V);
                                robot.keyRelease(KeyEvent.VK_V);
                                robot.keyRelease(KeyEvent.VK_CONTROL);
//                                //za mac
//                                robot.keyRelease(KeyEvent.VK_META);

                            }
                        }
                    }   
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
    }
        
    public static void registracijaTipki(){
        
        if (!boolTipkeRegistrirane) {
            //registriranje hooka
            try {
                GlobalScreen.registerNativeHook();

            } catch (Exception e) {
            }

            //dodavanje novog slušaca
            GlobalScreen.addNativeKeyListener(new MetodePrecac());

            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);
            
            boolTipkeRegistrirane = true;
        }
        
    }
    
    /**
     * učitavanje precaca iz dokumenta 
     * @param putanja
     */
    public static void pripremiPrecac(String putanja){
        try {
            //ciscenje liste svih precaca
            Precac.getAlSviPrecaci().clear();

            //otvaranje dokumenta na putanji i popunjavanje precaca
            Document document = (Document) Jsoup.parse(Datoteke.citanjeIzDatoteke(putanja).toString(),"", Parser.xmlParser());

            //popunjavanje liste mogućnosti
            for (Element e : document.select("precac")) {
                Precac precac = new Precac(e.select("tipka").text(), e.select("operacija").text(), e.select("opis").text(), e.select("prikazi").text(), e.select("sveAplikacije").text());
                Precac.getAlSviPrecaci().add(precac);
            }
            
            for (Element e : document.select("postavke")) {
                JpPrecaci.getPostavkePanela().pripremiLokacijuPanela(e.select("koordinate").text());
                JpPrecaci.getPostavkePanela().pripremiBojuPanela(e.select("bojaPozadinePanela").text(), e.select("bojaFontaPanela").text(), e.select("bojaFontaNaslovaPanela").text());  
                JpPrecaci.getPostavkePanela().pripremiSlovaPanela(e.select("velicinaFontaNaslovaPanela").text(), 
                                                                     e.select("velicinaFontaTekstaPanela").text(),
                                                                     e.select("naziv").text(), 
                                                                     e.select("vrstaFontaNaslovaPanela").text(), 
                                                                     e.select("vrstaFontaTekstaPanela").text());    
            }
        } catch (Exception e) {
            Precac precac = new Precac("1", "1", "Greška kod učitavanja!", "1", "1");
            Precac.getAlSviPrecaci().add(precac);
        }
        
    }
    
    /**
     * sprema podsjetnike u datoteku
     */
    public static void spremiPrecace(){
        
        Precac.sortiranjeListe();
        
        String strZaUpis = "<root>\n" +
                           "\t<precaci>\n\n";
        
        for (Precac precac : Precac.getAlSviPrecaci()) {
            if (!precac.getJtfOpis().getText().equals("")) {
                strZaUpis += "\t\t<precac>\n"+
                             "\t\t\t<opis>" + precac.getJtfOpis().getText()+"</opis>\n"+
                             "\t\t\t<tipka>" + precac.getJtfKodTipke().getText()+"</tipka>\n"+
                             "\t\t\t<operacija>" + precac.getJtfKodTipki().getText()+"</operacija>\n"+
                             "\t\t\t<prikazi>" + precac.getJcbPrikazi().isSelected() +"</prikazi>\n"+
                             "\t\t\t<sveAplikacije>" + precac.getJcbSveAplikacije().isSelected() +"</sveAplikacije>\n"+
                             "\t\t</precac>\n\n";
            }
        }
        
        strZaUpis +=        "\t</precaci>\n\n"+
                            "\t<postavke>\n"+
                            "\t\t<koordinate>" + JpPrecaci.getPostavkePanela().getJtfKoordinate().getText()+"</koordinate>\n"+
                            "\t\t<bojaPozadinePanela>" + JpPrecaci.getPostavkePanela().getJtfUredenaCetvorkaBojaPozadinePanela().getText()+"</bojaPozadinePanela>\n"+
                            "\t\t<bojaFontaPanela>" + JpPrecaci.getPostavkePanela().getJtfUredenaTrojkaBojaFontaPanela().getText()+"</bojaFontaPanela>\n"+
                            "\t\t<bojaFontaNaslovaPanela>" + JpPrecaci.getPostavkePanela().getJtfUredenaTrojkaBojaNaslovaPanela().getText()+"</bojaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaNaslovaPanela>" + JpPrecaci.getPostavkePanela().getJtfVelicinaFontaNaslovaPanela().getText()+"</velicinaFontaNaslovaPanela>\n"+
                            "\t\t<velicinaFontaTekstaPanela>" + JpPrecaci.getPostavkePanela().getJtfVelicinaFontaTekstaPanela().getText()+"</velicinaFontaTekstaPanela>\n"+
                            "\t\t<vrstaFontaNaslovaPanela>" + JpPrecaci.getPostavkePanela().getJtfVrstaFontaNaslovaPanela().getText()+"</vrstaFontaNaslovaPanela>\n"+
                            "\t\t<vrstaFontaTekstaPanela>" + JpPrecaci.getPostavkePanela().getJtfVrstaFontaTekstaPanela().getText()+"</vrstaFontaTekstaPanela>\n"+
                            "\t\t<naziv>" + JpPrecaci.getPostavkePanela().getJtfNazivPanela().getText()+"</naziv>\n"+
                            "\t</postavke>\n"+
                            "</root>";
        
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("datotekaPrecaca")), "UTF-8"));
            try {
                out.write(strZaUpis);
            } finally {
                out.close();
            }
        } catch (Exception e) {
        }
        
        //učitavanje precaca
        JpPrecaci.azurirajObrazac();
    }
    
    /**
     * dodaje novi prazni podsjetnik u listu
     */
    public static void dodajNoviPrecac(){
        
        Precac prazniPrecac = new Precac("", "", "", "true", "false");
        
        Precac.getAlSviPrecaci().add(prazniPrecac);

//        PrecacPomocni.getAlSviPomocniPrecaci().add(new PrecacPomocni(prazniPrecac.getStrOpis(), prazniPrecac.getStrKodTipke(), prazniPrecac.getAlStrKodTipki()));
    }
    
    /**
     * brise oznacene precace
     */
    public static void obrisiPrecace() {
        //prolazi kroz listu uredjaja i ukoliko je koji označen za brisanje, briše ga
        for (Iterator<Precac> iterator = Precac.getAlSviPrecaci().iterator(); iterator.hasNext();) {
            Precac next = iterator.next();
            if (next.getJcbObrisi().isSelected()){
                iterator.remove();
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}