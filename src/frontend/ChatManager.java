package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class ChatManager {
	
	@FXML protected void abaDisponiveis(ActionEvent event) {
		Gui.telaDisponiveis();
	}
	
	@FXML protected void abaPorqueAdotar(ActionEvent event) {
		Gui.telaPorqueAdotar();
	}
	
	@FXML public void apertou(ActionEvent event) {
        int id = Integer.parseInt(((Control)event.getSource()).getId());
		Gui.iniciarChat(Gui.contatos[id]);
    }
	
	@FXML public void enviouMensagem(ActionEvent event) {
		if(((TextArea)Gui.getComp("texto")).toString().length() != 0) {
			
		}
	}
	
}