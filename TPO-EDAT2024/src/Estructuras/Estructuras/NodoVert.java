package Estructuras;

import NodoAdy;

public class NodoVert {

    private Object elemento = null;
    private NodoVert sigNodoVert = null;
    private NodoAdy primerAdy = null;

    // constructor
    public NodoVert(Object ciudad, NodoVert siguienteNodo) {
        this.elemento = ciudad;
        this.sigNodoVert = siguienteNodo;
    }

    // modificadores
    public void setElemento(Object elem) {
        this.elemento = elem;
    }

    public void setSigNodoVert(NodoVert sigNodoVert) {
        this.sigNodoVert = sigNodoVert;
    }

    public boolean setArco(NodoVert vert, double etiqueta) {
        boolean exito = false;
        if (this.primerAdy == null) {
            this.primerAdy = new NodoAdy(vert, etiqueta);
            exito = true;
        } else {
            NodoAdy actual = this.primerAdy;
            NodoAdy siguiente = this.primerAdy.getSigAdyacente();
            boolean existe = false;
            boolean comparacion = actual.getVertice().getElemento().equals(vert.getElemento());
            if (comparacion) {
                existe = true;
            } else {
                while (!existe && siguiente != null) {
                    if (siguiente.getVertice().getElemento().equals(vert.getElemento())) {
                        existe = true;
                    }
                    actual = siguiente;
                    siguiente = actual.getSigAdyacente();
                }
            }

            if (!existe) {
                actual.setSigAdyacente(new NodoAdy(vert, etiqueta));
                exito = true;
            }
        }
        return exito;
    }

    public boolean eliminarArco(Object elem) {
        boolean exito = false;

        NodoAdy actual = this.primerAdy;
        if (actual != null) {
            boolean comparacion = actual.getVertice().getElemento().equals(elem);
            if (comparacion) {
                this.primerAdy = this.primerAdy.getSigAdyacente();
                exito = true;
            } else {
                NodoAdy siguiente = actual.getSigAdyacente();

                while (siguiente != null && !exito) {
                    comparacion = siguiente.getVertice().getElemento().equals(elem);
                    if (comparacion) {
                        actual.setSigAdyacente(siguiente.getSigAdyacente());
                        exito = true;
                    }
                    actual = siguiente;
                    siguiente = actual.getSigAdyacente();
                }

            }

        }

        return exito;

    }

    public boolean eliminarArcosDeAdyacentes() {
        boolean exito = true;
        NodoAdy actual = this.primerAdy;
        while (actual != null) {
            actual.getVertice().eliminarArco(this.elemento);
            actual = actual.getSigAdyacente();

        }

        return exito;
    }
    // lectores

    public NodoVert getSigNodoVert() {
        return sigNodoVert;
    }

    public NodoAdy getPrimerAdy() {
        return primerAdy;
    }

    public boolean eliminarNodoAdy() {
        boolean exito = false;

        return exito;
    }

    public void setPrimerAdy(NodoAdy primerAdy) {
        this.primerAdy = primerAdy;
    }

    // observadores
    public Object getElemento() {
        return this.elemento;
    }

    public boolean equals(NodoVert comparar) {
        return this.elemento.equals(comparar.elemento);
    }

    public String toString() {
        String respuesta = this.elemento.toString() + " arcos: ";

        if (this.primerAdy != null) {
            NodoAdy actual = this.primerAdy;

            while (actual != null) {
                respuesta = respuesta + "\n        " + actual.getVertice().getElemento().toString() + " Etiqueta "
                        + actual.getEtiqueta();
                actual = actual.getSigAdyacente();
            }

        }

        return respuesta;
    }
}
