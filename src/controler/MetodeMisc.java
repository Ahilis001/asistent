/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import model.Postavke;

/**
 *
 * @author Ahilis
 */
public class MetodeMisc {
    
    /**
     * azurira sadrzaj iz datoteka
     */
    public static void azurirajSadrzaj(){
        
        MetodeSat.pripremiSat(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("datotekaSata"));
        
        //priprema podataka za jpPrecaci
        if (Postavke.dajPostavku("prikaziPrecace").equals("1") || Postavke.dajPostavku("prikaziPrecace").equals("true")) {
            
            MetodePrecac.pripremiPrecac(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("datotekaPrecaca"));
        }
        
        //priprema podataka za jpPodsjetnici
        if (Postavke.dajPostavku("prikaziPodsjetnike").equals("1") || Postavke.dajPostavku("prikaziPodsjetnike").equals("true")) {
            
            //učitavanje podsjetnika
            MetodePodsjetnik.pripremiPodsjetnik(Postavke.dajPostavku("mapaResursa")+Postavke.dajPostavku("datotekaPodsjetnika"));
        }
            
        //priprema podataka za jpPoveznice
        if (Postavke.dajPostavku("prikaziPoveznice").equals("1") || Postavke.dajPostavku("prikaziPoveznice").equals("true")) {
            
            //učitavanje poveznica
            MetodePoveznice.pripremiPoveznicu(Postavke.dajPostavku("mapaResursa")+Postavke.dajPostavku("datotekaPoveznica"));
        }
            
        //priprema podataka za jpKirsneDatoteke
        if (Postavke.dajPostavku("prikaziKorisneDatoteke").equals("1") || Postavke.dajPostavku("prikaziKorisneDatoteke").equals("true")) {
            
            //učitavanje poveznica
            MetodeKorisnihDatoteka.pripremiKorisnuDatoteku(Postavke.dajPostavku("mapaResursa")+Postavke.dajPostavku("datotekaKorisnihDatoteka"));
        }
            
        //priprema podataka za jpRadio
        if (Postavke.dajPostavku("prikaziRadije").equals("1") || Postavke.dajPostavku("prikaziRadije").equals("true")) {
          
            //učitavanje radija
            MetodeRadio.pripremiRadio(Postavke.dajPostavku("mapaResursa")+Postavke.dajPostavku("datotekaRadija"));
        }
            
        //priprema podataka za jpPrognoza
        if (Postavke.dajPostavku("prikaziPrognozu").equals("1") || Postavke.dajPostavku("prikaziPrognozu").equals("true")) {
            
            MetodeMeteo.pripremiMeteo(Postavke.dajPostavku("mapaResursa")+Postavke.dajPostavku("datotekaPrognoze"));
        }

        //priprema podataka za jpKontakt
        if (Postavke.dajPostavku("prikaziKontakte").equals("1") || Postavke.dajPostavku("prikaziKontakte").equals("true")) {
        
            //učitavanje podsjetnika
            MetodeKontakt.pripremiKontakt(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("datotekaKontakata"));
        }
        
        //priprema podataka za jpUredivanje
        if (Postavke.dajPostavku("prikaziUredivanje").equals("1") || Postavke.dajPostavku("prikaziUredivanje").equals("true")) {
            
            MetodeUredivanje.pripremiUredivanje(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("datotekaUredivanja"));
        }

        //priprema podataka za jpRacunanjeDana
        if (Postavke.dajPostavku("prikaziRacunanjeDana").equals("1") || Postavke.dajPostavku("prikaziRacunanjeDana").equals("true")) {
        
            //učitavanje podsjetnika
            MetodeRacunanjaDana.pripremiDan(Postavke.dajPostavku("mapaResursa") + Postavke.dajPostavku("datotekaRacunanjaDana"));
        }
    }
}