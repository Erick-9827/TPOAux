package Estructuras;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grafo {

    private NodoVert primerNodo;

    public Grafo() {
    }

    public boolean insertarVertice(Object elem) {
        boolean exito = false;
        NodoVert nuevo;
        if (primerNodo == null) {
            nuevo = new NodoVert(elem, null);
            this.primerNodo = nuevo;
        } else {
            NodoVert aux = this.primerNodo;
            boolean noExiste = true;
            while (aux != null && noExiste) {
                if (aux.getElemento().equals(elem)) {
                    noExiste = false;
                }
                aux = aux.getSigNodoVert();
            }
            if (noExiste) {
                nuevo = new NodoVert(elem, this.primerNodo);
                this.primerNodo = nuevo;
                exito = true;
            }

        }
        return exito;

    }

    public boolean eliminarVertice(Object elem) {
        boolean exito = false;

        if (!esVacio()) {
            if (this.primerNodo.getElemento().equals(elem)) {
                this.primerNodo = this.primerNodo.getSigNodoVert();
                exito = true;
            } else {
                NodoVert anterior = this.primerNodo;
                NodoVert actual = this.primerNodo.getSigNodoVert();

                while (!exito && actual != null) {
                    if (actual.getElemento().equals(elem)) {
                        actual.eliminarArcosDeAdyacentes();
                        anterior.setSigNodoVert(actual.getSigNodoVert());
                        exito = true;
                    }
                    anterior = actual;
                    actual = anterior.getSigNodoVert();

                }
            }

        }

        return exito;
    }

    public boolean insertarArco(Object nombre1, Object nombre2, double etiqueta) {
        NodoVert elem1 = null;
        NodoVert elem2 = null;
        boolean exito = false;

        if (!esVacio()) {
            NodoVert actual = this.primerNodo;
            while (actual != null && (elem1 == null || elem2 == null)) {
                // Verifico si el nodo vertice actual es alguna de las ciudades
                if (actual.getElemento().equals(nombre1)) {
                    elem1 = actual;
                }
                if (actual.getElemento().equals(nombre2)) {
                    elem2 = actual;
                }
                actual = actual.getSigNodoVert();
            }
            if (elem1 != null && elem2 != null) {
                exito = elem1.setArco(elem2, etiqueta);
                if (exito) {
                    elem2.setArco(elem1, etiqueta);
                }

            }

        }
        return exito;

    }

    public boolean eliminarArco(Object nombre1, Object nombre2) {
        boolean exito = false;
        NodoVert ciudad1 = null;
        NodoVert ciudad2 = null;

        if (!esVacio()) {
            NodoVert actual = this.primerNodo;
            while (actual != null && (ciudad1 == null || ciudad2 == null)) {
                if (actual.getElemento().equals(nombre1)) {
                    ciudad1 = actual;
                }
                if (actual.getElemento().equals(nombre2)) {
                    ciudad2 = actual;
                }
                actual = actual.getSigNodoVert();
            }
            if (ciudad1 != null && ciudad2 != null) {
                ciudad1.eliminarArco(nombre2);
                ciudad2.eliminarArco(nombre1);
            }

        }

        return exito;
    }

    public String toString() {
        String respuesta = "";
        if (!esVacio()) {
            NodoVert actual = this.primerNodo;

            while (actual != null) {
                respuesta += "\n" + actual.toString() + "\t";
                actual = actual.getSigNodoVert();
            }
        }
        return respuesta;
    }

    public boolean esVacio() {
        return this.primerNodo == null;
    }

    public Object getElemento(Object nombre) {
        Object respuesta = null;
        NodoVert aux = this.primerNodo;
        boolean encontrado = false;
        while (!encontrado && aux != null) {
            if (aux.getElemento().equals(nombre)) {
                respuesta = aux.getElemento();
                encontrado = true;
            }
            aux = aux.getSigNodoVert();
        }
        return respuesta;
    }

    public boolean pertenece(Object nombre) {
        NodoVert aux = this.primerNodo;
        boolean encontrado = false;
        while (!encontrado && aux != null) {
            if (aux.getElemento().equals(nombre)) {
                encontrado = true;
            }
            aux = aux.getSigNodoVert();
        }
        return encontrado;
    }
    // Metodos del TPO

    NodoVert buscarNodo(Object elemento) {
        NodoVert actual = this.primerNodo;
        NodoVert resp = null;
        boolean encontrado = false;
        while (actual != null && !encontrado) {
            encontrado = actual.getElemento().equals(elemento);
            if (encontrado) {
                resp = actual;
            }
            actual = actual.getSigNodoVert();
        }
        return resp;
    }

    public Lista caminoMasCorto(Object inicio, Object fin) {
        NodoVert nodoInicio = buscarNodo(inicio);
        NodoVert nodoFin = buscarNodo(fin);

        Lista mejorCamino = new Lista();
        if (nodoInicio != null && nodoFin != null) {
            Lista caminoActual = new Lista();
            Hash visitados = new Hash();
            double[] menorTiempo = { Double.MAX_VALUE };

            caminoMasCortoAux(nodoInicio, nodoFin, caminoActual, mejorCamino, visitados, 0, menorTiempo);

        }

        return mejorCamino;
    }

    private void caminoMasCortoAux(NodoVert actual, NodoVert fin, Lista caminoActual, Lista mejorCamino,
            Hash visitados, double tiempoActual, double[] menorTiempo) {
        visitados.insertar(actual);
        caminoActual.insertar(actual.getElemento(), caminoActual.longitud() + 1);

        if (actual.equals(fin) || tiempoActual > menorTiempo[0]) {

            if (tiempoActual < menorTiempo[0]) {
                System.out.println();
                System.out.println("Mejor camino: " + mejorCamino.toString() + " menor tiempo: " + menorTiempo[0]);
                System.out.println("Camino actual: " + caminoActual.toString() + " tiempo actual: " + tiempoActual);

                menorTiempo[0] = tiempoActual;
                mejorCamino.vaciar();
                for (int index = 1; index <= caminoActual.longitud(); index++) {
                    mejorCamino.insertar(caminoActual.recuperar(index), index);
                }
                System.out.println(
                        "Nuevo mejor camino: " + mejorCamino.toString() + " nuevo menor tiempo: " + menorTiempo[0]);
            }
        } else {
            NodoAdy adyacente = actual.getPrimerAdy();
            while (adyacente != null) {
                if (!visitados.pertenece(adyacente.getVertice())) {
                    caminoMasCortoAux(adyacente.getVertice(), fin, caminoActual, mejorCamino, visitados,
                            tiempoActual + adyacente.getEtiqueta(), menorTiempo);
                }
                adyacente = adyacente.getSigAdyacente();
            }
        }

        visitados.eliminar(actual);
        caminoActual.eliminar(caminoActual.longitud());
    }

    // Obtener el camino que llegue de A a B de menor tiempo
    public Lista caminoConMenosVertices(Object inicio, Object fin) {
        NodoVert nodoInicio = buscarNodo(inicio);
        NodoVert nodoFin = buscarNodo(fin);

        Lista mejorCamino = new Lista();
        if (nodoInicio != null && nodoFin != null) {
            Lista caminoActual = new Lista();
            Hash visitados = new Hash();
            double[] menorTiempo = { Double.MAX_VALUE };

            caminoConMenosVerticesAux(nodoInicio, nodoFin, caminoActual, mejorCamino, visitados, 0, menorTiempo);

        }

        return mejorCamino;
    }

    private void caminoConMenosVerticesAux(NodoVert actual, NodoVert fin, Lista caminoActual, Lista mejorCamino,
            Hash visitados, double cantVerticesActual, double[] menorVertices) {
        visitados.insertar(actual);
        caminoActual.insertar(actual.getElemento(), caminoActual.longitud() + 1);

        if (actual.equals(fin) || cantVerticesActual > menorVertices[0]) {

            if (cantVerticesActual < menorVertices[0]) {
                System.out.println();
                System.out.println("Mejor camino: " + mejorCamino.toString() + " cant vertices: " + menorVertices[0]);
                System.out.println(
                        "Camino actual: " + caminoActual.toString() + " cant vertices actual: " + cantVerticesActual);

                menorVertices[0] = cantVerticesActual;
                mejorCamino.vaciar();
                for (int index = 1; index <= caminoActual.longitud(); index++) {
                    mejorCamino.insertar(caminoActual.recuperar(index), index);
                }
                System.out.println(
                        "Nuevo mejor camino: " + mejorCamino.toString() + " nuevo menor cant vertices: "
                                + menorVertices[0]);
            }
        } else {
            NodoAdy adyacente = actual.getPrimerAdy();
            while (adyacente != null) {
                if (!visitados.pertenece(adyacente.getVertice())) {
                    caminoMasCortoAux(adyacente.getVertice(), fin, caminoActual, mejorCamino, visitados,
                            cantVerticesActual + 1, menorVertices);
                }
                adyacente = adyacente.getSigAdyacente();
            }
        }

        visitados.eliminar(actual);
        caminoActual.eliminar(caminoActual.longitud());
    }
}
