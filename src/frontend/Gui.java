package frontend;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import backend.Pet;
import backend.Usuario;
import bd.BDConexaoClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * 
 * @author OS FODOES
 * TODO Tela_disponiveis, Tela_pet, Tela_pq_adotar, 
 * Tela_chat_pessoas, Tela_chat_conversa, Tela_final_adocao, Tela_anuncio
 */
//PRado e ai

public class Gui extends Application {
	static Parent root;
	static Stage Stg;
	static Usuario User;
	static boolean finalizaCadastro;
	static Usuario contatos[];
	static Button botaoContato[];
	
	//Uso na DisponiveisManager
	static Pet pet[];
	static int index;
	static int numeropaginas;
	static int paginaatual;
	
	
	public static Object getComp(String str) {
		return (Object)Gui.root.lookup("#" + str);
	}
	
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
	
	public static void telaInicial() throws IOException {
		FXMLLoader loader = new FXMLLoader(new File("src/frontend/inicial.fxml").toURI().toURL());
		Gui.root = loader.load();
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet");
        Gui.Stg.show();
	}
	
	public static void login() {
		Gui.User.setUserName(((TextField)getComp("user")).getText());
		Gui.User.setSenha(((PasswordField)getComp("password")).getText());
		try {
			if(BDConexaoClass.loginUser(Gui.User)){
				Gui.telaDisponiveis();
			}
		} catch (SQLException e) {
			System.out.println("Erro na inicializacao do BD");
		}
	} 
	
	public static void telaCadastro() throws IOException {
		FXMLLoader loader = new FXMLLoader(new File("src/frontend/cadastro.fxml").toURI().toURL());
		Gui.root = loader.load();
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet - Cadastro");
        Gui.Stg.show();
	}
	
	private static boolean validarCpf(String CPF) {
		return true;
	}
	
	public static void finalizaCadastro() {
		boolean nomeValido = ((TextField) getComp("user")).getLength() != 0;
		boolean senhaValida = ((TextField) getComp("password")).getLength() != 0;
		boolean cpfValido = validarCpf(((TextField)getComp("cpf")).getText());
		boolean validaTermos = ((CheckBox)getComp("aceita")).isSelected();
		if( nomeValido && cpfValido && senhaValida && validaTermos){
			try {
				BDConexaoClass.cadastroUser(setCadastroUser());
			} catch (SQLException e) {
				System.out.println("Erro na inicializacao BD!");
			}
			Gui.telaDisponiveis();
		}
	}

	public static void telaDisponiveis(){
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(new File("src/frontend/disponiveis.fxml").toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Erro no carregamento do FXML");
		}
		try {
			Gui.root = loader.load();
		} catch (IOException e) {
			System.out.println("Erro no carregamento do FXML");
		}
		
		//PEGA OBJETO PET DO BD E SETAR NO GUI.PET[].
		for(int i=0; i<9; i++) {
			try {
				Gui.pet[i] = BDConexaoClass.retornaPet(((Gui.paginaatual-1)*9)+(i+1));
			} catch (NumberFormatException | SQLException e) {
				System.out.println("Erro no retorno do Pet do BD");
			}
		}
		
		/**
		 * SETANDO NOMES E IMAGENS DOS PETS AQUI
		 */
		for(int i=0; i<9; i++) {
			((ImageView)Gui.getComp("image" +(i+1))).setImage(pet[i].getIcone());
			((Text)Gui.getComp("nome_pet" + (i+1))).setText(pet[i].getNome());
		}
		
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet");
        Gui.Stg.show();
	}
	
	
	public static void telaInfoPet() {
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(new File("src/frontend/infopet.fxml").toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Erro no carregamento do FXML");
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
		((TextField)(Gui.getComp("descricao"))).setText((Gui.pet[Gui.index].getDetalhes()));
	
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet - Info");
        Gui.Stg.show();
	}
	
	public static void telaAnunciar() {
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(new File("src/frontend/anunciar.fxml").toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Erro no carregamento do FXML");
		}
		try {
			Gui.root = loader.load();
		} catch (IOException e) {
			System.out.println("Erro no carregamento do FXML");
		}
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet - Info");
        Gui.Stg.show();
	}
	
	
	public static void telaChat() {
		FXMLLoader loader = null;
		boolean temContato = true;
		try {
			loader = new FXMLLoader(new File("src/frontend/chat.fxml").toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Erro no carregamento do FXML");
		}
		try {
			Gui.root = loader.load();
		} catch (IOException e) {
			System.out.println("Erro no carregamento do FXML");
		}
		try {
			Gui.contatos = BDConexaoClass.listaContatos(Gui.User);
		} catch (SQLException e) {
			temContato = false;
			System.out.println("Erro na inicializacao BD!");
		}
		
		if(temContato) {
			botaoContato = new Button[contatos.length];
			AnchorPane painel = (AnchorPane)getComp("pane");
			painel.setVisible(true);
			((Label)getComp("texto1")).setVisible(true);
			for(int i=0; i<contatos.length; i++) {
				Gui.botaoContato[i].setId(i + "");
				Gui.botaoContato[i].setText(Gui.contatos[i].getNome());
				painel.getChildren().add(botaoContato[i]);
			}
		}else {
			((Label)getComp("texto2")).setVisible(true);
		}
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet");
        Gui.Stg.show();
	}
	
	public static void iniciarChat(Usuario contato) {
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(new File("src/frontend/conversa.fxml").toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Erro no carregamento do FXML");
		}
		try {
			Gui.root = loader.load();
		} catch (IOException e) {
			System.out.println("Erro no carregamento do FXML");
		}
		
		((Label)getComp("textoInicial")).setText(((Label)getComp("textoInicial")).getText() + contato.getNome());
		((Label)getComp("texto")).setText(((Label)getComp("texto")).getText() + contato.getNome());
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet");
        Gui.Stg.show();
	}
	
	public static void telaPorqueAdotar() {
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(new File("src/frontend/porqueAdotar.fxml").toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Erro no carregamento do FXML");
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
    
    public static void main(String[] args) {
    	Gui.User = new Usuario();
    	Gui.pet = new Pet[9];
    	Gui.index = 0;
    	try {
			Gui.numeropaginas = BDConexaoClass.getSizePets() / 9;
		} catch (SQLException e) {
			System.out.println("Erro ao receber numero de pets do BD");
		}
    	Gui.numeropaginas = 1;
    	Gui.launch(args); //Requisitando inicializacao da Gui
    }

	public static void avancaPag() {
		Gui.paginaatual++;
		Gui.telaDisponiveis();
	}

	public static void voltaPag() {
		Gui.paginaatual--;
		Gui.telaDisponiveis();
	}
    
}