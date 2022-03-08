package dao;

import negocio.Odontologo;

import java.util.List;

public interface OdontologoDAO {

    public void guardar(Odontologo odontologo);
    public void eliminar(long id);
    public List<Odontologo> listar();
    public Odontologo buscarId(long id);
    public Odontologo buscar(String[] o);
    //public int buscarUser(String user, String pw);

}
