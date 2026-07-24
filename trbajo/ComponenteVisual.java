package trbajo;

import javax.swing.JComponent;

/**
 * Clase abstracta que representa un componente visual dentro del formulario
 * que se está diseñando (equivalente a un "control" en el editor de NetBeans).
 *
 * Cada tipo concreto (etiqueta, boton, campo de texto, etc.) hereda de esta
 * clase e implementa su propia forma de:
 *  - crearse como componente real de Swing (crearSwing)
 *  - guardar/leer su texto o datos propios (getExtra / setExtra)
 *  - generar el código Java que lo representa (generarCodigoJava)
 */
public abstract class ComponenteVisual {

    protected String nombre;
    protected int x;
    protected int y;
    protected int ancho;
    protected int alto;

    // Nombre de OTRO componente al que este componente está asociado/relacionado.
    // Por ejemplo, una etiqueta "Nombre:" puede asociarse al campo de texto "txtNombre".
    protected String asociadoA;

    public ComponenteVisual(String nombre, int x, int y, int ancho, int alto) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.asociadoA = "";
    }

    // ---- Métodos que cada componente concreto debe implementar ----

    public abstract TipoComponente getTipo();

    public abstract JComponent crearSwing();

    public abstract String getExtra();

    public abstract void setExtra(String extra);

    public abstract String generarCodigoJava(String nombreVariable);

    // ---- Getters y setters comunes ----

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public String getAsociadoA() {
        return asociadoA;
    }

    public void setAsociadoA(String asociadoA) {
        this.asociadoA = asociadoA;
    }

    /**
     * Convierte este componente en una línea de texto para guardarlo en el
     * fichero. Formato:
     * TIPO|nombre|x|y|ancho|alto|asociadoA|extra
     */
    public String getLinea() {
        String extraSeguro = getExtra() == null ? "" : getExtra().replace("|", "/").replace("\n", "~~");
        String asociadoSeguro = asociadoA == null ? "" : asociadoA;
        return getTipo().name() + "|" + nombre + "|" + x + "|" + y + "|" + ancho + "|" + alto
                + "|" + asociadoSeguro + "|" + extraSeguro;
    }
}
