package avance;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class panelPaleta extends JPanel implements ActionListener {

    private VentanaPrincipal ventana;
    private JButton btnEtiqueta, btnBoton, btnCampoTexto, btnCombo, btnLista;

    public panelPaleta(VentanaPrincipal ventana) {
        this.ventana = ventana;
        setLayout(new GridLayout(0, 1, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Paleta"));

        btnEtiqueta = new JButton("Etiqueta");
        btnBoton = new JButton("Boton");
        btnCampoTexto = new JButton("Campo de texto");
        btnCombo = new JButton("Combo");
        btnLista = new JButton("Lista");

        JButton[] botones = {btnEtiqueta, btnBoton, btnCampoTexto, btnCombo, btnLista};
        for (JButton b : botones) {
            b.addActionListener(this);
            add(b);
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object origen = e.getSource();
        if (origen == btnEtiqueta) {
            ventana.agregarComponente(TipoComponente.ETIQUETA);
        } else if (origen == btnBoton) {
            ventana.agregarComponente(TipoComponente.BOTON);
        } else if (origen == btnCampoTexto) {
            ventana.agregarComponente(TipoComponente.CAMPO_TEXTO);
        } else if (origen == btnCombo) {
            ventana.agregarComponente(TipoComponente.COMBO);
        } else if (origen == btnLista) {
            ventana.agregarComponente(TipoComponente.LISTA);
        }
    }
}