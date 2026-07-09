import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private Formulario formulario;

    private JComboBox<TipoComponente> comboTipo;
    private JTextField txtNombre;
    private JTextField txtX;
    private JTextField txtY;
    private JButton btnAgregar;
    private JButton btnEliminar;

    private JPanel panelLienzo;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaComponentes;
    private ArrayList<JComponent> componentesSwing;

    public VentanaPrincipal() {
        formulario = new Formulario();
        componentesSwing = new ArrayList<>();

        setTitle("Disenador de Formularios - Avance");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(TipoComponente.values());
        panelSuperior.add(comboTipo);

        panelSuperior.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(10);
        panelSuperior.add(txtNombre);

        panelSuperior.add(new JLabel("X:"));
        txtX = new JTextField(4);
        panelSuperior.add(txtX);

        panelSuperior.add(new JLabel("Y:"));
        txtY = new JTextField(4);
        panelSuperior.add(txtY);

        btnAgregar = new JButton("Agregar al lienzo");
        btnAgregar.addActionListener(this);
        panelSuperior.add(btnAgregar);

        panelLienzo = new JPanel(null);
        panelLienzo.setBackground(Color.WHITE);
        panelLienzo.setPreferredSize(new Dimension(600, 500));

        JPanel panelLateral = new JPanel(new BorderLayout());
        modeloLista = new DefaultListModel<>();
        listaComponentes = new JList<>(modeloLista);
        panelLateral.add(new JScrollPane(listaComponentes), BorderLayout.CENTER);

        btnEliminar = new JButton("Eliminar seleccionado");
        btnEliminar.addActionListener(this);
        panelLateral.add(btnEliminar, BorderLayout.SOUTH);
        panelLateral.setPreferredSize(new Dimension(200, 400));

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(panelLienzo), BorderLayout.CENTER);
        add(panelLateral, BorderLayout.EAST);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            agregarComponente();
        } else if (e.getSource() == btnEliminar) {
            eliminarComponenteSeleccionado();
        }
    }

    private void agregarComponente() {
        String nombre = txtNombre.getText();
        int x = Integer.parseInt(txtX.getText());
        int y = Integer.parseInt(txtY.getText());
        TipoComponente tipo = (TipoComponente) comboTipo.getSelectedItem();

        ComponenteVisual nuevoComponente;
        switch (tipo) {
            case ETIQUETA:
                nuevoComponente = new ComponenteEtiqueta(nombre, x, y);
                break;
            case BOTON:
                nuevoComponente = new ComponenteBoton(nombre, x, y);
                break;
            default:
                nuevoComponente = new ComponenteCampoTexto(nombre, x, y);
        }

        formulario.agregarComponente(nuevoComponente);

        JComponent componenteSwing = nuevoComponente.crearSwing();
        componentesSwing.add(componenteSwing);
        panelLienzo.add(componenteSwing);
        panelLienzo.revalidate();
        panelLienzo.repaint();

        modeloLista.addElement(tipo + " - " + nombre + " (" + x + ", " + y + ")");

        txtNombre.setText("");
        txtX.setText("");
        txtY.setText("");
    }

    private void eliminarComponenteSeleccionado() {
        int indice = listaComponentes.getSelectedIndex();
        if (indice == -1) {
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
}
