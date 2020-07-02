package model;

public class ClienteDTO {

	private int idCliente;
	private String nombre;
	private String apellido;
	private String email;
	private int telefono;
	private double saldo;
	
	public ClienteDTO() {
	}

	public ClienteDTO(int idCliente) {		
		this.idCliente = idCliente;
	}

	public ClienteDTO(String nombre, String apellido, String email, int telefono, double saldo) {		
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.saldo = saldo;
	}

	public ClienteDTO(int idCliente, String nombre, String apellido, String email, int telefono, double saldo) {		
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.saldo = saldo;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "ClienteDTO [idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", email="
				+ email + ", telefono=" + telefono + ", saldo=" + saldo + "]";
	}
		
}
