import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private Formulario formulario;

    private JTextField txtNombre;
    private JTextField txtX;
    private JTextField txtY;
    private JButton btnEliminar;

    private PanelPaleta panelPaleta;
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

        panelPaleta = new PanelPaleta(this);

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
        String nombre = txtNombre.getText();
        int x = Integer.parseInt(txtX.getText());
        int y = Integer.parseInt(txtY.getText());

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
            case LISTA:
                nuevoComponente = new ComponenteLista(nombre, x, y);
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