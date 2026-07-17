
package avance;

import javax.swing.*;

public class ComponenteCombo extends ComponenteVisual {

    public ComponenteCombo(String nombre, int x, int y) {
        super(nombre, x, y);
    }

    @Override
    public TipoComponente getTipo() {
        return TipoComponente.COMBO;
    }

    @Override
    public JComponent crearSwing() {
        String[] opciones = {"Opcion 1", "Opcion 2", "Opcion 3"};
        JComboBox<String> combo = new JComboBox<>(opciones);
        combo.setBounds(x, y, 140, 28);
        return combo;
    }
    
    @Override
    public String generarCodigo(String variable) {
        String comentario = "// Clase: ComponenteCombo | Constructor: ComponenteCombo(nombre, x, y)\n";
        String codigo = "JComboBox " + variable + " = new JComboBox(opciones);\n"
                      + variable + ".setBounds(" + x + ", " + y + ", 140, 28);\n";
        return comentario + codigo;
    }
}
