package frontend;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InicialManager {
	
	@FXML protected void clickLogin(ActionEvent event) {
		Gui.login();
	}
	
	@FXML protected void clickCadastro(ActionEvent event) {
		try {
			Gui.telaCadastro();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
