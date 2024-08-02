
public class Pila {
    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    public boolean apilar(Object elemento) {
        // creamos un nodo y le enlasamos con el tope
        Nodo nuevo = new Nodo(elemento, this.tope);
        // ahora el tope es el nuevo nodo
        this.tope = nuevo;
        // retornamos true
        return true;
    }

    public boolean desapilar() {
        boolean resultado = false;
        if (!this.esVacia()) {
            Nodo nuevo = this.tope.getEnlace();
            this.tope = nuevo;
            resultado = true;
        }
        return resultado;
    }

    public Object obtenerTope() {
        Object aux = null;
        if (this.tope != null) {
            aux = this.tope.getElemento();
        }
        return aux;
    }

    public boolean esVacia() {
        boolean resultado = false;
        if (this.tope == null) {
            resultado = true;
        }
        return resultado;
    }

    public void vaciar() {
        this.tope = null;
    }

    // to string
    @Override
    public String toString() {
        String contenido = "Pila vacia";

        if (this.tope != null) {
            contenido = "";
            // recorremos los enlaces de forma recursiva
            contenido = toStringRecursivoPaso(this.tope);
        }
        return contenido;
    }

    private String toStringRecursivoPaso(Nodo actual) {
        String contenido = "";
        if (actual != null) {
            contenido = "" + toStringRecursivoPaso(actual.getEnlace()) + "" + actual.getElemento() + ",";
        }
        return contenido;
    }

    @Override
    public Pila clone() {
        Pila clon = new Pila();
        this.clonarRecursivoPaso(clon, this.tope);
        return clon;
    }

    private void clonarRecursivoPaso(Pila pilaClon, Nodo actual) {

        if (actual != null) {
            clonarRecursivoPaso(pilaClon, actual.getEnlace());
            pilaClon.apilar(actual.getElemento());
        }

    }

}
