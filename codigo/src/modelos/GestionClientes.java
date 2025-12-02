/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author andres
 */
public class GestionClientes {

    private ArrayDeque<Cliente> cola;
    private int contadorClientes;

    public GestionClientes() {
        this.cola = new ArrayDeque<>();
        this.contadorClientes = 0;
    }

    public Cliente agregar(String nombre, String tipoSolicitud, String prioridad) {
        contadorClientes++;
        Cliente nuevo = new Cliente(contadorClientes, nombre, tipoSolicitud, prioridad);
        cola.addLast(nuevo);
        return nuevo;
    }

    public void agregarEn(int indice, Cliente cliente) {
        if (indice > cola.size()) {
            cola.add(cliente);
        } else {
            ArrayList<Cliente> listaTemp = new ArrayList<>(cola);
            listaTemp.add(indice, cliente);
            cola.clear();
            cola.addAll(listaTemp);
        }
    }
    
    public Cliente eliminar(int id) {
        if (id == -1) return cola.removeFirst();

        Cliente eliminado = null;
        for (Cliente c : cola) {
            if (c.getId() == id) {
                eliminado = c;
                cola.removeIf(e -> e.getId() == id);
                break;
            }
        }
        return eliminado;
    }
    
    public void eliminar(Cliente cliente) {
        cola.remove(cliente);
    }

    public Cliente peek() {
        return cola.peek();
    }

    public Cliente[] get() {
        Cliente[] clientes = new Cliente[cola.size()];
        int i = 0;
        for (Cliente cliente : cola) {
            clientes[i++] = cliente;
        }
        return clientes;
    }

    public int tamano() {
        return cola.size();
    }
}
