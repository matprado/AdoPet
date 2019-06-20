package frontend;

import bd.BDConexaoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;

public class ChatManager {
	
	@FXML protected void abaDisponiveis(ActionEvent event) {
		Gui.telaDisponiveis();
	}
	
	@FXML protected void abaPorqueAdotar(ActionEvent event) {
		Gui.telaPorqueAdotar();
	}
	
	public static void apertou(ActionEvent event) {
        String aux = ((Control)event.getSource()).getId();
		aux = aux.replaceFirst("b", "");
		int id = Integer.parseInt(aux);
        Gui.contato = Gui.contatos[id];
		Gui.iniciarChat();
    }
	
	@FXML public void enviouMensagem(ActionEvent event) {
		if(((TextArea)Gui.getComp("texto")).getText().length() != 0) {
			String mensagem =((TextArea)Gui.getComp("texto")).getText();
			Gui.mostrarNovaMensagem(mensagem);
			((TextArea)Gui.getComp("texto")).setText("");
			BDConexaoClass.criarMensagem(Gui.User, Gui.contato, mensagem);
		}
	}
	
	@FXML public void usuarioAceitou(ActionEvent event) {
		if(((Button)Gui.getComp(((Control)event.getSource()).getId())).getText() == "Finalizar") {
			((Button)Gui.getComp(((Control)event.getSource()).getId())).setText("Esperando");
			BDConexaoClass.finaliza(Gui.User, Gui.contato);	
		}
		if(BDConexaoClass.UsuarioAceitou(Gui.contato, Gui.User)) {
			Gui.finalizaAdocao();
		}
		
	}
	
	@FXML public void voltar(ActionEvent event) {
		Gui.telaChat();
	}
	
	@FXML public void cancelar(ActionEvent event) {
		//BDConexaoClass.excluirChat(Gui.User, Gui.contato);
		Gui.telaDisponiveis();
	}
	
}