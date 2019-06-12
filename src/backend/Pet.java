package backend;

import javafx.scene.image.Image;

/*
 * Essa classe representa um pet em adoção;
 * */
public class Pet {
	private long petID;
	private String especie;
	private String nome;
	private String sexo;
	private String detalhes;
	private Usuario anunciante;
	private long anuncianteID;
	
	
	public long getAnuncianteID() {
		return anuncianteID;
	}


	public void setAnuncianteID(long anuncianteID) {
		this.anuncianteID = anuncianteID;
	}


	private Image icone;
	
	/**
	 * Construtor da classe
	 * @param petID - long que representa o id único do pet;
	 * @param especie - String que representa a especie do pet;
	 * @param nome - String que representa o nome do pet;
	 * @param sexo - String que representa o sexo do pet;
	 * @param detalhes - String que contém detalhes adicionais do pet;
	 * @param anunciante - Usuario 
	 */
	public Pet(long petID, String especie, String nome, String sexo, String detalhes, Usuario anunciante, Image icone) {
		this.petID = petID;
		this.especie = especie;
		this.nome = nome;
		this.sexo = sexo;
		this.detalhes = detalhes;
		this.anunciante = anunciante;
		this.icone = icone;
	}
	
	
	public Pet() {
		;
	}


	public Image getIcone() {
		return this.icone;
	}


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


	public void setPetID(long petID) {
		this.petID = petID;
	}


	public void setEspecie(String especie) {
		this.especie = especie;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public void setAnunciante(Usuario anunciante) {
		this.anunciante = anunciante;
	}

	
	
}
