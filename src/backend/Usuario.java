package backend;

/**
 * Essa classe representa um usuario do programa;
 * @authors Mateus Prado, Mateus Tomieiro, Victor Reis, Matheus Rigato;
 *
 */
public class Usuario {
	
	//Atributos da classe Usuário:
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
	 * @param id - long que representa o id unico do usuario;
	 * @param userName - String que representa o nome de login do usuario;
	 * @param senha - String que representa a senha do usuario;
	 * @param nome - String que representa o nome do usuario;
	 * @param idade - int que representa a idade do usuario;
	 * @param cpf - String que representa o Cadastro de Pessoa Fisica;
	 * @param cidade - String que representa a cidade do usuario;
	 * @param endereco - String que representa o endereco do usuario;
	 * @param cep - String que representa o CEP do usuario;
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
	 * @return - long com o id do usuario;
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Setter para o atributo de id;
	 * @param id - long com o id do usuario;
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Getter para o atributo de nome de login;
	 * @return - String com o nome de login do usuario;
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Setter para o atributo de nome de login;
	 * @param userName - String com o nome de login do usuario;
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Getter para o atributo de senha;
	 * @return - String com a senha do usuario;
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Setter para o atributo de senha;
	 * @param senha - String com a senha do usuario;
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Getter para o atributo de nome;
	 * @return - String com o nome do usuario;
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setter para o atributo de nome;
	 * @param nome - String com o nome do usuario;
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Getter para o atributo de CPF;
	 * @return - String com o CPF do usuario;
	 */
	public String getCpf() {
		return cpf;
	}
	
	/**
	 * Setter para o atributo de CPF;
	 * @param cpf - String com o CPF do usuario;
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	/**
	 * Getter para o atributo de cidade;
	 * @return - String com a cidade do usuario;
	 */
	public String getCidade() {
		return cidade;
	}
	
	/**
	 * Setter para o atributo de cidade;
	 * @param cidade - String com a cidade do usuario;
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	/**
	 * Getter para o atributo de endereço;
	 * @return - String com o endereço do usuario;
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Setter para o atributo de endereço;
	 * @param endereco - String com o endereco do usuario;
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	/**
	 * Getter para o atributo de CEP;
	 * @return - String com o CEP do usuario;
	 */
	public String getCep() {
		return cep;
	}
	
	/**
	 * Setter para o atributo de CEP;
	 * @param cep - String com o CEP do usuario;
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
}
