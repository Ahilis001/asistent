/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import model.PostavkePanela;
import model.Podsjetnik;
import model.Postavke;
import view.jf.JfPocetna;

/**
 *
 * @author Ahilis
 */
public class JpPodsjetnici extends JPanel{
    
    static JpPodsjetnici jpPodsjetnici = new JpPodsjetnici();
    static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
    static PostavkePanela postavkePanela = new PostavkePanela();
    static int intAktivniBrojStranice = Integer.parseInt(Postavke.dajPostavku("aktivniBrojStranicePodsjetnika"));
;
    
    public JpPodsjetnici() {
        
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        
        catch(Exception e){}
    }
    
    
    
    /**
     * generira obrazac podsjetnika
     * @return 
     */
    public static JpPodsjetnici generirajObrazac() {
        
        //postavljanje okvira
        jpPodsjetnici.setBorder(javax.swing.BorderFactory.createTitledBorder(null, postavkePanela.getJtfNazivPanela().getText(),
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font(postavkePanela.getJtfNazivPanela().getText(), 0, postavkePanela.getIntVelicinaFontaNaslovaPanela()), 
                new java.awt.Color(postavkePanela.getIntBojaFontaNaslovaPanelaR(), postavkePanela.getIntBojaFontaNaslovaPanelaG(), postavkePanela.getIntBojaFontaNaslovaPanelaB())));
        
        jpPodsjetnici.setBackground(new java.awt.Color(postavkePanela.getIntBojaPozadinePanelaR(), postavkePanela.getIntBojaPozadinePanelaG(), postavkePanela.getIntBojaPozadinePanelaB()));
        
        //azuriranje obrasca
        JpPodsjetnici.azurirajObrazac(intAktivniBrojStranice);
        
        return jpPodsjetnici;
    }
    
    /**
     * azurira obrazac podsjetnika
     */
    public static void azurirajObrazac(int intAktivnaStranica){
        
        int intMoguciBrojStranica;
        ArrayList<Podsjetnik> alPodsjetniciZaPrikaz = new ArrayList<>();
    
        //micanje svega iz jpPodsjetnika
        jpPodsjetnici.removeAll();
        
        //postavljanje layouta glavnog jpanela
        jpPodsjetnici.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        
        //postavljanje layouta jpanela koji sadrzi brojeve stranica
        JPanel jpStraniceBrojevi = new JPanel();
        jpStraniceBrojevi.setBackground(new java.awt.Color(postavkePanela.getIntBojaPozadinePanelaR(), postavkePanela.getIntBojaPozadinePanelaG(), postavkePanela.getIntBojaPozadinePanelaB()));
        
        //postavljanje layouta jpanela koji sadrzi sadrzaj stranica
        JPanel jpStraniceSadrzaj = new JPanel(new GridBagLayout());
        jpStraniceSadrzaj.setBackground(new java.awt.Color(postavkePanela.getIntBojaPozadinePanelaR(), postavkePanela.getIntBojaPozadinePanelaG(), postavkePanela.getIntBojaPozadinePanelaB()));
        
        //dodavanje podsjetnika koji zadovoljavaju uvijete 
        //za prikazati u alPodsjetniciZaPrikaz
        for (Podsjetnik podsjetnik : Podsjetnik.getAlSviPodsjetnici()) {
            
            
            //varijabla koja sluzi za odredivanje hoce li se
            //podsjetnik dodati u listu ili ne
            boolean boolPrikaziPodsjetnik = false;
            
            //ako postoji datum i vrijeme podsjetnika
            try {

                //inicijalizacija varijable trenutnog datuma
                Date dtTrenutno = new SimpleDateFormat("dd.MM.yyyy. HH:mm").parse(formatter.format(new Date(System.currentTimeMillis())));

                //ako su datumi u redu postavljeni
                if (!podsjetnik.getJtfPocetak().getText().equals("") && !podsjetnik.getJtfKraj().getText().equals("")) {

                    //inicijalizacija varijable početnog i završnog datuma
                    Date dtPocetak = new SimpleDateFormat("dd.MM.yyyy. HH:mm").parse(podsjetnik.getJtfPocetak().getText());
                    Date dtKraj = new SimpleDateFormat("dd.MM.yyyy. HH:mm").parse(podsjetnik.getJtfKraj().getText());

                    if (dtPocetak.before(dtKraj)) {
                        if (dtPocetak.before(dtTrenutno) && dtKraj.after(dtTrenutno)) {
                            boolPrikaziPodsjetnik = true;
                        }
                    }
                } 

                //ako je opis prazni, sluzi za popunjavanje stranica
                else if (podsjetnik.getJtfOpis().getText().equals("") && podsjetnik.getJtfPocetak().getText().equals("") && podsjetnik.getJtfKraj().getText().equals("")) {
                    boolPrikaziPodsjetnik = true;
                }

                //ako je početak prazni, a završetak nije
                else if (podsjetnik.getJtfPocetak().getText().equals("") && !podsjetnik.getJtfKraj().getText().equals("")) {

                    //inicijalizacija varijable završnog datuma
                    Date dtKraj = new SimpleDateFormat("dd.MM.yyyy. HH:mm").parse(podsjetnik.getJtfKraj().getText());

                    if (dtTrenutno.before(dtKraj)) {
                        boolPrikaziPodsjetnik = true;
                    }
                }

                //ako je početak nije prazni, a završetak je
                else if (!podsjetnik.getJtfPocetak().getText().equals("") && podsjetnik.getJtfKraj().getText().equals("")) {

                    //inicijalizacija varijable početnog datuma
                    Date dtPocetak = new SimpleDateFormat("dd.MM.yyyy. HH:mm").parse(podsjetnik.getJtfPocetak().getText());

                    if (dtPocetak.before(dtTrenutno)) {
                        boolPrikaziPodsjetnik = true;
                    }
                }

                //ako su prazni početak i kraj
                else if (podsjetnik.getJtfPocetak().getText().equals("") && podsjetnik.getJtfKraj().getText().equals("")){
                    boolPrikaziPodsjetnik = true;
                }
                
                if (boolPrikaziPodsjetnik){
                    alPodsjetniciZaPrikaz.add(podsjetnik);
                }
            } 
            
            catch (Exception e) {

            }
        }
        
        //racunanje mogucih brojeva stranica
        intMoguciBrojStranica = alPodsjetniciZaPrikaz.size() / Integer.parseInt(Postavke.dajPostavku("brojPodsjetnikaPoStranici"));
        
        //ukoliko postoji broj podsjetnika takav da ne popunjuje cijelu stranicu podsjetnika
        //dodaje se zadnja stranica koja sadrzi ostatak podsjetnika
        if (alPodsjetniciZaPrikaz.size() % Integer.parseInt(Postavke.dajPostavku("brojPodsjetnikaPoStranici")) != 0){
            intMoguciBrojStranica++;
        }
        
        
        
        //dodavanje stranicenja
        dodavanjeNaPocetakIPredhodnaStranica(jpStraniceBrojevi);
        
        for (int i = 1; i <= intMoguciBrojStranica; i++) {
            
            JLabel jlStranica = new JLabel(String.valueOf(i));
            
            //oznacavanje trenutno aktivne stranice crvenom bojom
            if(jlStranica.getText().equals(String.valueOf(intAktivniBrojStranice))){
                jlStranica.setForeground(Color.RED);
            }
            else{
                jlStranica.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
            }
            
            //dodavanje funkcionalnosti "na klik", "enter" i "exit"
            jlStranica.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    intAktivniBrojStranice = Integer.parseInt(jlStranica.getText());
                    JfPocetna.azurirajObrazac();
                }

                public void mouseEntered(MouseEvent e) {

                    //on enter, promjena boje u plavo i podcrtano
                    Font font = jlStranica.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    jlStranica.setFont(font.deriveFont(attributes));
                    jlStranica.setForeground(Color.BLUE);
                }

                public void mouseExited(MouseEvent e) {
                    
                    //oznacavanje trenutno aktivne stranice crvenom bojom
                    if(jlStranica.getText().equals(String.valueOf(intAktivniBrojStranice))){

                        //on exit, promjena boje u crvenu i makivanje podcrtavanja
                        Font font = jlStranica.getFont();
                        Map attributes = font.getAttributes();
                        attributes.put(TextAttribute.UNDERLINE, -1);
                        jlStranica.setFont(font.deriveFont(attributes));
                        jlStranica.setForeground(Color.RED);
                    }
                    
                    //ako nije aktivna, oznacava se zadanom bojom iz postavki jpanela
                    else{

                        //on exit, promjena boje u boju iz postavki i makivanje podcrtavanja
                        Font font = jlStranica.getFont();
                        Map attributes = font.getAttributes();
                        attributes.put(TextAttribute.UNDERLINE, -1);
                        jlStranica.setFont(font.deriveFont(attributes));
                        jlStranica.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));

                    }
                }
            });
            jpStraniceBrojevi.add(jlStranica);
        }
            
        dodavanjeNaKrajISljedecaStranica(jpStraniceBrojevi, intMoguciBrojStranica);
        
        
        //racunanje broja stranica za prikazati
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        
        //dodavanje opisa stupaca
        c.ipadx = 10;
        jpStraniceSadrzaj.add(new JLabel("<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">"+"Opis"+"</p></html>"), c);

        c.gridx++;
        jpStraniceSadrzaj.add(new JLabel("<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">"+"Početak"+"</p></html>"), c);

        c.gridx++;
        c.ipadx = 0;
        jpStraniceSadrzaj.add(new JLabel("<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">"+"Kraj"+"</p></html>"), c);

        c.gridy++;
        
        for (int i = (intAktivniBrojStranice * Integer.parseInt(Postavke.dajPostavku("brojPodsjetnikaPoStranici"))) - Integer.parseInt(Postavke.dajPostavku("brojPodsjetnikaPoStranici"));
                 i < (intAktivniBrojStranice * Integer.parseInt(Postavke.dajPostavku("brojPodsjetnikaPoStranici"))); i++) {
            
            try {
                Podsjetnik podsjetnik = alPodsjetniciZaPrikaz.get(i);

                c.gridx = 0;
                c.ipadx = 10;
                jpStraniceSadrzaj.add(new JLabel("<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">"+podsjetnik.getJtfOpis().getText()+"</p><br></html>"), c);

                c.gridx++;
                jpStraniceSadrzaj.add(new JLabel("<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">"+podsjetnik.getJtfPocetak().getText()+"</p></html>"), c);

                c.gridx++;
                c.ipadx = 0;
                jpStraniceSadrzaj.add(new JLabel("<html><p style=color:rgb" + postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">"+podsjetnik.getJtfKraj().getText()+"</p></html>"), c);

                c.gridy++;
                
            } catch (Exception e) {
            }
            
        }
        
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;
        jpPodsjetnici.add(jpStraniceBrojevi, c);
        
        c.gridy++;
        jpPodsjetnici.add(jpStraniceSadrzaj, c);
        jpPodsjetnici.revalidate();
    }
    
    /**
     * dodavanje strelica za pocetnu i predhodnu stranicu
     * @param jpStranicenje 
     */
    static void dodavanjeNaPocetakIPredhodnaStranica(JPanel jpStranicenje){
        
            JLabel jlPocetak = new JLabel("<--");
            
            //bojanje zadanom bojom iz postavki jpanela
            jlPocetak.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
           
            
            //dodavanje funkcionalnosti "na klik", "enter" i "exit"
            jlPocetak.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    intAktivniBrojStranice = 1;
                    JfPocetna.azurirajObrazac();
                }

                public void mouseEntered(MouseEvent e) {

                    //on enter, promjena boje u plavo i podcrtano
                    Font font = jlPocetak.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    jlPocetak.setFont(font.deriveFont(attributes));
                    jlPocetak.setForeground(Color.BLUE);
                }

                public void mouseExited(MouseEvent e) {
                    
                    //on exit, promjena boje u boju iz postavki i makivanje podcrtavanja
                    Font font = jlPocetak.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, -1);
                    jlPocetak.setFont(font.deriveFont(attributes));
                    jlPocetak.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
                }
            });
        
            JLabel jlPrijasnja = new JLabel("<-");
            
            //bojanje zadanom bojom iz postavki jpanela
            jlPrijasnja.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
           
            
            //dodavanje funkcionalnosti "na klik", "enter" i "exit"
            jlPrijasnja.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    if(intAktivniBrojStranice != 1){
                        intAktivniBrojStranice -= 1;
                    
                    }
                    JfPocetna.azurirajObrazac();
                }

                public void mouseEntered(MouseEvent e) {

                    //on enter, promjena boje u plavo i podcrtano
                    Font font = jlPrijasnja.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    jlPrijasnja.setFont(font.deriveFont(attributes));
                    jlPrijasnja.setForeground(Color.BLUE);
                }

                public void mouseExited(MouseEvent e) {
                    
                    //on exit, promjena boje u boju iz postavki i makivanje podcrtavanja
                    Font font = jlPrijasnja.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, -1);
                    jlPrijasnja.setFont(font.deriveFont(attributes));
                    jlPrijasnja.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
                }
            });
            
            jpStranicenje.add(jlPocetak);
            jpStranicenje.add(jlPrijasnja);
            
    }
    
    /**
     * dodavanje strelica za sljedecu i posljednju stranicu
     * @param jpStranicenje
     * @param intZadnjaStranica 
     */
    static void dodavanjeNaKrajISljedecaStranica(JPanel jpStranicenje, int intZadnjaStranica){
        
            JLabel jlKraj = new JLabel("-->");
            
            //bojanje zadanom bojom iz postavki jpanela
            jlKraj.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
           
            
            //dodavanje funkcionalnosti "na klik", "enter" i "exit"
            jlKraj.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    intAktivniBrojStranice = intZadnjaStranica;
                    JfPocetna.azurirajObrazac();
                }

                public void mouseEntered(MouseEvent e) {

                    //on enter, promjena boje u plavo i podcrtano
                    Font font = jlKraj.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    jlKraj.setFont(font.deriveFont(attributes));
                    jlKraj.setForeground(Color.BLUE);
                }

                public void mouseExited(MouseEvent e) {
                    
                    //on exit, promjena boje u boju iz postavki i makivanje podcrtavanja
                    Font font = jlKraj.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, -1);
                    jlKraj.setFont(font.deriveFont(attributes));
                    jlKraj.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
                }
            });
        
            JLabel jlSljedeca = new JLabel("->");
            
            //bojanje zadanom bojom iz postavki jpanela
            jlSljedeca.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
           
            
            //dodavanje funkcionalnosti "na klik", "enter" i "exit"
            jlSljedeca.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    if(intAktivniBrojStranice != intZadnjaStranica){
                        intAktivniBrojStranice += 1;
                    }
                    JfPocetna.azurirajObrazac();
                }

                public void mouseEntered(MouseEvent e) {

                    //on enter, promjena boje u plavo i podcrtano
                    Font font = jlSljedeca.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    jlSljedeca.setFont(font.deriveFont(attributes));
                    jlSljedeca.setForeground(Color.BLUE);
                }

                public void mouseExited(MouseEvent e) {
                    
                    //on exit, promjena boje u boju iz postavki i makivanje podcrtavanja
                    Font font = jlSljedeca.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, -1);
                    jlSljedeca.setFont(font.deriveFont(attributes));
                    jlSljedeca.setForeground(new java.awt.Color(postavkePanela.getIntBojaFontaPanelaR(), postavkePanela.getIntBojaFontaPanelaG(), postavkePanela.getIntBojaFontaPanelaB()));
                }
            });
            
            jpStranicenje.add(jlSljedeca);
            jpStranicenje.add(jlKraj);
    }

    public static PostavkePanela getPostavkePanela() {
        return postavkePanela;
    }

    public static void setPostavkePanela(PostavkePanela postavkePanela) {
        JpPodsjetnici.postavkePanela = postavkePanela;
    }

    public static int getIntAktivniBrojStranice() {
        return intAktivniBrojStranice;
    }

    public static void setIntAktivniBrojStranice(int intAktivniBrojStranice) {
        JpPodsjetnici.intAktivniBrojStranice = intAktivniBrojStranice;
    }
    
    
}
