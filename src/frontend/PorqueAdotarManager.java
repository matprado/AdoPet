package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Classe manager para a tela de porque adotar;
 * @authors Mateus Prado, Mateus Tomieiro, Victor Reis, Matheus Rigato;
 *
 */
public class PorqueAdotarManager {
	
	/**
	 * Metodo para quando se quer ir para a tela de disponiveis;
	 * @param event - evento realizado;
	 */
	@FXML protected void abaDisponiveis(ActionEvent event) {
		Gui.telaDisponiveis();
	}
	
	/**
	 * Metodo para quando se quer ir para a tela de chat;
	 * @param event - evento realizado;
	 */
	@FXML protected void abaChat(ActionEvent event) {
		Gui.telaChat();
	}
}
