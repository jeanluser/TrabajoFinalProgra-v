
package avance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private Formulario formulario;

    private JTextField txtNombre;
    private JTextField txtX;
    private JTextField txtY;
    private JButton btnEliminar;

    private panelPaleta panelPaleta;
    private JPanel panelLienzo;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaComponentes;
    private ArrayList<JComponent> componentesSwing;

    private JTextArea areaCodigo;
    private int contador = 1;

    public VentanaPrincipal() {
        formulario = new Formulario();
        componentesSwing = new ArrayList<>();

        setTitle("Disenador de Formularios - Avance");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(10);
        panelSuperior.add(txtNombre);

        panelSuperior.add(new JLabel("X:"));
        txtX = new JTextField(4);
        panelSuperior.add(txtX);

        panelSuperior.add(new JLabel("Y:"));
        txtY = new JTextField(4);
        panelSuperior.add(txtY);

        panelPaleta = new panelPaleta(this);

        panelLienzo = new JPanel(null);
        panelLienzo.setBackground(Color.WHITE);
        panelLienzo.setPreferredSize(new Dimension(500, 500));

        JPanel panelLista = new JPanel(new BorderLayout());
        modeloLista = new DefaultListModel<>();
        listaComponentes = new JList<>(modeloLista);
        panelLista.add(new JScrollPane(listaComponentes), BorderLayout.CENTER);

        btnEliminar = new JButton("Eliminar seleccionado");
        btnEliminar.addActionListener(this);
        panelLista.add(btnEliminar, BorderLayout.SOUTH);
        panelLista.setPreferredSize(new Dimension(250, 200));

        areaCodigo = new JTextArea();
        areaCodigo.setEditable(false);
        JScrollPane scrollCodigo = new JScrollPane(areaCodigo);
        scrollCodigo.setBorder(BorderFactory.createTitledBorder("Codigo generado"));

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.add(panelLista, BorderLayout.NORTH);
        panelDerecho.add(scrollCodigo, BorderLayout.CENTER);
        panelDerecho.setPreferredSize(new Dimension(300, 500));

        add(panelSuperior, BorderLayout.NORTH);
        add(panelPaleta, BorderLayout.WEST);
        add(new JScrollPane(panelLienzo), BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.EAST);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEliminar) {
            eliminarComponenteSeleccionado();
        }
    }

    public void agregarComponente(TipoComponente tipo) {
        String nombre = txtNombre.getText().trim();
        String strX = txtX.getText().trim();
        String strY = txtY.getText().trim();

  
        if (nombre.isEmpty() || strX.isEmpty() || strY.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, completa los campos Nombre, X e Y antes de agregar un componente.", 
                "Campos vacíos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int x, y;
        try {
            x = Integer.parseInt(strX);
            y = Integer.parseInt(strY);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Los campos X e Y deben ser números enteros válidos.", 
                "Error de formato", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        ComponenteVisual nuevoComponente;
        switch (tipo) {
            case ETIQUETA:
                nuevoComponente = new ComponenteEtiqueta(nombre, x, y);
                break;
            case BOTON:
                nuevoComponente = new ComponenteBoton(nombre, x, y);
                break;
            case COMBO:
                nuevoComponente = new ComponenteCombo(nombre, x, y);
                break;
            case CAMPO_TEXTO:
                nuevoComponente = new ComponenteCampoTexto(nombre, x, y);
                break;
            case LISTA: 
                nuevoComponente = new ComponenteLista(nombre, x, y);
                break;    
            default:
                return;
        }

        formulario.agregarComponente(nuevoComponente);

        JComponent componenteSwing = nuevoComponente.crearSwing();
        componentesSwing.add(componenteSwing);
        panelLienzo.add(componenteSwing);
        habilitarArrastre(componenteSwing);
        panelLienzo.revalidate();
        panelLienzo.repaint();

        modeloLista.addElement(tipo + " - " + nombre + " (" + x + ", " + y + ")");

        String variable = "comp" + contador;
        contador++;
        areaCodigo.append(nuevoComponente.generarCodigo(variable));
        areaCodigo.append("\n");

        txtNombre.setText("");
        txtX.setText("");
        txtY.setText("");
    }

    private void eliminarComponenteSeleccionado() {
        int indice = listaComponentes.getSelectedIndex();
        if (indice == -1) {
            JOptionPane.showMessageDialog(this, 
                "Selecciona un componente de la lista para eliminarlo.", 
                "Sin selección", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        ComponenteVisual componenteAEliminar = formulario.getComponentes().get(indice);
        formulario.eliminarComponente(componenteAEliminar);

        JComponent componenteSwing = componentesSwing.get(indice);
        panelLienzo.remove(componenteSwing);
        componentesSwing.remove(indice);

        modeloLista.remove(indice);

        panelLienzo.revalidate();
        panelLienzo.repaint();
    }
    private void habilitarArrastre(JComponent componente) {
    MouseAdapter arrastre = new MouseAdapter() {
        private Point puntoInicial;

        @Override
        public void mousePressed(MouseEvent e) {
            puntoInicial = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point ubicacionActual = componente.getLocation();
            int nuevoX = ubicacionActual.x + e.getX() - puntoInicial.x;
            int nuevoY = ubicacionActual.y + e.getY() - puntoInicial.y;
            componente.setLocation(nuevoX, nuevoY);
        }
    };
    componente.addMouseListener(arrastre);
    componente.addMouseMotionListener(arrastre);
}
}