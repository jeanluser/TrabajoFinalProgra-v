import javax.swing.*;

public class ComponenteBoton extends ComponenteVisual {

    public ComponenteBoton(String nombre, int x, int y) {
        super(nombre, x, y);
    }

    public TipoComponente getTipo() {
        return TipoComponente.BOTON;
    }

    public JComponent crearSwing() {
        JButton boton = new JButton(nombre);
        boton.setBounds(x, y, 120, 30);
        return boton;
    }
}
