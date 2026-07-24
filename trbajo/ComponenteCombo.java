package trbajo;

import javax.swing.JComboBox;
import javax.swing.JComponent;

/**
 * Componente visual de tipo combo (JComboBox). Los elementos se guardan como
 * un único texto separado por comas, por ejemplo: "Opcion1,Opcion2".
 */
public class ComponenteCombo extends ComponenteVisual {

    private String items;

    public ComponenteCombo(String nombre, int x, int y, int ancho, int alto, String items) {
        super(nombre, x, y, ancho, alto);
        this.items = (items == null || items.trim().isEmpty()) ? "Opcion1,Opcion2" : items;
    }

    private String[] obtenerArregloItems() {
        String[] partes = items.split(",");
        for (int i = 0; i < partes.length; i++) {
            partes[i] = partes[i].trim();
        }
        return partes;
    }

    @Override
    public TipoComponente getTipo() {
        return TipoComponente.COMBO;
    }

    @Override
    public JComponent crearSwing() {
        JComboBox<String> combo = new JComboBox<>(obtenerArregloItems());
        combo.setBounds(x, y, ancho, alto);
        return combo;
    }

    @Override
    public String getExtra() {
        return items;
    }

    @Override
    public void setExtra(String extra) {
        this.items = (extra == null || extra.trim().isEmpty()) ? "Opcion1" : extra;
    }

    @Override
    public String generarCodigoJava(String nombreVariable) {
        String[] partes = obtenerArregloItems();
        StringBuilder arreglo = new StringBuilder();
        for (int i = 0; i < partes.length; i++) {
            arreglo.append("\"").append(partes[i]).append("\"");
            if (i < partes.length - 1) {
                arreglo.append(", ");
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("String[] items").append(nombreVariable).append(" = {").append(arreglo).append("};\n");
        sb.append("JComboBox<String> ").append(nombreVariable).append(" = new JComboBox<>(items")
                .append(nombreVariable).append(");\n");
        sb.append(nombreVariable).append(".setBounds(").append(x).append(", ").append(y).append(", ")
                .append(ancho).append(", ").append(alto).append(");\n");
        sb.append("panel.add(").append(nombreVariable).append(");\n");
        return sb.toString();
    }
}