package servicio;

import dao.TurnoDAO;
import dao.TurnoDAOArchivo;
import negocio.Turno;

import java.util.List;

public class TurnoService {

    private TurnoDAO turnoDAO;

    public TurnoService()
    {
        turnoDAO = new TurnoDAOArchivo();
    }

    public void guardar(Turno turno)
    {
        turnoDAO.guardar(turno);
    }

    public void eliminar(long id)
    {
        turnoDAO.eliminar(id);
    }

    public Turno buscarId(long id)
    {
        return turnoDAO.buscarId(id);
    }
    
    public boolean buscarHorario(String hora,String dia,int mes,int año)
    {
        return turnoDAO.buscar(hora,dia,mes,año);
    }

    public List<Turno> listar()
    {
        return turnoDAO.listar();
    }
}
