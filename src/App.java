
import java.util.Scanner;
import java.io.*;


public class App {
    public static void main(String[] args) throws Exception {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        final int MAX = 20;
        Tripulante [] tripulante = new Tripulante[MAX];
        Habilidad [] habilidad = new Habilidad[MAX];
        Mision [] mision = new Mision[MAX];
        
        int topeTripulante=0;
        int topeHabilidad=0;
        int topeMision=0;

        do{
            opcion = mostrarMenu(sc);
            switch(opcion) {
                case 1:
                    System.out.println("Insertar tripulante/habilidad/mision (al final)");
                    //creamos el array que almacena los topes actualizados
                    int [] topes = insertarDatos(sc, tripulante, habilidad, mision, topeTripulante, topeHabilidad, topeMision);
                    //1ºel tope de tripulantes, 2ºel de habilidades, 3ºel de misiones
                    topeTripulante = topes[0];
                    topeHabilidad = topes[1];
                    topeMision = topes[2];
                    break;
                case 2:
                    System.out.println("Insertar tripulante/habilidad/mision en posicion concreta del array");
                    //creamos el array que almacena los topes actualizados
                    int [] topesPos = insertarDatosPos(sc, tripulante, habilidad, mision, topeTripulante, topeHabilidad, topeMision);
                    //1ºel tope de tripulantes, 2ºel de habilidades, 3ºel de misiones
                    topeTripulante = topesPos[0];
                    topeHabilidad = topesPos[1];
                    topeMision = topesPos[2];
                    break;
                case 3:
                    System.out.println("Eliminar tripulante/habilidad/mision por posicion");
                    //creamos el array que almacena los topes actualizados
                    int [] topesElim = eliminarDatosPos(sc, tripulante, habilidad, mision, topeTripulante, topeHabilidad, topeMision);
                    //1ºel tope de tripulantes, 2ºel de habilidades, 3ºel de misiones
                    topeTripulante = topesElim[0];
                    topeHabilidad = topesElim[1];
                    topeMision = topesElim[2];
                    break;
                case 4:
                    System.out.println("Mostrar todos tripulantes/habilidades/misiones");
                    mostrarTodos(tripulante, habilidad, mision, topeTripulante, topeHabilidad, topeMision);
                    break;
                case 5:
                    System.out.println("Buscar tripulante/habilidad/mision por ID");
                    buscarPorID(sc, tripulante, habilidad, mision, topeTripulante, topeHabilidad, topeMision);
                    break;
                case 6:
                    System.out.println("Ordenar tripulantes alfabeticamente");
                    ordenarTripulantesAlfabeticamente(tripulante, topeTripulante);
                    break;
                case 7:
                    asignarHabilidadPorID(sc, tripulante, habilidad, topeTripulante, topeHabilidad);
                    break;
                case 8:
                    topeMision = completarMisionPorID(sc, tripulante, mision, topeTripulante, topeMision);
                    break;
                case 9:
                    int contador = contarPeligrosas(mision, topeMision, 0);
                    System.out.println("Misiones peligrosas (dificultad >= 7): " + contador);
                    break;
                 case 10:
                    System.out.println("Guardando datos en archivos de texto...");
                    guardarEnTexto(tripulante, habilidad, mision, topeTripulante, topeHabilidad, topeMision);
                    System.out.println("Guardado en texto completado");
                    break;
                case 11:
                    System.out.println("Cargar datos desde texto y mostrar");
                    int[] topesCargados = cargarDatosTexto(tripulante, habilidad, mision);
                    topeTripulante = topesCargados[0];
                    topeHabilidad = topesCargados[1];
                    topeMision = topesCargados[2];
                    break;
                case 12:
                    System.out.println("Guardar datos en archivos binarios");
                    guardarBinarios(tripulante, habilidad, mision, topeTripulante, topeHabilidad, topeMision);
                    break;
                case 13:
                    System.out.println("Cargar datos desde binarios y mostrar");
                    int[] nuevosTopes = cargarBinarios(tripulante, habilidad, mision);
                    topeTripulante = nuevosTopes[0];
                    topeHabilidad = nuevosTopes[1];
                    topeMision = nuevosTopes[2];
                    break;
                case 14:
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
    public static int mostrarMenu(Scanner sc) {
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
        int opcion = leerEnteroEnRango(sc, "Elija una opcion: ", 1, 14);
       //sc.nextLine();
       return opcion;
    }



    //Método para leer un número delimitado
    static int leerEnteroEnRango(Scanner sc, String prompt, int min, int max) {
        int x=0;
        boolean esValido = false;
        while(!esValido) {
            System.out.print(prompt);
            String entrada = sc.nextLine().trim();
            //1.-Comprobamos que no este vacío (ni inserts, ni espacios)
            if (entrada.length()==0) {
                System.out.print("No puede estar vacio.\n");
            //2.-Comprobamos que sea un int
            }else{
                boolean esNumero = true;
                //recorremos toda la cadena comprobando que todos los caracteres sean int
                for (int i = 0; i < entrada.length(); i++) {
                    //hacemos una variable char para almacenar el caracter actual, así solo se aceptara un - delante del numero
                    char c = entrada.charAt(i);
                    if(i==0 && c=='-'){
                    }else{
                        if (!Character.isDigit(c)) {
                            esNumero = false;
                        }
                    }
                }
                //salta mensaje de error si no es un numero
                if(!esNumero) {
                    System.out.print("Debe ser un numero entero.\n");
                }else{
                    //convierte el String a int
                    x = Integer.parseInt(entrada);
                    //3.-Comprobamos que sea mayor o igual que el minimo y menor o igual que el maximo
                    if (x < min || x > max) {
                        System.out.print("El numero introducido debe estar entre " + min + " y " + max + ".\n");
                    }else{
                        esValido = true;
                    }
                }
            }
        }
        return x;
    }

    //Método para leer un entero mínimo
    static int leerIntMin(Scanner sc, String prompt, int min) {
        int x=0;
        boolean esValido = false;
        while(!esValido) {
            System.out.print(prompt);
            String entrada = sc.nextLine().trim();
            //1.-Comprobamos que no este vacío (ni inserts, ni espacios)
            if (entrada.length()==0) {
                System.out.print("No puede estar vacio.\n");
            //2.-Comprobamos que sea un int
            }else{
                boolean esNumero = true;
                //recorremos toda la cadena comprobando que todos los caracteres sean int
                for (int i = 0; i < entrada.length(); i++) {
                    //hacemos una variable char para almacenar el caracter actual, así solo se aceptara un - delante del numero
                    char c = entrada.charAt(i);
                    if(i==0 && c=='-'){
                    }else{
                        if (!Character.isDigit(c)) {
                            esNumero = false;
                        }
                    }
                }
                //salta mensaje de error si no es un numero
                if(!esNumero) {
                    System.out.print("Debe ser un numero entero.\n");
                }else{
                    //convierte el String a int
                    x = Integer.parseInt(entrada);
                    //3.-Comprobamos que sea mayor o igual que el minimo
                    if (x < min) {
                        System.out.print("El numero introducido debe ser mayor que " + min  + ".\n");
                    }else{
                        esValido = true;
                    }
                }
            }
        }
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
                System.out.println("No puede estar vacío. ");;
            }else{
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
                    System.out.println("Opcion no valida. ");
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
            }else {
                esValido = true;
            }
        }
        return nombre;
    }


    //---FUNCIONALIDADES DEL PROGRAMA-----
    //-----------------------1.Insertar tripulante/habilidad/misión (al final)-----------------------
    //este método devuelve un array de enteros con los topes actualizados para poder trabajar con ellos por separado
    public static int[] insertarDatos(Scanner sc, Tripulante[] tripulante, Habilidad[] habilidad, Mision[] mision, int topeT, int topeH, int topeM) {
        int desearContinuar;
        do{
            int eleccion = leerEnteroEnRango(sc,"Que desea insertar? 1.-Tripulante 2.-Habilidad 3.-Mision:\n", 1, 3);
            if(eleccion == 1){
                topeT = insertarTripulante(sc, tripulante, topeT);
            } else if (eleccion == 2) {
                topeH = insertarHabilidad(sc, habilidad, topeH);
            } else {
                topeM = insertarMision(sc, mision, topeM);
            }
            desearContinuar = leerEnteroEnRango(sc, "Desea insertar otro dato? 1.-Si 2.-No \n", 1, 2);
        }while(desearContinuar != 2);
        return new int[]{topeT, topeH, topeM};
    }


    //separo el metodo para poder trabajar con distintos topes

    //Insertar tripulante
    public static int insertarTripulante(Scanner sc, Tripulante[] tripulante, int topeT) {
        String [] rolesValidos = {"Piloto", "Ingeniero", "Marine", "Medico"};
        //ID, nombre, rol, nivel, experiencia, vida, energía
        if(topeT<tripulante.length){
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
            t.energia = leerIntMin(sc, "Introduce la energia: ", 0);
            tripulante[topeT] = t;
            //en que puesto ha sido insertado
            System.out.println("Tripulante insertado en posicion " + topeT + ". ");
            topeT++;
        }else{
            System.out.println("No se pueden insertar más tripulantes, el array está lleno.");
        }
        //--------------------------------------return tope;
        return topeT;
    }

    //Insertar habilidad
    public static int insertarHabilidad(Scanner sc, Habilidad[] habilidad, int topeH) {
        String [] tiposValidos = {"Tecnologia", "Arma", "Soporte", "Hacking"};
        String [] rolesPermitidos = {"Piloto", "Ingeniero", "Marine", "Medico", "Todos"};
        //ID, nombre, descripcion, tipo, costeEnergia, clasePermitida
        if(topeH<habilidad.length){
            //creamos una nueva habilidad en cada posicion del array
            Habilidad h = new Habilidad();
            //ID
            h.id = leerIntMin(sc, "Introduce el ID: ", 0);
            //NOMBRE
            h.nombre = leerNombreValido(sc, "Introduce el nombre: ");
            //DESCRIPCION
            h.descripcion = leerNombreValido(sc, "Introduce la descripcion: ");
            //TIPO
            h.tipo = leerOpcionValida( sc,  "Introduce el tipo (Tecnología, Arma, Soporte, Hacking): ",  tiposValidos);
            //COSTE ENERGIA
            h.costeEnergia = leerIntMin(sc, "Introduce el coste de energia: ", 0);
            //CLASE PERMITIDA
            h.clasePermitida = leerOpcionValida( sc,  "Introduce que tipo tiene permitido usar esa habilidad (pueden ser todos): ",  rolesPermitidos);
            habilidad[topeH] = h;
            System.out.println("Habilidad insertada en posicion " + topeH  + ". ");
            topeH++;
        }else{
            System.out.println("No se pueden insertar más habilidades, el array está lleno.");
        }
        return topeH;
    }

    //Insertar mision
    public static int insertarMision(Scanner sc, Mision[] mision, int topeM) {
        //ID, nombre, dificultad, recompensaXP
        if(topeM<mision.length){
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
            mision[topeM] = m;
            System.out.println("Mision insertada en posicion " + topeM +  ". ");
            topeM++;
        }else{
            System.out.println("No se pueden insertar más misiones, el array está lleno.");
        }
        return topeM;
    }


    // -----------------------2.- Insertar tripulante/habilidad/misión en posición concreta del array-----------------------
        public static int[] insertarDatosPos(Scanner sc, Tripulante[] tripulante, Habilidad[] habilidad, Mision[] mision, int topeT, int topeH, int topeM) {
        int desearContinuar;
        do{
            int eleccion = leerEnteroEnRango(sc,"Que desea insertar? 1.-Tripulante 2.-Habilidad 3.-Mision:\n", 1, 3);
            if(eleccion == 1){
                if(topeT<tripulante.length){
                    int pos = leerEnteroEnRango(sc, "Introduce la posicion donde desea insertar los datos (0-" + (topeT) + "): ", 0, topeT);
                    topeT = insertarTripulantePos(sc, tripulante, topeT, pos);
                }else{
                    System.out.println("No se pueden insertar mas tripulantes, el array esta lleno.");
                }
            }else if (eleccion == 2) {
                if(topeH<habilidad.length){
                    int pos = leerEnteroEnRango(sc, "Introduce la posicion donde desea insertar los datos (0-" + (topeH) + "): ", 0, topeH);
                    topeH = insertarHabilidadPos(sc, habilidad, topeH, pos);
                }else{
                    System.out.println("No se pueden insertar mas habilidades, el array esta lleno.");
                }
            }else{
                if(topeM<mision.length){
                    int pos = leerEnteroEnRango(sc, "Introduce la posicion donde desea insertar los datos (0-" + (topeM) + "): ", 0, topeM);
                    topeM = insertarMisionPos(sc, mision, topeM, pos);
                }else{
                    System.out.println("No se pueden insertar mas misiones, el array esta lleno.");
                }
            }
            desearContinuar = leerEnteroEnRango(sc, "Desea insertar otro dato? 1.-Si 2.-No\n", 1, 2);
        }while(desearContinuar != 2);
        return new int[] {topeT, topeH, topeM};
    }


    //Insertar tripulante en posicion concreta
    public static int insertarTripulantePos(Scanner sc, Tripulante[] tripulante, int topeT, int pos) {
        String [] rolesValidos = {"Piloto", "Ingeniero", "Marine", "Medico"};
        //primero hacemos el desplazamiento de las posiciones del array para poder "insertar" el dato
        for(int i=topeT; i>pos; i--){
            tripulante[i] = tripulante[i-1];
        }
        //ID, nombre, rol, nivel, experiencia, vida, energía
        if(topeT<tripulante.length){
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
            t.energia = leerIntMin(sc, "Introduce la energia: ", 0);
            //en que puesto ha sido insertado
            System.out.println("Tripulante insertado en posicion " + pos + ". ");
            tripulante[pos] = t;
            topeT++;
        }else{
            System.out.println("No se pueden insertar más tripulantes, el array está lleno.");
        }
        return topeT;
    }


    //Insertar habilidad en posicion concreta
    public static int insertarHabilidadPos(Scanner sc, Habilidad[] habilidad, int topeH, int pos) {
        String [] tiposValidos = {"Tecnologia", "Arma", "Soporte", "Hacking"};
        String [] rolesPermitidos = {"Piloto", "Ingeniero", "Marine", "Medico", "Todos"};
        //primero hacemos el desplazamiento de las posiciones del array para poder "insertar" el dato
        for(int i=topeH; i>pos; i--){
            habilidad[i] = habilidad[i-1];
        }
        //ID, nombre, descripcion, tipo, costeEnergia, clasePermitida
        if(topeH<habilidad.length){
            //creamos una nueva habilidad en cada posicion del array
            Habilidad h = new Habilidad();
            //ID
            h.id = leerIntMin(sc, "Introduce el ID: ", 0);
            //NOMBRE
            h.nombre = leerNombreValido(sc, "Introduce el nombre: ");
            //DESCRIPCION
            h.descripcion = leerNombreValido(sc, "Introduce la descripcion: ");
            //TIPO
            h.tipo = leerOpcionValida( sc,  "Introduce el tipo (Tecnología, Arma, Soporte, Hacking): ",  tiposValidos);
            //COSTE ENERGIA
            h.costeEnergia = leerIntMin(sc, "Introduce el coste de energia: ", 0);
            //CLASE PERMITIDA
            h.clasePermitida = leerOpcionValida( sc,  "Introduce que tipo tiene permitido usar esa habilidad (pueden ser todos): ",  rolesPermitidos);
            System.out.print("Habilidad insertada en posicion " + pos);
            habilidad[pos] = h;
            topeH++;
        }else{
            System.out.println("No se pueden insertar más habilidades, el array está lleno.");
        }
        return topeH;
    }


    //Insertar mision en posicion concreta
    public static int insertarMisionPos(Scanner sc, Mision[] mision, int topeM, int pos) {
        //primero hacemos el desplazamiento de las posiciones del array para poder "insertar" el dato
        for(int i=topeM; i>pos; i--){
            mision[i] = mision[i-1];
        }
        //ID, nombre, dificultad, recompensaXP
        if(topeM<mision.length){
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
            System.out.println("Mision insertada en posicion " + pos);
            mision[pos] = m;
            topeM++;
        }else{
            System.out.println("No se pueden insertar más misiones, el array está lleno.");
        }
        return topeM;
    }

    //--------------3.- Eliminar tripulante/habilidad/misión por posición--------------
    public static int[] eliminarDatosPos(Scanner sc, Tripulante[] tripulante, Habilidad[] habilidad, Mision[] mision, int topeT, int topeH, int topeM) {
        int desearContinuar;
        do{
            int eleccion = leerEnteroEnRango(sc,"Que desea eliminar? 1.-Tripulante 2.-Habilidad 3.-Mision:\n", 1, 3);
            if(eleccion == 1 && topeT>0){
                topeT = eliminarTripulantePos(sc, tripulante, topeT);
            } else if (eleccion == 2 && topeH>0) {
                topeH = eliminarHabilidadPos(sc, habilidad, topeH);
            } else if (eleccion == 3 && topeM>0) {
                topeM = eliminarMisionPos(sc, mision, topeM);
            }else{
                System.out.println("No hay datos que eliminar en esta categoria.");
            }
            desearContinuar = leerEnteroEnRango(sc, "Desea eliminar otro dato? 1.-Si 2.-No\n", 1, 2);
        }while(desearContinuar != 2);
        return new int[]{topeT, topeH, topeM};
    }

    //Eliminar tripulante por posicion
    public static int eliminarTripulantePos(Scanner sc, Tripulante[] tripulante, int topeT) {
        int pos = leerEnteroEnRango(sc, "Introduce la posicion del tripulante que desea eliminar (0-" + (topeT-1) + "): \n", 0, topeT-1);
        System.out.println("Vas a eliminar al tripulante: ID: " + tripulante[pos].id + " | " + tripulante[pos].nombre + " (" + tripulante[pos].rol + ") | nivel:" + tripulante[pos].nivel + " | xp:" + tripulante[pos].experiencia + " | vida:" + tripulante[pos].vida + " | energia:" + tripulante[pos].energia + " | habs: [" + tripulante[pos].habilidadesAprendidas[0] + ", " + tripulante[pos].habilidadesAprendidas[1] + ", " + tripulante[pos].habilidadesAprendidas[2] + "]");
        int confirmar = leerEnteroEnRango(sc, "Esta seguro de querer eliminar este tripulante? (1.-Si, 2.-No)\n", 1, 2);
        if(confirmar == 1){
            //desplazamos las posiciones del array para "eliminar" el dato
            for(int i=pos; i<topeT-1; i++){
                tripulante[i] = tripulante[i+1];
            }
            tripulante[topeT-1] = null; //opcional, para evitar referencias colgantes
            topeT--;
            System.out.println("Tripulante eliminado de la posicion " + pos + ". ");
        }else{
            System.out.println("No se ha eliminado ningun tripulante.");
        }
        return topeT;
    }


    //Eliminar habilidad por posicion
    public static int eliminarHabilidadPos(Scanner sc, Habilidad[] habilidad, int topeH) {
        int pos = leerEnteroEnRango(sc, "Introduce la posicion de la habilidad que desea eliminar (0-" + (topeH-1) + "): \n", 0, topeH-1);
        System.out.println("Vas a eliminar la habilidad: ID:" + habilidad[pos].id + " | " + habilidad[pos].nombre + " (" + habilidad[pos].tipo + ") | coste energia:" + habilidad[pos].costeEnergia + " | clase permitida:" + habilidad[pos].clasePermitida + " | " + habilidad[pos].descripcion + ". ");
        int confirmar = leerEnteroEnRango(sc, "Esta seguro de querer eliminar esta habilidad? (1.-Si, 2.-No)\n", 1, 2);
        if(confirmar == 1){
            //desplazamos las posiciones del array para "eliminar" el dato
            for(int i=pos; i<topeH-1; i++){
                habilidad[i] = habilidad[i+1];
            }
            habilidad[topeH-1] = null;
            topeH--;
            System.out.println("Habilidad eliminada de la posicion " + pos + ".");
        }else{
            System.out.println("No se ha eliminado ninguna habilidad.");
        }
        return topeH;
    }

    //Eliminar mision por posicion
    public static int eliminarMisionPos(Scanner sc, Mision[] mision, int topeM) {
        int pos = leerEnteroEnRango(sc, "Introduce la posicion de la mision que desea eliminar (0-" + (topeM-1) + "): \n", 0, topeM-1);
        System.out.println("Vas a eliminar la mision: ID:" + mision[pos].id + " | " + mision[pos].nombre + " | dificultad:" + mision[pos].dificultad + " | recompensa xp:" + mision[pos].recompensaXP + ". ");
        int confirmar = leerEnteroEnRango(sc, "Esta seguro de querer eliminar esta mision? (1.-Si, 2.-No)\n", 1, 2);
        if(confirmar == 1){
            //desplazamos las posiciones del array para "eliminar" el dato
            for(int i=pos; i<topeM-1; i++){
                mision[i] = mision[i+1];
            }
            mision[topeM-1] = null;
            topeM--;
            System.out.println("Mision eliminada de la posicion " + pos + ". ");
        }else{
            System.out.println("No se ha eliminado ningun dato.");
        }
        return topeM;
    }


    //-----------------------4.-Mostrar todos tripulantes/habilidades/misiones-----------------------
    public static void mostrarTodos(Tripulante[] tripulante, Habilidad[] habilidad, Mision[] mision, int topeT, int topeH, int topeM) {
        System.out.println("-----TRIPULANTES-----");
        mostrarTodosTripulantes(tripulante, topeT);
        System.out.println("-----HABILIDADES-----");
        mostrarTodosHabilidades(habilidad, topeH);
        System.out.println("-----MISIONES-----");
        mostrarTodosMisiones(mision, topeM);
    }


    //Mostrar todos los tripulantes
    public static void mostrarTodosTripulantes(Tripulante[] tripulante, int topeT) {
        if(topeT==0){
            System.out.println("No hay tripulantes que mostrar.");
        }else{
            for(int i=0; i<topeT; i++){
                System.out.println("[" + i + "] " + " ID:" + tripulante[i].id + " | " + tripulante[i].nombre + " (" + tripulante[i].rol + ") | nivel:" + tripulante[i].nivel + " | xp:" + tripulante[i].experiencia + " | vida:" + tripulante[i].vida + " | energia:" + tripulante[i].energia + " | habs: [" + tripulante[i].habilidadesAprendidas[0] + ", " + tripulante[i].habilidadesAprendidas[1] + ", " + tripulante[i].habilidadesAprendidas[2] + "] ");
            }
        }
    }
    //Mostrar todas las habilidades
    public static void mostrarTodosHabilidades(Habilidad[] habilidad, int topeH) {
        if(topeH==0){
            System.out.println("No hay habilidades que mostrar.");
        }else{
            for(int i=0; i<topeH; i++){
                System.out.println("[" + i + "] " + " ID:" + habilidad[i].id + " | " + habilidad[i].nombre + " (" + habilidad[i].tipo + ") | coste energia:" + habilidad[i].costeEnergia + " | clase permitida:" + habilidad[i].clasePermitida + " | " + habilidad[i].descripcion);
            }
        }
    }
    //Mostrar todas las misiones
    public static void mostrarTodosMisiones(Mision[] mision, int topeM) {
        if(topeM==0){
            System.out.println("No hay misiones que mostrar.");
        }else{
            for(int i=0; i<topeM; i++){
                System.out.println("[" + i + "] " + " ID:" + mision[i].id + " | " + mision[i].nombre + " | dificultad:" + mision[i].dificultad + " | recompensa xp:" + mision[i].recompensaXP);
            }
        }
    }

    //-----------------------5.-Buscar tripulante/habilidad/misión por ID-----------------------
    //Búsqueda genral por ID
    public static void buscarPorID(Scanner sc, Tripulante[] tripulante, Habilidad[] habilidad, Mision[] mision, int topeT, int topeH, int topeM) {
        int desearContinuar;
        do {
            int eleccion = leerEnteroEnRango(sc, "Que desea buscar? 1.-Tripulante 2.-Habilidad 3.-Mision: ", 1, 3);
            int idBuscado = leerIntMin(sc, "Introduce el ID a buscar: ", 0);

            boolean encontrado;

            if (eleccion == 1) {
                encontrado = buscarTripulantePorID(tripulante, topeT, idBuscado);
            } else if (eleccion == 2) {
                encontrado = buscarHabilidadPorID(habilidad, topeH, idBuscado);
            } else {
                encontrado = buscarMisionPorID(mision, topeM, idBuscado);
            }

            if (!encontrado) {
                System.out.println("No existe ningún elemento con ese ID");
            }

            desearContinuar = leerEnteroEnRango(sc, "Desea buscar otro dato? 1.-Si 2.-No: ", 1, 2);
        } while (desearContinuar != 2);
    }

    //Busqueda tripulante por ID
    public static boolean buscarTripulantePorID(Tripulante[] tripulante, int topeT, int id) {
        for (int i = 0; i < topeT; i++) {
            if (tripulante[i].id == id) {
                System.out.println("-----TRIPULANTE ENCONTRADO-----");
                System.out.println(
                        "ID:" + tripulante[i].id +
                        " | " + tripulante[i].nombre +
                        " (" + tripulante[i].rol + ")" +
                        " | nivel:" + tripulante[i].nivel +
                        " | xp:" + tripulante[i].experiencia +
                        " | vida:" + tripulante[i].vida +
                        " | energia:" + tripulante[i].energia +
                        " | habs: [" +
                        tripulante[i].habilidadesAprendidas[0] + ", " +
                        tripulante[i].habilidadesAprendidas[1] + ", " +
                        tripulante[i].habilidadesAprendidas[2] + "]"
                );
                return true;
            }
        }
        return false;
    }

    //Busqueda habilidad por ID
    public static boolean buscarHabilidadPorID(Habilidad[] habilidad, int topeH, int id) {
        for (int i = 0; i < topeH; i++) {
            if (habilidad[i].id == id) {
                System.out.println("-----HABILIDAD ENCONTRADA-----");
                System.out.println(
                        "ID:" + habilidad[i].id +
                        " | " + habilidad[i].nombre +
                        " (" + habilidad[i].tipo + ")" +
                        " | coste energia:" + habilidad[i].costeEnergia +
                        " | clase permitida:" + habilidad[i].clasePermitida +
                        " | " + habilidad[i].descripcion
                );
                return true;
            }
        }
        return false;
    }

    //Busqueda mision por ID
    public static boolean buscarMisionPorID(Mision[] mision, int topeM, int id) {
        for (int i = 0; i < topeM; i++) {
            if (mision[i].id == id) {
                System.out.println("-----MISION ENCONTRADA-----");
                System.out.println(
                        "ID:" + mision[i].id +
                        " | " + mision[i].nombre +
                        " | dificultad:" + mision[i].dificultad +
                        " | recompensa xp:" + mision[i].recompensaXP
                );
                return true;
            }
        }
        return false;
    }

    //-----------------------6.	Ordenar tripulantes alfabéticamente-----------------------
    // Ordenar los tripulantes alfabeticamente
    public static void ordenarTripulantesAlfabeticamente(Tripulante[] tripulante, int topeT) {
        if (topeT <= 1) {
            System.out.println("No hay suficientes tripulantes para ordenar");
            return;
        }
        for (int i = 0; i < topeT - 1; i++) {
            for (int j = 0; j < topeT - 1 - i; j++) {

                if (tripulante[j].nombre.compareToIgnoreCase(tripulante[j + 1].nombre) > 0) {
                    Tripulante aux = tripulante[j];
                    tripulante[j] = tripulante[j + 1];
                    tripulante[j + 1] = aux;
                }
            }
        }
        System.out.println("-----TRIPULANTES ORDENADOS ALFABETICAMENTE-----");
        mostrarTodosTripulantes(tripulante, topeT);
    }


    // -----------------------7.-Asignar habilidad a tripulante a partir de IDs-----------------------
    //comprueba existencia por id, no duplica, máximo 3, y respeta clasePermitida ("Todos" o rol)
    public static void asignarHabilidadPorID(Scanner sc, Tripulante[] tripulante, Habilidad[] habilidad, int topeT, int topeH) {
        if (topeT == 0 || topeH == 0) {
            System.out.println("Faltan tripulantes o habilidades.");
            return;
        }

        int idT = leerIntMin(sc, "Introduce ID del tripulante: ", 0);
        Tripulante t = null;
        for (int i = 0; i < topeT; i++) {
            if (tripulante[i].id == idT) {
                t = tripulante[i];
                break;
            }
        }
        if (t == null) {
            System.out.println("Tripulante no encontrado.");
            return;
        }

        int idH = leerIntMin(sc, "Introduce ID de la habilidad: ", 0);
        Habilidad h = null;
        for (int i = 0; i < topeH; i++) {
            if (habilidad[i].id == idH) {
                h = habilidad[i];
                break;
            }
        }
        if (h == null) {
            System.out.println("Habilidad no encontrada.");
            return;
        }

        //Comprobar permiso de rol
        if (!h.clasePermitida.equalsIgnoreCase("Todos") && !h.clasePermitida.equalsIgnoreCase(t.rol)) {
            System.out.println("Rol del tripulante no permitido para esta habilidad.");
            return;
        }

        //Comprobar duplicado y hueco
        for (int i = 0; i < t.habilidadesAprendidas.length; i++) {
            if (t.habilidadesAprendidas[i] == idH) {
                System.out.println("El tripulante ya tiene esa habilidad.");
                return;
            }
        }
        for (int i = 0; i < t.habilidadesAprendidas.length; i++) {
            if (t.habilidadesAprendidas[i] == -1) {
                t.habilidadesAprendidas[i] = idH;
                System.out.println("Habilidad asignada correctamente.");
                return;
            }
        }
        System.out.println("No hay hueco para mas habilidades (max 3).");
    }

    //-----------------------8.- Completar misión-----------------------
    // Reparte la recompensaXP de la misión a todos los tripulantes, evalúa subidas de nivel
    // y elimina la misión del array (desplazando).
    public static int completarMisionPorID(Scanner sc, Tripulante[] tripulante, Mision[] mision, int topeT, int topeM) {
        if (topeM == 0) {
            System.out.println("No hay misiones registradas.");
            return topeM;
        }
        int idM = leerIntMin(sc, "Introduce ID de la mision a completar: ", 0);
        int pos = -1;
        for (int i = 0; i < topeM; i++) {
            if (mision[i].id == idM) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            System.out.println("Mision no encontrada.");
            return topeM;
        }

        System.out.println(
            "Completando misión: ID:" + mision[pos].id +
            " | " + mision[pos].nombre +
            " | dif:" + mision[pos].dificultad +
            " | XP:" + mision[pos].recompensaXP 
        );

        //repartir XP a todos los tripulantes y comprobar subida de nivel
        for (int i = 0; i < topeT; i++) {
            Tripulante t = tripulante[i];
            t.experiencia += mision[pos].recompensaXP;
            boolean subio = false;
            while (t.experiencia >= (int) Math.floor(t.nivel * 100 * 1.2)) {
                int umbral = (int) Math.floor(t.nivel * 100 * 1.2);
                //experiencia sobrante la conservamos para el siguiente nivel
                t.experiencia -= umbral;
                t.nivel += 1;
                t.vida += (int) Math.floor(t.vida * 0.10);
                t.energia += (int) Math.floor(t.energia * 0.25);
                subio = true;
            }
            if (subio) {
                System.out.println(t.nombre + " ha subido. Nuevo nivel: " + t.nivel + " | vida:" + t.vida + " | energia:" + t.energia);
            }
        }

        //eliminamos la mision del array
        for (int i = pos; i < topeM - 1; i++) {
            mision[i] = mision[i+1];
        }
        mision[topeM - 1] = null;
        topeM--;
        System.out.println("Misión completada y eliminada del listado.");
        return topeM;
    }

    //-----------------------9.-Contar misiones peligrosas (Función recursiva)-----------------------
    public static int contarPeligrosas(Mision[] misiones, int tope, int i) {
        //caso base
        if (i == tope) {
            return 0;
        }
        if (misiones[i].dificultad >= 7) {
            return 1 + contarPeligrosas(misiones, tope, i + 1);
        }
        return contarPeligrosas(misiones, tope, i + 1);
    }

    //-----------------------10.-Guardar datos en archivos de texto-------------------------
    public static void guardarEnTexto(Tripulante[] tripulante, Habilidad [] habilidad, Mision [] mision, int topeT, int topeH, int topeM){

        try(PrintWriter pw = new PrintWriter(new FileWriter ("tripulantes.txt"))){
            for(int i = 0; i < topeT; i++){
                Tripulante t = tripulante[i];
                pw.println(t.id + "," + t.nombre + "," + t.rol + "," + t.nivel + "," + 
                        t.vida + "," + t.energia + "," + 
                        t.habilidadesAprendidas[0] + "," + 
                        t.habilidadesAprendidas[1] + "," + 
                        t.habilidadesAprendidas[2]);
            }
            System.out.println("Archivo 'tripulantes.txt' guardado con exito");
        }catch (IOException e){
            System.out.println("Error al guardar tripulantes: " + e.getMessage());
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter("habilidades.txt"))){
            for(int i = 0; i < topeH; i++){
                Habilidad h = habilidad[i];
                pw.println(h.id + "," + h.nombre + "," + h.tipo + "," + 
                        h.costeEnergia + "," + h.clasePermitida + "," + h.descripcion);
            }
            System.out.println("Archivo 'habilidades.txt' guardado con exito");
        }catch (IOException e){
            System.out.println("Error al guardar habilidades: " + e.getMessage());
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter("misiones.txt"))){
            for (int i = 0; i < topeM; i ++){
                Mision m = mision[i];
                pw.println(m.id + "," + m.nombre + "," + m.dificultad + "," + m.recompensaXP);
            }
            System.out.println("Archivo 'misiones.txt' guardado con exito");
        }catch (IOException e){
            System.out.println("Error al guardar misiones: " + e.getMessage());
        }
    }

    //---------------------------------11.-Cargar datos desde texto y mostrar-------------------------------
    public static int[] cargarDatosTexto(Tripulante[] tripulantes, Habilidad [] habilidades, Mision[] misiones){
        int topeT = 0, topeH = 0, topeM = 0;
        try {
            File fT = new File("tripulantes.txt");
            if(fT.exists()){
                Scanner lector = new Scanner(fT);
                while (lector.hasNextLine() && topeT < tripulantes.length){
                    String linea = lector.nextLine();
                    String[] partes = linea.split(",");
                    if(partes.length >= 9){
                        Tripulante t = new Tripulante();
                        t.id = Integer.parseInt(partes[0]);
                        t.nombre = partes[1];
                        t.rol = partes[2];
                        t.nivel = Integer.parseInt(partes[3]);
                        t.vida = Integer.parseInt(partes[4]);
                        t.energia = Integer.parseInt(partes[5]);
                        t.habilidadesAprendidas[0] = Integer.parseInt(partes[6]);
                        t.habilidadesAprendidas[1] = Integer.parseInt(partes[7]);
                        t.habilidadesAprendidas[2] = Integer.parseInt(partes[8]);
                        tripulantes[topeT] = t;
                        topeT++;
                    }
                }
                lector.close();
                System.out.println("Tripulantes cargados correctamente");
                mostrarTodosTripulantes(tripulantes, topeT);
            }
        }catch(Exception e){
            System.out.println("Error al cargar tripulantes: " + e.getMessage());
        }
        try{
            File fH = new File("habilidades.txt");
            if (fH.exists()){
                Scanner lector = new Scanner(fH);
                while (lector.hasNextLine() && topeH < habilidades.length){
                    String linea = lector.nextLine();
                    String[] partes = linea.split(",");
                    Habilidad h = new Habilidad();
                    h.id = Integer.parseInt(partes[0]);
                    h.nombre = partes[1];
                    h.tipo = partes[2];
                    h.costeEnergia = Integer.parseInt(partes[3]);
                    h.clasePermitida = partes[4];
                    h.descripcion = partes[5];
                    habilidades[topeH] = h;
                    topeH++;
                }
                lector.close();
                System.out.println("Habilidades cargadas correctamente");
                mostrarTodosHabilidades(habilidades, topeH);
            }
        }catch(Exception e){
            System.out.println("Error al cargar habilidades: " + e.getMessage());
        }
        try {
            File fM = new File("misiones.txt");
            if (fM.exists()){
                Scanner lector = new Scanner(fM);
                while (lector.hasNextLine() && topeM < misiones.length){
                    String linea = lector.nextLine();
                    String[] partes = linea.split(",");
                    Mision m = new Mision();
                    m.id = Integer.parseInt(partes[0]);
                    m.nombre = partes[1];
                    m.dificultad = Integer.parseInt(partes[2]);
                    m.recompensaXP = Integer.parseInt(partes[3]);
                    misiones[topeM] = m;
                    topeM++;
                }
                lector.close();
                System.out.println("Misiones cargadas correctamente");
                mostrarTodosMisiones(misiones, topeM);
            }
        }catch(Exception e){
            System.out.println("Error al cargar misiones: " + e.getMessage());
        }
        return new int[] {topeT, topeH, topeM};
    }


    //--------------------------12.-Guardar datos en archivos binarios----------------------------------
    public static void guardarBinarios(Tripulante[] tripulante, Habilidad[] habilidad, Mision[] mision,
                                   int topeTripulante, int topeHabilidad, int topeMision) {
        try {
            ObjectOutputStream outTrip = new ObjectOutputStream(new FileOutputStream("tripulantes.dat"));
            for (int i = 0; i < topeTripulante; i++) {
                outTrip.writeObject(tripulante[i]);
            }
            outTrip.close();

            ObjectOutputStream outHab = new ObjectOutputStream(new FileOutputStream("habilidades.dat"));
            for (int i = 0; i < topeHabilidad; i++) {
                outHab.writeObject(habilidad[i]);
            }
            outHab.close();

            ObjectOutputStream outMis = new ObjectOutputStream(new FileOutputStream("misiones.dat"));
            for (int i = 0; i < topeMision; i++) {
                outMis.writeObject(mision[i]);
            }
            outMis.close();

            System.out.println("Datos guardados correctamente en binario.");
        } catch (IOException e) {
            System.out.println("Error al guardar archivos binarios.");
        }
    }
        
        //-----------------------------13.-Cargar datos desde binarios y mostrar--------------------
        public static int[] cargarBinarios(Tripulante[] tripulante, Habilidad[] habilidad, Mision[] mision) {
        int topeTrip = 0, topeHab = 0, topeMis = 0;

        try {
            ObjectInputStream inTrip = new ObjectInputStream(new FileInputStream("tripulantes.dat"));
            while (true) {
                tripulante[topeTrip] = (Tripulante) inTrip.readObject();
                System.out.println(tripulante[topeTrip]);
                topeTrip++;
            }
        } catch (EOFException e) {
        } catch (Exception e) {
            System.out.println("Error cargando tripulantes.");
        }

        try {
            ObjectInputStream inHab = new ObjectInputStream(new FileInputStream("habilidades.dat"));
            while (true) {
                habilidad[topeHab] = (Habilidad) inHab.readObject();
                System.out.println(habilidad[topeHab]);
                topeHab++;
            }
        } catch (EOFException e) {
        } catch (Exception e) {
            System.out.println("Error cargando habilidades.");
        }

        try {
            ObjectInputStream inMis = new ObjectInputStream(new FileInputStream("misiones.dat"));
            while (true) {
                mision[topeMis] = (Mision) inMis.readObject();
                System.out.println(mision[topeMis]);
                topeMis++;
            }
        } catch (EOFException e) {
        } catch (Exception e) {
            System.out.println("Error cargando misiones.");
        }

        return new int[]{topeTrip, topeHab, topeMis};
    }
}