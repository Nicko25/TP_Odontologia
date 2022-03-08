package servicio;

import dao.UsuarioDAO;
import dao.UsuarioDAOArchivo;
import negocio.Usuario;

import java.util.List;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService()
    {
        usuarioDAO = new UsuarioDAOArchivo();
    }

    public void guardar(Usuario usuario)
    {
        usuarioDAO.guardar(usuario);
    }

    public void eliminar(long id)
    {
        usuarioDAO.eliminar(id);
    }

    public Usuario buscarId(long id)
    {
        return usuarioDAO.buscarId(id);
    }
    
    public int buscarUser(String user, String pw){
        return usuarioDAO.buscarUser(user, pw);
    }
    
    public Usuario recuperarUser(String user, String pw){
        return usuarioDAO.recuperarUser(user, pw);
    }

    public List<Usuario> listar()
    {
        return usuarioDAO.listar();
    }
}
