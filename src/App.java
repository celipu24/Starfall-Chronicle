import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    static int opcion;
    public static void main(String[] args) throws Exception {
        final int MAX = 20;
        Tripulante [] tripulante = new Tripulante[MAX];
        Habilidad [] habilidad = new Habilidad[MAX];
        Mision [] mision = new Mision[MAX];
        /*String [] rolesValidos = {"Piloto", "Ingeniero", "Marine", "Médico"};
        String [] tiposValidos = {"Tecnología", "Arma", "Soporte", "Hacking"};
        String [] tiposValidosTodos = {"Tecnología", "Arma", "Soporte", "Hacking", "Todos"};
        */
        int tope=0;
        boolean salir = false;
        do{
            mostrarMenu();
            switch(opcion) {
                case 1:
                    System.out.println("Insertar tripulante/habilidad/mision (al final)");
                    insertarDatos(tripulante, habilidad, mision);
                    break;
                case 14:
                    salir = true;
                    System.out.println("Has decidido salir del programa.");
                    break;
                default:
                    System.out.println("Opcion no valida, por favor seleccione una opcion del 1 al 14.");
            }
        }while(opcion != 14);
        sc.close();
    }

    //---FUNCIONALIDADES GENERALES-----
    //Metodo para mostrar el menu
    public static void mostrarMenu() {
        System.out.println("-------------STARFALL CHRONICLE-------------");
        System.out.println("Menu: ");
        System.out.println("1.- Insertar tripulante/habilidad/mision (al final)");
        System.out.println("2.- Insertar tripulante/habilidad/mision en posicion concreta del array");
        System.out.println("3.- Eliminar tripulante/habilidad/mision por posicion");
        System.out.println("4.- Mostrar todos tripulantes/habilidades/misiones");
        System.out.println("5.- Buscar tripulante/habilidad/mision por ID");
        System.out.println("6.- Ordenar tripulantes alfabeticamente");
        System.out.println("7.- Asignar habilidad a tripulante a partir de IDs");
        System.out.println("8.- Completar mision");
        System.out.println("9.- Contar misiones peligrosas");
        System.out.println("10.- Guardar datos en archivos de texto");
        System.out.println("11.- Cargar datos desde texto y mostrar");
        System.out.println("12.- Guardar datos en archivo binarios");
        System.out.println("13.- Cargar datos desde binarios y mostrar");
        System.out.println("14.- Salir");
        opcion = sc.nextInt();
        sc.nextLine();
    }



    //Método para leer un número delimitado
    static int leerEnteroEnRango(Scanner sc, String prompt, int min, int max) {
        int x;
        do {
            System.out.println(prompt);
            while (!sc.hasNextInt()) {
                System.out.print("Introduce un entero: ");
                sc.next();
            }
            x = sc.nextInt();
            sc.nextLine();
        } while (x < min || x > max);
        return x;
    } 

    //ARREGLAR ESTE METODO PARA QUE NO LEA LOS ESPACIOS O INSERTS
    //Método para leer un entero mínimo
    static int leerIntMin(Scanner sc, String prompt, int min) {
        int x;
        do {
            System.out.print(prompt);
            while (!sc.hasNextInt()) { System.out.print("Introduce un número: "); sc.next(); }
            x = sc.nextInt(); sc.nextLine();
        } while (x < min);
        return x;
    }

    //METODO PARA PEDIR UNA OPCION VALIDA
    public static String leerOpcionValida(Scanner sc, String prompt, String [] opcionesValidas) {
        String cadena = "";
        boolean esValida = false;
        while (!esValida) {
            System.out.print(prompt);
            cadena = sc.nextLine().trim();
            if (cadena.length() == 0) {
                System.out.println("No puede estar vacio. ");;
            } else {
                //creo la variable coincide para comprobar si la opcion introducida es valida y así salir del bucle
                boolean coincide = false;
                for (int i = 0; i < opcionesValidas.length; i++) {
                    if (cadena.equalsIgnoreCase(opcionesValidas[i])) {
                        coincide = true;
                    }
                }
                if(coincide) {
                    esValida = true;
                } else {
                    //String join es para unir los elementos de un array en una sola cadena separada por comas
                    System.out.println("Opcion no valida, debe ser una de las siguientes: " + String.join(", ", opcionesValidas));
                }
            }
        }
        return cadena;
    }

    //Método para comprobar que el nombre es válido(que no esté vacío)
    public static String leerNombreValido(Scanner sc, String prompt) {     
        String nombre = ""; 
        boolean esValido = false;  
        while(esValido!=true) {
            System.out.print(prompt);
            nombre = sc.nextLine().trim();
            //Comprueba que queda algo después de quitar espacios
            if (nombre.length() == 0) {
                System.out.println("El nombre no puede estar vacío. ");
            } else {
                esValido = true;
            }
        }
        return nombre;
    }


    //---FUNCIONALIDADES DEL PROGRAMA-----
    //1.Insertar tripulante/habilidad/misión (al final)
    public static void insertarDatos(Tripulante[] tripulante, Habilidad[] habilidad, Mision[] mision) {
        int desearContinuar;
        int tope=0;
        String [] rolesValidos = {"Piloto", "Ingeniero", "Marine", "Médico"};
        String [] tiposValidos = {"Tecnología", "Arma", "Soporte", "Hacking"};
        String [] tiposValidosTodos = {"Tecnología", "Arma", "Soporte", "Hacking", "Todos"};


        do{
            int eleccion = leerEnteroEnRango(sc,"Que desea insertar? 1.-Tripulante 2.-Habilidad 3.-Mision", 1, 3);
            if(eleccion == 1){
                //Insertar tripulante
                //ID, nombre, rol, nivel, experiencia, vida, energía
                if(tope<tripulante.length){
                    //creamos un nuevo tripulante en cada posicion del array
                    Tripulante t = new Tripulante();
                    //ID
                    t.id = leerIntMin(sc, "Introduce el ID: ", 0);
                    //NOMBRE
                    t.nombre = leerNombreValido(sc, "Introduce el nombre: ");
                    //ROL
                    t.rol = leerOpcionValida( sc,  "Introduce el rol (Piloto, Ingeniero, Marine, Medico): ",  rolesValidos);
                    //NIVEL
                    t.nivel = leerIntMin(sc, "Introduce el nivel: ", 0);
                    //EXPERIENCIA
                    t.experiencia = leerIntMin(sc, "Introduce la experiencia: ", 0);
                    //VIDA
                    t.vida = leerIntMin(sc, "Introduce la vida: ", 0);
                    //ENERGÍA
                    t.energía = leerIntMin(sc, "Introduce la energia: ", 0);
                    //en que puesto ha sido insertado
                    System.out.print("Tripulante insertado en posicion " + tope + ". ");
                    tope++;
                }else{
                    System.out.println("No se pueden insertar más tripulantes, el array está lleno.");
                }
                //--------------------------------------return tope;
            } else if (eleccion == 2) {
                //Insertar habilidad
                //ID, nombre, descripcion, tipo, costeEnergia, clasePermitida
                if(tope<habilidad.length){
                    //creamos una nueva habilidad en cada posicion del array
                    Habilidad h = new Habilidad();
                    //ID
                    h.id = leerIntMin(sc, "Introduce el ID: ", 0);
                    //NOMBRE
                    h.nombre = leerNombreValido(sc, "Introduce el nombre: ");
                    ////////NO SE MUY BIEN COMO HACER ESTO DE LA DESCRIPCION////////
                    System.out.print("Introduce la descripcion: ");
                    h.descripcion = leerNombreValido(sc, "Introduce la descripcion: ");
                    //TIPO
                    h.tipo = leerOpcionValida( sc,  "Introduce el tipo (Tecnología, Arma, Soporte, Hacking): ",  tiposValidos);
                    //COSTE ENERGIA
                    h.costeEnergia = leerIntMin(sc, "Introduce el coste de energia: ", 0);
                    //CLASE PERMITIDA
                    h.clasePermitida = leerOpcionValida( sc,  "Introduce que tipo tiene permitido usar esa habilidad (pueden ser todos): ",  tiposValidosTodos);

                    System.out.print("Habilidad insertada en posicion " + tope);
                    tope++;
                }else{
                    System.out.println("No se pueden insertar más habilidades, el array está lleno.");
                }
            } else {
                //Insertar mision
                //ID, nombre, dificultad, recompensaXP
                if(tope<mision.length){
                    //creamos una nueva mision en cada posicion del array
                    Mision m = new Mision();
                    //ID
                    m.id = leerIntMin(sc, "Introduce el ID: ", 0);
                    //NOMBRE
                    m.nombre = leerNombreValido(sc, "Introduce el nombre: ");
                    //DIFICULTAD
                    m.dificultad = leerEnteroEnRango(sc, "Introduce la dificultad (1..10): ", 1, 10);
                    //RECOMPENSA XP
                    m.recompensaXP = leerIntMin(sc, "Introduce la recompensa de XP: ", 0);
                    System.out.print("Mision insertada en posicion " + tope);
                    tope++;
                }else{
                    System.out.println("No se pueden insertar más misiones, el array está lleno.");
                }
            }
            desearContinuar = leerEnteroEnRango(sc, "Desea insertar otro dato? 1.-Si 2.-No", 1, 2);
        }while(desearContinuar != 2);
        
    }



}
