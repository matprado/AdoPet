package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AnunciarManager {

	@FXML protected void voltarDisponiveis(ActionEvent event) {
		Gui.telaDisponiveis();
	}
	
	@FXML protected void selecionarImagem(ActionEvent event) {
		Gui.escolheFoto();
	}
}
