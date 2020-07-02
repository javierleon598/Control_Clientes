package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.*;
import model.ClienteDTO;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletControlador() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");
		
		if (accion != null) {
			if (accion.equals("editar")) {
				this.editarCliente(request, response);
			} else if (accion.equals("eliminar")) {
				this.eliminarCliente(request, response);
			} else {
				this.accionDefault(request, response);
			}
		} else {
			this.accionDefault(request, response);
		}

	}

	private void accionDefault(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<ClienteDTO> clientes = new ClienteDaoImpl().select();
			System.out.println("Clientes:" + clientes);
			/**
			request.setAttribute("clientes", clientes);
			request.setAttribute("totalClientes", clientes.size());
			request.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));			
			request.getRequestDispatcher("clientes.jsp").forward(request, response);
			*/
			HttpSession session = request.getSession();
			session.setAttribute("clientes", clientes);
			session.setAttribute("totalClientes", clientes.size());
			session.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
			response.sendRedirect("clientes.jsp");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private double calcularSaldoTotal(List<ClienteDTO> clientes) {
		double saldoTotal = 0;

		for (ClienteDTO clienteDTO : clientes) {
			saldoTotal += clienteDTO.getSaldo();
		}
		return saldoTotal;
	}
	
	private void editarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		
		Connection conn = null;
		try {
			conn = Conexion.getConnection();

			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}

			ClienteDao clienteDao = new ClienteDaoImpl(conn);
			
			// Creamos el objeto de cliente (modelo)
			ClienteDTO cliente = new ClienteDTO(idCliente);
			
			// update el objeto a la BD
			cliente = clienteDao.search(cliente);
			
			conn.commit();
			System.out.println("Se ha hecho el commit de la Transaccion");
			
			request.setAttribute("cliente", cliente);
			request.getRequestDispatcher("/WEB-INF/paginas/cliente/editarCliente.jsp").forward(request, response);
			

		} catch (SQLException e) {
			e.printStackTrace(System.out);
			System.out.println("Entramos al rollback");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace(System.out);
			}
		}
	}
	
	private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recuperamos los valores del formulario editarCliente
		
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));

		Connection conn = null;
		try {
			//Se crea la conexion
			conn = Conexion.getConnection();

			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}

			ClienteDao clienteDao = new ClienteDaoImpl(conn);
			
			// Creamos el objeto de cliente (modelo)
			ClienteDTO cliente = new ClienteDTO(idCliente);
			
			// modificar el objeto a la BD
			int registrosInsertados = clienteDao.delete(cliente);
			
			System.out.println("Registro eliminados = " + registrosInsertados);

			conn.commit();
			System.out.println("Se ha hecho el commit de la Transaccion");
			
			//Redirigimos a la accion por default
			this.accionDefault(request, response);
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			System.out.println("Entramos al rollback");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace(System.out);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");

		if (accion != null) {
			if (accion.equals("insertar")) {
				this.insertarCliente(request, response);
			} else if (accion.equals("modificar")) {
				this.modificarCliente(request, response);
			} else {
				this.accionDefault(request, response);
			}
		} else {
			this.accionDefault(request, response);
		}
	}

	private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		double saldo = Double.parseDouble(request.getParameter("saldo"));

		Connection conn = null;
		try {
			conn = Conexion.getConnection();

			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}

			ClienteDao clienteDao = new ClienteDaoImpl(conn);
			
			// Creamos el objeto de cliente (modelo)
			ClienteDTO cliente = new ClienteDTO(nombre, apellido, email, telefono, saldo);
			
			// insertar el objeto a la BD
			int registrosInsertados = clienteDao.insert(cliente);
			
			System.out.println("Registro modificados = " + registrosInsertados);

			conn.commit();
			System.out.println("Se ha hecho el commit de la Transaccion");
			
			//Redirigimos a la accion por default
			this.accionDefault(request, response);
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			System.out.println("Entramos al rollback");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace(System.out);
			}
		}

	}

	
	
	private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recuperamos los valores del formulario editarCliente
		
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		double saldo = Double.parseDouble(request.getParameter("saldo"));

		Connection conn = null;
		try {
			//Se crea la conexion
			conn = Conexion.getConnection();

			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}

			ClienteDao clienteDao = new ClienteDaoImpl(conn);
			
			// Creamos el objeto de cliente (modelo)
			ClienteDTO cliente = new ClienteDTO(idCliente, nombre, apellido, email, telefono, saldo);
			
			// modificar el objeto a la BD
			int registrosInsertados = clienteDao.update(cliente);
			
			System.out.println("Registro modificados = " + registrosInsertados);

			conn.commit();
			System.out.println("Se ha hecho el commit de la Transaccion");
			
			//Redirigimos a la accion por default
			this.accionDefault(request, response);
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			System.out.println("Entramos al rollback");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace(System.out);
			}
		}

	}
	
	

}
