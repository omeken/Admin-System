/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omeken.biometrics;

import javax.swing.JButton;

/**
 *
 * @author NEVS I
 */
public class DigitalPersonaThread extends Thread{
    
  
    String matricNo;
    int  findex;
    
    public DigitalPersonaThread( String matNo, int index){
        this.findex = index;
        this.matricNo = matNo;
        
    }
    public void run(){
        DigitalPersona dgp = new DigitalPersona(this.findex, this.matricNo);
        dgp.capture(1);
    }
}
