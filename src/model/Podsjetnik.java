/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author Ahilis
 */
public class Podsjetnik{
    
    JTextField jtfOpis = new JTextField();
    JTextField jtfPocetak = new JTextField();
    JTextField jtfKraj = new JTextField();
    JCheckBox jcbObrisi = new JCheckBox();
    
    static ArrayList<Podsjetnik> alSviPodsjetnici = new ArrayList<>();

    public Podsjetnik(String strOpis, String strPocetak, String strKraj) {
        this.jtfOpis.setText(strOpis);
        this.jtfPocetak.setText(strPocetak);
        this.jtfKraj.setText(strKraj);
        this.jcbObrisi.setSelected(false);
    }

    public JTextField getJtfOpis() {
        return jtfOpis;
    }

    public void setJtfOpsis(JTextField jtfOpsis) {
        this.jtfOpis = jtfOpsis;
    }

    public JTextField getJtfPocetak() {
        return jtfPocetak;
    }

    public void setJtfPocetak(JTextField jtfPocetak) {
        this.jtfPocetak = jtfPocetak;
    }

    public JTextField getJtfKraj() {
        return jtfKraj;
    }

    public void setJtfKraj(JTextField jtfKraj) {
        this.jtfKraj = jtfKraj;
    }

    public JCheckBox getJcbObrisi() {
        return jcbObrisi;
    }

    public void setJcbObrisi(JCheckBox jcbObrisi) {
        this.jcbObrisi = jcbObrisi;
    }

    public static ArrayList<Podsjetnik> getAlSviPodsjetnici() {
        return alSviPodsjetnici;
    }

    public static void setAlSviPodsjetnici(ArrayList<Podsjetnik> alSviPodsjetnici) {
        Podsjetnik.alSviPodsjetnici = alSviPodsjetnici;
    }
    
    
}
