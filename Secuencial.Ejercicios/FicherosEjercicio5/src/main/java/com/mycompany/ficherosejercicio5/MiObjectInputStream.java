/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ficherosejercicio5;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;


/**
 *
 * @author Estefania
 */
public class MiObjectInputStream extends ObjectInputStream{
    
    public MiObjectInputStream() throws IOException, SecurityException {
    super();
    }
    
    public MiObjectInputStream(InputStream in) throws IOException{
    super(in);
    }
    
    @Override
    protected void readStreamHeader() throws IOException {
        
    }
    
}
