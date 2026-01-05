import java.io.Serializable; 

public class Tripulante implements Serializable {  
    //variable que evita problemas al serializar para los binarios
    private static final long serialVersionUID = 1L;  

    int id, nivel, experiencia, vida, energia;
    String nombre, rol; //“Piloto”, “Ingeniero”, “Marine”, “Médico”
    int[] habilidadesAprendidas; //tamaño 3, contiene IDs de habilidades o -1 si vacía

    public Tripulante() {
        habilidadesAprendidas = new int[3];
        for (int i = 0; i < habilidadesAprendidas.length; i++) {
            //si no tiene habilidad, se inicializa en -1
            habilidadesAprendidas[i] = -1;
        }
    }

    //sobreescribir toString para mostrar la información del tripulante
    @Override
    public String toString() {
        return "ID:" + id +
               " | " + nombre +
               " (" + rol + ")" +
               " | nivel:" + nivel +
               " | xp:" + experiencia +
               " | vida:" + vida +
               " | energia:" + energia +
               " | habs: [" +
               habilidadesAprendidas[0] + ", " +
               habilidadesAprendidas[1] + ", " +
               habilidadesAprendidas[2] + "]";
    }
}
