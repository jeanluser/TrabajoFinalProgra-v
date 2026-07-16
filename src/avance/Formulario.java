package avance;
import java.util.*;

public class Formulario {

    private ArrayList<ComponenteVisual> componentes;

    public Formulario() {
        componentes = new ArrayList<>();
    }

    public ArrayList<ComponenteVisual> getComponentes() {
        return componentes;
    }

    public void agregarComponente(ComponenteVisual componente) {
        componentes.add(componente);
    }

    public void eliminarComponente(ComponenteVisual componente) {
        componentes.remove(componente);
    }
}