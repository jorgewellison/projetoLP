import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; ///extends Exception
import java.util.ArrayList;
import java.util.List;

public class GravadorDeParticipantes {

	/**
	 * Recupera o texto que está no arquivo chamado "nomeArquivo" e retorna o
	 * texto como uma lista de Strings, onde cada elemento da lista é uma linha
	 * do arquivo.
	 * 
	 * @param nomeArquivo
	 *            É o nome do arquivo a ser lido.
	 * @return uma lista de Strings com o conteúdo do arquivo lido.
	 * @throws IOException
	 *             se houver algum problema na leitura.
	 */
	public List<Participante> recuperarParticipantesDoArquivo(String nomeArquivo) throws IOException { //função de recuperar os dados do arquivo
		BufferedReader leitor = null;
		List<Participante> participantes = new ArrayList<Participante>();
		try {
			leitor = new BufferedReader(new FileReader(nomeArquivo));   //ele lê o arquivo e coloca na variavell leitor
			String texto = null;										//seta a variavel texto como null
			do {
				texto = leitor.readLine();			// pega cada linha do arquivo e le, colocando na variavel texto
				if (texto != null) {
					String[] informacao = texto.split("#");					//ele pega a linha e separa pelo #
					Endereco end = new Endereco();
					end.setLogradouro(informacao[3]);
					end.setNumero(Integer.parseInt(informacao[4]));
					end.setCidade(informacao[5]);
					end.setEstado(informacao[6]);
					Participante participante = new Participante(informacao[0], informacao[1], informacao[2], end);
					participantes.add(participante);
				}
			} while (texto != null); // será null ao chegar no fim do arquivo
		} finally { // se o leitor não tá nulo então feche
			if (leitor != null) {
				leitor.close(); // ocorre fechamento do stream de leitura, para poupar memória
			}
		}
		return participantes;
	}

	/**
	 * Grava um certo texto passado como parâmetro em um arquivo.
	 * 
	 * @param texto
	 *            Uma lista de Strings que serão gravadas no arquivo uma por
	 *            linha.
	 * 
	 * @param nomeArquivo
	 *            O nome do arquivo onde vou gravar o texto
	 * @throws IOException
	 *             se houver algum problema na gravação.
	 */
	public void gravarParticipanteNoArquivo(List<Participante> participantes, String nomeArquivo) throws IOException {
		BufferedWriter gravador = null;
		try {					//função recebe uma lista e percorre ela, adicionando simultanemante no arquivo
			gravador = new BufferedWriter(new FileWriter(nomeArquivo));
			for (Participante p : participantes) {
				gravador.write(p.toStringSalvarArquivo() + "\n"); 		//escreve no arquivo
			}
		} finally {
			if (gravador != null) {
				gravador.close(); // fechamendo
			}
		}
	}

	public void gravarMinicursoNoArquivo(List<Minicurso> minicursos, String nomeArquivo) throws IOException {
		BufferedWriter gravador = null;
		try {
			gravador = new BufferedWriter(new FileWriter(nomeArquivo)); //semelhante a gravarParticipanteNoArquivo
			for (Minicurso m : minicursos) {
				gravador.write(m.toStringSalvarArquivo() + "\n");
			}
		} finally {
			if (gravador != null) {
				gravador.close(); // fechamento
			}
		}
	}

	public List<Minicurso> recuperarMinicursoDoArquivo(String nomeArquivo, SistemaInscricoes sistema)
			throws IOException {
		BufferedReader leitor = null;
		List<Minicurso> minicursos = new ArrayList<Minicurso>();
		try {
			leitor = new BufferedReader(new FileReader(nomeArquivo));				//semelhante a recuperaParticipante
			String texto = null;
			do {
				texto = leitor.readLine();
				if (texto != null) {
					String[] informacao = texto.split("@");
					Minicurso m = new Minicurso(informacao[0], Integer.parseInt(informacao[1]));
					try {
						sistema.cadastraMinicurso(m);
					} catch (MinicursoJaExisteException e) {
						System.out.println(e);
					}
					int tamanhoList = Integer.parseInt(informacao[2]);
					int indexCursor = 3;
					for (int i = 0; i < tamanhoList; i++) {
						try {
							sistema.inscreveParticipanteEmMinicurso(informacao[indexCursor++], m.getTitulo());
						} catch (ParticipanteNaoExisteException e) {
							System.out.println(e);
						} catch (MinicursoNaoExisteException e) {
							System.out.println(e);
						}
					}
				}
			} while (texto != null); // vai ser null quando chegar no fim do
										// arquivo
		} finally {
			if (leitor != null) {
				leitor.close(); // fechamento
			}
		}
		return minicursos;
	}
}
