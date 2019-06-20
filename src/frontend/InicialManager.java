package frontend;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Classe manager para a tela inicial;
 * @authors Mateus Prado, Mateus Tomieiro, Victor Reis, Matheus Rigato;
 *	
 */
public class InicialManager {
	
	/**
	 * Metodo para quando se clica no botao de login;
	 * @param event - evento realizado;
	 */
	@FXML protected void clickLogin(ActionEvent event) {
		Gui.login();
	}
	
	/**
	 * Metodo para quando se clica no botao de cadastro;
	 * @param event - evento realizado;
	 */
	@FXML protected void clickCadastro(ActionEvent event) {
		try {
			Gui.telaCadastro();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
