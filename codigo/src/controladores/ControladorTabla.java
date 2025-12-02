/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import modelos.MisColores;
import vistas.Vista;

/**
 *
 * @author andres
 */
public class ControladorTabla implements MouseListener {
    private Controlador cp;
    private Vista vista;
    private int contador;
    private int maximoFilas;
    private int ultimaFilaSeleccionada;
    private int pagina;
    private int paginas;
    private JPanel[] FILAS;
    private JLabel[][] COLUMNAS;
    private boolean canSelect;
    
    public ControladorTabla(Vista vista, Controlador cp) {
        this.canSelect = true;
        this.FILAS = vista.getFilas();
        this.COLUMNAS = vista.getColumnas();
        this.contador = 0;
        this.maximoFilas = 8;
        this.pagina = 1;
        this.paginas = 1;
        this.vista = vista;
        this.cp = cp;
        vista.getTabla().addMouseListener(this);
        vista.getPaginacion()[1].addMouseListener(this);
        vista.getPaginacion()[2].addMouseListener(this);
    }
    
    public void actualizar(Object[] arreglo, int mostrar) {
        limpiar();
        JLabel[] paginacion = vista.getPaginacion();
        paginas = arreglo.length / maximoFilas;
        paginas += arreglo.length % maximoFilas == 0 ? 0: 1;
        if (pagina == 0) pagina = 1;
        if (pagina > paginas) pagina = paginas;
        paginacion[0].setText(pagina + "/" +  paginas);
        
        if (pagina > 1) paginacion[1].setForeground(MisColores.color2);
        else  paginacion[1].setForeground(MisColores.grisFlecha); 
        if (paginas > pagina) paginacion[2].setForeground(MisColores.color2);
        else  paginacion[2].setForeground(MisColores.grisFlecha); 
        
        if (arreglo.length == 0) {
            COLUMNAS[3][1].setText("Sin resultados...");
            return;
        } else {
            COLUMNAS[3][1].setText("");
        }
        
        for (int i = maximoFilas * (pagina - 1); i < arreglo.length; i++) {
            if (arreglo[i] == null) return;
            String[] info = arreglo[i].toString().split(";");
            
            //Centrar texto con simbolos
            if (i == 0 && mostrar == 0) {
                COLUMNAS[0][0].setHorizontalAlignment(2);
                int espacios = 25 - info[1].length();
                for (int j = 0; j < espacios; j++) {
                    if (j % 2 == 0) info[1] += " "; else info[1] = " " + info[1];
                }
                info[1] = ">" + info[1];
            } else if (mostrar == 1) {
                int espacios = 22 - info[1].length();
                for (int j = 0; j < espacios; j++) {
                    if (j % 2 == 0) info[1] = " " + info[1];
                }
                info[1] = info[0] + ": " + info[1];
            }
           
            agregarFila(info);
        }
    }
    
    public void limpiar() {
        if (contador > 0) {
            for (int i = 0; i < contador; i++) {
                FILAS[i].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                FILAS[i].setBackground(MisColores.blanco);
                FILAS[i].putClientProperty("id", null);
                COLUMNAS[i][0].setText("");
                COLUMNAS[i][1].setText("");
                COLUMNAS[i][2].setText("");
                COLUMNAS[i][0].setForeground(MisColores.grisOscuro);
                COLUMNAS[i][1].setForeground(MisColores.grisOscuro);
                COLUMNAS[i][2].setForeground(MisColores.grisOscuro);
                COLUMNAS[i][0].setHorizontalAlignment(0);
            }
            
            contador = 0;
        }
        resetearFila(ultimaFilaSeleccionada);
    }
    
    public int getSelected() {
        if (ultimaFilaSeleccionada != -1 && contador != 0) {
            Object id = vista.getFilas()[ultimaFilaSeleccionada].getClientProperty("id");
            if (id != null) return Integer.parseInt((String) id);
        }
        return -1;
    }
   
    private void agregarFila(String[] info) {
        if (contador >= maximoFilas) return;
        
        JPanel fila = FILAS[contador];
        JLabel[] columnas = COLUMNAS[contador];
        
        if (canSelect) fila.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (info[1].contains(":")) columnas[0].setHorizontalAlignment(2);
        fila.putClientProperty("id", info[0]);
        columnas[0].setText(info[1]);
        columnas[1].setText(info[2]);
        switch (info[1]) {
            case "atender" -> columnas[0].setForeground(MisColores.azul);
            case "eliminar" -> columnas[0].setForeground(MisColores.naranja);
            case "restaurar" -> columnas[0].setForeground(MisColores.verde);
        }
        columnas[2].setText(info[3]);
        contador++;
    }
    
    private void resetearFila(int indexFila) {
        if (indexFila != -1) {
            JPanel fila = vista.getFilas()[indexFila];
            fila.setBorder(null);
            fila.setBackground(MisColores.blanco);
        }
        ultimaFilaSeleccionada = -1;
    }
    
    private void siguientePagina() {
        if (pagina >= paginas) return;
        pagina++;
        cp.actualizarTabla(true);
    }
    
    private void anteriorPagina() {
        if (pagina <= 1) return;
        pagina--;
        cp.actualizarTabla(true);
    }
    
    public void setCanSelect(boolean can) {
        canSelect = can;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Object src = e.getSource();
        Component hijoTabla = vista.getTabla().getComponentAt(e.getPoint());
        
        //Quitar ultima selección
        resetearFila(ultimaFilaSeleccionada);
        
        //Botones
        if (src == vista.getPaginacion()[1]) {
            anteriorPagina();
            return;
        } else if (src == vista.getPaginacion()[2]) {
            siguientePagina();
            return;
        }
        
        if (!canSelect) return;
        //Seleccción de filas
        for (int i = 0; i < maximoFilas; i++) {
            if (hijoTabla == FILAS[i] && FILAS[i].getClientProperty("id") != null) {
                ultimaFilaSeleccionada = i;
                FILAS[i].setBorder(new LineBorder(MisColores.color1, 2));
                FILAS[i].setBackground(MisColores.acento);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}