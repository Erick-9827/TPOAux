package PropiosTPO;

@SuppressWarnings("rawtypes")
public class Equipo implements Comparable {
    private String nombrePais;
    private String directorTecnico;
    private char grupo;
    private int puntaje = 0, cantGolesFavor, cantGolesContra;
    private boolean jugoSemis = false, jugoCuartos = false, jugoFinal = false;
    private static boolean ordenarPorgoles = false;
    private boolean jugoPartido = false;

    public Equipo(String pais, String directorTecnico, char grupo) {
        this.nombrePais = pais.toUpperCase();
        this.directorTecnico = directorTecnico.toUpperCase();
        this.grupo = grupo;
    }

    public Equipo(String nombrePais) {
        this.nombrePais = nombrePais.toUpperCase();
    }

    public static void setOrdenarPorGoles(boolean cambiar) {
        ordenarPorgoles = cambiar;
    }

    public void aumentarPuntos(int puntos) {
        this.puntaje += puntos;

    }

    public void actualizarFase(String fase) {
        String aux = fase.toLowerCase();
        switch (aux) {
            case "semis":
                jugoSemis = true;

                break;
            case "cuartos":
                jugoCuartos = true;

                break;
            case "final":
                jugoFinal = true;

                break;
            default:
                break;
        }

    }

    public boolean jugoFase(String fase) {
        String aux = fase.toLowerCase();
        boolean jugoFase = false;
        switch (aux) {
            case "semis":
                jugoFase = this.jugoSemis;

                break;
            case "cuartos":
                jugoFase = this.jugoCuartos;

                break;
            case "final":
                jugoFase = this.jugoFinal;

                break;
            default:
                break;
        }
        return jugoFase;

    }

    public void actualizarGoles(int aFavor, int enContra) {
        this.cantGolesContra += enContra;
        this.cantGolesFavor += aFavor;

    }

    // getters
    public int getCantGolesFavor() {
        return cantGolesFavor;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public String getDirectorTecnico() {
        return directorTecnico;
    }

    public char getGrupo() {
        return grupo;
    }

    public boolean isJugoPartido() {
        return jugoPartido;
    }

    @Override
    public int compareTo(Object equipo2) {
        int resp = 0;

        if (equipo2 instanceof Equipo) {

            if (ordenarPorgoles) {
                int n1 = this.cantGolesFavor;
                int n2 = ((Equipo) equipo2).cantGolesFavor;
                resp = Integer.compare(n1, n2);
            } else {
                String n1 = this.nombrePais.toLowerCase();
                String n2 = ((String) ((Equipo) equipo2).nombrePais).toLowerCase();
                resp = n1.compareTo(n2);
            }

        } else {
            if (equipo2 instanceof String) {
                String n2 = ((String) equipo2).toLowerCase();
                String n1 = this.nombrePais.toLowerCase();
                resp = n1.compareTo(n2);

            }
        }
        return resp;
    }

    public String todaInformacion() {
        return "Pais: " + this.nombrePais + " Puntaje: " + this.puntaje + " Gol a favor: "
                + this.cantGolesFavor + " Gol en contra: "
                + this.cantGolesContra + " Diferencia de goles " + (this.cantGolesFavor - this.cantGolesContra);

    }

    public String toString() {
        return this.nombrePais;
    }
    // modificadores

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais.toUpperCase();
    }

    public void setDirectorTecnico(String directorTecnico) {
        this.directorTecnico = directorTecnico.toUpperCase();
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    public void setJugoPartido(boolean jugoPartido) {
        this.jugoPartido = jugoPartido;
    }

}
