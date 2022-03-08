package dao;

import negocio.Turno;

import java.util.List;

public interface TurnoDAO {

    public void guardar(Turno turno);
    public void eliminar(long id);
    public List<Turno> listar();
    public Turno buscarId(long id);
    public boolean buscar(String hora,String dia,int mes,int año);

}
