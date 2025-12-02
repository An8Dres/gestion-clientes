/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.time.LocalDate;
import java.util.Stack;

/**
 *
 * @author andres
 */
public class RegistroAcciones {
    private int contadorId;
    private Stack<Accion> pila;
    
    public RegistroAcciones() {
        contadorId = 0;
        this.pila = new Stack();
    }
    
    public void registrar(String tipo, Cliente cliente) {
        contadorId++;
        pila.push(new Accion(contadorId, tipo, cliente, LocalDate.now()));
    }
    
    public Accion deshacer() {
        Accion ultima = null;
        for (Accion a : get()) {
            if (!a.getTipo().equals("restaurar") && !a.restaurada) {
                a.restaurada = true;
                ultima = a;
                break;
            }
        }
        return ultima;
    }
    
    public Accion[] get() {
        Accion[] acciones = new Accion[pila.size()];
        int i = pila.size() - 1;
        for (Accion accion : pila) {
            acciones[i--] = accion;
        }
        return acciones;
    }
}
