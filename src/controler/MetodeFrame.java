/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import model.Postavke;

/**
 *
 * @author Ahilis
 */
public class MetodeFrame {
    
    static int pX = 0, pY = 0;
    /**
     * postavlja izgled jFramea s gumbovima (_, |=|, X) ili bez njih
     * @param jFrame
     * @param strVrsta - 1 - undecorated, 2 - decorated
     * @param strNaziv
     * @param blPocetniJF označava ako je početni jf ili ne, zbog zatvaranja app na pritisak na X
     */
    public static void postaviJf(JFrame jFrame, String strVrsta, String strNaziv, boolean blPocetniJF){
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        switch (strVrsta){
            
            //undecorated
            case "1":{
                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        postaviLokaciju(jFrame);
                    }
                });
                jFrame.setUndecorated(true);
                break;
            }
        
            //decorated
            case "2":{
                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        postaviLokaciju(jFrame);
                        jFrame.setTitle(strNaziv);
                    }
                });
                jFrame.setUndecorated(false);
                break;
            }
        }
        
        if (blPocetniJF) {
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            
            //za micanje jfamea
            jFrame.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    //koordinate
                    pX = me.getX();
                    pY = me.getY();
                    
                    //ako se može micati
                    if (Postavke.dajPostavku("mozeSeMicati").equals("true")) {
                        jFrame.setLocation(jFrame.getLocation().x + me.getX() - pX, jFrame.getLocation().y + me.getY() - pY);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                    
                    //ako se može micati, sprema se zadnja lokacija
                    if (Postavke.dajPostavku("mozeSeMicati").equals("true")) {
                        Postavke.azurirajPostavku("koordinateAplikacijeX", String.valueOf(jFrame.getLocation().x));
                        Postavke.azurirajPostavku("koordinateAplikacijeY", String.valueOf(jFrame.getLocation().y));
                    }
                }
            });

            jFrame.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent me) {
                    
                    //micanje ako se može micati
                    if (Postavke.dajPostavku("mozeSeMicati").equals("true")) {
                        jFrame.setLocation(jFrame.getLocation().x + me.getX() - pX, jFrame.getLocation().y + me.getY() - pY);
                    }
                }
            });
            
            //ako nema obrub
            if (jFrame.isUndecorated()) {
                String[] poljeBojaPozadinePanela = Postavke.dajPostavku("bojaGlavneForme").substring(1, Postavke.dajPostavku("bojaGlavneForme").length() - 1).split(","); 
                jFrame.setBackground(new Color(Integer.parseInt(poljeBojaPozadinePanela[0]), Integer.parseInt(poljeBojaPozadinePanela[1]), Integer.parseInt(poljeBojaPozadinePanela[2]), Integer.parseInt(poljeBojaPozadinePanela[3])));
            } 

            //ako ima
            else {
                String[] poljeBojaPozadinePanela = Postavke.dajPostavku("bojaGlavneForme").substring(1, Postavke.dajPostavku("bojaGlavneForme").length() - 1).split(","); 
                jFrame.setBackground(new Color(Integer.parseInt(poljeBojaPozadinePanela[0]), Integer.parseInt(poljeBojaPozadinePanela[1]), Integer.parseInt(poljeBojaPozadinePanela[2])));
            }
        }
        
        jFrame.revalidate();
        jFrame.pack();
        jFrame.setVisible(true);
        
    }
    
    public static void postaviLokaciju(JFrame jFrame){
        jFrame.setLocation(Integer.parseInt(Postavke.dajPostavku("koordinateAplikacijeX")), Integer.parseInt(Postavke.dajPostavku("koordinateAplikacijeY")));
    }
}
