import java.util.List;

public interface SistemaInscricoes {


		public void cadastraParticipante(Participante pessoa) throws ParticipanteJaExisteException;
		public void removeParticipante(String nomeParticipante) throws ParticipanteNaoExisteException;
		public List<Participante> pesquisaParticipantesDaInstituicao(String nomeInstituicao);
		public List<Participante> pesquisaParticipantesDoEstado(String estado);
		public Participante pesquisaParticipante(String email) throws ParticipanteNaoExisteException;
		public void inscreveParticipanteEmMinicurso(String emailParticipante, String tituloMinicurso) throws ParticipanteNaoExisteException, MinicursoNaoExisteException;
		public List<Participante> getListaTotalDeParticipantes();
		public List<Participante> getParticipantesDoMinicurso(String tituloMinicurso) throws MinicursoNaoExisteException;
		public void cadastraMinicurso(Minicurso m) throws MinicursoJaExisteException;
		public Minicurso pesquisaMinicurso(String titulo) throws MinicursoNaoExisteException;
		public List<Minicurso> getMinicursos();
	
}
