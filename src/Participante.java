
public class Participante {

	private String nome;
	private String email;
	private String instituicao;
	private Endereco endereco;

	public Participante(){
		this("", "", "", new Endereco());
	}
	
	public Participante(String nome, String email, String instituicao, Endereco endereco){
		this.nome = nome;
		this.email = email;
		this.instituicao = instituicao;
		this.endereco = endereco; 
		
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}

	public String getEmail(){
		return this.email;
	}

	public String getInstituicao(){
		return this.instituicao;
	}
	
	public void setInstituicao(String instituicao ){
		this.instituicao = instituicao;
	}

	public Endereco getEndereco(){
		return this.endereco;
	}

	public void setEndereco(Endereco endereco){
		this.endereco = endereco;
	}

	public String toStringSalvarArquivo(){
		String montagem = this.nome + "#" + this.email + "#" + this.instituicao + "#" + this.endereco.getLogradouro() + "#" +
		this.endereco.getNumero() + "#" + this.endereco.getCidade() + "#" + this.endereco.getEstado()+"#";
		return montagem;
	}
}
