import java.util.ArrayList;
import java.util.List;

public class SistemaInscricoesList implements SistemaInscricoes {

	private List<Participante> participantes; //= new ArrayList<Participante>();
	private List<Minicurso> minicursos; //= new ArrayList<Minicurso>();
	
	public SistemaInscricoesList(){
		this.participantes = new ArrayList<Participante>();
		this.minicursos = new ArrayList<Minicurso>();
	}
	public List<Minicurso> getMinicursos(){
		return this.minicursos;
	}
	public void cadastraParticipante(Participante p) throws ParticipanteJaExisteException {
		for (Participante part: participantes){
			if (p.getEmail().equalsIgnoreCase(part.getEmail())){
				throw new ParticipanteJaExisteException("Participante já cadastrado no sistema");
			}
		}
		participantes.add(p);
		
	}

	public void cadastraMinicurso(Minicurso curso) throws MinicursoJaExisteException {
		for(Minicurso m : minicursos){
			if(curso.getTitulo().equalsIgnoreCase(m.getTitulo())){
				throw new MinicursoJaExisteException("Minicurso já cadastrado no sistema");
			}
		}
		minicursos.add(curso);
	}
	
	@Override
	public void removeParticipante(String emailParticipante) throws ParticipanteNaoExisteException {
		
		boolean verificacao = false;
		for(Participante p : participantes){
			if (p.getEmail().equals(emailParticipante)){
				participantes.remove(p);
				verificacao = true;
				break;
			}
		}
		if (verificacao == true){
			System.out.println("O participante foi removido com sucesso");
		}else{
			throw new ParticipanteNaoExisteException("Participante não existente");
		}
	}

	@Override
	public List<Participante> pesquisaParticipantesDaInstituicao(String nomeInstituicao) {
		ArrayList<Participante> listaDeParticipantes = new ArrayList<Participante>();
		for (Participante p : participantes){
			if(p.getInstituicao().equalsIgnoreCase(nomeInstituicao)){
				listaDeParticipantes.add(p);
			}
		}
		return listaDeParticipantes;
	}

	@Override
	public List<Participante> pesquisaParticipantesDoEstado(String estado) {
		List<Participante> listaDePartEstado = new ArrayList<Participante>();
		for (Participante p: participantes){
			if(p.getEndereco().getEstado().equalsIgnoreCase(estado)){
				listaDePartEstado.add(p);
			}
		}
		return listaDePartEstado;
	}

	@Override
	public Participante pesquisaParticipante(String emailParticipante) throws ParticipanteNaoExisteException {
		for (int i = 0; i<participantes.size(); i++){
			if(participantes.get(i).getEmail().equalsIgnoreCase(emailParticipante)){
				return participantes.get(i);
			}
		}
		throw new ParticipanteNaoExisteException("Participante não existente");
	}

	@Override
	public void inscreveParticipanteEmMinicurso(String emailParticipante, String tituloMinicurso) throws ParticipanteNaoExisteException, MinicursoNaoExisteException {
		
		Minicurso miniCurso = null;
		Participante part = pesquisaParticipante(emailParticipante);
		
		for(Minicurso m:minicursos){
			if(m.getTitulo().equalsIgnoreCase(tituloMinicurso)){
				miniCurso = m;
				m.adicionarParticipante(part);
				break;
			}
		}
		if(miniCurso == null){
			throw new MinicursoNaoExisteException("Minicurso não existente");
		}
	}
		

	@Override
	public List<Participante> getListaTotalDeParticipantes() {
		return this.participantes;
	}

	@Override
	public List<Participante> getParticipantesDoMinicurso(String tituloMinicurso) throws MinicursoNaoExisteException {
		for(Minicurso m : minicursos){
			if(m.getTitulo().equals(tituloMinicurso)){
				return m.getParticipantes(); 
				
			}
		}
		throw new MinicursoNaoExisteException("Minicurso não existente");
	}

	@Override
	public Minicurso pesquisaMinicurso(String titulo) throws MinicursoNaoExisteException {
		for (Minicurso m : minicursos){
			if (m.getTitulo().equalsIgnoreCase(titulo)){
				return m;
			}
		}
		throw new MinicursoNaoExisteException("Minicurso não existente");
	}


	
		
}
	
