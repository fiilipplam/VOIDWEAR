package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
	private int idLog;
	private LocalDateTime fechaHora;
	private String usuario;
	private String tipoEvento;
	private String mensaje;

	// Constructor vacío
	public Log() {
	}

	// Constructor con ID para carga desde SQL
	public Log(int idLog, String usuario, String tipoEvento, String mensaje, LocalDateTime fechaHora) {
		this.idLog = idLog;
		this.usuario = usuario;
		this.tipoEvento = tipoEvento;
		this.mensaje = mensaje;
		this.fechaHora = fechaHora;
	}

	// Constructor completo (sin ID si se autogenera en la BD)
	public Log(LocalDateTime fechaHora, String usuario, String tipoEvento, String mensaje) {
		this.fechaHora = fechaHora;
		this.usuario = usuario;
		this.tipoEvento = tipoEvento;
		this.mensaje = mensaje;
	}

	// Getters y setters
	public int getIdLog() {
		return idLog;
	}

	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	// Formato de visualización
	@Override
	public String toString() {
		return fechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " - " + usuario + " - "
				+ tipoEvento.toUpperCase() + " - " + mensaje;
	}
}
