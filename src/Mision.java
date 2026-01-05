import java.io.Serializable; 

public class Mision implements Serializable {  
    //variable que evita problemas al serializar para los binarios
    private static final long serialVersionUID = 1L; 

    int id, dificultad; 
    String nombre;
    int recompensaXP;

    //Constructor por defecto
    public Mision() {
    }

    //sobreescribir toString para mostrar la información de la misión
    @Override
    public String toString() {
        return "ID:" + id +
               " | " + nombre +
               " | dificultad:" + dificultad +
               " | recompensa xp:" + recompensaXP;
    }
}
