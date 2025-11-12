/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package sw2.demofront;

import sw2.gui.frmPrincipalServicio;

/**
 *
 * @author pipe
 */
public class DemoFront {

    public static void main(String[] args) {
        try {
            frmPrincipalServicio f= new frmPrincipalServicio();
        f.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
