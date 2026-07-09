import javax.swing.*;

public class ComponenteCampoTexto extends ComponenteVisual {

    public ComponenteCampoTexto(String nombre, int x, int y) {
        super(nombre, x, y);
    }

    public TipoComponente getTipo() {
        return TipoComponente.CAMPO_TEXTO;
    }

    public JComponent crearSwing() {
        JTextField campo = new JTextField(nombre);
        campo.setBounds(x, y, 140, 28);
        return campo;
    }
}
