/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JTextField;

/**
 *
 * @author Ahilis
 */
public class PostavkePanela {
    
    JTextField jtfKoordinate = new JTextField();
    JTextField jtfUredenaCetvorkaBojaPozadinePanela = new JTextField();
    JTextField jtfUredenaTrojkaBojaFontaPanela = new JTextField();
    JTextField jtfUredenaTrojkaBojaNaslovaPanela = new JTextField();
    JTextField jtfVelicinaFontaNaslovaPanela = new JTextField();
    JTextField jtfVelicinaFontaTekstaPanela = new JTextField();
    JTextField jtfVrstaFontaNaslovaPanela = new JTextField();
    JTextField jtfVrstaFontaTekstaPanela = new JTextField();
    JTextField jtfNazivPanela = new JTextField();
    
    Integer intKoordinataX;
    Integer intKoordinataY;
    Integer intBrojStupacaPanela;
    Integer intBrojRedovaPanela;
    
    Integer intBojaPozadinePanelaR;
    Integer intBojaPozadinePanelaG;
    Integer intBojaPozadinePanelaB;
    Integer intBojaPozadinePanelaA;
    
    Integer intBojaFontaPanelaR;
    Integer intBojaFontaPanelaG;
    Integer intBojaFontaPanelaB;
    
    Integer intBojaFontaNaslovaPanelaR;
    Integer intBojaFontaNaslovaPanelaG;
    Integer intBojaFontaNaslovaPanelaB;
    
    Integer intVelicinaFontaNaslovaPanela;
    Integer intVelicinaFontaTekstaPanela;
    

    public PostavkePanela() {
    }

    public void pripremiLokacijuPanela(String strKoordinate) {
        
        String[] poljeKoordinata = strKoordinate.substring(1, strKoordinate.length() - 1).split(","); 
        
        try {
            this.jtfKoordinate.setText(strKoordinate);
            this.intKoordinataX = Integer.parseInt(poljeKoordinata[0]);
            this.intKoordinataY = Integer.parseInt(poljeKoordinata[1]);
            this.intBrojStupacaPanela = Integer.parseInt(poljeKoordinata[2]);
            this.intBrojRedovaPanela = Integer.parseInt(poljeKoordinata[3]);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void pripremiBojuPanela(String strUredenaCetvorkaBojaPozadinePanela, String strUredenaTrojkaBojaFontaPanela, String strUredenaTrojkaBojaFontaNaslovaPozadinePanel) {
        
        this.jtfUredenaTrojkaBojaNaslovaPanela.setText(strUredenaTrojkaBojaFontaNaslovaPozadinePanel);
        this.jtfUredenaTrojkaBojaFontaPanela.setText(strUredenaTrojkaBojaFontaPanela);
        this.jtfUredenaCetvorkaBojaPozadinePanela.setText(strUredenaCetvorkaBojaPozadinePanela);

        String[] poljeBojaPozadinePanela = jtfUredenaCetvorkaBojaPozadinePanela.getText().substring(1, jtfUredenaCetvorkaBojaPozadinePanela.getText().length() - 1).split(","); 
        String[] poljeBojaFontaPanela = jtfUredenaTrojkaBojaFontaPanela.getText().substring(1, jtfUredenaTrojkaBojaFontaPanela.getText().length() - 1).split(","); 
        String[] poljeBojaFontaNaslovaPozadinePanel = jtfUredenaTrojkaBojaNaslovaPanela.getText().substring(1, jtfUredenaTrojkaBojaNaslovaPanela.getText().length() - 1).split(","); 
        
        try {
            this.intBojaPozadinePanelaR = Integer.parseInt(poljeBojaPozadinePanela[0]);
            this.intBojaPozadinePanelaG = Integer.parseInt(poljeBojaPozadinePanela[1]);
            this.intBojaPozadinePanelaB = Integer.parseInt(poljeBojaPozadinePanela[2]);
            this.intBojaPozadinePanelaA = Integer.parseInt(poljeBojaPozadinePanela[3]);
            
            this.intBojaFontaPanelaR = Integer.parseInt(poljeBojaFontaPanela[0]);
            this.intBojaFontaPanelaG = Integer.parseInt(poljeBojaFontaPanela[1]);
            this.intBojaFontaPanelaB = Integer.parseInt(poljeBojaFontaPanela[2]);
            
            this.intBojaFontaNaslovaPanelaR = Integer.parseInt(poljeBojaFontaNaslovaPozadinePanel[0]);
            this.intBojaFontaNaslovaPanelaG = Integer.parseInt(poljeBojaFontaNaslovaPozadinePanel[1]);
            this.intBojaFontaNaslovaPanelaB = Integer.parseInt(poljeBojaFontaNaslovaPozadinePanel[2]);
            
        } catch (Exception e) {
            
            this.intBojaPozadinePanelaR = 240;
            this.intBojaPozadinePanelaG = 240;
            this.intBojaPozadinePanelaB = 240;
            this.intBojaPozadinePanelaA = 255;
            
            this.intBojaFontaPanelaR = 0;
            this.intBojaFontaPanelaG = 0;
            this.intBojaFontaPanelaB = 0;
            
            this.intBojaFontaNaslovaPanelaR = 0;
            this.intBojaFontaNaslovaPanelaG = 0;
            this.intBojaFontaNaslovaPanelaB = 0;
            
            e.printStackTrace();
        }
    }
    
    public void pripremiSlovaPanela(String strVelicinaFontaNaslovaPanela, String strVelicinaFontaTekstaPanela, String strNazivPanela, String strVrstaFontaNaslovaPanela, String strVrstaFontaTekstaPanela){
        
        try {
            this.jtfVelicinaFontaNaslovaPanela.setText(strVelicinaFontaNaslovaPanela);
            this.jtfVelicinaFontaTekstaPanela.setText(strVelicinaFontaTekstaPanela);
            this.jtfNazivPanela.setText(strNazivPanela);
            this.jtfVrstaFontaNaslovaPanela.setText(strVrstaFontaNaslovaPanela);
            this.jtfVrstaFontaTekstaPanela.setText(strVrstaFontaTekstaPanela);
            
            this.intVelicinaFontaNaslovaPanela = Integer.parseInt(strVelicinaFontaNaslovaPanela);
            this.intVelicinaFontaTekstaPanela = Integer.parseInt(strVelicinaFontaTekstaPanela);
            
        } catch (Exception e) {
            
            this.intVelicinaFontaNaslovaPanela = 11;
            this.intVelicinaFontaTekstaPanela = 11;
            this.jtfNazivPanela.setText("");
            this.jtfVrstaFontaNaslovaPanela.setText("Tahoma");
            this.jtfVrstaFontaTekstaPanela.setText("Tahoma");
            
            e.printStackTrace();
        }
    }

    public Integer getIntBrojStupacaPanela() {
        return intBrojStupacaPanela;
    }

    public void setIntBrojStupacaPanela(Integer intBrojStupacaPanela) {
        this.intBrojStupacaPanela = intBrojStupacaPanela;
    }
    
    public JTextField getJtfKoordinate() {
        return jtfKoordinate;
    }

    public void setJtfKoordinate(JTextField jtfKoordinate) {
        this.jtfKoordinate = jtfKoordinate;
    }

    public Integer getIntKoordinataX() {
        return intKoordinataX;
    }

    public void setIntKoordinataX(Integer intKoordinataX) {
        this.intKoordinataX = intKoordinataX;
    }

    public Integer getIntKoordinataY() {
        return intKoordinataY;
    }

    public void setIntKoordinataY(Integer intKoordinataY) {
        this.intKoordinataY = intKoordinataY;
    }

    public Integer getIntBrojRedovaPanela() {
        return intBrojRedovaPanela;
    }

    public void setIntBrojRedovaPanela(Integer intBrojRedovaPanela) {
        this.intBrojRedovaPanela = intBrojRedovaPanela;
    }

    public JTextField getJtfUredenaCetvorkaBojaPozadinePanela() {
        return jtfUredenaCetvorkaBojaPozadinePanela;
    }

    public void setJtfUredenaCetvorkaBojaPozadinePanela(JTextField jtfUredenaCetvorkaBojaPozadinePanela) {
        this.jtfUredenaCetvorkaBojaPozadinePanela = jtfUredenaCetvorkaBojaPozadinePanela;
    }

    public JTextField getJtfUredenaTrojkaBojaFontaPanela() {
        return jtfUredenaTrojkaBojaFontaPanela;
    }

    public void setJtfUredenaTrojkaBojaFontaPanela(JTextField jtfUredenaTrojkaBojaFontaPanela) {
        this.jtfUredenaTrojkaBojaFontaPanela = jtfUredenaTrojkaBojaFontaPanela;
    }

    public JTextField getJtfUredenaTrojkaBojaNaslovaPanela() {
        return jtfUredenaTrojkaBojaNaslovaPanela;
    }

    public void setJtfUredenaTrojkaBojaNaslovaPanela(JTextField jtfUredenaTrojkaBojaNaslovaPanela) {
        this.jtfUredenaTrojkaBojaNaslovaPanela = jtfUredenaTrojkaBojaNaslovaPanela;
    }

    public JTextField getJtfVelicinaFontaNaslovaPanela() {
        return jtfVelicinaFontaNaslovaPanela;
    }

    public void setJtfVelicinaFontaNaslovaPanela(JTextField jtfVelicinaFontaNaslovaPanela) {
        this.jtfVelicinaFontaNaslovaPanela = jtfVelicinaFontaNaslovaPanela;
    }

    public JTextField getJtfVelicinaFontaTekstaPanela() {
        return jtfVelicinaFontaTekstaPanela;
    }

    public void setJtfVelicinaFontaTekstaPanela(JTextField jtfVelicinaFontaTekstaPanela) {
        this.jtfVelicinaFontaTekstaPanela = jtfVelicinaFontaTekstaPanela;
    }

    public Integer getIntBojaPozadinePanelaR() {
        return intBojaPozadinePanelaR;
    }

    public void setIntBojaPozadinePanelaR(Integer intBojaPozadinePanelaR) {
        this.intBojaPozadinePanelaR = intBojaPozadinePanelaR;
    }

    public Integer getIntBojaPozadinePanelaG() {
        return intBojaPozadinePanelaG;
    }

    public void setIntBojaPozadinePanelaG(Integer intBojaPozadinePanelaG) {
        this.intBojaPozadinePanelaG = intBojaPozadinePanelaG;
    }

    public Integer getIntBojaPozadinePanelaB() {
        return intBojaPozadinePanelaB;
    }

    public void setIntBojaPozadinePanelaB(Integer intBojaPozadinePanelaB) {
        this.intBojaPozadinePanelaB = intBojaPozadinePanelaB;
    }

    public Integer getIntBojaFontaPanelaR() {
        return intBojaFontaPanelaR;
    }

    public void setIntBojaFontaPanelaR(Integer intBojaFontaPanelaR) {
        this.intBojaFontaPanelaR = intBojaFontaPanelaR;
    }

    public Integer getIntBojaFontaPanelaG() {
        return intBojaFontaPanelaG;
    }

    public void setIntBojaFontaPanelaG(Integer intBojaFontaPanelaG) {
        this.intBojaFontaPanelaG = intBojaFontaPanelaG;
    }

    public Integer getIntBojaFontaPanelaB() {
        return intBojaFontaPanelaB;
    }

    public void setIntBojaFontaPanelaB(Integer intBojaFontaPanelaB) {
        this.intBojaFontaPanelaB = intBojaFontaPanelaB;
    }

    public Integer getIntBojaFontaNaslovaPanelaR() {
        return intBojaFontaNaslovaPanelaR;
    }

    public void setIntBojaFontaNaslovaPanelaR(Integer intBojaFontaNaslovaPanelaR) {
        this.intBojaFontaNaslovaPanelaR = intBojaFontaNaslovaPanelaR;
    }

    public Integer getIntBojaFontaNaslovaPanelaG() {
        return intBojaFontaNaslovaPanelaG;
    }

    public void setIntBojaFontaNaslovaPanelaG(Integer intBojaFontaNaslovaPanelaG) {
        this.intBojaFontaNaslovaPanelaG = intBojaFontaNaslovaPanelaG;
    }

    public Integer getIntBojaFontaNaslovaPanelaB() {
        return intBojaFontaNaslovaPanelaB;
    }

    public void setIntBojaFontaNaslovaPanelaB(Integer intBojaFontaNaslovaPanelaB) {
        this.intBojaFontaNaslovaPanelaB = intBojaFontaNaslovaPanelaB;
    }

    public Integer getIntVelicinaFontaNaslovaPanela() {
        return intVelicinaFontaNaslovaPanela;
    }

    public void setIntVelicinaFontaNaslovaPanela(Integer intVelicinaFontaNaslovaPanela) {
        this.intVelicinaFontaNaslovaPanela = intVelicinaFontaNaslovaPanela;
    }

    public Integer getIntVelicinaFontaTekstaPanela() {
        return intVelicinaFontaTekstaPanela;
    }

    public void setIntVelicinaFontaTekstaPanela(Integer intVelicinaFontaTekstaPanela) {
        this.intVelicinaFontaTekstaPanela = intVelicinaFontaTekstaPanela;
    }

    public JTextField getJtfNazivPanela() {
        return jtfNazivPanela;
    }

    public void setJtfNazivPanela(JTextField jtfNazivPanela) {
        this.jtfNazivPanela = jtfNazivPanela;
    }

    public JTextField getJtfVrstaFontaNaslovaPanela() {
        return jtfVrstaFontaNaslovaPanela;
    }

    public void setJtfVrstaFontaNaslovaPanela(JTextField jtfVrstaFontaNaslovaPanela) {
        this.jtfVrstaFontaNaslovaPanela = jtfVrstaFontaNaslovaPanela;
    }

    public JTextField getJtfVrstaFontaTekstaPanela() {
        return jtfVrstaFontaTekstaPanela;
    }

    public void setJtfVrstaFontaTekstaPanela(JTextField jtfVrstaFontaTekstaPanela) {
        this.jtfVrstaFontaTekstaPanela = jtfVrstaFontaTekstaPanela;
    }
}
