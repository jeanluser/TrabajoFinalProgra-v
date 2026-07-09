import javax.swing.*;

public class ComponenteEtiqueta extends ComponenteVisual {

    public ComponenteEtiqueta(String nombre, int x, int y) {
        super(nombre, x, y);
    }

    public TipoComponente getTipo() {
        return TipoComponente.ETIQUETA;
    }

    public JComponent crearSwing() {
        JLabel etiqueta = new JLabel(nombre);
        etiqueta.setBounds(x, y, 120, 25);
        return etiqueta;
    }
}
