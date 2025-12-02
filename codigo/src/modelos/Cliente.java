/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author andres
 */
public class Cliente {
    private int id;
    private String nombre;
    private String tipoSolicitud;
    private String prioridad;

    public Cliente(int id, String nombre, String tipoSolicitud, String prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.tipoSolicitud = tipoSolicitud;
        this.prioridad = prioridad;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
    
    @Override
    public String toString() {
        return id + ";" + nombre + ";" + prioridad + ";" + tipoSolicitud;
    }
    
}
