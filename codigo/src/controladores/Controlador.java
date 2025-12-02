/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import modelos.Accion;
import modelos.Cliente;
import modelos.GestionClientes;
import modelos.HistorialAtencion;
import modelos.RegistroAcciones;
import vistas.VentanaInfo;
import vistas.Vista;

/**
 *
 * @author andres
 */
public class Controlador {
    private ControladorCombo ctlCmbTabla;
    private ControladorCombo ctlCmbTipoSolicitud;
    private ControladorTabla ctlTabla;
    private GestionClientes gestionClientes;
    private HistorialAtencion historial;
    private RegistroAcciones registroAcciones;
    private VentanaInfo ventana;
    private Vista vista;

    public Controlador(Vista vista) {
        String[] opcionesTabla = {"COLA DE ESPERA", "LISTA DE ATENDIDOS", "REGISTRO DE ACCIONES"};
        this.ctlCmbTabla = new ControladorCombo(vista.getCmbPanelTabla(), vista.getCmbLabelTabla(), opcionesTabla, this);
        String[] opcionesTipoSolicitud = {"SOPORTE", "MANTENIMINETO", "RECLAMO"};
        this.ctlCmbTipoSolicitud = new ControladorCombo(vista.getCmbPanelTipoSolicitud(), vista.getCmbLabelTipoSolicitud(), opcionesTipoSolicitud, this);
        this.ctlTabla = new ControladorTabla(vista, this);
        this.gestionClientes = new GestionClientes();
        this.historial = new HistorialAtencion();
        this.registroAcciones = new RegistroAcciones();
        new ControladorBotones(vista, this);

        this.vista = vista;
        this.ventana = new VentanaInfo();
        this.ventana.setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);
        vista.setVisible(true);
        this.agregarClientes();
    }
    
    private void agregarClientes() {
        String[][] clientes = {
            {"Milagros", "SOPORTE", "Urgente"},
            {"Andres", "MANTENIMIENTO", "Normal"},
            {"Sebastian", "RECLAMO", "Normal"}
        };
        for (String[] cliente : clientes) {
            registroAcciones.registrar("registrar", gestionClientes.agregar(cliente[0], cliente[1], cliente[2]));
        }
        actualizarTabla(true);
    }
    
    public void registrar() {
        String nombre = vista.getInputNombre().getText();
        if (nombre.isEmpty()) return;
        String solicitud = ctlCmbTipoSolicitud.getText();
        String prioridad = vista.getCheckBox().isSelected() ? "Urgente" : "Normal";
        vista.getCheckBox().setSelected(false);
        ctlCmbTabla.setIndex(0);
        Cliente cli = gestionClientes.agregar(nombre, solicitud, prioridad);
        registroAcciones.registrar("registrar", cli);
        actualizarTabla(true);
    }
    
    public void atender() {
        if (ctlCmbTabla.getIndex() != 0 || gestionClientes.tamano() == 0) return;
        Cliente cliente = gestionClientes.eliminar(ctlTabla.getSelected());
        historial.agregar(cliente);
        registroAcciones.registrar("atender", cliente);
        actualizarTabla(true);
    }
    
    public void actual() {
        ctlCmbTabla.setIndex(0);
        Cliente cli = gestionClientes.peek();
        Cliente[] arreglo = {cli};
        if (cli == null) arreglo = new Cliente[0];
        ctlTabla.actualizar(arreglo, -1);
    }
    
    public void buscar() {
        String busqueda = vista.getInputBuscar().getText().trim().toLowerCase();
        boolean esNumero;
        
        vista.getCabeceras()[0].setText("CLIENTE");
        vista.getCabeceras()[1].setText("PRIORIDAD");
        vista.getCabeceras()[2].setText("SOLICITUD");
        ctlCmbTabla.setIndex(1);
        ctlTabla.setCanSelect(false);
        
        try {Integer.parseInt(busqueda); esNumero = true;
        } catch (NumberFormatException e) {esNumero = false;}
        
        if (esNumero) {
            Cliente cli = historial.buscarPorId(Integer.parseInt(busqueda));
            Cliente[] arreglo = {cli};
            if (cli == null) arreglo = new Cliente[0];
            ctlTabla.actualizar(arreglo, 1);
        } else {
            ctlTabla.actualizar(historial.buscarPorTipoSolicitud(busqueda), 1);
        }
    }
    
    public void eliminar() {
        int id = ctlTabla.getSelected();
        if (id == -1) return;
        Cliente eliminado = null;
        
        if (ctlCmbTabla.getIndex() == 0) {
            eliminado = gestionClientes.eliminar(id);
        }
        
        registroAcciones.registrar("eliminar", eliminado);
        actualizarTabla(true);
    }
   
    public void deshacer() {
        Accion accion = registroAcciones.deshacer();
        if (accion == null) return;
        Cliente cli = accion.getCliente();
        switch (accion.getTipo()) {
            case "registrar" -> {
                gestionClientes.eliminar(cli);
            }
            case "atender" -> {
                gestionClientes.agregarEn(cli.getId() - 1, cli);
                historial.eliminar(cli);
            }
            case "eliminar" -> {
                gestionClientes.agregarEn(cli.getId() - 1, cli);
            }
        }
        registroAcciones.registrar("restaurar", cli);
        actualizarTabla(true);
    }
    
    public void mostrarInfo() {
        ventana.getTotalEnEspera().setText(String.valueOf(gestionClientes.tamano()));
        ventana.getTotalAtendidos().setText(String.valueOf(historial.tamano()));
        ventana.getPromedio().setText("1 min");
        ventana.setVisible(true);
    }
    
    public void actualizarTabla(boolean soloSiClickeo) {
        javax.swing.JLabel[] cabeceras = vista.getCabeceras();
        
        if (!soloSiClickeo && !vista.getInputBuscar().getText().equals("Buscar...")) {
            buscar();
            return;
        }
        
        switch (ctlCmbTabla.getIndex()) {
            case 0 -> {
                cabeceras[0].setText("CLIENTE");
                cabeceras[1].setText("PRIORIDAD");
                cabeceras[2].setText("SOLICITUD");
                ctlTabla.setCanSelect(true);
                ctlTabla.actualizar(gestionClientes.get(), 0); 
            }
            case 1 -> {
                cabeceras[0].setText("CLIENTE");
                cabeceras[1].setText("PRIORIDAD");
                cabeceras[2].setText("SOLICITUD");
                ctlTabla.setCanSelect(false);
                ctlTabla.actualizar(historial.get(), 1);
            }
            case 2 -> {
                cabeceras[0].setText("TIPO");
                cabeceras[1].setText("CLIENTE");
                cabeceras[2].setText("FECHA/HORA");
                ctlTabla.setCanSelect(false);
                ctlTabla.actualizar(registroAcciones.get(), -1);
            }
        }
    }
}
