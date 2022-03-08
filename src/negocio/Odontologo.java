package negocio;

import java.io.Serializable;

public class Odontologo implements Serializable{
    
    private long id;
    private String nombre;
    private String apellido;
    private boolean[] semana;
    private int matricula;

    public Odontologo() {
    }

    public Odontologo(long id, String nombre, String apellido, int matricula,boolean[] semana) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
        this.semana = semana;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public boolean[] getSemana() {
        return semana;
    }

    public void setSemana(boolean[] semana) {
        this.semana = semana;
    }
    
}
