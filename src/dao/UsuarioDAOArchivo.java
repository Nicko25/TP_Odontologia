package dao;

import negocio.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOArchivo implements UsuarioDAO{

    @Override
    public void guardar(Usuario usuario) {

        //1 recuperar de un archivo todo el listado de personas
        List<Usuario> lista = listar();

        if(lista == null)
            lista = new ArrayList<>();

        long idMax = 0;
        //2 preguntar si id de la persona es igual a cero
        if(usuario.getId() == 0)
        {
            for(Usuario p:lista) {
                if (p.getId() > idMax)
                    idMax = p.getId();
            }
            //3 agregar a persona dentro de la lista de personas
            usuario.setId(idMax + 1);
            lista.add(usuario);
        }else
        {
            //4 si el id es difernte de cero tengo que modificar los atributos de la persona con ese id
            for(Usuario p:lista) {
                if(p.getId() == usuario.getId())
                {
                    p.setUsuario(usuario.getUsuario());
                    p.setContraseña(usuario.getContraseña());
                    p.setNombre(usuario.getNombre());
                    p.setApellido(usuario.getApellido());
                    p.setAdmin(usuario.isAdmin());
                }
                
            }
        }
        //5 guardar el listado en el archivo
        Archivo archivoPersonas = new Archivo("usuarios.txt");
        archivoPersonas.guardar(lista);
    }

    @Override
    public void eliminar(long id) {
        //1 recuperar de un archivo todo el listado de usuarios
        List<Usuario> listado = listar();
        //2 buscar en el listado de usuarios una usuario con el id que viene por parametro

        int i = 0;
        boolean encontro = false;
        while(i < listado.size() && !encontro)
        {
            //3 si lo encontre lo elimino de la lista
            Usuario p = listado.get(i);
            if(p.getId() == id)
            {
                listado.remove(p);
                encontro = true;
            }
            i++;
        }

        //4 guardar el listado en el archivo
        Archivo archivoUsuarios = new Archivo("usuarios.txt");
        archivoUsuarios.guardar(listado);

    }

    @Override
    public List<Usuario> listar() {
        //1 recuperar de un archivo todo el listado de usuarios
        Archivo archivoUsuarios = new Archivo("usuarios.txt");
        List<Usuario> lista = archivoUsuarios.recuperar();

        if(lista == null)
            lista = new ArrayList<>();
        //2 retornar ese listado*/
        return lista;
    }

    @Override
    public Usuario buscarId(long id) {
        //1 recuperar de un archivo todo el listado de usuarios
        List<Usuario> listado = listar();
        //2 buscar dentro de ese listado una usuario con el id que viene por parametro en este metodo
        for(Usuario p : listado) {
            //3 si lo encuentro retornar ese objeto usuario.
            if (p.getId() == id)
                return p;
        }
        return null;
    }
    
    @Override
    public int buscarUser(String user, String pw) {
        
        //1 recuperar de un archivo todo el listado de usuarios
        List<Usuario> listado = listar();
        //2 buscar dentro de ese listado una usuario con el id que viene por parametro en este metodo
        for(Usuario usuario : listado) {
            if(usuario.getUsuario().equals(user) && usuario.getContraseña().equals(pw)){
                if(usuario.isAdmin()){
                    return 1;

                }
                else{
                    return 2;
                    
                }
            }
        }
        return 0;
    }

    @Override
    public Usuario recuperarUser(String user, String pw) {
        Usuario usuario = new Usuario();
        List<Usuario> listado = listar();
        for (Usuario u : listado){
            if(u.getUsuario().equals(user) && u.getContraseña().equals(pw)){
                usuario = u;
                return usuario;
            }
        }
        return null;
        
    }
    
    
    
}
