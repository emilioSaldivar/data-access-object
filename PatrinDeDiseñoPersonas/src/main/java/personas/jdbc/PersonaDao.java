package personas.jdbc;

import java.sql.SQLException;
import java.util.List;
import personas.dto.PersonaDTO;

public interface PersonaDao {

    public int insertar(PersonaDTO persona) throws SQLException;

    public int actualizar(PersonaDTO persona) throws SQLException;

    public int borrar(PersonaDTO persona) throws SQLException;

    public List<PersonaDTO> seleccionar() throws SQLException;

}
