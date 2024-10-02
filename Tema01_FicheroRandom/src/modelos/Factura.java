package modelos;


public class Factura {
    
    private int nFactura;
    private char serie;
    private double total;
    private static int tamanio = 14;
    
   

    public Factura() {
    }

    public Factura(int nFactura, char serie, double total) {
        this.nFactura = nFactura;
        this.serie = serie;
        this.total = total;
    }

    public int getnFactura() {
        return nFactura;
    }

    public char getSerie() {
        return serie;
    }

    public double getTotal() {
        return total;
    }

    public void setnFactura(int nFactura) {
        this.nFactura = nFactura;
    }

    public void setSerie(char serie) {
        this.serie = serie;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public static int getTamanio() {
        return tamanio;
    }

    public static void setTamanio(int tamanio) {
        Factura.tamanio = tamanio;
    }
    

    @Override
    public String toString() {
        return "Factura{" + "nFactura=" + nFactura + ", serie=" + serie + ", total=" + total + '}';
    }
    
    
    
    
}
