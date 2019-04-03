import java.util.ArrayList;
import java.util.List;

public class Minicurso {

	private List<Participante> participantes = new ArrayList<Participante>();
	private int maxParticipantes;
	private String tituloMinicurso;

	public Minicurso(String titulo, int maxParticipantes){
		this.tituloMinicurso = titulo;
		this.maxParticipantes = maxParticipantes;
	}

	public List<Participante> getParticipantes(){
		return this.participantes;
	}

	public void adicionarParticipante(Participante part){
		if (participantes.size() < maxParticipantes){
			this.participantes.add(part);	
		}
	}

	public int getMaxParticipantes(){
		return this.maxParticipantes;
	}

	public String getTitulo(){
		return this.tituloMinicurso;
	}

	public String toStringSalvarArquivo(){
		String montagem = this.tituloMinicurso + "@" + this.maxParticipantes + "@" + this.participantes.size() + "@";
		for (Participante p: participantes){
			montagem += p.getEmail() + "@";
		}
		return montagem;
	}
}
	