package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PorqueAdotarManager {
	
	@FXML protected void abaDisponiveis(ActionEvent event) {
		Gui.telaDisponiveis();
	}
	
	@FXML protected void abaChat(ActionEvent event) {
		Gui.telaChat();
	}
}
