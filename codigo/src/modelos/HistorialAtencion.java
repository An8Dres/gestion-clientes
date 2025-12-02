/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author andres
 */
public class HistorialAtencion {
    private LinkedList<Cliente> lista;
    
    public HistorialAtencion() {
        this.lista = new LinkedList<>();
    }
    
    public void agregar(Cliente clienteAtendido) {
        if (clienteAtendido == null) return;
        lista.add(clienteAtendido);
    }
    
    public void eliminar(Cliente cliente) {
        lista.remove(cliente);
    }
    
    public Cliente buscarPorId(int id) {
        for (Cliente cli : lista) {
            if (cli.getId() == id) return cli;
        }
        return null;
    }
    
    public Cliente[] buscarPorTipoSolicitud(String tipoSolicitud) {
        Cliente[] clientesEncontrados = new Cliente[lista.size()];
        
        int i = 0;
        for (Cliente cli : lista) {
            if (cli.getTipoSolicitud().trim().toLowerCase().contains(tipoSolicitud)) {
                clientesEncontrados[i++] = cli;
            }
        }
        
        return Arrays.copyOf(clientesEncontrados, i);
    }
    
    public int tamano() {
        return lista.size();
    }
    
    public Cliente[] get() {
        Cliente[] clientes = new Cliente[lista.size()];
        int i = 0;
        for (Cliente cliente : lista) {
            clientes[i++] = cliente;
        }
        return clientes;
    }
}
