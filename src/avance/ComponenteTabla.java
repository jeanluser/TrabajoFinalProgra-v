
package avance;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;


public class ComponenteTabla extends ComponenteVisual {

  

    private JTable tabla;
    private DefaultTableModel modelo;
    private String[] columnas;

   public ComponenteTabla(String nombre, int x, int y) {
    super(nombre, x, y);

    columnas = new String[]{"Columna 1"};

    modelo = new DefaultTableModel(columnas,0);
    
    modelo.addRow(new Object[columnas.length]);
    tabla = new JTable(modelo);
    }

    public TipoComponente getTipo() {
        return TipoComponente.TABLA;
    }

    public JComponent crearSwing() {
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(x, y, 300, 150);
        return scroll;
    }

    public void agregarFila(Object[] fila) {
        modelo.addRow(fila);
    }

    public String[] getColumnas() {
        return columnas;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    @Override
    public String generarCodigo(String variable) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
