/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance;
import javax.swing.*;

public class ComponenteLista extends ComponenteVisual {

    public ComponenteLista(String nombre, int x, int y) {
        super(nombre, x, y);
    }

    @Override
    public TipoComponente getTipo() {
        return TipoComponente.LISTA;
    }

    @Override
    public JComponent crearSwing() {
        DefaultListModel<String> modeloEjemplo = new DefaultListModel<>();
        modeloEjemplo.addElement("Elemento 1");
        modeloEjemplo.addElement("Elemento 2");
        
        JList<String> lista = new JList<>(modeloEjemplo);
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBounds(x, y, 150, 100);
        return scroll;
    }
    
    @Override
    public String generarCodigo(String variable) {
        String comentario = "// Clase: ComponenteLista | Constructor: ComponenteLista(nombre, x, y)\n";
        String codigo = "DefaultListModel<String> " + variable + "Model = new DefaultListModel<>();\n"
                      + variable + "Model.addElement(\"Elemento 1\");\n"
                      + variable + "Model.addElement(\"Elemento 2\");\n"
                      + "JList<String> " + variable + " = new JList<>(" + variable + "Model);\n"
                      + "JScrollPane scroll" + variable + " = new JScrollPane(" + variable + ");\n"
                      + "scroll" + variable + ".setBounds(" + x + ", " + y + ", 150, 100);\n";
        return comentario + codigo;
    }
}