package avance;

import javax.swing.*;

public abstract class ComponenteVisual {

    protected String nombre;
    protected int x;
    protected int y;

    public ComponenteVisual(String nombre, int x, int y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
    }

    public abstract JComponent crearSwing();
    public abstract TipoComponente getTipo();
    public abstract String generarCodigo(String variable);

    public String getNombre() {
        return nombre;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}