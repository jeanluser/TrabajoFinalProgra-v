package trbajo;

public class ComponenteBoton extends ComponenteVisual {

    private String texto;

    public ComponenteBoton(String nombre, int x, int y, int ancho, int alto, String texto) {
        super(nombre, x, y, ancho, alto);
        this.texto = texto == null ? "Boton" : texto;
    }

    @Override
    public TipoComponente getTipo() {
        return TipoComponente.BOTON;
    }

    @Override
    public JComponent crearSwing() {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, alto);
        return boton;
    }

    @Override
    public String getExtra() {
        return texto;
    }

    @Override
    public void setExtra(String extra) {
        this.texto = extra;
    }

    @Override
    public String generarCodigoJava(String nombreVariable) {
        StringBuilder sb = new StringBuilder();
        sb.append("JButton ").append(nombreVariable).append(" = new JButton(\"").append(texto).append("\");\n");
        sb.append(nombreVariable).append(".setBounds(").append(x).append(", ").append(y).append(", ")
                .append(ancho).append(", ").append(alto).append(");\n");
        sb.append("// TODO: agregar aqui el ActionListener del boton si es necesario\n");
        sb.append("panel.add(").append(nombreVariable).append(");\n");
        return sb.toString();
    }
}
