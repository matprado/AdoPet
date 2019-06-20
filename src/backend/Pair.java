package backend;

public class Pair<ID,MENSAGEM>{
	
	private ID id;
	private MENSAGEM mensagem;
	
	public Pair(ID id, MENSAGEM msg){
		this.id = id;
		this.mensagem = msg;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public MENSAGEM getMensagem() {
		return mensagem;
	}

	public void setMensagem(MENSAGEM mensagem) {
		this.mensagem = mensagem;
	}
	
	
	
	
	
	
	
	
	

}
