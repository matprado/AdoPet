package backend;

import javafx.scene.image.Image;

/**
 * Essa classe representa um pet em adocao;
 * @authors Mateus Prado, Mateus Tomieiro, Victor Reis, Matheus Rigato
 *
 */
public class Pet {
	
	//Atributos do pet:
	private int petID;
	private String especie;
	private String nome;
	private String sexo;
	private String detalhes;
	private Usuario anunciante;
	private long anuncianteID;
	private Image icone;
	
	/**
	 * Getter para o ID do anunciante;
	 * @return - O ID do anunciante;
	 */
	public long getAnuncianteID() {
		return anuncianteID;
	}

	/**
	 * Setter para o ID do anunciante;
	 * @param anuncianteID - Novo valor do ID do anunciante;
	 */
	public void setAnuncianteID(long anuncianteID) {
		this.anuncianteID = anuncianteID;
	}

	/**
	 * Construtor da classe
	 * @param petID - long que representa o id unico do pet;
	 * @param especie - String que representa a especie do pet;
	 * @param nome - String que representa o nome do pet;
	 * @param sexo - String que representa o sexo do pet;
	 * @param detalhes - String que contem detalhes adicionais do pet;
	 * @param anunciante - Usuario 
	 */
	
	/**
	 * Construtor vazio
	 */
	public Pet() {
		;
	}


	/**
	 * Getter para o atributo de icone do pet;
	 * @return - Image com o icone do pet;
	 */
	public Image getIcone() {
		return this.icone;
	}

	/**
	 * Setter para o atributo de icone do pet;
	 * @param icone - Image com icone;
	 */
	public void setIcone(Image icone) {
		this.icone = icone;
	}


	/**
	 * Getter para o atributo de ID do pet;
	 * @return - long com o petID;
	 */
	public long getPetID() {
		return petID;
	}

	/**
	 * Getter para o atributo de especie;
	 * @return - String com a especie do pet;
	 */
	public String getEspecie() {
		return especie;
	}

	/**
	 * Getter para o atributo de nome do pet;
	 * @return - String com o nome do pet;
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Getter para o atributo de sexo do pet;
	 * @return - String com o sexo do pet;
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Getter para o atributo de detalhes adicionais;
	 * @return - String com os detalhes do pet;
	 */
	public String getDetalhes() {
		return detalhes;
	}
	
	/**
	 * Setter para o atributo de detalhes adicionais;
	 * @param nome - String com os detalhes do pet;
	 */
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	/**
	 * Getter para o atributo de anunciante;
	 * @return - String com o anunciante do pet;
	 */
	public Usuario getAnunciante() {
		return anunciante;
	}

	/**
	 * Setter para o atributo de id do pet;
	 * @param petID - Inteiro com o id do pet;
	 */
	public void setPetID(int petID) {
		this.petID = petID;
	}

	/**
	 * Setter para o atributo de especie do pet;
	 * @param especie - String com a especie do pet;
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	
	/**
	 * Setter para o atributo de nome do pet;
	 * @param nome - String com o nome do pet;
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	/**
	 * Setter para o atributo de sexo do pet;
	 * @param sexo - String com o sexo do pet;
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	
	/**
	 * Setter para o atributo de anunciante do pet;
	 * @param anunciante - Usuario que esta anunciando o pet;
	 */
	public void setAnunciante(Usuario anunciante) {
		this.anunciante = anunciante;
	}

	
	
}
