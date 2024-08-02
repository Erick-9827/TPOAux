package Estructuras;

public class NodoAdy {
    private NodoVert vertice;
    private double etiqueta;
    private NodoAdy sigAdyacente;

    public NodoAdy(NodoVert vert, double etiqueta) {
        this.vertice = vert;
        this.etiqueta = etiqueta;
    }

    public NodoVert getVertice() {
        return vertice;
    }

    public double getEtiqueta() {
        return etiqueta;
    }

    public NodoAdy getSigAdyacente() {
        return sigAdyacente;
    }

    public void setVertice(NodoVert vertice) {
        this.vertice = vertice;
    }

    public void setEtiqueta(double etiqueta) {
        this.etiqueta = etiqueta;
    }

    public void setSigAdyacente(NodoAdy sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }

}
