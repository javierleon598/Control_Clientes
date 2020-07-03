package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ClienteDTO;

public class ClienteDaoImpl implements ClienteDao {

	private static final String SQL_SELECT = "select * from clientes";
	private static final String SQL_SELECT_BY_ID = "select * from clientes where id_cliente=?";
	private static final String SQL_INSERT = "insert into clientes (nombre, apellido, email, telefono, saldo) values (?,?,?,?,?)";
	private static final String SQL_UPDATE = "update clientes set nombre=?, apellido=?, email=?, telefono=?, saldo=? where id_cliente=?";
	private static final String SQL_DELETE = "DELETE FROM clientes where id_cliente=?";

	private Connection conexionTransaccional;

	public ClienteDaoImpl() {
	}

	public ClienteDaoImpl(Connection conexionTransaccional) {
		this.conexionTransaccional = conexionTransaccional;
	}

	@Override
	public int insert(ClienteDTO cliente) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {
			conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getApellido());
			stmt.setString(3, cliente.getEmail());
			stmt.setInt(4, cliente.getTelefono());
			stmt.setDouble(5, cliente.getSaldo());

			System.out.println("Ejecutando Query: " + SQL_INSERT);

			rows = stmt.executeUpdate();

			System.out.println("Registros insertados " + rows);
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			Conexion.close(stmt);
			if (this.conexionTransaccional == null) {
				Conexion.close(conn);
			}
		}
		return rows;
	}

	@Override
	public int update(ClienteDTO cliente) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {
			conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getApellido());
			stmt.setString(3, cliente.getEmail());
			stmt.setInt(4, cliente.getTelefono());
			stmt.setDouble(5, cliente.getSaldo());
			stmt.setInt(6, cliente.getIdCliente());

			System.out.println("Ejecutando Query: " + SQL_UPDATE);

			rows = stmt.executeUpdate();

			System.out.println("Registros actualizados " + rows);
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			Conexion.close(stmt);
			if (this.conexionTransaccional == null) {
				Conexion.close(conn);
			}
		}
		return rows;
	}

	@Override
	public int delete(ClienteDTO cliente) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {
			conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, cliente.getIdCliente());

			System.out.println("Ejecutando Query: " + SQL_DELETE);

			rows = stmt.executeUpdate();

			System.out.println("Registros eliminados " + rows);
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			Conexion.close(stmt);
			if (this.conexionTransaccional == null) {
				Conexion.close(conn);
			}
		}
		return rows;
	}

	@Override
	public ClienteDTO search(ClienteDTO cliente) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, cliente.getIdCliente());
			rs = stmt.executeQuery();
			
			//if (rs.next()) {
			rs.absolute(1);//se posiciona en el primer registro devuelto

			int idCliente = rs.getInt("id_cliente");
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			String email = rs.getString("email");
			int telefono = rs.getInt("telefono");
			double saldo = rs.getDouble("saldo");

			cliente = new ClienteDTO(idCliente, nombre, apellido, email, telefono, saldo);
 
			//}
			System.out.println("Ejecutando Query: " + SQL_SELECT_BY_ID);
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			Conexion.close(stmt);
			if (this.conexionTransaccional == null) {
				Conexion.close(conn);
			}
		}
		return cliente;
	}

	@Override
	public List<ClienteDTO> select() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ClienteDTO cliente = null;
		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();

		try {
			conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int idCliente = rs.getInt("id_cliente");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				int telefono = rs.getInt("telefono");
				double saldo = rs.getDouble("saldo");

				cliente = new ClienteDTO(idCliente, nombre, apellido, email, telefono, saldo);
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);	
		} finally {
			Conexion.close(rs);
			Conexion.close(stmt);
			if (this.conexionTransaccional == null) {
				Conexion.close(conn);
			}
		}
		return clientes;
	}

}
