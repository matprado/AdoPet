package frontend;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.InputMismatchException;
import java.util.Vector;

import backend.Pet;
import backend.Usuario;
import bd.BDConexaoClass;
import backend.Pair;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Essa classe representa a GUI do programa;
 * @authors Mateus Prado, Mateus Tomieiro, Victor Reis, Matheus Rigato;
 *
 */
public class Gui extends Application {
	static Parent root;
	static Stage Stg;
	public static Usuario User;
	static boolean finalizaCadastro;
	static Usuario contatos[];
	static Button botaoContato[];
	static Usuario contato;
	
	//Uso na DisponiveisManager
	public static Pet pet[];
	public static int index;
	static int numeropaginas;
	static int paginaatual;
	static Image fotopet;
	public static Button[] botaoPets;
	public static Vector<Pet> vpets;
	public static Pet selecionadoChat;
	public static Pet petCorrente;
	
	/**
	 * Metodo para acessar um componente no fmxl
	 * @param str - nome(id) do componente
	 * @return - O objeto relacionado ao componente
	 */
	public static Object getComp(String str) {
		return (Object)Gui.root.lookup("#" + str);
	}
	
	/**
	 * Metodo para cadastrar um usuario;
	 * @return - usuário criado;
	 */
	public static Usuario setCadastroUser() {
		Gui.User.setNome(((TextField)Gui.getComp("name")).getText());
		Gui.User.setUserName(((TextField)Gui.getComp("user")).getText());
		Gui.User.setSenha(((PasswordField)Gui.getComp("password")).getText());
		Gui.User.setCpf(((TextField)Gui.getComp("cpf")).getText());
		Gui.User.setCidade(((TextField)Gui.getComp("cidade")).getText());
		Gui.User.setCep(((TextField)Gui.getComp("cep")).getText());
		Gui.User.setEndereco(((TextField)Gui.getComp("endereco")).getText());
		return User;
	}
	
	/**
	 * Metodo para imprimir a tela inicial do programa;
	 * @throws IOException
	 */
	public static void telaInicial() throws IOException {
		//carrega do fxml correspondente
		FXMLLoader loader = new FXMLLoader(new File("src/frontend/inicial.fxml").toURI().toURL());
		Gui.root = loader.load();
		//cria a cena e seta a mesma
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet");
        Gui.Stg.show();
	}
	
	/**
	 * Metodo de tentativa de login do usuario
	 */
	public static void login() {
		Gui.User.setUserName(((TextField)getComp("user")).getText());
		Gui.User.setSenha(((PasswordField)getComp("password")).getText());
		//confere se bate o usuário com a senha no BD
		if(BDConexaoClass.loginUser(Gui.User)){
			Gui.telaDisponiveis();
		}
	} 
	
	/**
	 * Metodo para imprimir uma tela de cadastro;
	 * @throws IOException
	 */
	public static void telaCadastro() throws IOException {
		//carrega o fxml correspondente
		FXMLLoader loader = new FXMLLoader(new File("src/frontend/cadastro.fxml").toURI().toURL());
		Gui.root = loader.load();
		//carrega nova cena
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet - Cadastro");
        Gui.Stg.show();
	}
	
	/**
	 * Algortimo validador de CPF;
	 * @param CPF - String com o CPF a ser verificado
	 * @return - boolean
	 */
	public static boolean validarCPF(String CPF) {
        // confere se o cpf é formado por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);
          
        char dig10, dig11;
        int soma, i, res, num, peso;
          
        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            soma = 0;
            peso = 10;
            for (i=0; i<9; i++) {                       
            	num = (int)(CPF.charAt(i) - 48); 
            	soma = soma + (num * peso);
            	peso = peso - 1;
            }
          
            res = 11 - (soma % 11);
            if ((res == 10) || (res == 11))
                dig10 = '0';
            else dig10 = (char)(res + 48); // converte no respectivo caractere numerico
          
            // Calculo do 2o. Digito Verificador
            soma = 0;
            peso = 11;
            for(i=0; i<10; i++) {
	            num = (int)(CPF.charAt(i) - 48);
	            soma = soma + (num * peso);
	            peso = peso - 1;
	        }
          
            res = 11 - (soma % 11);
            if ((res == 10) || (res == 11))
                 dig11 = '0';
            else dig11 = (char)(res + 48);
          
            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                 return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
	}
	
	/**
	 * Metodo para finalizar o cadastro de um usuario;
	 */
	public static void finalizaCadastro() {
		//verifica se os valores de nome, senha, cpf e aceitar termos estao validos;
		boolean nomeValido = ((TextField) getComp("user")).getLength() != 0;
		boolean senhaValida = ((TextField) getComp("password")).getLength() != 0;
		boolean cpfValido = validarCPF(((TextField)getComp("cpf")).getText());
		boolean validaTermos = ((CheckBox)getComp("aceita")).isSelected();
		//Caso estejam validos, insere no BD e entra no programa em si
		if(nomeValido && cpfValido && senhaValida && validaTermos){
			BDConexaoClass.cadastroUser(setCadastroUser());	
			Gui.telaDisponiveis();
		}
	}

	/**
	 * Metodo para imprimir a tela de pets disponiveis para adicao que tambem e 
	 * a tela inicial do programa apos o login/cadastro do usuario;	
	 */
	public static void telaDisponiveis(){
		//Define a paginacao
		if(BDConexaoClass.getSizePets() < 4) {
			Gui.numeropaginas = 1;
		}
		Gui.numeropaginas = BDConexaoClass.getSizePets() / 4;
		
		//carrega o fxml correspondente
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(new File("src/frontend/disponiveis.fxml").toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Erro na URL do FXML");
		}
		try {
			Gui.root = loader.load();
		} catch (IOException e) {
			System.out.println("Erro no carregamento do FXML");
		}
		
		((Text)Gui.getComp("npag")).setText("Pagina " + (Gui.paginaatual+1));
		
		//PEGA OBJETO PET DO BD E SETAR NO GUI.PET[].
		for(int i=0; i<4; i++) {
			try {
				Gui.pet[i] = BDConexaoClass.retornaPet(((Gui.paginaatual)*4)+(i+1)-1);
			} catch (NumberFormatException e) {
				System.out.println("Erro no retorno do Pet do BD");
			}
		}
		
		 //SETANDO NOMES E IMAGENS DOS PETS AQUI
		for(int i=0; i<4; i++) {
			if(Gui.pet[i] == null){
				((Button)Gui.getComp("nome_pet" + (i+1))).setVisible(false);
			}
			else {
				((Button)Gui.getComp("nome_pet" + (i+1))).setVisible(true);
				((ImageView)Gui.getComp("image" +(i+1))).setImage(pet[i].getIcone());
				((Button)Gui.getComp("nome_pet" + (i+1))).setText(pet[i].getNome());
			}
		}
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet");
        Gui.Stg.show();
	}
	
	/**
	 * Metodo para imprimir a tela com as características de um pet;
	 */
	public static void telaInfoPet() {
		//carrega o fxml correspondente;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(new File("src/frontend/infopet.fxml").toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Erro na URL do FXML");
		}
		try {
			Gui.root = loader.load();
		} catch (IOException e) {
			System.out.println("Erro no carregamento do FXML");
		}
		
		//Setando infos do pet
		((ImageView)(Gui.getComp("icone"))).setImage(Gui.pet[Gui.index].getIcone());
		((Text)(Gui.getComp("nome"))).setText((Gui.pet[Gui.index].getNome()));
		((TextField)(Gui.getComp("especie"))).setText((Gui.pet[Gui.index].getEspecie()));
		((TextField)(Gui.getComp("sexo"))).setText((Gui.pet[Gui.index].getSexo()));
		((TextField)(Gui.getComp("dono"))).setText((Gui.pet[Gui.index].getAnunciante().getNome()));
		((TextField)(Gui.getComp("cidade"))).setText((Gui.pet[Gui.index].getAnunciante().getCidade()));
		((TextField)(Gui.getComp("endereco"))).setText((Gui.pet[Gui.index].getAnunciante().getEndereco()));
		((TextArea)(Gui.getComp("descricao"))).setText((Gui.pet[Gui.index].getDetalhes()));
	
		if(BDConexaoClass.getIdAnun(Gui.User.getUserName()) == Gui.pet[Gui.index].getAnuncianteID()) {
			((Button)(Gui.getComp("mensagem"))).setVisible(false);
		}else {
			((Button)(Gui.getComp("mensagem"))).setVisible(true);
		}
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet - Info");
        Gui.Stg.show();
	}
	
	/**
	 * Metodo para imprimir a tela de anuncio de um pet;
	 */
	public static void telaAnunciar() {
		//carrega o fxml correspondente
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(new File("src/frontend/anunciar.fxml").toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Erro na URL do FXML");
		}
		try {
			Gui.root = loader.load();
		} catch (IOException e) {
			System.out.println("Erro no carregamento do FXML");
		}
		//carrega a cena
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet - Info");
        Gui.Stg.show();
	}
	
	/**
	 * Metodo para a escolha de uma imagem como foto de um pet;
	 */
	public static void escolheFoto() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Selecione a foto do pet!");
		fc.getExtensionFilters().addAll(new ExtensionFilter("Image(.jpg .jpeg)","*.jpg","*.jpeg"));
		File foto = fc.showOpenDialog(Gui.Stg);
		if (foto == null) {
        	Gui.telaDisponiveis();
        	return;
        }
		Gui.fotopet= new Image(foto.toURI().toString());
        ((ImageView)Gui.getComp("imagem_anuncio")).setImage(Gui.fotopet);
	}
	
	/**
	 * Metodo para confirmar o anuncio de um pet;
	 * @param pet - O pet sendo anunciado;
	 */
	public static void confirmarAnuncio(Pet pet) {
		BDConexaoClass.cadastroPet(pet);
		Gui.paginaatual = 0;
		Gui.telaDisponiveis();
	}
	
	/**
	 * Metodo que imprime a tela de contatos do chat;
	 */
	public static void telaChat() {
		//carrega o fxml correspondente
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(new File("src/frontend/chat.fxml").toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Erro na URL do FXML");
		}
		try {
			Gui.root = loader.load();
		} catch (IOException e) {
			System.out.println("Erro no carregamento do FXML");
		}
		//carrega os contatos do usuario logado
		Gui.contatos = BDConexaoClass.listaContatos(Gui.User);
		
		//se ter algum contato
		if(Gui.contatos.length != 0) {
			//cria um botao para cada contato
			Gui.botaoContato = new Button[Gui.contatos.length];
			VBox painel = ((VBox)((ScrollPane)Gui.getComp("spane")).getContent().lookup("#vpane"));
			((Label)getComp("texto1")).setVisible(true);
			((Label)getComp("texto2")).setVisible(false);
			for(int i=0; i<contatos.length; i++) {
				Gui.botaoContato[i] = new Button();
				Gui.botaoContato[i].setOnAction(new EventHandler<ActionEvent>() {
				    @Override public void handle(ActionEvent event) {
				       ChatManager.apertou(event);
				    }
				});
				Gui.botaoContato[i].setId(i + "b");
				Gui.botaoContato[i].setText(Gui.contatos[i].getNome());
				Gui.botaoContato[i].setVisible(true);
				Gui.botaoContato[i].setMinSize(320, 30);
				Gui.botaoContato[i].setAlignment(Pos.CENTER);
				painel.getChildren().add(Gui.botaoContato[i]);
				((ScrollPane)Gui.getComp("spane")).setVisible(true);
			}
		}else {
			//caso contrario, mostra texto de que nao ha contatos;
			((Label)getComp("texto2")).setVisible(true);
			((Label)getComp("texto1")).setVisible(false);
		}
		//carrega a cena
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet");
        Gui.Stg.show();
	}
	
	/**
	 * Metodo para imprimir a tela de chat entre o usuario e o contato escolhido;
	 */
	public static void iniciarChat(){
		//carrega o fxml correspondente
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(new File("src/frontend/conversa.fxml").toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Erro na URL do FXML");
		}
		try {
			Gui.root = loader.load();
		} catch (IOException e) {
			System.out.println("Erro no carregamento do FXML");
		}
		
		//se ainda nao existe um chat entre o usuario e seu contato, entao cria-se um;
		if(!BDConexaoClass.existeChat(Gui.User, Gui.contato)){
			BDConexaoClass.comecarChat(Gui.User, Gui.contato);
		}else{
			//caso contrario, mostra mensagens antigas;
			Gui.mostrarMensagensAntigas();
		}
		//Se o usuario ja aceitou a adotagem entao muda o texto do botao
		if(BDConexaoClass.UsuarioAceitou(Gui.User, Gui.contato)) {
			((Button)Gui.getComp("finalizar")).setText("Esperando");
		}
		//mostra com quem o usuario esta conversando
		((Label)Gui.getComp("titulo")).setText(((Label)getComp("titulo")).getText() + Gui.contato.getNome());
		//carrega a cena
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet");
        Gui.Stg.show();
	}
	
	public static void mostraPetsChat() {
		
		Gui.vpets = BDConexaoClass.getPetsFromChat(Gui.User, Gui.contato);
		
		Gui.botaoPets = new Button[vpets.size()];
		VBox painel = ((VBox)((ScrollPane)Gui.getComp("spane_pet")).getContent().lookup("#vpane_pet"));
		for(int i=0; i<vpets.size(); i++) {
			Gui.botaoPets[i] = new Button();
			Gui.botaoPets[i].setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent event) {
					ChatManager.apertouPet(event);
				}
			});
			Gui.botaoPets[i].setId(i + "bp");
			Gui.botaoPets[i].setText(Gui.vpets.get(i).getNome());
			Gui.botaoPets[i].setVisible(true);
			Gui.botaoPets[i].setMinSize(320, 30);
			Gui.botaoPets[i].setAlignment(Pos.CENTER);
			painel.getChildren().add(Gui.botaoPets[i]);
			((ScrollPane)Gui.getComp("spane_pet")).setVisible(true);
			((ScrollPane)Gui.getComp("spane")).setVisible(false);
		}
	}
	
	/**
	 * Metodo para mostrar mesagens antigas entre o usuario e seu contato escolhido;
	 */
	public static void mostrarMensagensAntigas() {
		//cria um vector de elementos do tipo pair que sao pares de inteiros com strings;
		Vector<Pair<Integer, String>> mensagens = null;
		//recupera os pares de mensagens antigas;
		mensagens = BDConexaoClass.getMensagensAntigas(Gui.User, Gui.contato);
		VBox box = (VBox)((ScrollPane)Gui.getComp("pbox")).getContent().lookup("#box");
		//alinha todas as mensagens de acordo com quem enviou
		for(int i=0; i<mensagens.size(); i++) {
			//texto da mensagem fica numa label
			Label texto = new Label();
			//os valores inteiros dos pairs representam quem mandou a mensagem
			if(mensagens.get(i).getId() == BDConexaoClass.getIdAnun((Gui.User.getUserName()))) {
				texto.setText("[" + Gui.contato.getUserName() + "]: " + mensagens.get(i).getMensagem());
				texto.setMinWidth(470);
				texto.setMinHeight(30);
				texto.setAlignment(Pos.CENTER_LEFT);
			}else{
				texto.setText("[voce]: " + mensagens.get(i).getMensagem());
				texto.setMinWidth(470);
				texto.setMinHeight(30);
				texto.setAlignment(Pos.CENTER_RIGHT);
			}
			//coloca a label na caixa de chat;
			box.getChildren().add(texto);
		}
	}
	
	/**
	 * Metodo para mostrar uma nova mensagem no chat;
	 * @param mensagem - String com a nova mensagem;
	 */
	public static void mostrarNovaMensagem(String mensagem) {
		VBox box = (VBox)((ScrollPane)Gui.getComp("pbox")).getContent().lookup("#box");
		//mensagem fica numa label
		Label nova = new Label();
		nova.setText("[voce]: " + mensagem);
		nova.setMinWidth(470);
		nova.setMinHeight(30);
		nova.setAlignment(Pos.TOP_RIGHT);
		//coloca a label na caixa de chat;
		box.getChildren().add(nova);
	}
	
	/**
	 * Metodo para finalizar uma adocao;
	 */
	public static void finalizaAdocao() {
		/*BDConexaoClass.excluirChat(Gui.User, Gui.contato);
		BDConexaoClass.adotaPet(Gui.User, Gui.contato);*/
		Gui.telaDisponiveis();
	}
	
	/**
	 * Metodo que imprime a tela de porque adotar;
	 */
	public static void telaPorqueAdotar() {
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(new File("src/frontend/porqueAdotar.fxml").toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Erro na URL do FXML");
		}
		try {
			Gui.root = loader.load();
		} catch (IOException e) {
			System.out.println("Erro no carregamento do FXML");
		}
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet");
        Gui.Stg.show();
	}

	/**
	 * Metodo para iniciar um stage
	 */
	@Override
    public void start (Stage stage){
        Gui.Stg = stage;
        Gui.Stg.setResizable(false);
        try {
			Gui.telaInicial();
		} catch (IOException e) {
			System.out.println("Erro no carregamento do FXML");
		}
    }
	
	/**
	 * Metodo para avancar uma pagina nos anuncios de pets
	 */
	public static void avancaPag() {
		if(!(Gui.paginaatual == (BDConexaoClass.getSizePets()/4))) {
			Gui.paginaatual++;
			Gui.telaDisponiveis();
		}
		
	}

	/**
	 * Metodo para voltar uma pagina nos anuncios de pets
	 */
	public static void voltaPag() {
		if(!(Gui.paginaatual == 0)) {
			Gui.paginaatual--;
			Gui.telaDisponiveis();
		}
		
	}
	
	/**
	 * Metodo par dar um start na Gui;
	 * @param args
	 */
	public static void run(String[] args) {
    	Gui.User = new Usuario();
    	Gui.pet = new Pet[4];
    	Gui.index = 0;
    	Gui.numeropaginas = 1;
    	if(BDConexaoClass.getSizePets() < 4) {
			Gui.numeropaginas = 1;
		}
		Gui.numeropaginas = (BDConexaoClass.getSizePets() / 4);
    	Gui.launch(args); //Requisitando inicializacao da Gui
    }
	
	
    
}