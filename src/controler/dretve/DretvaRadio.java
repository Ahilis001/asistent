/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.dretve;

import controler.MetodeRadio;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import view.jp.JpRadio;

/**
 *
 * @author Ahilis
 */
public class DretvaRadio extends PlaybackListener implements Runnable{
    
    private String strURL;
    private AdvancedPlayer player;
    private Thread playerThread;    

    public DretvaRadio(String strURL){
        this.strURL = strURL;
    }

    public void play(){
        try{
            String urlAsString = this.strURL;
            
            this.player = new AdvancedPlayer
            (
                new java.net.URL(urlAsString).openStream(),
                javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice()
            );

            this.player.setPlayBackListener(this);

            this.playerThread = new Thread(this, "AudioPlayerThread");

            this.playerThread.start();
        }
        catch (Exception ex){
            
            MetodeRadio.zaustaviSveRadije();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
    }
    
//    public void play(){
//        
//        try {
//            String urlAsString = this.strURL;
//        
//            URLConnection urlConnection = new URL ( urlAsString ).openConnection ();
//
//            // If you have proxy
//            //        Properties systemSettings = System.getProperties ();
//            //        systemSettings.put ( "proxySet", true );
//            //        systemSettings.put ( "http.proxyHost", "host" );
//            //        systemSettings.put ( "http.proxyPort", "port" );
//            // If you have proxy auth
//            //        BASE64Encoder encoder = new BASE64Encoder ();
//            //        String encoded = encoder.encode ( ( "login:pass" ).getBytes () );
//            //        urlConnection.setRequestProperty ( "Proxy-Authorization", "Basic " + encoded );
//
//            // Connecting
//            urlConnection.connect ();
//
//            // Playing
//            Player player = new Player ( urlConnection.getInputStream () );
//            player.play ();
//        } catch (Exception e) {
//            
//        }
//         
//           
//    }

    // PlaybackListener members

    public void playbackStarted(PlaybackEvent playbackEvent){
        System.out.println("playbackStarted()");
    }

    public void playbackFinished(PlaybackEvent playbackEvent){
        System.out.println("playbackEnded()");
        JpRadio.getJlTrenutnoSvira().setText("<html><p style=color:rgb" + JpRadio.postavkePanela.getJtfUredenaTrojkaBojaFontaPanela().getText()+">"+"Trenutno ništa ne svira"+"</p></html>");
    }    

    // Runnable members

    public void run(){
        try{
            this.player.play();
        }
        catch (javazoom.jl.decoder.JavaLayerException ex){
            
            MetodeRadio.zaustaviSveRadije();
            ex.printStackTrace();
        }
    }
    
    public void stop(){
    
        try {
            this.player.stop();
        } catch (Exception e) {
        }
    }
    
    
}
