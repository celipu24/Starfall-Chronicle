import java.util.Scanner;

public class App {
    //variable global
    static int opcion;
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        final int MAX = 20;
        Tripulante [] tripulante = new Tripulante[MAX];
        Habilidad [] habilidad = new Habilidad[MAX];
        Mision [] mision = new Mision[MAX];
        /*String [] rolesValidos = {"Piloto", "Ingeniero", "Marine", "Médico"};
        String [] tiposValidos = {"Tecnología", "Arma", "Soporte", "Hacking"};
        String [] tiposValidosTodos = {"Tecnología", "Arma", "Soporte", "Hacking", "Todos"};
        */
        int topeTripulante=0;
        int topeHabilidad=0;
        int topeMision=0;
        boolean salir = false;
        do{
            mostrarMenu(sc);
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
    public static void mostrarMenu(Scanner sc) {
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
        opcion = leerEnteroEnRango(sc, "Elija una opcion: ", 1, 14);
       //sc.nextLine();
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

    //ARREGLAR ESTE METODO PARA QUE NO LEA LOS ESPACIOS O INSERTS *SEGURIDAD*
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
                System.out.println("No puede estar vacio. ");;
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
            t.energía = leerIntMin(sc, "Introduce la energia: ", 0);
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
        String [] tiposValidosTodos = {"Tecnologia", "Arma", "Soporte", "Hacking", "Todos"};
        //ID, nombre, descripcion, tipo, costeEnergia, clasePermitida
        if(topeH<habilidad.length){
            //creamos una nueva habilidad en cada posicion del array
            Habilidad h = new Habilidad();
            //ID
            h.id = leerIntMin(sc, "Introduce el ID: ", 0);
            //NOMBRE
            h.nombre = leerNombreValido(sc, "Introduce el nombre: ");
            ////////NO SE MUY BIEN COMO HACER ESTO DE LA DESCRIPCION////////
            h.descripcion = leerNombreValido(sc, "Introduce la descripcion: ");
            //TIPO
            h.tipo = leerOpcionValida( sc,  "Introduce el tipo (Tecnología, Arma, Soporte, Hacking): ",  tiposValidos);
            //COSTE ENERGIA
            h.costeEnergia = leerIntMin(sc, "Introduce el coste de energia: ", 0);
            //CLASE PERMITIDA
            h.clasePermitida = leerOpcionValida( sc,  "Introduce que tipo tiene permitido usar esa habilidad (pueden ser todos): ",  tiposValidosTodos);
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
                    topeT = insertarHabilidadPos(sc, habilidad, topeH, pos);
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
            desearContinuar = leerEnteroEnRango(sc, "Desea insertar otro dato? 1.-Si 2.-No", 1, 2);
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
            t.energía = leerIntMin(sc, "Introduce la energia: ", 0);
            //en que puesto ha sido insertado
            System.out.print("Tripulante insertado en posicion " + pos + ". ");
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
        String [] tiposValidosTodos = {"Tecnologia", "Arma", "Soporte", "Hacking", "Todos"};
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
            ////////NO SE MUY BIEN COMO HACER ESTO DE LA DESCRIPCION////////
            System.out.print("Introduce la descripcion: ");
            h.descripcion = leerNombreValido(sc, "Introduce la descripcion: ");
            //TIPO
            h.tipo = leerOpcionValida( sc,  "Introduce el tipo (Tecnología, Arma, Soporte, Hacking): ",  tiposValidos);
            //COSTE ENERGIA
            h.costeEnergia = leerIntMin(sc, "Introduce el coste de energia: ", 0);
            //CLASE PERMITIDA
            h.clasePermitida = leerOpcionValida( sc,  "Introduce que tipo tiene permitido usar esa habilidad (pueden ser todos): ",  tiposValidosTodos);

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
            System.out.print("Mision insertada en posicion " + pos);
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
            if(eleccion == 1){
                topeT = eliminarTripulantePos(sc, tripulante, topeT);
            } else if (eleccion == 2) {
                topeH = eliminarHabilidadPos(sc, habilidad, topeH);
            } else {
                topeM = eliminarMisionPos(sc, mision, topeM);
            }
            desearContinuar = leerEnteroEnRango(sc, "Desea eliminar otro dato? 1.-Si 2.-No", 1, 2);
        }while(desearContinuar != 2);
        return new int[]{topeT, topeH, topeM};
    }

    //Eliminar tripulante por posicion
    public static int eliminarTripulantePos(Scanner sc, Tripulante[] tripulante, int topeT) {
        int pos = leerEnteroEnRango(sc, "Introduce la posicion del tripulante que desea eliminar (0-" + (topeT-1) + "): ", 0, topeT-1);
        System.out.print("Vas a eliminar al tripulante: " + /*METER METODO MOSTRAR TRIPULANTE*/ tripulante[pos].id + ". ");
        //desplazamos las posiciones del array para "eliminar" el dato
        for(int i=pos; i<topeT-1; i++){
            tripulante[i] = tripulante[i+1];
        }
        tripulante[topeT-1] = null; //opcional, para evitar referencias colgantes
        topeT--;
        System.out.print("Tripulante eliminado de la posicion " + pos + ". ");
        return topeT;
    }


    //Eliminar habilidad por posicion
    public static int eliminarHabilidadPos(Scanner sc, Habilidad[] habilidad, int topeH) {
        int pos = leerEnteroEnRango(sc, "Introduce la posicion de la habilidad que desea eliminar (0-" + (topeH-1) + "): ", 0, topeH-1);
        System.out.print("Vas a eliminar la habilidad: " + /*METER METODO MOSTRAR HABILIDAD*/ habilidad[pos].id + ". ");
        //desplazamos las posiciones del array para "eliminar" el dato
        for(int i=pos; i<topeH-1; i++){
            habilidad[i] = habilidad[i+1];
        }
        habilidad[topeH-1] = null; 
        topeH--;
        System.out.print("Habilidad eliminada de la posicion " + pos + ". ");
        return topeH;
    }

    //Eliminar mision por posicion
    public static int eliminarMisionPos(Scanner sc, Mision[] mision, int topeM) {
        int pos = leerEnteroEnRango(sc, "Introduce la posicion de la mision que desea eliminar (0-" + (topeM-1) + "): ", 0, topeM-1);
        System.out.print("Vas a eliminar la mision: " + /*METER METODO MOSTRAR MISION*/ mision[pos].id + ". ");
        //desplazamos las posiciones del array para "eliminar" el dato
        for(int i=pos; i<topeM-1; i++){
            mision[i] = mision[i+1];
        }
        mision[topeM-1] = null; 
        topeM--;
        System.out.print("Mision eliminada de la posicion " + pos + ". ");
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
                System.out.println("[" + i + "] " + " ID:" + tripulante[i].id + " | " + tripulante[i].nombre + " (" + tripulante[i].rol + ") | nivel:" + tripulante[i].nivel + " | xp:" + tripulante[i].experiencia + " | vida:" + tripulante[i].vida + " | energia:" + tripulante[i].energía + " | habs: " /* METER HABILIDADES*/);
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
    










}
