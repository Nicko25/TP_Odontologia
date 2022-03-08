package dao;

import negocio.Odontologo;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOArchivo implements OdontologoDAO{

    @Override
    public void guardar(Odontologo odontologo) {

        //1 recuperar de un archivo todo el listado de personas
        List<Odontologo> lista = listar();

        if(lista == null)
            lista = new ArrayList<>();

        long idMax = 0;
        //2 preguntar si id de la persona es igual a cero
        if(odontologo.getId() == 0)
        {
            for(Odontologo p:lista) {
                if (p.getId() > idMax)
                    idMax = p.getId();
            }
            //3 agregar a persona dentro de la lista de personas
            odontologo.setId(idMax + 1);
            lista.add(odontologo);
        }else
        {
            //4 si el id es difernte de cero tengo que modificar los atributos de la persona con ese id
            for(Odontologo p:lista) {
                if(p.getId() == odontologo.getId())
                {
                    p.setNombre(odontologo.getNombre());
                    p.setApellido(odontologo.getApellido());
                    p.setMatricula(odontologo.getMatricula());
                    p.setSemana(odontologo.getSemana());
                }
                
            }
        }
        //5 guardar el listado en el archivo
        Archivo archivoPersonas = new Archivo("odontologos.txt");
        archivoPersonas.guardar(lista);
    }

    @Override
    public void eliminar(long id) {
        //1 recuperar de un archivo todo el listado de usuarios
        List<Odontologo> listado = listar();
        //2 buscar en el listado de usuarios una usuario con el id que viene por parametro

        int i = 0;
        boolean encontro = false;
        while(i < listado.size() && !encontro)
        {
            //3 si lo encontre lo elimino de la lista
            Odontologo p = listado.get(i);
            if(p.getId() == id)
            {
                listado.remove(p);
                encontro = true;
            }
            i++;
        }

        //4 guardar el listado en el archivo
        Archivo archivoUsuarios = new Archivo("odontologos.txt");
        archivoUsuarios.guardar(listado);

    }

    @Override
    public List<Odontologo> listar() {
        //1 recuperar de un archivo todo el listado de usuarios
        Archivo archivoOdontologos = new Archivo("odontologos.txt");
        List<Odontologo> lista = archivoOdontologos.recuperar();

        if(lista == null)
            lista = new ArrayList<>();
        //2 retornar ese listado*/
        return lista;
    }

    @Override
    public Odontologo buscarId(long id) {
        //1 recuperar de un archivo todo el listado de usuarios
        List<Odontologo> listado = listar();
        //2 buscar dentro de ese listado una usuario con el id que viene por parametro en este metodo
        for(Odontologo p : listado) {
            //3 si lo encuentro retornar ese objeto usuario.
            if (p.getId() == id)
                return p;
        }
        return null;
    }

    @Override
    public Odontologo buscar(String[] odontDat) {
        List<Odontologo> listado = listar();
        for(Odontologo odontArch : listado) {
            if (odontArch.getNombre().equals(odontDat[0]) && odontArch.getApellido().equals(odontDat[1])){
                return odontArch;
            }
        }
        return null;
    }
    
    
}
