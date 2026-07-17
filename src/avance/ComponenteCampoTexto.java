package avance;

import javax.swing.*;

public class ComponenteCampoTexto extends ComponenteVisual {

    public ComponenteCampoTexto(String nombre, int x, int y) {
        super(nombre, x, y);
    }

    @Override
    public TipoComponente getTipo() {
        return TipoComponente.CAMPO_TEXTO;
    }

    @Override
    public JComponent crearSwing() {
        JTextField campo = new JTextField(nombre);
        campo.setBounds(x, y, 140, 28);
        return campo;
    }
    
    @Override
    public String generarCodigo(String variable) {
        String comentario = "// Clase: ComponenteCampoTexto | Constructor: ComponenteCampoTexto(nombre, x, y)\n";
        String codigo = "JTextField " + variable + " = new JTextField(\"" + nombre + "\");\n"
                      + variable + ".setBounds(" + x + ", " + y + ", 140, 28);\n";
        return comentario + codigo;
    }
}