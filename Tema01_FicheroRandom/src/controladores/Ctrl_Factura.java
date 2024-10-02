
package controladores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.Factura;


public class Ctrl_Factura {
    
    private RandomAccessFile raf;
    private long puntero;
    private File f;
    private Factura fa;
    
    public Ctrl_Factura(){
        
        f = new File("fichero.dat");
        
        if(f.isFile() && f.exists()){
            
            try {
                raf = new RandomAccessFile(f, "rw");
                
                
                
                
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al abrir");
                
            }
            
        }
        
    }
    
    
    public int alta(Factura f){
        
        int res = -1;
    

        try {
            raf = new RandomAccessFile("datos_factura", "rw");

            long puntero = (f.getnFactura() - 1) * Factura.getTamanio();

            
            if (puntero < raf.length()) {
                raf.seek(puntero);
                int factura = raf.readInt();

                if (factura != 0) { 
                    int n = JOptionPane.showConfirmDialog(null, "¿Está seguro de sobreescribir?", "SI", JOptionPane.YES_NO_OPTION);

                    if (n == JOptionPane.YES_OPTION) {
                        raf.seek(puntero);
                        
                        raf.writeInt(f.getnFactura());
                        raf.writeChar(f.getSerie());
                        raf.writeDouble(f.getTotal());
                        res = 1;
                    }
                } else {
                    raf.seek(puntero);
                    raf.writeInt(f.getnFactura());
                    raf.writeChar(f.getSerie());
                    raf.writeDouble(f.getTotal());
                    res = 1;
                }
                
            } else { 
                
                raf.seek(puntero);
                raf.writeInt(f.getnFactura());
                raf.writeChar(f.getSerie());
                raf.writeDouble(f.getTotal());
                res = 1;
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Ctrl_Factura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                raf.close();
            } catch (IOException ex) {
                Logger.getLogger(Ctrl_Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    return res;
          
    }
    
    
    
    
    public int bajaFactura (Factura f){
        int res = -1;
        
        if(this.seekFactura(f) == 1){
            
            puntero = (f.getnFactura() - 1) * Factura.getTamanio();
        
            try {
                raf.seek(puntero);

                raf.writeInt(0);
                res = 1;

            } catch (IOException ex) {}
            
        }
        
        return res;
    }
    
    public Factura buscarFactura (Factura f){
        
        Factura res = null;
        
        if(this.seekFactura(f) == 1){
            
            try {
                res.setnFactura(f.getnFactura());
                res.setSerie(raf.readChar());
                res.setTotal(raf.readDouble());
            } catch (IOException ex) {
                Logger.getLogger(Ctrl_Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
                
                
                
        
        return res;
    }
    
    
    
    public int seekFactura(Factura f){
        
        // -1 fuera fichero
        // 0 baja logica
        // 1 existe info de la factura
        
        int res = -1;
        
        puntero = (f.getnFactura() - 1) * Factura.getTamanio();
        
        try {
            raf.seek(puntero);
            
            if(raf.readInt() != 0){ res = 1; }  else{ res = 0; }
            
        } catch (IOException ex) {}
        
        return res;
        
    }
    
    
    
    public ArrayList<Factura> listarFacturas (){
        
        ArrayList<Factura> res = listarFacturas();
        fa = new Factura();
        long nr;
        try {
            
            nr =  raf.length() / Factura.getTamanio();
            
            for(int i = 0  ; i< nr; i++){
        
            puntero = (i - 1) * Factura.getTamanio();
            
        
            if(seekFactura(fa) == 1){
                
                res.add(new Factura());
            }
        
        
           }
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Ctrl_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        return res;
    }
    
    
    
    
    
}
