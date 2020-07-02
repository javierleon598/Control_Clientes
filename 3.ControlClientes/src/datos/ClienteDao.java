package datos;

import java.sql.SQLException;
import java.util.List;

import model.ClienteDTO;

public interface ClienteDao {
	
	public abstract int insert(ClienteDTO cliente) throws SQLException;
	
	public int update(ClienteDTO cliente) throws SQLException;
	
	public int delete(ClienteDTO cliente) throws SQLException;
	
	public ClienteDTO search(ClienteDTO cliente) throws SQLException;
	
	public List<ClienteDTO> select() throws SQLException;	

}
