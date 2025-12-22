public class Tripulante {
    int id, nivel, experiencia, vida, energía;
    String nombre, rol; // “Piloto”, “Ingeniero”, “Marine”, “Médico”
    int[] habilidadesAprendidas; // tamaño 3, contiene IDs de habilidades o -1 si vacía

    public Tripulante() {
        habilidadesAprendidas = new int[3];
        for (int i = 0; i < habilidadesAprendidas.length; i++) {
            //si no tiene habilidad, se inicializa en -1
            habilidadesAprendidas[i] = -1; 
        }
    }
}
