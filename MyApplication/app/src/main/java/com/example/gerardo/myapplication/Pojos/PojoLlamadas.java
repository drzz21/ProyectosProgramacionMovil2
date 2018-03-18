package com.example.gerardo.myapplication.Pojos;



public class PojoLlamadas {
    private int ID;
    private String Contacto;
    private String Telefono;
    private String Hora;
    private String Fecha;

    public PojoLlamadas(){}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public PojoLlamadas(String contacto, String telefono, String hora, String fecha) {
        Contacto = contacto;
        Telefono = telefono;
        Hora = hora;
        Fecha = fecha;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String contacto) {
        Contacto = contacto;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
