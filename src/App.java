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

    //Método para comprobar que el nombre es válido(que no esté vacío)
    public static boolean nombreValido(Scanner sc, String prompt) {     
        String nombre = sc.nextLine();   
        if (nombre == null) {
            return false;
        }
        //Elimina espacios al principio y al final
        nombre = nombre.trim();
        //Comprueba que queda algo después de quitar espacios
        if (nombre.length() == 0) {
            return false;
        }
        return true;  
    }

    //Método que devuelve el nombre si es válido
    public static String leerStringValida(Scanner sc, String prompt) {
        String x;
        do {
            System.out.print(prompt);
            x = sc.nextLine();
        } while (!nombreValido(sc, x));
        return x;
    }

    //Método para comprobar que el nombre es válido(que no esté vacío)
    public static boolean rolValido(Scanner sc, String prompt) {     
        String rol = sc.nextLine();   
        if (rol == null) {
            return false;
        }
        //Elimina espacios al principio y al final
        rol = rol.trim();
        //Comprueba que queda algo después de quitar espacios
        if (rol.length() == 0) {
            return false;
        }
        return true;  
    }
        //Método que devuelve el nombre si es válido
    public static String leerRolValida(Scanner sc, String prompt) {
        String x;
        do {
            System.out.print(prompt);
            x = sc.nextLine();
        } while (!rolValido(sc, x));
        return x;
    }
    //Método para leer un rol válido
    static String leerRolBueno(Scanner sc, String prompt, String[] rolesValidos) {
        System.out.println("Rol (Piloto / Ingeniero / Marine / Medico): ");
        System.out.print(prompt);
        if(prompt.equals("Piloto") || prompt.equals("Ingeniero") || prompt.equals("Marine") || prompt.equals("Medico")){
            return prompt;
        } else {
            System.out.print("Rol no valido. ");
        }
        return sc.nextLine();
    }



    //---FUNCIONALIDADES DEL PROGRAMA-----
    //1.Insertar tripulante/habilidad/misión (al final)
    public void insertarDatos(Tripulante[] tripulante, Habilidad[] habilidad, Mision[] mision) {
        int desearContinuar=0;
        int tope=0;
        String [] rolesValidos = {"Piloto", "Ingeniero", "Marine", "Médico"};

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
                    tripulante[i].rol = leerRolValido( sc,  "Introduce el rol (Piloto, Ingeniero, Marine, Medico): ",  rolesValidos);
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
                    habilidad[i].descripcion = sc.next();

                    System.out.print("Introduce el tipo (Tecnología, Arma, Soporte, Hacking): ");
                    habilidad[i].tipo = sc.next();
                    System.out.print("Introduce el coste de energia: ");
                    habilidad[i].costeEnergia = sc.nextInt();
                    System.out.print("Introduce la clase permitida (rol permitido o Todos): ");
                    habilidad[i].clasePermitida = sc.next();
                    System.out.print("Habilidad insertada en posicion " + tope);
                    tope++;
                }
            } else if (eleccion == 3) {
                //Insertar mision
                //ID, nombre, dificultad, recompensaXP
                for(int i=0; i<mision.length; i++){
                    System.out.print("Introduce el ID: ");
                    mision[i].id = sc.nextInt();
                    System.out.print("Introduce el nombre: ");
                    mision[i].nombre = sc.next();
                    System.out.print("Introduce la dificultad (1..10): ");
                    mision[i].dificultad = sc.nextInt();
                    System.out.print("Introduce la recompensa de XP: ");
                    mision[i].recompensaXP = sc.nextInt();
                    System.out.print("Mision insertada en posicion " + tope);;
                    tope++;
                }
            } else {
                System.out.println("Opcion no valida");
            }
            desearContinuar = leerEnteroEnRango(sc, "Desea insertar otro dato? 1.-Si 2.-No", 1, 2);
        }while(desearContinuar != 2);
        
    }



}
