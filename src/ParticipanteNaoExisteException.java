public class ParticipanteNaoExisteException extends Exception {
	/**
	 * Constante representando o identificador da versão desta classe de exceção.
	 */
	private static final long serialVersionUID = 1L;

	public ParticipanteNaoExisteException(String msg){
		super(msg);
	}
}