package dao;

import negocio.Turno;

import java.util.ArrayList;
import java.util.List;

public class TurnoDAOArchivo implements TurnoDAO{

    @Override
    public void guardar(Turno turno) {

        //1 recuperar de un archivo todo el listado de personas
        List<Turno> lista = listar();

        if(lista == null)
            lista = new ArrayList<>();

        long idMax = 0;
        //2 preguntar si id de la persona es igual a cero
        if(turno.getId() == 0)
        {
            for(Turno p:lista) {
                if (p.getId() > idMax)
                    idMax = p.getId();
            }
            //3 agregar a persona dentro de la lista de personas
            turno.setId(idMax + 1);
            lista.add(turno);
        }else
        {
            //4 si el id es difernte de cero tengo que modificar los atributos de la persona con ese id
            for(Turno p:lista) {
                if(p.getId() == turno.getId())
                {
                    p.setUsuario(turno.getUsuario());
                    p.setOdontNombre(turno.getOdontNombre());
                    p.setOdontApellido(turno.getOdontApellido());
                    p.setNombre(turno.getNombre());
                    p.setApellido(turno.getApellido());
                    p.setDia(turno.getDia());
                    p.setMes(turno.getMes());
                    p.setAño(turno.getAño());
                    p.setHorario(turno.getHorario());
                }
                
            }
        }
        //5 guardar el listado en el archivo
        Archivo archivoTurnos = new Archivo("turnos.txt");
        archivoTurnos.guardar(lista);
    }

    @Override
    public void eliminar(long id) {
        //1 recuperar de un archivo todo el listado de usuarios
        List<Turno> listado = listar();
        //2 buscar en el listado de usuarios una usuario con el id que viene por parametro

        int i = 0;
        boolean encontro = false;
        while(i < listado.size() && !encontro)
        {
            //3 si lo encontre lo elimino de la lista
            Turno p = listado.get(i);
            if(p.getId() == id)
            {
                listado.remove(p);
                encontro = true;
            }
            i++;
        }

        //4 guardar el listado en el archivo
        Archivo archivoUsuarios = new Archivo("turnos.txt");
        archivoUsuarios.guardar(listado);

    }

    @Override
    public List<Turno> listar() {
        //1 recuperar de un archivo todo el listado de usuarios
        Archivo archivoUsuarios = new Archivo("turnos.txt");
        List<Turno> lista = archivoUsuarios.recuperar();

        if(lista == null)
            lista = new ArrayList<>();
        //2 retornar ese listado*/
        return lista;
    }

    @Override
    public Turno buscarId(long id) {
        //1 recuperar de un archivo todo el listado de usuarios
        List<Turno> listado = listar();
        //2 buscar dentro de ese listado una usuario con el id que viene por parametro en este metodo
        for(Turno p : listado) {
            //3 si lo encuentro retornar ese objeto usuario.
            if (p.getId() == id)
                return p;
        }
        return null;
    }

    @Override
    public boolean buscar(String horario,String dia, int mes, int año) {
        List<Turno> listado = listar();
        boolean resultado = false;
        for(Turno t : listado) {
            if (t.getHorario().equals(horario) && t.getDia().equals(dia) && t.getMes() == mes && t.getAño() == año){
                resultado = true;
            }
        }
        return resultado;
    }
    
    
}
