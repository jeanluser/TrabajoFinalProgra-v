package avance;

import javax.swing.*;

public class ComponenteBoton extends ComponenteVisual {

    public ComponenteBoton(String nombre, int x, int y) {
        super(nombre, x, y);
    }

    @Override
    public TipoComponente getTipo() {
        return TipoComponente.BOTON;
    }

    @Override
    public JComponent crearSwing() {
        JButton boton = new JButton(nombre);
        boton.setBounds(x, y, 120, 30);
        return boton;
    }
    
    @Override
    public String generarCodigo(String variable) {
        return "JButton " + variable + " = new JButton(\"" + nombre + "\");\n"
             + variable + ".setBounds(" + x + ", " + y + ", 120, 30);\n";
    }
}
