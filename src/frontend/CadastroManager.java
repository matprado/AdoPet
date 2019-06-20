package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Classe para o manager da tela de cadastro de usuario;
 * @authors Mateus Prado, Mateus Tomieiro, Victor Reis, Matheus Rigato;
 *
 */
public class CadastroManager {
	
	/**
	 * Metodo para para quando finaliza o cadastro;
	 * @param event - evento realizado
	 */
	@FXML protected void clickFinalizar(ActionEvent event) {
		Gui.finalizaCadastro();
	}
}
