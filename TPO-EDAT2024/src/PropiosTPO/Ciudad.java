package PropiosTPO;

public class Ciudad {

    private String nombre;
    private boolean alojamientoDisp, sedeDeCopa;

    public Ciudad(String nombre, boolean alojamientoDisp, boolean sedeDeCopa) {
        this.nombre = nombre;
        this.alojamientoDisp = alojamientoDisp;
        this.sedeDeCopa = sedeDeCopa;
    }

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    // observadores
    public String toString() {
        return nombre;

    }

    public String todaInformacion() {
        return "Nombre: " + this.nombre + " Sede: " + this.sedeDeCopa + " Alojamiento disponible: "
                + this.alojamientoDisp;

    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAlojamientoDisp() {
        return alojamientoDisp;
    }

    public boolean isSedeDeCopa() {
        return sedeDeCopa;
    }

    public boolean equals(Object ciudad) {
        boolean resp = false;
            resp = this.nombre.equalsIgnoreCase(((Ciudad) ciudad).getNombre());
        
        return resp;

    }
    // modificadores

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAlojamientoDisp(boolean alojamientoDisp) {
        this.alojamientoDisp = alojamientoDisp;
    }

    public void setSedeDeCopa(boolean sedeDeCopa) {
        this.sedeDeCopa = sedeDeCopa;
    }

}
