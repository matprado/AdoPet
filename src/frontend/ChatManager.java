package frontend;

import bd.BDConexaoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;

public class ChatManager {
	
	@FXML protected void abaDisponiveis(ActionEvent event) {
		Gui.telaDisponiveis();
	}
	
	@FXML protected void abaPorqueAdotar(ActionEvent event) {
		Gui.telaPorqueAdotar();
	}
	
	@FXML public void apertou(ActionEvent event) {
        int id = Integer.parseInt(((Control)event.getSource()).getId());
		Gui.contato = Gui.contatos[id];
        Gui.iniciarChat();
    }
	
	@FXML public void enviouMensagem(ActionEvent event) {
		if(((TextArea)Gui.getComp("texto")).toString().length() != 0) {
			//criarMensagem(Gui.User, Gui.contato, ((TextArea)Gui.getComp("texto")).toString());
			mostrarNovaMensagem(((TextArea)Gui.getComp("texto")).toString());
			BDConexaoClass.criarMensagem(Gui.User, Gui.contato, ((TextArea)Gui.getComp("texto")).toString());
		}
	}
	
}