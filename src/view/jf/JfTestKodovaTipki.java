/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jf;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Ahilis
 */
public class JfTestKodovaTipki extends JFrame{
    
    static JLabel jlTest = new JLabel("Test");

    public JfTestKodovaTipki() {
    }
    
    public static void generirajObrazac(){
        
        JfTestKodovaTipki jfTestKodovaTipki = new JfTestKodovaTipki();
        jfTestKodovaTipki.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        jfTestKodovaTipki.add(jlTest);
        
        //postavljanje izgleda
        jfTestKodovaTipki.setVisible(true);
        
        jfTestKodovaTipki.revalidate();
        jfTestKodovaTipki.pack();
        
    }

    public static JLabel getJlTest() {
        return jlTest;
    }

    public static void setJlTest(JLabel jlTest) {
        JfTestKodovaTipki.jlTest = jlTest;
    }
    
    
}
