
public class Endereco {
	private String logradouro;
	private int numero;
	private String cidade;
	private String estado;
	
	public Endereco(){
		this.logradouro = "";
		this.numero = 0;
		this.cidade = "";
		this.estado = "";
	}
	
	public Endereco(String logradouro, int numero, String cidade, String estado){
		this.logradouro = logradouro;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
	
	}

	public String toString(){
		return this.logradouro+" ,"+this.numero+" ,"+this.cidade+" ,"+this.estado;
	}
	
	public String getLogradouro(){
		return this.logradouro;
	}

	public void setLogradouro(String logradouro){
		this.logradouro =  logradouro;
	}

	public int getNumero(){
		return this.numero;
	}

	public void setNumero(int numero){
		this.numero = numero;
	}

	public String getCidade(){
		return this.cidade;
	}

	public void setCidade(String cidade){
		this.cidade = cidade;
	}
	
	public String getEstado(){
		return this.estado;
	}

	public void setEstado(String estado){
		this.estado = estado;
	}

}
