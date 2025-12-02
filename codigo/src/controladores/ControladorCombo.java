/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import modelos.MisColores;

/**
 *
 * @author andres
 */
public class ControladorCombo {
    private JPanel panelPrincipal;
    private JLabel labelPrincipal;
    private JLabel primeraOpcion;
    private JLabel ultimaOpcion;
    private String icono;
    private String[] opciones;
    private JPopupMenu popup;
    private int opcionWidth;
    private int opcionHeight;
    private int indexSeleccionado;
    private Controlador cp;
    
    public ControladorCombo(JPanel panelComboBox, JLabel labelComboBox, String[] opciones, Controlador cp) {
        this.icono = "▶";
        this.panelPrincipal = panelComboBox;
        this.labelPrincipal = labelComboBox;
        this.opcionWidth = panelComboBox.getWidth();
        this.opcionHeight = labelComboBox.getHeight();
        this.indexSeleccionado = 0;
        this.opciones = opciones;
        this.cp = cp;
        
        panelPrincipal.setRequestFocusEnabled(true);
        panelPrincipal.setFocusable(true);
        
        JPanel panelOpciones = new JPanel();
        panelOpciones.setPreferredSize(new Dimension(opcionWidth, opcionHeight * opciones.length));
        panelOpciones.setLayout(new GridLayout(opciones.length, 1));
        panelOpciones.setBackground(panelComboBox.getBackground());
        panelOpciones.setForeground(labelComboBox.getForeground());
        
        for (int i = 0; i < opciones.length; i++) {
            JLabel lbl = crearOpcion(opciones[i]);
            lbl.putClientProperty("index", i);
            panelOpciones.add(lbl);
            
            if (i == 0) {
                this.primeraOpcion = lbl;
            } else if (i == opciones.length - 1) {
                this.ultimaOpcion = lbl;
            }
        }
        
        //**Cambiar estilos por defecto
        UIManager.put("PopupMenu.border", BorderFactory.createEmptyBorder(0,0,0,0));
        
        //Popup con opciones
        this.popup = new JPopupMenu();
        popup.setOpaque(false);
        popup.setPopupSize(new Dimension(opcionWidth, panelOpciones.getPreferredSize().height));
        popup.add(panelOpciones);
        
        labelComboBox.setText(icono + " " + opciones[indexSeleccionado]);
        
        addEvents();
    }
    
    private JLabel crearOpcion(String texto) {
        JLabel lbl = new JLabel("   " + texto);
        lbl.setFont(labelPrincipal.getFont());
        lbl.setPreferredSize(new Dimension(opcionWidth, opcionHeight));
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl.setRequestFocusEnabled(true);
        opcionFocus(lbl, false);
        lbl.setFocusable(true);
        lbl.setOpaque(true);
        
        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                indexSeleccionado = (int) lbl.getClientProperty("index");
                labelPrincipal.setText(icono + " " + opciones[indexSeleccionado]);
                opcionFocus(lbl, false);
                togglePopup();
                //callback
                cp.actualizarTabla(true);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) { opcionFocus(lbl, true); }

            @Override
            public void mouseExited(MouseEvent e) { opcionFocus(lbl, false); }
        });
        
        lbl.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { opcionFocus(lbl, true); }
            @Override
            public void focusLost(FocusEvent e) { opcionFocus(lbl, false); if (e.getSource() == ultimaOpcion) primeraOpcion.requestFocus(); }
        });
        
        lbl.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
                    indexSeleccionado = (int) lbl.getClientProperty("index");
                    labelPrincipal.setText(icono + " " + opciones[indexSeleccionado]);
                    opcionFocus(lbl, false);
                    togglePopup();
                    //callback
                    cp.actualizarTabla(true);
                }
            }
        });
                
        return lbl;
    }
    
    private void opcionFocus(JLabel opcion, boolean hayEnfoque) {
        if (hayEnfoque) {
            opcion.setBackground(MisColores.acento);
            opcion.setForeground(panelPrincipal.getBackground());
        } else {
            opcion.setBackground(panelPrincipal.getBackground());
            opcion.setForeground(panelPrincipal.getForeground());
        }
    }
    
    private void addEvents() {
        panelPrincipal.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { togglePopup(); }
            @Override
            public void mouseEntered(MouseEvent e) { labelPrincipal.setForeground(MisColores.acento); }
            @Override
            public void mouseExited(MouseEvent e) { labelPrincipal.setForeground(MisColores.texto); }
            
        });
        panelPrincipal.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { labelPrincipal.setForeground(MisColores.acento); }
            @Override
            public void focusLost(FocusEvent e) { labelPrincipal.setForeground(MisColores.texto); }
        });
        panelPrincipal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
                    togglePopup();
                    primeraOpcion.requestFocus();
                }
            }
        });
    }
    
    private void togglePopup() {
        if (popup.isVisible()) popup.setVisible(false);
        else { panelPrincipal.requestFocus(); popup.show(panelPrincipal, 0, panelPrincipal.getHeight()); } //TO TOP: -opcionHeight * opciones.length
    }
    
    public int getIndex() {
        return indexSeleccionado;
    }
    
    public void setIndex(int i) {
        if (i >= opciones.length) return;
        indexSeleccionado = i;
        labelPrincipal.setText(icono + " " + opciones[i]);
    }
    
    public String getText() {
        return opciones[indexSeleccionado];
    }
}