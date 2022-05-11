/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.dretve;

/**
 *
 * @author Ahilis
 */
public class DretvaZaPokretanjeRadija extends Thread{

    public DretvaZaPokretanjeRadija() {
    }

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
    }

    public synchronized void start(DretvaRadio drRadio) {
        super.start(); //To change body of generated methods, choose Tools | Templates.
        drRadio.play();
    }
    
}
