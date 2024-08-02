
import PropiosTPO.Equipo;

public class NodoAVL {

    private Comparable elemento;
    private NodoAVL hijoDerch;
    private NodoAVL hijoIzq;
    private int altura = 0;

    // constructor
    public NodoAVL(Comparable elemento) {
        this.elemento = elemento;
    }

    // modificadores
    public void setElemento(Comparable elemento) {
        this.elemento = elemento;
    }

    // observadores
    public Comparable getElemento() {
        return this.elemento;
    }

    public NodoAVL getDerecho() {
        return this.hijoDerch;
    }

    public NodoAVL getIzquierdo() {
        return this.hijoIzq;
    }

    public void setDerecho(NodoAVL derech) {
        this.hijoDerch = derech;
    }

    public void setIzquierdo(NodoAVL izq) {
        this.hijoIzq = izq;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }

    public void recalcularAltura() {
        int altD = -1, altI = -1;
        if (hijoDerch != null) {
            altD = hijoDerch.getAltura();
        }
        if (hijoIzq != null) {
            altI = hijoIzq.getAltura();
        }
        altura = Math.max(altI, altD) + 1;
    }

}
