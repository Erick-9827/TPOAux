
import java.util.Scanner;
import Estructuras.AVL;
import Estructuras.Grafo;
import Estructuras.Hash;
import Estructuras.HeapMaximo;
import Estructuras.Lista;
import PropiosTPO.Ciudad;
import PropiosTPO.Equipo;
import PropiosTPO.Partido;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TPO {
    private static Scanner s = new Scanner(System.in);
    private static Grafo ciudades = new Grafo();
    private static AVL equipos = new AVL();
    private static Hash partidos = new Hash();

    public static void main(String[] args) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("archivoLog.txt"))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        cargaInicial();
        log("");
        log("--------------------------------------------------");
        log("Modificaciones");

        char respuesta;
        do {
            menu();
            String opcion = s.nextLine();
            switch (opcion) {
                case "1":
                    System.out.println("Eligio 1-Alta Baja Modificacion de ua ciudad");
                    ciudadABM();
                    break;
                case "2":
                    System.out.println("Eligio 2-Alta Baja Modificacion de un equipo");
                    equipoABM();

                    break;
                case "3":
                    System.out.println("Eligio 3-Altas de partidos");
                    altaPartido();

                    break;
                case "4":
                    System.out.println("Eligio 4-Consultas de equipo");
                    consultaDeEquipo();

                    break;
                case "5":
                    System.out.println("Eligio 5-Consulta sobre partidos");
                    System.out.println("Dados 2 equipos, si jugaron algún partido entre sí, mostrar los resultados.");
                    partidosEntreEquipos();
                    break;
                case "6":
                    System.out.println("Eligio 6-Consultas sobre viajes");
                    consultasSobreViajes();

                    break;
                case "7":
                    System.out.println("7-Lista de los equipos ordenados por cantidad de goles a favor");
                    Equipo.setOrdenarPorGoles(true);
                    HeapMaximo heap = equipos.heapOrdenado();

                    while (heap.obtenerCima() != null) {
                        System.out.println(((Equipo) heap.obtenerCima()).todaInformacion());
                        heap.eliminar();
                    }
                    Equipo.setOrdenarPorGoles(false);
                    break;
                case "8":
                    System.out.println("Eligio 8-Debugging del sistema");
                    debugging();
                    break;

                default:
                    System.out.println("Opcion elegida no valida");
                    break;
            }
            System.out.println("Desea ir al menu principal? y/n");
            respuesta = s.nextLine().toLowerCase().charAt(0);

        } while (respuesta == 'y');
        // Vacio el archivo para que no quede informacion de los usos pasados
        estadoFinal();
    }

    public static void estadoFinal() {
        log("");
        log("--------------------------------------------------");
        log("Estado Final del sistema");
        log("");
        log("Estado final de Equipos");
        log(equipos.toString());
        log("");
        log("Estado final de ciudades");
        log(ciudades.toString());
        log("");
        log("Estado final de partidos");
        log(partidos.toString());

    }

    // debugging, muestras las estructuras
    public static void debugging() {
        char respuesta;
        do {
            System.out.println("Elija una de las siguientes opciones");
            System.out.println("1-Ver contenido de Hashmap ");
            System.out.println("2-Ver contenido de Grafo");
            System.out.println("3-Ver contenido de AVL");
            String opcion = s.nextLine();
            switch (opcion) {
                case "1":
                    System.out.println(partidos.toString());
                    break;
                case "2":
                    System.out.println(ciudades.toString());
                    break;
                case "3":
                    System.out.println(equipos.toString());
                    break;

                default:
                    System.out.println("Opcion elegida no valida");
                    break;
            }
            System.out.println("Desea ir al menu de debugging? y/n");
            respuesta = s.nextLine().toLowerCase().charAt(0);
        } while (respuesta == 'y');
    }

    // Menu principal del sitema
    public static void menu() {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Elija una de las siguientes opciones");
        System.out.println("1-Altas, bajas y modificaciones de ciudades");
        System.out.println("2-Alta,baja,modificacion de un equipo");
        System.out.println("3-Altas de partidos");
        System.out.println("4-Consultas de equipo");
        System.out.println("5-Consultar si dos equipos jugaron un partido");
        System.out.println("6-Consultas sobre viajes");
        System.out.println("7-Lista de los equipos ordenados por cantidad de goles a favor");
        System.out.println("8-Debugging del sistema");
        System.out.println("--------------------------------------------------------------------");
    }

    // Consultas sobre viajes
    public static void consultasSobreViajes() {
        char respuesta;
        do {
            System.out.println("Elija una de las siguientes opciones");
            System.out.println("1-Obtener el camino que llegue de A a B de menor tiempo");
            System.out.println("2-Obtener el camino que llegue de A a B pasando por la mínima cantidad de ciudades");
            String opcion = s.nextLine();
            switch (opcion) {
                case "1":
                    String a, b;
                    System.out.println("Eligio 1-Obtener el camino que llegue de A a B de menor tiempo");
                    System.out.println("Ingrese el nombre de la ciudad A");
                    a = s.nextLine();
                    System.out.println("Ingrese el nombre de la ciudad B");

                    b = s.nextLine();
                    System.out.println(ciudades.caminoMasCorto(a, b));
                    break;
                case "2":
                    System.out.println(
                            "Eligio 2-Obtener el camino que llegue de A a B pasando por la mínima cantidad de ciudades");
                    System.out.println("Ingrese el nombre de la ciudad A");
                    a = s.nextLine();
                    System.out.println("Ingrese el nombre de la ciudad B");

                    b = s.nextLine();
                    System.out.println(ciudades.caminoConMenosVertices(a, b));
                    break;

                default:
                    System.out.println("Opcion elegida no valida");
                    break;
            }
            System.out.println("Desea ir al menu principal? y/n");
            respuesta = s.nextLine().toLowerCase().charAt(0);
        } while (respuesta == 'y');
    }

    // Altas de partido
    public static void altaPartido() {
        // Declaracion de variables
        int golPais1, golPais2;
        Equipo equipo1, equipo2;
        String nombreEquipo1, nombreEquipo2, nombreCiudad, fase, estadio;
        // Pedido de datos
        System.out.println("Ingrese el nombre del equipo 1");
        nombreEquipo1 = s.nextLine();
        equipo1 = (Equipo) equipos.getElemento(nombreEquipo1);
        System.out.println("Ingrese el nombre del equipo 2");
        nombreEquipo2 = s.nextLine();
        equipo2 = (Equipo) equipos.getElemento(nombreEquipo2);
        // Verifico que los equipos elegidos existan y que sean diferentes
        if (equipo1 != null && equipo2 != null && !equipo1.equals(equipo2)) {
            System.out.println(equipo1.toString());
            System.out.println(equipo2.toString());
            System.out.println("Ingrese el nombre de la ciudad");
            nombreCiudad = s.nextLine();
            Ciudad ciudad = (Ciudad) ciudades.getElemento(nombreCiudad);
            // Verifico que la ciudad elegida exista y sea sede de la copa
            if (ciudad != null && ciudad.isSedeDeCopa()) {
                System.out.println("Ingrese la fase del partido: Grupo/Cuartos/Semis/Final");
                fase = s.nextLine().toLowerCase();
                boolean esGrupo = fase.equals("grupo") && equipo1.getGrupo() == equipo2.getGrupo();
                boolean esEliminatoria = fase.equals("cuartos") || fase.equals("semis") || fase.equals("final");
                boolean faseJugada = equipo1.jugoFase(fase) || equipo2.jugoFase(fase);
                System.out.println("Fase jugada: " + faseJugada);
                /*
                 * Verifico:
                 * que elija correctamente una de las fases,
                 * que si la fase elegida es grupo ambos pertenezcan al mismo grupo
                 * que si la fase elejida es eliminatoria, ninguno de los equipos la haya jugado
                 */
                if ((esGrupo || esEliminatoria) && !faseJugada) {
                    System.out.println("Ingrese los goles de " + equipo1.getNombrePais());
                    golPais1 = s.nextInt();
                    System.out.println("Ingrese los goles de " + equipo2.getNombrePais());
                    golPais2 = s.nextInt();
                    s.nextLine();
                    System.out.println("Ingrese el nombre del estadio:");
                    estadio = s.nextLine();
                    Partido nuevo = new Partido(equipo1, equipo2, fase, estadio, ciudad, golPais1, golPais2,
                            esEliminatoria);
                    boolean exito = partidos.insertar(nuevo);
                    if (exito) {
                        System.out.println("Partido creado con exito");
                        log("Cree partido: " + nuevo.toString());
                    } else {
                        System.out.println("Ha ocurrido un error:");
                        System.out.println("1-Es posible que ya hayan jugado un partido en la fase elegida ");

                    }
                } else {
                    System.out.println("Ha ocurrido un error, verifique las siguientes cosas:");
                    System.out.println("1-Es fase de grupo y los equipos pertenecen a diferentes grupos.");
                    System.out.println("2-Es fase de elimitaria y uno o ambos equipos ya jugaron una.");
                    System.out.println("2-No escribio correctamente la fase en la que se encuentra el partido.");

                }
            } else {
                System.out.println("La ciudad no existe o no es sede del torneo.");

            }

        } else {
            System.out.println("Ha ocurrido un error, verifique las siguientes cosas:");
            System.out.println("1-Uno o ambos equipos no existen.");
            System.out.println("2-Escribio dos veces el mismo equipo");
        }
    }
    // Consulta de partido

    public static void partidosEntreEquipos() {
        // Declaracion de variables
        boolean noJugaron = true;
        String nombreEquipo1, nombreEquipo2;
        // Pedido de datos
        System.out.println("Ingrese el nombre del equipo 1");
        nombreEquipo1 = s.nextLine().toUpperCase();
        System.out.println("Ingrese el nombre del equipo 2");
        nombreEquipo2 = s.nextLine().toUpperCase();
        int key = Partido.hashCode(nombreEquipo1, nombreEquipo2) % partidos.getSize();
        Lista aux = partidos.recuperarConCode(key);
        int i = 1;
        while (i <= aux.longitud()) {
            Partido e = (Partido) aux.recuperar(i);
            if (e.sonEquipos(nombreEquipo1, nombreEquipo2)) {
                noJugaron = false;
                System.out.println(e.toString());

            }
            i = i + 1;
        }
        if (noJugaron) {
            System.out.println("No se encontraron partidos, verifique las posibles razones");
            System.out.println("1-Los equipos no jugaron ningun partido");
            System.out.println("2-Ingreso mal uno de los nombres");
        }

    }

    // Altas Bajas Modificaciones de ciudad
    public static void ciudadABM() {
        char respuesta = 'n';
        do {

            System.out.println("ABM Ciudad: Elija una de las siguientes opciones");
            System.out.println("1-Dar alta de una ciudad");
            System.out.println("2-Dar de baja una ciudad");
            System.out.println("3-Modificacion de una ciudad");

            String opcion = s.nextLine();
            switch (opcion) {
                case "1":
                    System.out.println("Eligio 1-Dar de alta una ciudad");
                    darAltaCiudad();

                    break;
                case "2":
                    System.out.println("Eligio 2-Dar de baja una ciudad");
                    darBajaCiudad();

                    break;
                case "3":
                    System.out.println("Eligio 3-Modificacion de una ciudad");
                    modificarCiudad();
                    break;
                default:
                    System.out.println("Opcion elegida no valida");
                    break;
            }
            System.out.println("ABM Ciudad: desea seguir ? y/n");
            respuesta = s.nextLine().toLowerCase().charAt(0);

        } while (respuesta == 'y');

    }

    public static boolean darAltaCiudad() {
        String nombre;
        char respuesta;
        boolean sedeCopa, alojamientoDisp, exito = false;

        System.out.print("Ingrese el nombre de la ciudad: ");
        nombre = s.nextLine().toUpperCase();
        System.out.print("Si es sede de copa ingrese y: ");
        respuesta = s.nextLine().toLowerCase().charAt(0);
        sedeCopa = respuesta == 'y';
        System.out.print("Si tiene alojamiento disponible ingrese y: ");
        respuesta = s.nextLine().toLowerCase().charAt(0);
        alojamientoDisp = respuesta == 'y';
        Ciudad nueva = new Ciudad(nombre, alojamientoDisp, sedeCopa);
        exito = ciudades.insertarVertice(nueva);
        if (exito) {
            System.out.println("Creado con exito");
            log("Agrego ciudad: " + nueva.todaInformacion());
        } else {
            System.out.println("Error, no se pudo crear la ciudad:");
            System.out.println("1-La ciudad ya existe");
        }
        return exito;
    }

    public static void darBajaCiudad() {
        System.out.println("Ingrese el nombre de la ciudad");
        String ciudad = s.nextLine();
        boolean exito = ciudades.eliminarVertice(ciudad);
        if (exito) {
            System.out.println("La ciudad se elimino con exito");
            log("Elimine la ciudad: " + ciudad.toUpperCase());
        } else {
            System.out.println("Hubo un error al eliminar,verifique una de las opciones");
            System.out.println("1-El nombre es incorrecto");
            System.out.println("2-La ciudad no existe");
        }
    }

    public static void modificarCiudad() {
        System.out.println("Ingrese el nombre de la ciudad");
        String nombreCiudad1 = s.nextLine(), nombreCiudad2;
        Ciudad ciudad = (Ciudad) ciudades.getElemento(nombreCiudad1), ciudad2;

        if (ciudad != null) {
            char respuesta = 'n';
            do {
                System.out.println("Que desea modificar");
                System.out.println("1-Nombre");
                System.out.println("2-Si es sede");
                System.out.println("3-Si tiene espacio disponible");
                System.out.println("4-Agregar un arco con otra ciudad");
                System.out.println("5-Eliminar un arco con otra ciudad");
                String opcion = s.nextLine();
                switch (opcion) {
                    case "1":
                        System.out.println("Ingrese el nuevo nombre de la ciudad:");
                        String nuevoNombre = s.nextLine().toUpperCase();
                        if (!ciudades.pertenece(nuevoNombre)) {
                            log("Cambie el nombre la ciudad: " + ciudad.getNombre() + " a: " + nuevoNombre);
                            ciudad.setNombre(nuevoNombre);
                            System.out.println("Nombre cambiado con exito.");
                        } else {
                            System.out.println("Ya existe una ciudad con ese nombre :");

                        }

                        break;
                    case "2":
                        System.out.print("Si es sede de copa ingrese y: ");
                        respuesta = s.nextLine().toLowerCase().charAt(0);
                        boolean sedeCopa = respuesta == 'y';
                        log("Cambie si es sede de copa: " + ciudad.isSedeDeCopa() + " a: " + sedeCopa);
                        ciudad.setSedeDeCopa(sedeCopa);

                        break;
                    case "3":
                        System.out.print("Si tiene alojamiento disponible ingrese y: ");
                        respuesta = s.nextLine().toLowerCase().charAt(0);
                        boolean alojamientoDisp = respuesta == 'y';
                        log("Cambie si tiene alojamiento disponible: " + ciudad.isAlojamientoDisp() + " a: "
                                + alojamientoDisp);
                        ciudad.setAlojamientoDisp(alojamientoDisp);
                        break;
                    case "4":
                        System.out.println("4-Agregar un arco con otra ciudad");
                        System.out.println("Ingrese el nombre de la otra ciudad");
                        nombreCiudad2 = s.nextLine();
                        ciudad2 = (Ciudad) ciudades.getElemento(nombreCiudad2);
                        if (ciudad2 != null && !ciudad.equals(ciudad2)) {
                            System.out.println("Ingrese el tiempo de vuelo entre ambas ciudades");
                            double tiempoVuelo = s.nextDouble();
                            ciudades.insertarArco(nombreCiudad1, nombreCiudad2, tiempoVuelo);
                            System.out.println("Arco agregado con exito");
                            log("Cree arco: " + nombreCiudad1.toUpperCase() + " a: " + nombreCiudad2.toUpperCase()
                                    + " etiqueta: " + tiempoVuelo);
                            s.nextLine();
                        } else {
                            System.out.println("La ciudad con la que quiere hacer arco no existe");
                        }

                        break;
                    case "5":
                        System.out.println("5-Eliminar un arco con otra ciudad");
                        System.out.println("Ingrese el nombre de la otra ciudad");
                        nombreCiudad2 = s.nextLine();
                        ciudad2 = (Ciudad) ciudades.getElemento(nombreCiudad2);
                        if (ciudad2 != null && !ciudad.equals(ciudad2)) {
                            if (ciudades.eliminarArco(nombreCiudad1, nombreCiudad2)) {
                                System.out.println("Arco eliminado con exito");
                                log("Elimine arco: " + nombreCiudad1.toUpperCase() + " a: "
                                        + nombreCiudad2.toUpperCase());
                            } else {
                                System.out.println("No existe arco con esa ciudad");
                            }

                        } else {
                            System.out.println("La ciudad no existe");
                        }
                        break;
                    default:
                        System.out.println("Opcion elegida no valida.");
                        break;
                }

                System.out.println("Modificacion de la ciudad " + nombreCiudad1 + " : desea seguir ? y/n");
                respuesta = s.nextLine().toLowerCase().charAt(0);

            } while (respuesta == 'y');
        } else {
            System.out.println("Hubo un error al modificar,verifique una de las opciones");
            System.out.println("1-El nombre es incorrecto");
            System.out.println("2-La ciudad no existe");
        }
    }

    // Altas Bajas Modificaciones de equipo
    public static void equipoABM() {
        char respuesta = 'n';
        do {
            System.out.println("ABM Equipo: Elija una de las siguientes opciones");
            System.out.println("1-Dar alta de un equipo");
            System.out.println("2-Dar de baja un equipo");
            System.out.println("3-Modificacion de un equipo");

            String opcion = s.nextLine();
            switch (opcion) {
                case "1":
                    System.out.println("Eligio 1-Dar de alta un equipo");
                    darAltaEquipo();

                    break;
                case "2":
                    System.out.println("Eligio 2-Dar de baja un equipo");
                    darBajaEquipo();

                    break;
                case "3":
                    System.out.println("Eligio 3-Modificacion de un equipo");
                    modificarEquipo();
                    break;
                default:
                    System.out.println("Opcion elegida no valida");
                    break;
            }
            System.out.println("ABM equipo: desea seguir ? y/n");
            respuesta = s.nextLine().toLowerCase().charAt(0);

        } while (respuesta == 'y');

    }

    public static boolean darAltaEquipo() {
        boolean exito = false;
        String pais;
        String tecnico;
        char grupo;
        System.out.print("Ingrese el nombre del pais: ");
        pais = s.nextLine();
        System.out.print("Ingrese el grupo:A-B-C-D: ");
        grupo = s.nextLine().toUpperCase().charAt(0);
        System.out.println();
        if (grupoAceptado(grupo)) {
            System.out.print("Ingrese el nombre del director tecnico: ");
            tecnico = s.nextLine();
            Equipo nuevo = new Equipo(pais, tecnico, grupo);
            exito = equipos.insertar(nuevo);
            if (exito) {
                System.out.println("Creado con exito");
                log("Agregue el equipo: " + nuevo.todaInformacion());
            } else {
                System.out.println("Error, no se pudo crear el equipo");
                System.out.println("El pais ya existe");

            }
        } else {
            System.out.println("El grupo no es correcto");
        }

        return exito;
    }

    public static boolean grupoAceptado(char grupo) {
        return (grupo == 'A' || grupo == 'B' || grupo == 'C' || grupo == 'D');

    }

    public static void darBajaEquipo() {
        System.out.println("Ingrese el nombre del pais");
        String pais = s.nextLine();
        boolean exito = equipos.eliminar(pais);
        if (exito) {
            log("Elimine el equipo: " + pais.toUpperCase());
            System.out.println("El equipo se elimino con exito");
        } else {
            System.out.println("Hubo un error al eliminar,verifique una de las opciones");
            System.out.println("1-El nombre es incorrecto");
            System.out.println("2-El pais no existe");
        }
    }

    public static void modificarEquipo() {
        System.out.println("Ingrese el nombre del pais");
        String pais = s.nextLine();
        Equipo equipo = (Equipo) equipos.getElemento(pais);

        if (equipo != null) {
            System.out.println(equipo.todaInformacion());
            System.out.println("Que desea modificar");
            System.out.println("1-Nombre");
            System.out.println("2-Grupo");
            System.out.println("3-Director tecnico");
            String opcion = s.nextLine();
            switch (opcion) {
                case "1":
                    System.out.println("Ingrese el nuevo nombre del pais:");
                    String nuevoNombre = s.nextLine();
                    if (!equipos.pertenece(nuevoNombre)) {
                        log("Cambie el nombre del pais: " + pais.toUpperCase() + " a: " + nuevoNombre.toUpperCase());
                        equipos.eliminar(pais);
                        equipo.setNombrePais(nuevoNombre);
                        equipos.insertar(equipo);
                    } else {
                        System.out.println("Ya existe un pais con ese nombre :");

                    }
                    System.out.println(equipo.todaInformacion());

                    break;
                case "2":
                    if (!equipo.isJugoPartido()) {
                        System.out.print("Ingrese el nuevo grupo:");
                        char grupo = s.nextLine().toUpperCase().charAt(0);
                        if (grupoAceptado(grupo)) {
                            log("Cambie el grupo del equipo de: " + equipo.getGrupo() + " a: " + grupo);
                            equipo.setGrupo(grupo);

                        } else {
                            System.out.println("Grupo ingresado no valido.");
                        }
                    } else {
                        System.out.println("Este equipo ya jugo un partido, su grupo ya no puede ser cambiado.");
                    }
                    break;
                case "3":
                    System.out.print("Ingrese el nombre del nuevo director tecnico:");
                    String tecnico = s.nextLine().toUpperCase();
                    log("Cambie el nombre del DT: " + equipo.getDirectorTecnico() + " a: " + tecnico);
                    equipo.setDirectorTecnico(tecnico);
                    break;

                default:
                    break;
            }
        } else {
            System.out.println("Hubo un error al eliminar,verifique una de las opciones");
            System.out.println("1-El nombre es incorrecto");
            System.out.println("2-El pais no existe");
        }
    }

    // Consultas de equipo
    public static void consultaDeEquipo() {

        char respuesta = 'n';
        do {

            System.out.println("Consultas de equipos: Elija una de las siguientes opciones");
            System.out.println("1-Mostrar informacion de un pais");
            System.out.println("2-Devolver los equipos cuyo nombre esté alfabéticamente en el rango de dos cadenas.");

            String opcion = s.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("1-Mostrar informacion de un pais");
                    datosEquipo();
                    break;
                case "2":
                    System.out.println(
                            "2-Devolver los equipos cuyo nombre esté alfabéticamente en el rango de dos cadenas.");
                    String min, max;
                    System.out.println("Ingrese la primera palabra");
                    min = s.nextLine();
                    System.out.println("Ingrese la segunda palabra");
                    max = s.nextLine();
                    System.out.println(equipos.listarRango(min, max));
                    break;
                default:
                    System.out.println("Opcion elegida no valida");
                    break;
            }
            System.out.println("Consultas de equipos: desea seguir ? y/n");
            respuesta = s.next().toLowerCase().charAt(0);
        } while (respuesta == 'y');

    }

    public static void datosEquipo() {
        System.out.println("Ingrese el nombre del pais");
        String pais = s.nextLine();
        Equipo equipo = (Equipo) equipos.getElemento(pais);

        if (equipo != null) {
            System.out.println(equipo.todaInformacion());
        } else {
            System.out.println("El equipo no fue encontrado.");

        }

    }

    // Carga inicial de las estructuras
    public static void cargaInicial() {
        boolean exito;
        // Cargar Equipos
        log("Carga inicial de datos");
        try (BufferedReader br = new BufferedReader(new FileReader("archivoEquipos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("; ");
                exito = equipos.insertar(new Equipo(datos[0], datos[1], datos[2].charAt(0)));
                if (!exito) {
                    System.out.println("no se inserto equipo " + datos[0]);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log("Carga inicial de equipos:");
        log(equipos.toString());

        // Cargar Ciudades
        try (BufferedReader br = new BufferedReader(new FileReader("archivoCiudades.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("; ");
                exito = ciudades.insertarVertice(
                        new Ciudad(datos[0], Boolean.parseBoolean(datos[1]), Boolean.parseBoolean(datos[2])));
                if (!exito) {
                    System.out.println("No se inserto ciudad " + datos[0]);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cargar Rutas
        try (BufferedReader br = new BufferedReader(new FileReader("archivoRutas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("; ");
                exito = ciudades.insertarArco(datos[0], datos[1], Integer.parseInt(datos[2]));
                if (!exito) {
                    System.out.println("No se inserto ruta " + datos[0] + " - " + datos[1]);

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log("Carga inicial de ciudades:");
        log(ciudades.toString());
        // Cargar Partidos
        try (BufferedReader br = new BufferedReader(new FileReader("archivoPartidos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("; ");
                int golPais1, golPais2;
                Equipo equipo1, equipo2;
                String fase, estadio;
                equipo1 = (Equipo) equipos.getElemento(datos[0]);
                equipo2 = (Equipo) equipos.getElemento(datos[1]);

                // Verifico que los equipos elegidos existan y que sean diferentes
                if (equipo1 != null && equipo2 != null && !equipo1.equals(equipo2)) {
                    Ciudad ciudad = (Ciudad) ciudades.getElemento(datos[3]);
                    // Verifico que la ciudad elegida exista y sea sede de la copa
                    if (ciudad != null && ciudad.isSedeDeCopa()) {
                        fase = datos[2].toLowerCase();
                        boolean esGrupo = fase.equals("grupo") && equipo1.getGrupo() == equipo2.getGrupo();
                        boolean esEliminatoria = fase.equals("cuartos") || fase.equals("semis") || fase.equals("final");
                        boolean faseJugada = equipo1.jugoFase(fase) || equipo2.jugoFase(fase);
                        if ((esGrupo || esEliminatoria) && !faseJugada) {
                            golPais1 = Integer.parseInt(datos[5]);
                            golPais2 = Integer.parseInt(datos[6]);
                            estadio = datos[4];
                            exito = partidos
                                    .insertar(new Partido(equipo1, equipo2, fase, estadio, ciudad, golPais1, golPais2,
                                            esEliminatoria));
                            if (!exito) {
                                System.out.println("No se inserto partido " + datos[0] + "-" + datos[1]);
                            }
                        }
                    } else {
                        System.out.println("No se inserto partido " + datos[0] + "-" + datos[1]);
                    }
                } else {
                    System.out.println("No se inserto partido " + datos[0] + "-" + datos[1]);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log("Carga inicial de partidos:");
        log(partidos.toString());
    }

    public static void log(String informacion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("archivoLog.txt", true))) {
            writer.write(informacion);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
