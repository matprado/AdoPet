package backend;

/**
 * Esta classe representa um par de dois tipos genericos que são um inteiro ID e uma string mensagem;
 * @authors Mateus Prado, Mateus Tomieiro, Victor Reis, Matheus Rigato
 *
 * @param <ID> - Inteiro ID que e uma chave;
 * @param <MENSAGEM> - String Mensagem que e um valor;
 */
public class Pair<ID,MENSAGEM>{
	
	private ID id;
	private MENSAGEM mensagem;
	
	/**
	 * Construtor da classe Pair
	 * @param id - Id
	 * @param msg - mensagem
	 */
	public Pair(ID id, MENSAGEM msg){
		this.id = id;
		this.mensagem = msg;
	}

	/**
	 * Getter para ID;
	 * @return - o ID;
	 */
	public ID getId() {
		return id;
	}

	/**
	 * Setter para o ID;
	 * @param id - o novo ID;
	 */
	public void setId(ID id) {
		this.id = id;
	}
	
	/**
	 * Getter para mensagem;
	 * @return - mensagem;
	 */
	public MENSAGEM getMensagem() {
		return mensagem;
	}
	
	/**
	 * Setter para mensagem;
	 * @param mensagem - a nova mensagem;
	 */
	public void setMensagem(MENSAGEM mensagem) {
		this.mensagem = mensagem;
	}
	
	
	
	
	
	
	
	
	

}
