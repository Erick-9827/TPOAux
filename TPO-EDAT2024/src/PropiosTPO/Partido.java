package PropiosTPO;

public class Partido {
    private Equipo equipo1, equipo2;
    private String ronda, nombreEstadio;
    private Ciudad city;
    private int golesEquipo1, golesEquipo2;
    private boolean esEliminatoria;

    public Partido(Equipo e1, Equipo e2, String ronda, String nombreEstadio, Ciudad city, int golEqp1,
            int golEqp2, boolean eliminatoria) {
        if (e1.getNombrePais().compareTo(e2.getNombrePais()) > 0) {
            Equipo aux = e1;
            e1 = e2;
            e2 = aux;
            int golAux = golEqp1;
            golEqp1 = golEqp2;
            golEqp2 = golAux;
        }
        // instanciasion de variables
        this.equipo1 = e1;
        this.golesEquipo1 = golEqp1;
        this.equipo2 = e2;
        this.golesEquipo2 = golEqp2;
        this.ronda = ronda;
        this.nombreEstadio = nombreEstadio;
        this.city = city;
        this.esEliminatoria = eliminatoria;
        // Actualizacion de informacion de los partidos
        actualizarDatos(golEqp1, golEqp2);
    }

    private void actualizarDatos(int golEqp1, int golEqp2) {
        this.equipo1.setJugoPartido(true);
        this.equipo2.setJugoPartido(true);
        this.equipo1.actualizarGoles(this.golesEquipo1, this.golesEquipo2);
        this.equipo2.actualizarGoles(this.golesEquipo2, this.golesEquipo1);
        this.equipo1.actualizarFase(ronda);
        this.equipo2.actualizarFase(ronda);
        if (golEqp1 == golEqp2) {
            this.equipo1.aumentarPuntos(1);
            this.equipo2.aumentarPuntos(1);
        } else {
            if (golEqp1 > golEqp2) {
                this.equipo1.aumentarPuntos(3);
            } else {
                this.equipo2.aumentarPuntos(3);

            }
        }
    }

    public Partido(Equipo e1, Equipo e2) {
        if (e1.getNombrePais().compareTo(e2.getNombrePais()) > 0) {
            Equipo aux = e1;
            e1 = e2;
            e2 = aux;
        }
        this.equipo1 = e1;
        this.equipo2 = e2;
    }

    public String toString() {
        String info = "Equipo 1: " + this.equipo1.getNombrePais() + "  Equipo 2: " + this.equipo2.getNombrePais()
                + " Ronda: " + this.ronda + " Resultado: " + this.golesEquipo1 + "-" + this.golesEquipo2 + " Ciudad: "
                + this.city.getNombre() + " Estadio: " + this.nombreEstadio;

        return info;
    }

    public String getNombreEquipo1() {
        return "" + equipo1.getNombrePais();
    }

    public String getNombreEquipo2() {
        return (String) equipo2.getNombrePais();
    }

    public boolean isEsEliminatoria() {
        return esEliminatoria;
    }

    @Override
    public boolean equals(Object partido) {
        Partido p1 = (Partido) partido;
        String n1, n2;
        n1 = (String) this.equipo1.getNombrePais();
        n2 = (String) this.equipo2.getNombrePais();
        return (p1.getNombreEquipo1().equalsIgnoreCase(n1)
                && p1.getNombreEquipo2().equalsIgnoreCase(n2)) && p1.esEliminatoria == this.esEliminatoria;
    }

    @Override
    public int hashCode() {

        String n1 = ((String) this.equipo1.getNombrePais()).toLowerCase();
        String n2 = ((String) this.equipo2.getNombrePais()).toLowerCase();
        String key;
        if (n1.compareTo(n2) > 0) {
            key = n1 + n2;
        } else {
            key = n2 + n1;
        }
        int hash = 7;
        for (char c : key.toCharArray()) {
            hash = hash * 37 + c;
        }
        return hash;
    }

    public static int hashCode(String n1, String n2) {
        n1 = n1.toLowerCase();
        n2 = n2.toLowerCase();
        String key;
        if (n1.compareTo(n2) > 0) {
            key = n1 + n2;
        } else {
            key = n2 + n1;
        }
        int hash = 7;
        for (char c : key.toCharArray()) {
            hash = hash * 37 + c;
        }
        return hash;

    }

    public boolean sonEquipos(String nombre1, String nombre2) {
        if (nombre1.toLowerCase().compareTo(nombre2.toLowerCase()) > 0) {
            String aux = nombre1;
            nombre1 = nombre2;
            nombre2 = aux;
        }
        boolean e1 = this.equipo1.getNombrePais().toLowerCase().equals(nombre1.toLowerCase());
        boolean e2 = this.equipo2.getNombrePais().toLowerCase().equals(nombre2.toLowerCase());
        return e1 && e2;
    }

    public boolean sonEquipos(Equipo equipo1, Equipo equipo2) {
        String nombre1 = equipo1.getNombrePais();
        String nombre2 = equipo2.getNombrePais();

        if (nombre1.toLowerCase().compareTo(nombre2.toLowerCase()) > 0) {
            String aux = nombre1;
            nombre1 = nombre2;
            nombre2 = aux;
        }
        boolean e1 = this.equipo1.getNombrePais().toLowerCase().equals(nombre1.toLowerCase());
        boolean e2 = this.equipo2.getNombrePais().toLowerCase().equals(nombre2.toLowerCase());

        return e1 && e2;
    }

}
