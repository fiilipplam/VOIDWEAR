package modelo;

public class Empleado {
	private int idEmpleado;
	private int idUsuario;

	public Empleado() {
	}

	public Empleado(int idEmpleado, int idUsuario) {
		this.idEmpleado = idEmpleado;
		this.idUsuario = idUsuario;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
