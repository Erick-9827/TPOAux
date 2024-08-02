package Estructuras;

import Lista;
import Nodo;

public class Hash {
    private Nodo[] tabla = new Nodo[40];
    private int size = 40;
    private int cant = 0;

    public Hash() {
    }
    /*
     * Algoritmo insertar (elemento)
     * 1. calcular posición en la tabla pos = hash(elemento) MOD tamaño de la tabla
     * 2. buscar el elemento en la lista con cabecera tabla[pos]
     * 3. si no lo encuentra, crear el nodo nuevo con (elemento) y enlazarlo a la
     * lista de tabla[pos]
     * sino, verifica que elementos en esa posicion sean diferentes
     * Si alguno no es diferente devolver error (elemento repetido)
     */

    public boolean insertar(Object elemento) {
        boolean exito = false;

        int key = Math.abs(elemento.hashCode()) % size;
        Nodo aux = tabla[key];
        boolean existe = false;
        while (aux != null && !existe) {
            existe = aux.getElemento().equals(elemento);
            aux = aux.getEnlace();
        }
        if (!existe) {
            tabla[key] = new Nodo(elemento, tabla[key]);
            exito = true;
            cant++;
        }
        if (!exito) {
            System.out.println("Elemento repetido");
        }
        return exito;

    }

    public boolean eliminar(Object elemento) {
        boolean exito = false;

        int key = Math.abs(elemento.hashCode()) % size;
        Nodo aux = tabla[key];
        Nodo siguiente;
        boolean existe = false;
        if (aux != null) {
            if (aux.getElemento().equals(elemento)) {
                tabla[key] = aux.getEnlace();
                cant--;
            } else {
                while (aux.getEnlace() != null && !existe) {
                    siguiente = aux.getEnlace();
                    if (siguiente.getElemento().equals(elemento)) {
                        aux.setEnlace(siguiente.getEnlace());
                        cant--;
                    } else {
                        aux = aux.getEnlace();
                    }

                }
            }
        }
        return exito;
    }

    public Lista recuperar(Object elemento) {
        Lista respuesta = new Lista();
        int key = Math.abs(elemento.hashCode()) % size;
        Nodo aux = tabla[key];
        while (aux != null) {
            respuesta.insertar(aux.getElemento(), 1);
            aux = aux.getEnlace();
        }
        return respuesta;
    }

    public boolean pertenece(Object elemento) {
        boolean respuesta = false;
        int key = Math.abs(elemento.hashCode()) % size;
        Nodo aux = tabla[key];
        while (aux != null && !respuesta) {
            respuesta = aux.getElemento().equals(elemento);
            aux = aux.getEnlace();
        }

        return respuesta;
    }

    public Lista recuperarConCode(int key) {
        Lista respuesta = new Lista();
        Nodo aux = tabla[key];
        if (aux != null) {
            while (aux != null) {
                respuesta.insertar(aux.getElemento(), 1);
                aux = aux.getEnlace();
            }
        }
        return respuesta;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        int i = 0;
        int contados = 0;
        String respuesta = "";
        while (i < this.size && contados < this.cant) {
            Nodo actual = tabla[i];
            while (actual != null) {
                respuesta += "\n" + actual.getElemento().toString() + "\t";
                actual = actual.getEnlace();
                contados++;
            }
            i = i + 1;
        }
        return respuesta;
    }

}
