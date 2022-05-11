
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ahilis
 */
import controler.Datoteke;
import controler.MetodePostavki;
import model.Postavke;
import view.jf.JfPocetna;

public class Main {
    
    public static void main(String[] args){  
        
        //todo obr
//        NativeSwing.initialize();
        
//        //http://live.radio.si/RockMB
//                <radio>
//			<naziv>Sjeverozapad</naziv>
//			<URL>https://azuracast.novi-net.net/radio/8000/</URL>
//		</radio>
//        try {
//            Files.copy(Paths.get("C:\\Users\\Korisnik\\Desktop\\asistent/icons/13n.png"), Paths.get("resursi\\icons/13n.png"), StandardCopyOption.REPLACE_EXISTING);
//        } catch (Exception ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }






        
        
        MetodePostavki.ucitajPostavke("resursi/appData/postavke.xml");
        
        if (Postavke.dajPostavku("sinkronizacijaUkljucena").equals("1") || Postavke.dajPostavku("sinkronizacijaUkljucena").equals("true")) {

            Datoteke.downloadSvihDatotekaMapeSURLa(Postavke.dajPostavku("URLStraniceResursa") + Postavke.dajPostavku("nazivAplikacije") + "/", Postavke.dajPostavku("mapaResursa"));
            Datoteke.downloadSvihDatotekaMapeSURLa(Postavke.dajPostavku("URLStraniceResursa") + Postavke.dajPostavku("nazivAplikacije") + "/" + Postavke.dajPostavku("mapaKorisnihDatoteka"), Postavke.dajPostavku("mapaResursa")+Postavke.dajPostavku("mapaKorisnihDatoteka"));
            if (Postavke.dajPostavku("sinkronizacijaIkona").equals("true")) {
                Datoteke.downloadSvihDatotekaMapeSURLa(Postavke.dajPostavku("URLStraniceResursa") + Postavke.dajPostavku("nazivAplikacije") + "/" + Postavke.dajPostavku("mapaIkona"), Postavke.dajPostavku("mapaResursa")+Postavke.dajPostavku("mapaIkona"));
            }
        }
        
        JfPocetna.generirajObrazac();
    }

}
