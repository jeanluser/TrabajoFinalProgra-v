package avance;

import javax.swing.*;

public class ComponenteEtiqueta extends ComponenteVisual {

    public ComponenteEtiqueta(String nombre, int x, int y) {
        super(nombre, x, y);
    }

    @Override
    public TipoComponente getTipo() {
        return TipoComponente.ETIQUETA;
    }

    @Override
    public JComponent crearSwing() {
        JLabel etiqueta = new JLabel(nombre);
        etiqueta.setBounds(x, y, 120, 25);
        return etiqueta;
    }
    
    @Override
    public String generarCodigo(String variable) {
        return "JLabel " + variable + " = new JLabel(\"" + nombre + "\");\n"
             + variable + ".setBounds(" + x + ", " + y + ", 120, 25);\n";
    }
}
