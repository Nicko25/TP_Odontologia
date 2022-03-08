package dao;

import negocio.Usuario;

import java.util.List;

public interface UsuarioDAO {

    public void guardar(Usuario usuario);
    public void eliminar(long id);
    public List<Usuario> listar();
    public Usuario buscarId(long id);
    public int buscarUser(String user, String pw);
    public Usuario recuperarUser(String user, String pw);
}
