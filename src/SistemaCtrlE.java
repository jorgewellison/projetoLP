import java.util.List;

import javax.swing.JOptionPane;

public class SistemaCtrlE {

	public static void main(String[] args) throws Exception{
		
		boolean verificador = true;
		int opcao = 0;
		String nomeArquivo = "Participantes inscritos.txt";
		String nomeArquivoMinicurso = "Minicursos.txt";
		
		SistemaInscricoes ctrlE = new SistemaInscricoesList();
		GravadorDeParticipantes gravador = new GravadorDeParticipantes();
		List <Participante> participantesArquivo = gravador.recuperarParticipantesDoArquivo(nomeArquivo);
		
		for(Participante i: participantesArquivo){
			ctrlE.cadastraParticipante(i);
		}
		gravador.recuperarMinicursoDoArquivo(nomeArquivoMinicurso, ctrlE);
		while(verificador == true){
			String texto = "<html>***MENU***<br>"  
					 +"1* Cadastrar participante <br>"  
					 +"2* Remover participante<br>"
					 +"3* Pesquisar participante por instituição<br>"
					 +"4* Pesquisar participante por estado<br>"
					 +"5* Pesquisar participante (sem restrições)<br>"
					 +"6* Cadastrar participante em minicurso<br>"
					 +"7* Listar todos os participantes<br>"
					 +"8* Pesquisar participante por minicurso<br>"
					 +"9* Cadastrar minicurso<br>"
					 +"10* Pesquisar minicurso<br>"
					 +"***OU PRESSIONE QUALQUER OUTRA TECLA PARA SAIR***<br>";
			
			JOptionPane.showMessageDialog(null, texto);
			
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite a opção: "));
			
			switch(opcao){
			case 1: //case para cadastrar participante no sistema
				
				String nome = JOptionPane.showInputDialog("Digite o nome do participante");
				String email = JOptionPane.showInputDialog("Digite o email do participante");
				String instituicao = JOptionPane.showInputDialog("Digite o nome da instituição");
				
				String logradouro = JOptionPane.showInputDialog("Digite o logradouro");
				int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o número"));
				String cidade = JOptionPane.showInputDialog("Digite o nome da cidade");
				String estado = JOptionPane.showInputDialog("Digite o estado");
				
				Endereco end = new Endereco(logradouro, numero, cidade, estado);
		
				Participante part = new Participante(nome, email, instituicao, end);
				try {
					ctrlE.cadastraParticipante(part);
				} catch (ParticipanteJaExisteException e) {
					System.out.println(e);
				}
				
				break;
			
			case 2: //case para remover participante
				
				String emailPart = JOptionPane.showInputDialog("Digite o email do participante que deseja remover");
				try {
					ctrlE.removeParticipante(emailPart);
				} catch (ParticipanteNaoExisteException e) {
					System.out.println(e);
				}
				
				break;
			
			case 3: //case para pesquisar estudantes da instituição				
				
				String nomeInstituicao = JOptionPane.showInputDialog("Digite o nome da instituição");
				List<Participante> listaDeParticipantes = ctrlE.pesquisaParticipantesDaInstituicao(nomeInstituicao);
				for (Participante p: listaDeParticipantes){
					System.out.println(p.getNome());
				}
				break;
			case 4: //pesquisar participantes do estado
				String partDoEstado = JOptionPane.showInputDialog("Digite o nome do estado");
				List<Participante> listaDePartDoEstado = ctrlE.pesquisaParticipantesDoEstado(partDoEstado);
				for (Participante p: listaDePartDoEstado){
					System.out.println(p.getNome());
				}
				break;
				
			case 5: //pesquisar participante
				String pesqParticipante = JOptionPane.showInputDialog("Digite o email do participante");
				Participante participante = null;
				try {
					participante = ctrlE.pesquisaParticipante(pesqParticipante);
				} catch (ParticipanteNaoExisteException e) {
					System.out.println(e);
				}
				System.out.println("Nome do participante: "+participante.getNome()+" \nEmail do participante: "+participante.getEmail());
				break;
			
			case 6: //adicionar participante ao minicurso
				String addPartMinicurso = JOptionPane.showInputDialog("Digite o nome do participante que deseja adicionar");
				String nomeMinicurso = JOptionPane.showInputDialog("Digite o nome do minicurso que deseja se inscrever");
				try {
					ctrlE.inscreveParticipanteEmMinicurso(addPartMinicurso, nomeMinicurso);
				} catch (ParticipanteNaoExisteException | MinicursoNaoExisteException e) {
					System.out.println(e);
				}
				break;
			
			case 7: //imprimir todos os participantes
				List<Participante> todosPart = ctrlE.getListaTotalDeParticipantes();
				for(Participante p: todosPart){
					System.out.println("Nome do participante: " + p.getNome());
				}
				break;
			
			case 8: //pesquisar participantes por minicurso 
				String partMinicurso = JOptionPane.showInputDialog("Digite o nome do minicurso");
				List<Participante> participantes = null;
				try {
					participantes = ctrlE.getParticipantesDoMinicurso(partMinicurso);
				} catch (MinicursoNaoExisteException e) {
					System.out.println(e);
				}
				for(Participante p: participantes){
					System.out.println(p.getNome());
				}
				break;
			case 9: //cadastra minicurso
				String tituloMinicurso = JOptionPane.showInputDialog("Digite o título do minicurso");
				int max = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade máxima de pessoas por minicurso"));
				Minicurso curso = new Minicurso(tituloMinicurso, max);
				try {
					ctrlE.cadastraMinicurso(curso);
				} catch (MinicursoJaExisteException e) {
					System.out.println(e);
				}
				break;
			case 10: //pesquisa minicurso
				String pesqMinicurso = JOptionPane.showInputDialog("Digite o minicurso que deseja pesquisar");
				try {
					ctrlE.pesquisaMinicurso(pesqMinicurso);
				} catch (MinicursoNaoExisteException e) {
					System.out.println(e);
				}
				List<Participante> participanteDoMinicurso = ctrlE.getParticipantesDoMinicurso(pesqMinicurso);
				System.out.println("Membros do minicurso de " + pesqMinicurso + ":" );
				for(Participante p: participanteDoMinicurso){
					System.out.println(p.getNome());
				}
				break;
				
			default:
				verificador = false;
				gravador.gravarParticipanteNoArquivo(ctrlE.getListaTotalDeParticipantes(), nomeArquivo);
				gravador.gravarMinicursoNoArquivo(ctrlE.getMinicursos(), nomeArquivoMinicurso);
			}
		}
	}
}