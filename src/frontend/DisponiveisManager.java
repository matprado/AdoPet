package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DisponiveisManager {
	@FXML protected void abaPorqueAdotar(ActionEvent event) {
		Gui.telaPorqueAdotar();
	}
	
	@FXML protected void abaChat(ActionEvent event) {
		Gui.telaChat();
	}
}
