package frontend;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.HashMap;

import backend.Pet;
import backend.Usuario;
import bd.BDConexaoClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Gui extends Application {
	static Parent root;
	static Stage Stg;
	static Usuario User;
	static boolean finalizaCadastro;
	static Usuario contatos[];
	static Button botaoContato[];
	static Usuario contato;
	
	//Uso na DisponiveisManager
	static Pet pet[];
	static int index;
	static int numeropaginas;
	static int paginaatual;
	static File fotopet;
	
	
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
			System.out.println("Não encontrou o usuario no BD");
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
		if(nomeValido && cpfValido && senhaValida && validaTermos){
			try {
				BDConexaoClass.cadastroUser(setCadastroUser());
			} catch (SQLException e) {
				System.out.println("Erro no cadastro do usuario no BD!");
			}
			Gui.telaDisponiveis();
		}
	}

	public static void telaDisponiveis(){
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
				Gui.pet[i] = BDConexaoClass.retornaPet(((Gui.paginaatual-1)*4)+(i+1));
			} catch (NumberFormatException | SQLException | IOException e) {
				System.out.println("Erro no retorno do Pet do BD");
			}
		}
		
		 //SETANDO NOMES E IMAGENS DOS PETS AQUI
		for(int i=0; i<4; i++) {
			if(Gui.pet[i] == null){
				((Text)Gui.getComp("nome_pet" + (i+1))).setText("");
			}
			else {
				((ImageView)Gui.getComp("image" +(i+1))).setImage(pet[i].getIcone());
				((Text)Gui.getComp("nome_pet" + (i+1))).setText(pet[i].getNome());
			}
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
			System.out.println("Erro na URL do FXML");
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
	
	public static void escolheFoto() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Selecione a foto do pet!");
		fc.getExtensionFilters().addAll(new ExtensionFilter("Image(.jpg)","*.jpg"));
		Gui.fotopet= fc.showOpenDialog(Gui.Stg);
        if (Gui.fotopet != null) {
        	
        	Gui.telaDisponiveis();
        }
	}
	
	
	public static void telaChat() {
		FXMLLoader loader = null;
		boolean temContato = true;
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
		try {
			Gui.contatos = BDConexaoClass.listaContatos(Gui.User);
		} catch (SQLException e) {
			temContato = false;
			System.out.println("Erro ao trazer contatos do BD!");
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
	
	public static void iniciarChat(Usuario contato) throws SQLException {
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
		if(!BDConexaoClass.existeChat(Gui.User, contato)) {
			BDConexaoClass.comecarChat(Gui.User, contato);
			((Label)getComp("textoInicial")).setText(((Label)getComp("textoInicial")).getText() + contato.getNome());
		}else{
			((Label)getComp("textoInicial")).setDisable(true);
			mostrarMensagensAntigas();		
		}		
		((Label)getComp("texto")).setText(((Label)getComp("texto")).getText() + contato.getNome());	
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet");
        Gui.Stg.show();
	}
	
	public static void mostrarMensagensAntigas() {
		HashMap<Integer, String> mensagens = null;
		try {
			mensagens = BDConexaoClass.getMensagensAntigas(Gui.User, contato);
		} catch (SQLException e) {
			System.out.println("Erro ao receber mensagens do BD");
			return;
		}
		VBox box = (VBox)getComp("box");
		for(HashMap.Entry<Integer, String> msg : mensagens.entrySet()) {
			Label texto = new Label();
			texto.setText(msg.getValue());
			if(msg.getKey() == 0) {
				//mensagem do usu�rio...
				texto.setAlignment(Pos.CENTER_LEFT);
			}else texto.setAlignment(Pos.CENTER_RIGHT);
			box.getChildren().add(texto);
		}
	}
	
	public static void mostrarNovaMensagem(String mensagem) {
		VBox box = (VBox)getComp("box");
		Label nova = new Label();
		nova.setText(mensagem);
		nova.setAlignment(Pos.CENTER_LEFT);
		box.getChildren().add(nova);
	}
	
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
    	Gui.pet = new Pet[4];
    	Gui.index = 0;
    	Gui.numeropaginas = 1;
    	try {
			Gui.numeropaginas = BDConexaoClass.getSizePets() / 4;
			if(Gui.numeropaginas % 4 > 0) {
				Gui.numeropaginas++;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao receber numero de pets do BD");
		}
    	Gui.launch(args); //Requisitando inicializacao da Gui
    }

	public static void avancaPag() {
		Gui.paginaatual++;
		if(Gui.paginaatual >= Gui.numeropaginas) {
			Gui.paginaatual = Gui.numeropaginas-1;
		}
		Gui.telaDisponiveis();
	}

	public static void voltaPag() {
		Gui.paginaatual--;
		if(Gui.paginaatual <= 0) {
			Gui.paginaatual = 0;
		}
		Gui.telaDisponiveis();
	}
    
}