/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Class Log.
 */
public class Log {
	
	/**
	 * The id log.
	 */
	private int idLog;
	
	/**
	 * The fecha hora.
	 */
	private LocalDateTime fechaHora;
	
	/**
	 * The usuario.
	 */
	private String usuario;
	
	/**
	 * The tipo evento.
	 */
	private String tipoEvento;
	
	/**
	 * The mensaje.
	 */
	private String mensaje;

	/**
	 * Instantiates a new log.
	 */
	// Constructor vacío
	public Log() {
	}

	/**
	 * Instantiates a new log.
	 *
	 * @param idLog      the id log
	 * @param usuario    the usuario
	 * @param tipoEvento the tipo evento
	 * @param mensaje    the mensaje
	 * @param fechaHora  the fecha hora
	 */
	// Constructor con ID para carga desde SQL
	public Log(int idLog, String usuario, String tipoEvento, String mensaje, LocalDateTime fechaHora) {
		this.idLog = idLog;
		this.usuario = usuario;
		this.tipoEvento = tipoEvento;
		this.mensaje = mensaje;
		this.fechaHora = fechaHora;
	}

	/**
	 * Instantiates a new log.
	 *
	 * @param fechaHora  the fecha hora
	 * @param usuario    the usuario
	 * @param tipoEvento the tipo evento
	 * @param mensaje    the mensaje
	 */
	// Constructor completo (sin ID si se autogenera en la BD)
	public Log(LocalDateTime fechaHora, String usuario, String tipoEvento, String mensaje) {
		this.fechaHora = fechaHora;
		this.usuario = usuario;
		this.tipoEvento = tipoEvento;
		this.mensaje = mensaje;
	}

	/**
	 * Gets the id log.
	 *
	 * @return the id log
	 */
	// Getters y setters
	public int getIdLog() {
		return idLog;
	}

	/**
	 * Sets the id log.
	 *
	 * @param idLog the new id log
	 */
	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fecha hora
	 */
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora the new fecha hora
	 */
	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the tipo evento.
	 *
	 * @return the tipo evento
	 */
	public String getTipoEvento() {
		return tipoEvento;
	}

	/**
	 * Sets the tipo evento.
	 *
	 * @param tipoEvento the new tipo evento
	 */
	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	// Formato de visualización
	@Override
	public String toString() {
		return fechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " - " + usuario + " - "
				+ tipoEvento.toUpperCase() + " - " + mensaje;
	}
}
