package negocio;

import java.io.Serializable;

public class Turno implements Serializable {
    
    //datos usuario
    private long id;
    private String usuario;
    private String odontNombre;
    private String odontApellido;
    private String nombre;
    private String apellido;
    private String dia;
    private int mes;
    private int año;
    private String horario;

    public Turno(String usuario, String odontNombre, String odontApellido, String nombre, String apellido, String dia, int mes,int año, String horario) {
        this.usuario = usuario;
        this.odontNombre = odontNombre;
        this.odontApellido = odontApellido;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.horario = horario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getOdontNombre() {
        return odontNombre;
    }

    public void setOdontNombre(String odontNombre) {
        this.odontNombre = odontNombre;
    }

    public String getOdontApellido() {
        return odontApellido;
    }

    public void setOdontApellido(String odontApellido) {
        this.odontApellido = odontApellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }
    
    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    
    

}
