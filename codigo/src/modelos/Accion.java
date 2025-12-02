/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.time.LocalDate;

/**
 *
 * @author andres
 */
public class Accion {
    private int id;
    private String tipo;
    private Cliente cliente;
    private String nombre;
    private LocalDate fechaHora;
    public boolean restaurada;

    public Accion(int id, String tipo, Cliente cliente, LocalDate fechaHora) {
        this.id = id;
        this.tipo = tipo;
        this.cliente = cliente;
        this.restaurada = false;
        this.fechaHora = fechaHora;
        this.nombre = cliente.getNombre();
    }
    
    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public void removeCliente() {
        this.cliente = null;
    }

    public LocalDate getTiempo() {
        return fechaHora;
    }
    
    @Override
    public String toString() {
        return id + ";" + tipo + ";" + nombre + ";" + fechaHora.toString() + ";" + false;
    }
}
