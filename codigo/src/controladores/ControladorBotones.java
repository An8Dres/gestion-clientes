/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import modelos.MisColores;
import vistas.Vista;

/**
 *
 * @author andres
 */
public class ControladorBotones implements MouseListener, FocusListener, KeyListener {
    private Controlador controladorPrincipal;
    private Vista vista;
    
    public ControladorBotones(Vista vista, Controlador cp) {
        this.controladorPrincipal = cp;
        this.vista = vista;
        vista.getBtnRegistrar().addMouseListener(this);
        vista.getBtnRegistrar().addFocusListener(this);
        vista.getBtnRegistrar().addKeyListener(this);
        
        vista.getBtnAtender().addMouseListener(this);
        vista.getBtnAtender().addFocusListener(this);
        vista.getBtnAtender().addKeyListener(this);
        
        vista.getBtnActual().addMouseListener(this);
        vista.getBtnActual().addFocusListener(this);
        vista.getBtnActual().addKeyListener(this);
        
        vista.getBtnEliminar().addMouseListener(this);
        vista.getBtnEliminar().addFocusListener(this);
        vista.getBtnEliminar().addKeyListener(this);
        
        vista.getBtnDeshacer().addMouseListener(this);
        vista.getBtnDeshacer().addFocusListener(this);
        vista.getBtnDeshacer().addKeyListener(this);
        
        vista.getBtnInfo().addMouseListener(this);
        vista.getBtnBuscar().addMouseListener(this);
        
        vista.getCheckBox().addKeyListener(this);
        vista.getCheckBox().addFocusListener(this);
        vista.getCheckBox().addMouseListener(this);

        vista.getInputBuscar().addFocusListener(this);
        vista.getInputBuscar().addKeyListener(this);
        vista.getInputNombre().addFocusListener(this);
        vista.getInputNombre().addKeyListener(this);
    }
    
    private void registrarFocus(boolean estaEnfocado) {
        if (estaEnfocado) {
            vista.getBtnRegistrar().setBackground(MisColores.acento);
        } else {
            vista.getBtnRegistrar().setBackground(MisColores.blanco);
        }
    }
    private void atenderFocus(boolean estaEnfocado) {
        if (estaEnfocado) {
            vista.getBtnAtender().setBackground(MisColores.blanco);
        } else {
            vista.getBtnAtender().setBackground(MisColores.texto);
        }
    }
    private void actualFocus(boolean estaEnfocado) {
        if (estaEnfocado) {
            vista.getBtnActual().setForeground(MisColores.blanco);
            vista.getBtnActual().setBorder(new LineBorder(MisColores.blanco, 2));
        } else {
            vista.getBtnActual().setForeground(MisColores.texto);
            vista.getBtnActual().setBorder(new LineBorder(MisColores.texto, 2));
        }
    }
    private void eliminarFocus(boolean estaEnfocado) {
        if (estaEnfocado) {
            vista.getBtnEliminar().setForeground(MisColores.rojoHover);
        } else {
            vista.getBtnEliminar().setForeground(MisColores.grisFlecha);
        }
    }
     private void deshacerFocus(boolean estaEnfocado) {
        if (estaEnfocado) {
            vista.getBtnDeshacer().setForeground(MisColores.color2);
        } else {
            vista.getBtnDeshacer().setForeground(MisColores.grisFlecha);
        }
    }
    private void nombreFocus(boolean estaEnfocado) {
        if (estaEnfocado) {
            if (vista.getInputNombre().getText().trim().equals("Nombre del Cliente")) {
               vista.getInputNombre().setText("");
            }
        } else {
            if (vista.getInputNombre().getText().isEmpty()) {
               vista.getInputNombre().setText("Nombre del Cliente");
            }
        }
    }
    private void buscarFocus(boolean estaEnfocado) {
        if (estaEnfocado) {
            if (vista.getInputBuscar().getText().trim().equals("Buscar")) {
               vista.getInputBuscar().setText("");
            }
        } else {
            if (vista.getInputBuscar().getText().isEmpty()) {
               vista.getInputBuscar().setText("Buscar");
            }
        }
    }
    private void checkboxFocus(boolean estaEnfocado) {
        if (estaEnfocado) {
            vista.getCheckBox().setForeground(MisColores.acento);
        } else {
            vista.getCheckBox().setForeground(MisColores.texto);
        }
    }
    
    private void allFocus(Object src, boolean hayEnfoque) {
        if (src == vista.getBtnRegistrar()) {
            registrarFocus(hayEnfoque);
        } else if (src == vista.getBtnAtender()) {
            atenderFocus(hayEnfoque);
        } else if (src == vista.getBtnActual()) {
            actualFocus(hayEnfoque);
        } else if (src == vista.getBtnEliminar()) {
            eliminarFocus(hayEnfoque);
        }   else if (src == vista.getBtnDeshacer()) {
            deshacerFocus(hayEnfoque);
        } else if (src == vista.getInputNombre()) {
            nombreFocus(hayEnfoque);
        } else if (src == vista.getInputBuscar()) {
            buscarFocus(hayEnfoque);
        } else if (src == vista.getCheckBox()) {
            checkboxFocus(hayEnfoque);
        }
    }
    
    private void allClicks(Object src) {
        if (src == vista.getBtnRegistrar()) {
           controladorPrincipal.registrar(); 
        } else if (src == vista.getBtnAtender()) {
           controladorPrincipal.atender();
        } else if (src == vista.getBtnActual()) {
            controladorPrincipal.actual();
        } else if (src == vista.getBtnEliminar()) {
           controladorPrincipal.eliminar();
        } else if (src == vista.getBtnDeshacer()) {
           controladorPrincipal.deshacer();
        } else if (src == vista.getBtnBuscar()) {
            javax.swing.JTextField input = vista.getInputBuscar();
            input.requestFocus();
            if (!input.getText().equals("Buscar")) controladorPrincipal.buscar();
        } else if (src == vista.getBtnInfo()) {
           controladorPrincipal.mostrarInfo();
        }
    }
    
    private void allInputs(Object src) {
        if (src == vista.getInputBuscar()) {
           controladorPrincipal.buscar(); 
        } else if (src == vista.getCheckBox()) {
            JCheckBox cb = vista.getCheckBox();
           if (cb.isSelected()) cb.setSelected(false); else cb.setSelected(true);
        } else if (src == vista.getInputNombre()) {
           vista.getCmbPanelTipoSolicitud().requestFocus();
        }
    } 
    
    @Override
    public void mouseClicked(MouseEvent e) {
        allClicks(e.getSource());
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        allFocus(e.getSource(), true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
       allFocus(e.getSource(), false);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void focusGained(FocusEvent e) {
        allFocus(e.getSource(), true);
    }

    @Override
    public void focusLost(FocusEvent e) {
        allFocus(e.getSource(), false);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
            allClicks(e.getSource());
            allInputs(e.getSource());
        } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
            controladorPrincipal.deshacer();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
