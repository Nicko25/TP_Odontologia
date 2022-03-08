package servicio;

import dao.OdontologoDAO;
import dao.OdontologoDAOArchivo;
import negocio.Odontologo;

import java.util.List;

public class OdontologoService {

    private OdontologoDAO odontologoDAO;

    public OdontologoService()
    {
        odontologoDAO = new OdontologoDAOArchivo();
    }

    public void guardar(Odontologo odontologo)
    {
        odontologoDAO.guardar(odontologo);
    }

    public void eliminar(long id)
    {
        odontologoDAO.eliminar(id);
    }

    public Odontologo buscarId(long id)
    {
        return odontologoDAO.buscarId(id);
    }
    
    public Odontologo buscar(String[] odontDat)
    {
        return odontologoDAO.buscar(odontDat);
    }

    public List<Odontologo> listar()
    {
        return odontologoDAO.listar();
    }
}
