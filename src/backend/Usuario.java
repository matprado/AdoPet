package backend;

/*
 * Essa classe representa um usuário do programa;
 * */
public class Usuario {
	
	private long id;
	private String userName;
	private String senha;
	private String nome;
	private String cpf;
	private String cidade;
	private String endereco;
	private String cep;

	/**
	 * Construtor da classe
	 * @param id - long que representa o id único do usuário;
	 * @param userName - String que representa o nome de login do usuário;
	 * @param senha - String que representa a senha do usuário;
	 * @param nome - String que representa o nome do usuário;
	 * @param idade - int que representa a idade do usuário;
	 * @param cpf - String que representa o Cadastro de Pessoa Física;
	 * @param cidade - String que representa a cidade do usuário;
	 * @param endereco - String que representa o endereço do usuário;
	 * @param cep - String que representa o CEP do usuário;
	 */
	public Usuario(long id, String userName, String senha, String nome, int idade, String cpf, String cidade, String endereco, String cep) {
		this.id = id;
		this.userName= userName;
		this.senha = senha;
		this.nome = nome;
		this.cpf = cpf;
		this.cidade = cidade;
		this.endereco = endereco;
		this.cep = cep;
	}
	
	/**
	 * Construtor vazio
	 */
	public Usuario() {
		;
	}

	/**
	 * Getter para o atributo de id;
	 * @return - long com o id do usuário;
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Setter para o atributo de id;
	 * @param id - long com o id do usuário;
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Getter para o atributo de nome de login;
	 * @return - String com o nome de login do usuário;
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Setter para o atributo de nome de login;
	 * @param userName - String com o nome de login do usuário;
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Getter para o atributo de senha;
	 * @return - String com a senha do usuário;
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Setter para o atributo de senha;
	 * @param senha - String com a senha do usuário;
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Getter para o atributo de nome;
	 * @return - String com o nome do usuário;
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setter para o atributo de nome;
	 * @param nome - String com o nome do usuário;
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Getter para o atributo de CPF;
	 * @return - String com o CPF do usuário;
	 */
	public String getCpf() {
		return cpf;
	}
	
	/**
	 * Setter para o atributo de CPF;
	 * @param cpf - String com o CPF do usuário;
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	/**
	 * Getter para o atributo de cidade;
	 * @return - String com a cidade do usuário;
	 */
	public String getCidade() {
		return cidade;
	}
	
	/**
	 * Setter para o atributo de cidade;
	 * @param cidade - String com a cidade do usuário;
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	/**
	 * Getter para o atributo de endereÃ§o;
	 * @return - String com o endereÃ§o do usuário;
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Setter para o atributo de endereÃ§o;
	 * @param endereco - String com o endereço do usuário;
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	/**
	 * Getter para o atributo de CEP;
	 * @return - String com o CEP do usuário;
	 */
	public String getCep() {
		return cep;
	}
	
	/**
	 * Setter para o atributo de CEP;
	 * @param cep - String com o CEP do usuário;
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
}
