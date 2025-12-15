import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Tripulante [] tripulante = new Tripulante[20];
        Habilidad [] habilidad = new Habilidad[20];
        Mision [] mision = new Mision[20];
        String [] rolesValidos = {"Piloto", "Ingeniero", "Marine", "Médico"};


        System.out.println("-------------STARFALL CHRONICLE-------------");
        System.out.println("Menu: ");

    }

    //---FUNCIONALIDADES GENERALES-----
    //Método para leer un número delimitado
    static int leerEnteroEnRango(Scanner sc, String prompt, int min, int max) {
        int x;
        do {
            System.out.print(prompt);
            while (!sc.hasNextInt()) {
                System.out.print("Introduce un entero: ");
                sc.next();
            }
            x = sc.nextInt();
            sc.nextLine();
        } while (x < min || x > max);
        return x;
    } 

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
            if (cadena == null || cadena.length() == 0) {
                System.out.println("No puede estar vacio. ");;
            } else {
                for (int i = 0; i < opcionesValidas.length; i++) {
                    if (cadena.equalsIgnoreCase(opcionesValidas[i])) {
                        esValida = true;
                    }else{
                        //String join es para unir los elementos de un array en una sola cadena separada por comas
                        System.out.println("Opcion no valida, debe ser una de las siguientes: " + String.join(", ", opcionesValidas));
                    }
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
    public void insertarDatos(Tripulante[] tripulante, Habilidad[] habilidad, Mision[] mision) {
        int desearContinuar=0;
        int tope=0;
        String [] rolesValidos = {"Piloto", "Ingeniero", "Marine", "Médico"};
        String [] tiposValidos = {"Tecnología", "Arma", "Soporte", "Hacking"};
        String [] tiposValidosTodos = {"Tecnología", "Arma", "Soporte", "Hacking", "Todos"};


        do{
            int eleccion = leerEnteroEnRango(sc,"Que desea insertar? 1.-Tripulante 2.-Habilidad 3.-Mision", 1, 3);
            if(eleccion == 1){
                //Insertar tripulante
                //ID, nombre, rol, nivel, experiencia, vida, energía
                for(int i=0; i<tripulante.length; i++){
                    //ID
                    tripulante[i].id = leerIntMin(sc, "Introduce el ID: ", 0);
                    //NOMBRE
                    tripulante[i].nombre = leerNombreValido(sc, "Introduce el nombre: ");
                    //ROL
                    tripulante[i].rol = leerOpcionValida( sc,  "Introduce el rol (Piloto, Ingeniero, Marine, Medico): ",  rolesValidos);
                    //NIVEL
                    tripulante[i].nivel = leerIntMin(sc, "Introduce el nivel: ", 0);
                    //EXPERIENCIA
                    tripulante[i].experiencia = leerIntMin(sc, "Introduce la experiencia: ", 0);
                    //VIDA
                    tripulante[i].vida = leerIntMin(sc, "Introduce la vida: ", 0);
                    //ENERGÍA
                    tripulante[i].energía = leerIntMin(sc, "Introduce la energia: ", 0);
                    //en que puesto ha sido insertado
                    System.out.print("Tripulante insertado en posicion " + i + ". ");
                    tope++;
                }
            } else if (eleccion == 2) {
                //Insertar habilidad
                //ID, nombre, descripcion, tipo, costeEnergia, clasePermitida
                for(int i=0; i<habilidad.length; i++){
                    //ID
                    habilidad[i].id = leerIntMin(sc, "Introduce el ID: ", 0);
                    //NOMBRE
                    habilidad[i].nombre = leerNombreValido(sc, "Introduce el nombre: ");
                    ////////NO SE MUY BIEN COMO HACER ESTO DE LA DESCRIPCION////////
                    System.out.print("Introduce la descripcion: ");
                    habilidad[i].descripcion = leerNombreValido(sc, "Introduce la descripcion: ");
                    //TIPO
                    habilidad[i].tipo = leerOpcionValida( sc,  "Introduce el tipo (Tecnología, Arma, Soporte, Hacking): ",  tiposValidos);
                    //COSTE ENERGIA
                    habilidad[i].costeEnergia = leerIntMin(sc, "Introduce el coste de energia: ", 0);
                    //CLASE PERMITIDA
                    habilidad[i].clasePermitida = leerOpcionValida( sc,  "Introduce que tipo tiene permitido usar esa habilidad (pueden ser todos): ",  tiposValidosTodos);

                    System.out.print("Habilidad insertada en posicion " + tope);
                    tope++;
                }
            } else {
                //Insertar mision
                //ID, nombre, dificultad, recompensaXP
                for(int i=0; i<mision.length; i++){
                    //ID
                    mision[i].id = leerIntMin(sc, "Introduce el ID: ", 0);
                    //NOMBRE
                    mision[i].nombre = leerNombreValido(sc, "Introduce el nombre: ");
                    //DIFICULTAD
                    mision[i].dificultad = leerEnteroEnRango(sc, "Introduce la dificultad (1..10): ", 1, 10);
                    //RECOMPENSA XP
                    mision[i].recompensaXP = leerIntMin(sc, "Introduce la recompensa de XP: ", 0);
                    System.out.print("Mision insertada en posicion " + tope);
                    tope++;
                }
            }
            desearContinuar = leerEnteroEnRango(sc, "Desea insertar otro dato? 1.-Si 2.-No", 1, 2);
        }while(desearContinuar != 2);
        
    }



}
