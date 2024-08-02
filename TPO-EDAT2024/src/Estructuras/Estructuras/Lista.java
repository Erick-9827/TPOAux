package Estructuras;

import Nodo;

public class Lista {
    // Atributes

    private Nodo cabecera;
    private int longitud;

    /**
     * constructor vacio
     */
    public Lista() {
        this.cabecera = null;
        longitud = 0;
    }

    /**
     * Agrega el elemento pasado por parametro en la posicion pos,la cantidad de
     * elementos de la lista se incrementa en 1. Para una insercion exitosa, la
     * posicion recibida debe ser 1 <= pos<= longitud(lista) + 1. Devuelve
     * verdadero si se puede insertar correctamente y falso en caso contrario.
     */
    public boolean insertar(Object elem, int pos) {

        boolean exito = false;
        Nodo nuevo = new Nodo(elem, null);

        if (pos > 0 && pos <= this.longitud() + 1) {
            if (pos == 1) {
                nuevo.setEnlace(this.cabecera);
                this.cabecera = nuevo;
            } else {
                insertarRecursivoPaso(nuevo, this.cabecera, 1, pos);
            }

            this.longitud++;
            exito = true;
        }
        return exito;
    }

    private void insertarRecursivoPaso(Nodo insertar, Nodo actual, int posActual, int posBusc) {
        if (posBusc - 1 == posActual) {
            insertar.setEnlace(actual.getEnlace());
            actual.setEnlace(insertar);
        } else {
            insertarRecursivoPaso(insertar, actual.getEnlace(), posActual + 1, posBusc);
        }
    }

    /**
     * devuelve la cantidad de elementos de la lista q fue actualizada en el
     * insertar y eliminar
     */
    public int longitud() {
        return this.longitud;
    }

    /**
     * Borra el elemento de la posicion pos, por lo que la cantidad de elementos
     * de la lista disminuye en uno. Para una eliminacion exitosa, la lista no
     * debe estar vacia y la posicion recibida debe ser 1 <= pos <=
     * longitud(lista). Devuelve verdadero si se pudo eliminar correctamente y
     * falso en caso contrario.
     *
     */
    public boolean eliminar(int pos) {
        boolean exito = false;
        if (pos > 0 && pos <= this.longitud()) {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                eliminarRecursivoPaso(this.cabecera, 1, pos);
            }
            exito = true;
            this.longitud--;
        }
        return exito;
    }

    private void eliminarRecursivoPaso(Nodo actual, int posActual, int posBusc) {
        if (posBusc - 1 == posActual) {
            actual.setEnlace(actual.getEnlace().getEnlace());
        } else {
            eliminarRecursivoPaso(actual.getEnlace(), posActual + 1, posBusc);
        }
    }

    /**
     * Devuelve la posicion en la que se encuentra la primera ocurrencia de elem
     * dentro de la lista. En caso de no encontrarlo devuelve -1.
     *
     */
    public int localizar(Object busc) {
        int pos = -1;
        if (!this.esVacia()) {
            pos = localizarRecursivoPaso(this.cabecera, 1, busc);
        }
        return pos;
    }

    private int localizarRecursivoPaso(Nodo actual, int posActual, Object elemBusc) {
        int posicion = -1;
        if (elemBusc == actual.getElemento()) {
            posicion = posActual;
        } else {
            if (actual.getEnlace() != null) {
                posicion = localizarRecursivoPaso(actual.getEnlace(), posActual + 1, elemBusc);
            }
        }
        return posicion;
    }

    /**
     * Devuelve el elemento de la posicion pos. La precondicion es que la
     * posicion sea valida.
     */
    public Object recuperar(int pos) {
        Object aux = null;
        if (pos > 0 && pos <= this.longitud()) {
            aux = recuperarRecursivoPaso(this.cabecera, 1, pos);
        }
        return aux;
    }

    private Object recuperarRecursivoPaso(Nodo actual, int posActual, int posBusc) {
        Object aux = null;
        if (posBusc == posActual) {
            aux = actual.getElemento();
        } else {
            if (actual.getEnlace() != null) {
                aux = recuperarRecursivoPaso(actual.getEnlace(), posActual + 1, posBusc);
            }
        }
        return aux;
    }

    /**
     * Quita todos los elementos de la lista.
     */
    public void vaciar() {
        this.cabecera = null;
        this.longitud = 0;
    }

    /**
     * Verifica que esta vacia
     */
    public boolean esVacia() {
        return this.cabecera == null;
    }

    /**
     * Devuelve una copia exacta de los datos en la estructura original, y
     * respetando el orden de los mismos, en otra estructura del mismo tipo
     *
     */
    public Lista clone() {
        Lista clon = new Lista();
        this.clonarRecursivoPaso(clon, this.cabecera, 1);
        return clon;
    }

    private void clonarRecursivoPaso(Lista listaClon, Nodo actual, int pos) {

        if (actual != null) {
            listaClon.insertar(actual.getElemento(), pos);
            clonarRecursivoPaso(listaClon, actual.getEnlace(), pos + 1);

        }
    }

    /**
     * Crea y devuelve una cadena de caracteres formada por todos los elementos
     * de la lista para poder mostrarla por pantalla.
     */
    public String toString() {
        String contenido = "Lista vacia";

        if (!this.esVacia()) {
            contenido = "";
            // recorremos los enlaces de forma recursiva
            contenido = "[" + toStringRecursivoPaso(this.cabecera) + "]";
        }
        return contenido;
    }

    private String toStringRecursivoPaso(Nodo actual) {
        String contenido = "";
        if (actual != null) {
            contenido = actual.getElemento() + " " + toStringRecursivoPaso(actual.getEnlace());
        }
        return contenido;
    }

}
