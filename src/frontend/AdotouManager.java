package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Classe de manager para a tela de adocao feita;
 * @authors Mateus Prado, Mateus Tomieiro, Victor Reis, Matheus Rigato;
 *
 */
public class AdotouManager {

	/**
	 * Metodo para quando o usuario apeta o botao para voltar;
	 * @param event - evento realizado;
	 */
	@FXML protected void voltou(ActionEvent event) {
		Gui.telaDisponiveis();
	}
	
}
