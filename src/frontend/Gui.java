package frontend;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Gui extends Application {
	
	static Parent root;
	static Stage Stg;
	
	static String user, password;
	static boolean finalizaCadastro;
	
	public static Object getComp(String str) {
		return (Object)root.lookup("#" + str);
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
		Gui.user = ((TextField)getComp("user")).getText();
		Gui.password = ((PasswordField)getComp("password")).getText();
		if(/*existeBD*/true){
			Gui.telaDisponiveis();
		}
	} 
	
	public static void telaCadastro() throws IOException {
		FXMLLoader loader = new FXMLLoader(new File("src/frontend/cadastro.fxml").toURI().toURL());
		Gui.root = loader.load();
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet");
        Gui.Stg.show();
	}
	
	public static void finalizaCadastro() {
		boolean nomeValido = ((TextField) getComp("user")).getLength() != 0;
		boolean senhaValida = ((TextField) getComp("senha")).getLength() != 0;
		boolean cpfValido = validarCpf(((TextField)getComp("cpf")).getText());
		// if(nomeValido && senhaValida && ...) ...
		if(((CheckBox)getComp("aceita")).isSelected()){
			/*COLOCA NO BD*/
			Gui.telaDisponiveis();
		}
	}
	
	public static void telaDisponiveis(){
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(new File("src/frontend/disponiveis.fxml").toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			Gui.root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene S = new Scene(root);
		Gui.Stg.setScene(S);
        Gui.Stg.setTitle("AdoPet");
        Gui.Stg.show();
	}
	
	@Override
    public void start (@SuppressWarnings("exports") Stage stage){
        Gui.Stg = stage;
        try {
			Gui.telaInicial();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
    	Gui.launch(args); //Requisitando inicializacao da Gui
    }
    
}