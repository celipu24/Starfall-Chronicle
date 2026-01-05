import java.io.Serializable;

public class Habilidad implements Serializable {
    //variable que evita problemas al serializar para los binarios
    private static final long serialVersionUID = 1L;

    int id, costeEnergia;
    String nombre, descripcion;
    String tipo;
    String clasePermitida;

    public Habilidad() {
    }

    //sobreescribir toString para mostrar la información de la habilidad
    @Override
    public String toString() {
        return "ID:" + id +
               " | " + nombre +
               " (" + tipo + ")" +
               " | coste energia:" + costeEnergia +
               " | clase permitida:" + clasePermitida +
               " | " + descripcion;
    }
}
