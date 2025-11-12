/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sw.Services;

import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author pipe
 */
public abstract class MasterPanel extends JPanel{
    private JDialog modalPadre;
    
    public abstract <T> T getSelection();
    
    public void setModalPadre(JDialog dialog){
        this.modalPadre = dialog;
    }
    public void closeModal(){
        if(this.modalPadre!=null){
            this.modalPadre.dispose();
        }
    }
}
