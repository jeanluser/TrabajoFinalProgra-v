package trbajo;

import javax.swing.SwingUtilities;

/**
 clase principal del proyecto. Unico punto de entrada de la aplicacion.
 */
public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal();
            }
        });
    }
}