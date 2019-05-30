package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CadastroManager {
	@FXML protected void clickFinalizar(ActionEvent event) {
		Gui.finalizaCadastro();
	}
}
